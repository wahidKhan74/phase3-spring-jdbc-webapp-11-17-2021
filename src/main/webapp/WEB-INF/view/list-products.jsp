<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<core:choose>
		<core:when test="${list.size()> 0 }">
			<core:forEach var="product" items="${list}">
				${product.getId()} - ${product.getName()}  - ${product.getPrice()} - ${product.getDateAdded()} <br>
				<br>
			</core:forEach>
		</core:when>
		<core:otherwise>
			<p>Product list is empty !</p>
		</core:otherwise>
	</core:choose>
</body>
</html>