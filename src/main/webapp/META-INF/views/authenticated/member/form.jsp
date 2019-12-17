<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="authenticated.member.form.label.username" path="authenticated.userAccount.username" readonly="${command!='create'}"/>
	<acme:form-textbox code="authenticated.member.form.label.title" path="messageThread.title" readonly="true"/>
	<acme:form-hidden path="messageThread.id"/>
	
	<acme:form-submit test="${command=='create'}" code="authenticated.member.form.button.create" action="create"/>
	<acme:form-submit test="${command!='create'}" code="authenticated.member.form.button.delete" action="delete"/>
	<acme:form-return code="authenticated.member.form.button.return"/>
</acme:form>