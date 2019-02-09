<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

[
<c:forEach var="board" items="${boardList}" varStatus="i">
{ "boardNum" : "${board.num}", "writer" : "${board.writer}",
  "content" : "${boart.content}", "title" : "${board.title}",
  "readcount" : "${board.readcount}", "likecount" : "${board.likecount}",
  "category" : "${board.category}", "w_date" : "${board.w_date}" }
<c:if test="${!i.last}">,</c:if>
</c:forEach>
]