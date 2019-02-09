<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

[
<c:forEach var="room" items="${list}" varStatus="i">
{ "roomname" : "${room.roomname}", "roompass" : "${room.roompass}",
  "userid" : "${room.userid}", "indata" : "${room.indata}" }
<c:if test="${!i.last}">,</c:if>
</c:forEach>
]