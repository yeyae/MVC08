<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 리스트</title>
</head>
<body>
	<%-- 메세지 리스트, 밑에 메세지 입력 폼을 만들어주자. 
	메세지 리스트는 sevlet에서 이미 속성으로 만들어 줬다고 가정
	
	만약 속성 이름이 messageList  ==> ${messageList} (el 사용)
--%>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자이름</th>
			<th>제목</th>
			<th>내용</th>
		</tr>
		<c:forEach items="${viewData.boardList }" var="board">
			<tr>
				<td>${board.id }</td>
				<td>${board.name }</td>
				<td>${board.title }</td>
				<td>${board.content }</td>
				<td><button onclick="location.href='boardDelete?id=${board.id}'">삭제</button></td>
				<%-- 주소에 파라미터 담아서 보내기
				요청주소?파라미터이름=값
				form이 아니라 단순 페이지 이동을 할때도 파라미터를 같이 포함시켜서 이동
				이동한 다음 페이지에서 파라미터를 사용하기 위해
				<form action="confirmDelte" method="get">
					<input type="text" name="id" value="messge.id">
				</form>
			 --%>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<%-- 여기에 페이지 네비게이션을 표시 --%>
	<%-- 처음 이전 1 2 3 4 5 6 7 8 9 10 다음 마지막 --%>
	<c:if test="${viewData.startPage != 1 }">
		<a href="boardList?page=1">[처음]</a>
		<a href="boardList?page=${viewData.startPage-1 }">[이전]</a>
	</c:if>
	
	<c:forEach var="pageNum" 
	begin="${viewData.startPage }" 
	end="${viewData.endPage < viewData.pageTotalCount ? viewData.endPage : viewData.pageTotalCount }">
		<c:choose>
		<%--현재 페이지인 경우 링크 비활성화 --%>
			<c:when test="${pageNum == viewData.currentPage }">
				<b>[${pageNum }]</b>
			</c:when>
		<%-- 다른 페이지로 넘어갈수 있도록 링크 제공 --%>
			<c:otherwise>
				<a href="boardList?page=${pageNum }">[${pageNum }]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${viewData.endPage < viewData.pageTotalCount }">
		<a href="boardList?page=${viewData.endPage+1 }">[다음]</a>
		<a href="boardList?page=${viewData.pageTotalCount }">[마지막]</a>
	</c:if>
	
	<%-- write 요청을 보낼 form --%>
	<form action="write" method="post">
		<fieldset>
			<legend>게시글 작성</legend>
			<p>
				제목 : <input type="text" name="title">
			</p>
			<p>
				내용
			</p>
			<p>
				<textarea rows="3" cols="30" name="content"></textarea>
			</p>
			<input type="submit" value="게시글 저장">
		</fieldset>
	</form>
	<p><button onclick="location.href='${pageContext.request.contextPath}/member/main'">메인화면으로</button></p>
</body>
</html>