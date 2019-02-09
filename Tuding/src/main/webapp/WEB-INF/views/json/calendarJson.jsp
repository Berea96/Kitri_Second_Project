<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

[
<c:forEach var="calendar" items="${list}" varStatus="i">
{ "calnum" : "${calendar.calnum}", "start" : "${calendar.caldate1}",
  "end" : "${calendar.caldate2}", "title" : "${calendar.calevent}",
  "username" : "${calendar.member_id}" }
<c:if test="${!i.last}">,</c:if>
</c:forEach>
]