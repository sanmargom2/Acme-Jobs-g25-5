<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="authenticated.message.list.label.title" path="title" width="20%" />
	<acme:list-column code="authenticated.message.list.label.moment" path="moment" width="40%" />
	<acme:list-column code="authenticated.message.list.label.author" path="authenticated.userAccount.username" width="20%" />
	
	<%--  <acme:form-submit code="authenticated.message.list.button.create" method="get" action="/authenticated/message/create?threadId=${id}" />
	--%>
</acme:list>