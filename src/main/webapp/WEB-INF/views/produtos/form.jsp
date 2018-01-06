<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produtos</title>
</head>
<body>
	<c:url value="/casadocodigo/produtos" var="url"/>
<form method="post" action="/casadocodigo/produtos">
	<h2>Informações para cadastro de produtos</h2>
	<div>
		<label for="titulo">Titulo</label>
		<input type="text" name="titulo" id="titulo">
	</div>
	<div>
		<label for="paginas">Número de páginas </label>
		<input type="text" name="paginas" id=paginas>
	</div>
	<div>
		<label for="descricao">Descrição</label>
		<textarea rows="10" cols="20" name="descricao" id="descricao"></textarea>
	</div>
	<div>
		<input type="submit" value="Enviar">
	</div>

</form>



</body>
</html>