<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL의 fmt태그를 사용하기 위해 작성 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>책 상세 페이지</title>
</head>
<body>
<div class="container">
   <div class="row">
      <div class="col-md-12">  
      
      <h1 class="mt-3 mb-3">책 상세페이지</h1> 
      <table class="table">
      	<tr>
      		<th>제목</th><td>${data.title}</td>
      	</tr>
      	<tr>
      		<th>카테고리</th><td>${data.category}</td>
      	</tr>
      	<tr>
      		<th>가격</th>
      		<td>
      			<fmt:formatNumber type="number" maxFractionDigits="3" value="${data.price}"/>
      			<!-- maxFractionDigits="3"->숫자 출력시 3자리씩 끊어서 출력 -->
      		</td>
      	</tr>
      	<tr>
      		<th>입력일</th>
      		<td>
      			<fmt:formatDate value="${data.insert_date}" pattern="yyyy.MM.dd HH:mm:ss"/>
      		</td>
      	</tr>
      </table>
      
      <div class="d-flex justify-content-end">
      	<a href="/update?bookId=${bookId}" class="btn btn-success">수정</a>
      	<form method="POST" action="/delete">
	      	<input type="hidden" name="bookId" value="${bookId}"/>
	      	<input type="submit" value="삭제" class="btn btn-danger">
	      	<a href="/list" class="btn btn-primary">목록으로</a>
      	</form>
      </div>
         
      </div>               
   </div>
</div>


</body>
</html>