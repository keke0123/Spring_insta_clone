package com.instaclone.project.upload.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.upload.dao.UploadDao;
import com.instaclone.project.upload.dto.UploadDataDto;
import com.instaclone.project.upload.dto.UploadDto;

@Service
public class UploadServiceImpl implements UploadService{
	@Autowired
	private UploadDao dao;
	
	@Override
	public ResponseDto saveImgBase64(HttpServletRequest request, UploadDto dto) {
		// base64 포맷 형태로 oracle에 저장
		// 실제 파일로 저장하는건 아래 코드로
        String base64String = dto.getImgBase64();
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }    
		// 파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		// 콘솔창에 테스트 출력
		//System.out.println(realPath);	
		// 저장할 파일의 상세 경로
		String filePath = realPath + File.separator;

        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        //String path = "C:\\Users\\Ene\\Desktop\\test_image." + extension;
        
		// 디렉토리를 만들 파일 객체 생성
		File file = new File(filePath);
		if (!file.exists()) {// 디렉토리가 존재하지 않는다면
			file.mkdir();// 디렉토리를 만든다.
		}
		
		// 파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
		dto.setNum(dao.getSequence());
		String saveFileName = dto.getNum()+"."+extension; // 여기 숫자 1은 이따가 sequence 받아서 넣기
		String path = filePath+saveFileName;
		file = new File(path);
		
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(path);
        dto.setSavePath(path);
		dao.insertImgBase64(dto);
		
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResult("success");
		
		return responseDto;
	}

	@Override
	public ResponseDto saveData(UploadDataDto dto, HttpServletRequest request) {
		// board number 를 생성할 sequence 를 돌려서 생성한다.
		int board_num = dao.getBoardSequence();
		dto.setBoard_num(board_num);
		// board 생성 및 저장 / board number, writer, content
		dao.insertBoardData(dto);
		// savePath 만들어주는 service 생성
		// for 문을 돌면서 upload 폴더에 image 데이타 저장
		dto=this.saveImg(dto, request);
		// for 문을 돌면서 db 에 image 경로 저장 / board number, save path
		for(String savePath : dto.getSavePaths()) {
			UploadDto uploadDto = new UploadDto();
			uploadDto.setBoard_num(board_num);
			uploadDto.setSavePath(savePath);
			dao.insertImgData(uploadDto);
		}
		// for 문을 돌면서 db 에 tag 데이타 저장 / board number, tag
		for(String tag : dto.getTags()) {
			UploadDto uploadDto = new UploadDto();
			uploadDto.setBoard_num(board_num);
			uploadDto.setTag(tag);
			dao.insertTagData(uploadDto);
		}
		// 응답객체 생성해서 응답 설정후 리턴
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResult("success");
		responseDto.setMessage("데이타 저장에 성공하였습니다.");
		return responseDto;
	}
	// UploadDataDto 객체를 받아서 img 를 저장후
	// savePath 를 UploadDataDto 객체에 넣어서 리턴한다.
	@Override
	public UploadDataDto saveImg(UploadDataDto dto, HttpServletRequest request) {
		// dto 에 담겨있는 base64 값을 Array 객체에 저장
		ArrayList<String> uploadImgs = dto.getUploadImgs(); 
		// savePath 를 저장해서 dto 객체에 담을 Array 객체 생성
		ArrayList<String> savePaths = new ArrayList<>();
		// for 문을 돌면서 save 할 base64 값을 불러온다.
		for(String base64String : uploadImgs) {
			
			String[] strings = base64String.split(",");
	        String extension;
	        switch (strings[0]) {//check image's extension
	            case "data:image/jpeg;base64":
	                extension = "jpeg";
	                break;
	            case "data:image/png;base64":
	                extension = "png";
	                break;
	            default://should write cases for more images types
	                extension = "jpg";
	                break;
	        }    
			// 파일을 저장할 폴더의 절대 경로를 얻어온다.
			String realPath = request.getSession().getServletContext().getRealPath("/upload");
			// 콘솔창에 테스트 출력
			//System.out.println(realPath);	
			// 저장할 파일의 상세 경로
			String filePath = realPath + File.separator;

	        //convert base64 string to binary data
	        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
	        //String path = "C:\\Users\\Ene\\Desktop\\test_image." + extension;
	        
			// 디렉토리를 만들 파일 객체 생성
			File file = new File(filePath);
			if (!file.exists()) {// 디렉토리가 존재하지 않는다면
				file.mkdir();// 디렉토리를 만든다.
			}
			
			// 파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
			int saveName=dao.getSequence();
			String saveFileName = saveName+"."+extension; // 여기 숫자 1은 이따가 sequence 받아서 넣기
			String path = filePath+saveFileName;
			file = new File(path);
			
	        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
	            outputStream.write(data);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println(path);
	        // path 값을 하나하나 list 에 저장한다.
	        savePaths.add(saveFileName);
		}
		// 사진이 저장된 위치를 db 에 넣기위에 dto 객체에 저장한다.
		dto.setSavePaths(savePaths);
        		
		return dto;
	}

}
