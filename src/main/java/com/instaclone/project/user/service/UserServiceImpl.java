package com.instaclone.project.user.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.user.dao.UserDao;
import com.instaclone.project.user.dto.UserDto;
import com.instaclone.project.user.dto.UserLoginDto;
import com.instaclone.project.user.dto.UserPasswordDto;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;

	// custom token 생성기
	@Override
	public String generateToken(int n) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    String token = "";
	    for(int i = 0; i < n; i++) {
	        token += chars.charAt((int)Math.floor(Math.random() * chars.length()));
	    }
	    return token;
	}
	// user 가입정보를 저장한다.
	@Override
	public ResponseDto saveUser(UserDto dto) {
		// user 정보를 저장하기 전에 password 를 BCrypt 암호화 방식으로 암호화 시킨다.
		String plainText=dto.getPassword();
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String hash=encoder.encode(plainText);
		dto.setPassword(hash);
		// 사용가능한 아이디인지 확인
		String isEmptyId = dao.selectUserId(dto);
		// 이메일 중복체크 (아직안함)
		//응답객체
		ResponseDto responseDto = new ResponseDto();
		if(isEmptyId == null) {
			// dto 객체를 이용해서 저장한다.
			dao.insertUserData(dto);
			responseDto.setResult("success");
			responseDto.setMessage("가입에 성공하셨습니다.");
		}else {
			responseDto.setResult("fail");
			responseDto.setMessage("가입에 실패하셨습니다. / 에러:아이디 중복");
		}
		return responseDto;
	}
	
	// 사용 가능한 id 인지 확인한다.
	@Override
	public ResponseDto isCanUseId(UserDto dto) {
		// 사용가능한 아이디인지 확인
		String isEmptyId = dao.selectUserId(dto);
		// 응답객체
		ResponseDto responseDto = new ResponseDto();
		if(isEmptyId == null) {
			responseDto.setResult("success");
			responseDto.setMessage("사용가능한 id 입니다.");
		}else {
			responseDto.setResult("fail");
			responseDto.setMessage("이미 사용중인 id 입니다. / 에러:아이디 중복");
		}
		return responseDto;
	}
	
	// 유저와 패스워드를 확인해서 loginData table 에 값에 저장한다.
	// return / true 비밀번호 맞음 / false 비밀번호 틀림
	@Override
	public ResponseDto isCorrectUser(UserDto dto) {
		// 아이디 값으로 DB에서 비밀번호를 불러온다.
		String hashPassword=dao.selectPassword(dto);
		// 불러온 값과 현재 입력된 암호를 비교해서 boolean 값 return
		boolean isCorrect=BCrypt.checkpw(dto.getPassword(), hashPassword);
		// 응답객체
		ResponseDto responseDto = new ResponseDto();
		
		if(isCorrect) {
			// token 값을 생성해서 넣는다.
			String token = this.generateToken(10);
			responseDto.setResult("success");
			responseDto.setMessage("로그인에 성공하셨습니다.");
			responseDto.setToken(token);
			// 유효 시간 생성 / 일단 5분
			int ableTime = (int)System.currentTimeMillis()+300000;
			// login_data table 에 저장
			UserLoginDto loginDto = new UserLoginDto();
			loginDto.setId(dto.getId());
			loginDto.setToken(token);
			loginDto.setAbleTime(ableTime);
			System.out.println("id : "+loginDto.getId());
			System.out.println("token : "+loginDto.getToken());
			System.out.println("ableTime : "+loginDto.getAbleTime());
			// login table 에 loginDto 객체 내용 저장
			dao.insertLoginData(loginDto);
			return responseDto;
		}else {
			responseDto.setResult("fail");
			responseDto.setMessage("로그인에 실패하셨습니다.");
			return responseDto;
		}
	}
	
	@Override
	public void sendMail() {
		/** 
		 * 자바 메일 발송 
		 * @throws MessagingException  
		 * @throws AddressException
		 * smtp 프로토콜 (Simple Mail Transfer Protocol) 
		**/
		// 네이버일 경우 smtp.naver.com 을 입력합니다.
		// google일 경우 smtp.gmail.com 을 입력합니다.
		String host = "smtp.naver.com";
		final String username=""; // 아이디 입력
		final String password=""; // 비밀번호 입력
		final String useremail="";
		int port=465; // 포트번호
		
		// 메일내용
		String recipient = ""; // 받는 사람의 메일 주소
		String subject = "메일 테스트"; // 메일 제목 입력
		
		String body = username+"님으로 부터 메일을 받았습니다."; // 메일 내용 입력
		
		Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성
		
		// SMTP 서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		// Session 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un=username;
			String pw=password;
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new javax.mail.PasswordAuthentication(un,pw);
			}
		});
		session.setDebug(true); // for debug
		
		Message mimeMessage = new MimeMessage(session); // MimeMessage 생성
		try {
			mimeMessage.setFrom(new InternetAddress(useremail)); // 발신자 세팅
			// 보내는 사람의 이메일 주소를 한번더 입력한다.
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			// 수신자 세팅 / setRecipients 메소드로 사용하면 여러명한테 배열로 발송가능
			// .TO 외에 .CC(참조) .BCC(숨은참조) 도 있음
			mimeMessage.setSubject(subject); // 제목 세팅
			mimeMessage.setText(body); // 내용 세팅
			Transport.send(mimeMessage); // javax.mail.Transport.send() 이용
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public ResponseDto sendMailForPassword(UserDto dto) {
		// 응답객체 생성
		ResponseDto responseDto = new ResponseDto();
		// 이메일 주소를 검색해서 존재하는 이메일인지 확인
		if(dao.selectEmail(dto)==null) {
			responseDto.setResult("fail");
			responseDto.setMessage("존재하지 않는 메일 주소입니다.");
			return responseDto;
		}
		// 이메일로 전송할 랜덤주소 생성
		int passNum = (int)(Math.random()*100)+1;
		// 만약 변경요청유효시간에 랜덤숫자가 있으면 다시 생성(이 기능은 아직 구현안함)
		// 비밀번호 변경 관련 내용 insert / 이메일, 변경요청시간, 랜덤주소
		int ableTime = (int)System.currentTimeMillis()+300000;
		System.out.println("현재 시간 : "+(System.currentTimeMillis()/1000));
		System.out.println("변경 유효시간 : "+(ableTime/1000));
		// 비밀번호 관련 내용 객체에 담아서 db 에 저장
		UserPasswordDto passwordDto = new UserPasswordDto();
		passwordDto.setEmail(dto.getEmail());
		passwordDto.setAbleTime(ableTime);
		passwordDto.setPassNum(passNum);
		dao.insertPasswordDto(passwordDto);
		
		// 네이버일 경우 smtp.naver.com 을 입력합니다.
		// google일 경우 smtp.gmail.com 을 입력합니다.
		String host = "smtp.naver.com";
		final String username=""; // 아이디 입력
		final String useremail=""; // email 입력
		final String password=""; // 비밀번호 입력
		int port=465; // 포트번호
		
		// 메일내용
		String recipient = dto.getEmail(); // 받는 사람의 메일 주소
		String subject = "비밀번호 변경 요청"; // 메일 제목 입력
		
		String body = "비밀번호를 변경하시려면 아래의 주소로 접속해주세요\r\n"
				+ "http://localhost:8888/project/"+passNum+"/changepassword.do"; // 메일 내용 입력
		System.out.println("다음과 같은 내용으로 email 발송");
		System.out.println(body);
		
		Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성
		
		// SMTP 서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		// Session 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un=username;
			String pw=password;
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new javax.mail.PasswordAuthentication(un,pw);
			}
		});
		session.setDebug(true); // for debug
		
		Message mimeMessage = new MimeMessage(session); // MimeMessage 생성
		try {
			mimeMessage.setFrom(new InternetAddress(useremail)); // 발신자 세팅
			// 보내는 사람의 이메일 주소를 한번더 입력한다.
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			// 수신자 세팅 / setRecipients 메소드로 사용하면 여러명한테 배열로 발송가능
			// .TO 외에 .CC(참조) .BCC(숨은참조) 도 있음
			mimeMessage.setSubject(subject); // 제목 세팅
			mimeMessage.setText(body); // 내용 세팅
			Transport.send(mimeMessage); // javax.mail.Transport.send() 이용
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		responseDto.setResult("success");
		responseDto.setMessage("메일이 전송되었습니다.");
		return responseDto;
	}
	// 비밀번호 변경 
	@Override
	public ModelAndView passwordChange(int passNum, String password, ModelAndView mView) {
		// 유효시간을 체크하기 위한 현재 시간
		int ableTime = (int)System.currentTimeMillis();
		// 패스워드 객체 생성
		UserPasswordDto passwordDto = new UserPasswordDto();
		passwordDto.setPassNum(passNum);
		passwordDto.setAbleTime(ableTime);
		// instaclone_change_password 에서 유효시간 확인
		String email=null;
		try {
			email = dao.selectAbleTime(passwordDto);
		}catch(Exception e) {
			System.out.println("정말 확률적으로 말도안되게 겹쳐서 다시 메일 전송 받아주세요");
		}
		// 
		if(email!=null) {
			UserDto dto = new UserDto();
			dto.setEmail(email);
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			String hash=encoder.encode(password);
			dto.setPassword(hash);
			dao.updatePassword(dto);
			mView.addObject("msg","업데이트에 성공하였습니다.");
		}else {
			mView.addObject("msg","제한시간이 초과되었습니다.");
		}
		return mView;
	}
	// 로그인 정보가 유효한지 확인
	@Override
	public boolean isLogined(UserLoginDto dto) {
		boolean islogined=false;
		// 현재 시간 정보를 넣어준다.
		int ableTime=(int)System.currentTimeMillis();
		dto.setAbleTime(ableTime);
		try {
			String ableId=dao.selectLoginData(dto);
			if(ableId!=null) {
				islogined=true;
			}
		}catch(Exception e) {
			System.out.println("같은 아이디와 토큰로 중복된 유효데이터가 있습니다.");
		}
		return islogined;
	}






	
}
