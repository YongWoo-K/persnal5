<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>책 수정</title>
</head>
<body>
<div class="container">
   <div class="row">
      <div class="col-md-12">
      	<h1 class="mt-3 mb-3">책 수정</h1>	  
      	<form method="POST">
      		<div class="input-group my-3">
      			<label class="input-group-text">제목 : </label>
      			<input type="text" name="title" class="form-control" value="${data.title}">
      		</div>
      		
      		<div class="input-group my-3">
      			<label class="input-group-text">카테고리 : </label>
      			<input type="text" name="category" class="form-control" value="${data.category}">
      		</div>
      		
      		<div class="input-group my-3">
      			<label class="input-group-text">가격 : </label>
      			<input type="text" name="price" class="form-control" value="${data.price}">
      		</div>
      		
      		<div class="d-flex justify-content-end mt-3 mb-3">
      			<input type="submit" class="btn btn-success" value="수정하기">
      		</div>
      	</form>         
      </div>               
   </div>
</div>


</body>
</html>