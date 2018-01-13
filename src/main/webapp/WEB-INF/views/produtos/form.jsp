<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produtos</title>
</head>
<body>
	
<form:form action="/casadocodigo/produtos" method="post" commandName="product">
	<h2>Informações para cadastro de produtos</h2>
	<div>
		<label for="titulo">Titulo</label>
		<form:input type="text" name="titulo" id="titulo" path="titulo"/>
		<form:errors path="titulo"/>
	</div>
	<div>
		<label for="paginas">Número de páginas </label>
		<input type="text" name="paginas" id=paginas>
		<form:errors path="paginas"/>
	</div>
	<div>
		<label for="descricao">Descrição</label>
		<textarea rows="10" cols="20" name="descricao" id="descricao"></textarea>
		<form:errors path="descricao"/>
	</div>
	<div>
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/>
				
				<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
			</div>
		</c:forEach>
	</div>
	
	
	
	<div>
		<input type="submit" value="Enviar">
	</div>
	
	
	

</form:form>



</body>
</html>