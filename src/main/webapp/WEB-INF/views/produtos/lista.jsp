<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="cdc" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
    <cdc:page title="Lista de Produtos">    
    
    <sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user"/>
		<spring:message code="users.welcome" arguments="${user.name}"/>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:url value="/produtos/formulario" var="formLink"/>
		<a href="${formLink }">Cadastrar Novo Produto</a>
	
	</sec:authorize>
 	

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
			<td>
				<c:url value="/produtos/${produto.id}" var="linkDetalhar"/>
				<a href="${linkDetalhar }">Detalhar</a>
			</td>
		</tr>
	</c:forEach>
	
</table>

</cdc:page>