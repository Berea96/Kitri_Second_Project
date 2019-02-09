<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

[
<c:forEach var="comment" items="${commentList}" varStatus="i">
{ "board_num" : "${comment.board_num}", "writer" : "${comment.writer}",
  "num" : "${comment.num}", "content" : "${comment.content}",
  "w_date" : "${comment.w_date}" }
<c:if test="${!i.last}">,</c:if>
</c:forEach>
]