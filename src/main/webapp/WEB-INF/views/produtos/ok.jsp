<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="cdc" %>
    
    <cdc:page title="ok">    
    
    <sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user"/>
	<div>
		Olá ${user.name }
	</div>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:url value="/produtos/formulario" var="formLink"/>
		<a href="${formLink }">Cadastrar Novo Produto</a>
	
	</sec:authorize>
 	

	<h1>Produto Cadastrado com sucesso</h1>
</body>
</html>
</cdc:page>