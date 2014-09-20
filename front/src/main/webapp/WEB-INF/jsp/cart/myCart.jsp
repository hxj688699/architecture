<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/static/css/application.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/js/application.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
	<table width="70%" align="center">
		<c:forEach items="${carts }" var="cart" varStatus="status">			
				<tr>
					<td><img src="${pageContext.request.contextPath}/static/images/gb.png"></td>
					<td>${cart.customerUuid }</td>
					<td>${cart.goodsUuid }</td>
					<td>${cart.buyNum }</td>
				</tr>							
			</c:forEach>
			<tr>
				<td><a href="${pageContext.request.contextPath}/order">提交订单</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/toIndex">继续购物</a></td>
			</tr>
	</table>
</body>
</html>