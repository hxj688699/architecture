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
		<tr>
			<td><a href="${pageContext.request.contextPath}/toCart">查看购物车</a></td>
		</tr>
		<c:forEach items="${goods }" var="good" varStatus="status">
			<c:if test="${status.index % 4 == 0 }">
				<tr>
			</c:if>
					<td>
						<table>
							<tr>
								<td><a href="${pageContext.request.contextPath}/toGoodsDesc/${good.uuid }"><img alt="${good.name }" src="${pageContext.request.contextPath}/static/images/gb.png"></a></td>
								<td width="5"></td>
							</tr>
							<tr>
								<td>${good.description }</td>
							</tr>
						</table>
					</td>				
			<c:if test="${status.count % 4 == 0 }">
				</tr>
			</c:if>
			</c:forEach>
	</table>
</body>
</html>