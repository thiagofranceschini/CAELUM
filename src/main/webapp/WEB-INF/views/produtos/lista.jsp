<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de produtos</title>
</head>
<body>
<h3>${message}</h3>
<table>
	<tr>Título</tr>
	<tr>Descricao</tr>
	
	<c:forEach items="${products }" var="produto">
		<tr>
			<td>${produto.titulo}</td>
			<td>${produto.descricao}</td>
			<td>
				<c:forEach items="${produto.prices}" var="prices">
					${prices.bookType }-${prices.value}
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
	
</table>


</body>
</html>