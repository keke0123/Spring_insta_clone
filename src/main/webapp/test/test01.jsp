<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="container" style="margin-top:50px;">
	<ul>
		<li><a href="javascript:" id="emailTest">email 발송</a></li>
	</ul>
</div>
<script>
	$("#emailTest").on("click", function(){
		console.log("mailTest");
		
		$.ajax({
	      url: "/project/mailTest.do",
	      type: "GET",
	      data: {},
	      //contentType: false,
	      //cache: false,
	      //processData: false,
	      success: function(data)
	      {
	        console.log(data);
	        
	      }
	    });
	});
</script>
</body>
</html>