<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.member.list.label.username" path="authenticated.userAccount.username" width="40%"/>
	<acme:list-column code="authenticated.member.list.label.title" path="messageThread.title" width="40%"/>
</acme:list>

<acme:form>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/member/create?messageThread.id=${param.id}')"
	class="btn btn-default">
		<acme:message code="authenticated.member.form.label.create" />
	</button>
	<acme:form-return code="authenticated.member.form.button.return"/>
</acme:form>