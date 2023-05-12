<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c태그와 fmt태그를 사용하기 위해 선언 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>책 목록</title>
</head>
<body>
<div class="container">
   <div class="row">
      <div class="col-md-12"> 
      
      <h1>책 목록</h1>  
 
	  <form>  
	  	<div class="input-group">
	  		<input type="text" placeholder="검색" class="form-control" name="keyword" value="${keyword}" />  
	  		<input type="submit" class="btn btn-success" value="검색" /> 
	  	</div>
	  </form>  

      <table class="table">
      	<thead>
      		<tr>
      			<th>제목</th>
      			<th>카테고리</th>
      			<th>가격</th>
      		</tr>
      	</thead>

      	<tbody>
      		<!-- JSTL에서 반복문을 사용하기위해 c태그의 forEach구문 사용 -->
			<!-- 변수는 목록의 한 행, item은 컨트롤러에서 전달받은 데이터 -->
      		<c:forEach var="row" items="${data}">
      			<tr>
      				<td>
      					<a href="/detail?bookId=${row.book_id}">${row.title}</a>
      				</td>
      				<td>${row.category}</td>
      				<td>
      					<!-- 숫자를 3자리씩 끊어서 출력하기위해 fmt태그 사용 -->
      					<fmt:formatNumber type="number" maxFractionDigits="3" value="${row.price}" />
      				</td>
      			</tr>
      		</c:forEach>
      	</tbody>
      </table>
      
      <div class="d-flex justify-content-end">
      	<a href="/create" class="btn btn-primary">글 쓰기</a>
      </div>
                
      </div>               
   </div>
</div>

</body>
</html>