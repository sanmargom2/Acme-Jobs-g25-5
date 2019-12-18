<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="authenticated.message.list.label.title" path="title" width="20%" />
	<acme:list-column code="authenticated.message.list.label.moment" path="moment" width="40%" />
	<acme:list-column code="authenticated.message.list.label.author" path="authenticated.userAccount.username" width="20%" />
	
</acme:list>

<acme:form>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/message/create?messageThread.id=${param.id}')"
	class="btn btn-default">
		<acme:message code="authenticated.message.form.button.create" />
	</button>
	<acme:form-return code="authenticated.person.form.button.return"/>
</acme:form>
