<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="cdc" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
    <cdc:page title="formulário de Produtos">    
	
<form:form action="/casadocodigo/produtos" method="post" commandName="product" enctype="multipart/form-data" >
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
		<label>Data de Lançamento</label>
		<form:input path="lancamento" type="text" id="lancamento"/>
		<form:errors path="lancamento"/>
	</div>
	<div>
		<label for="summary">Sumario</label>
		<input type="file" name="summary" id="summary"/>
		
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



</cdc:page>