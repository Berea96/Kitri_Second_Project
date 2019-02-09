<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

{ "num" : "${board.num}", "writer" : "${board.writer}",
  "content" : "${board.content}", "title" : "${board.title}",
  "readcount" : "${board.readcount}", "likecount" : "${board.likecount}",
  "category" : "${board.category}", "w_date" : "${board.w_date}" }
