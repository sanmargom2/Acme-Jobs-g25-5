<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.auditor.form.label.firm" path="firm"/>
	<acme:form-textbox code="authenticated.auditor.form.label.responsabilityStatement" path="responsabilityStatement"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.auditor.form.button.create" action="/authenticated/auditor/create"/>
	
	<acme:form-submit test="${command == 'update'}" code="authenticated.auditor.form.button.update" action="/authenticated/auditor/update"/>
	<acme:form-return code="authenticated.auditor.form.button.return"/>
</acme:form>