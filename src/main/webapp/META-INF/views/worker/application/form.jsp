<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="worker.application.form.label.worker" path="worker.authorityName" />
	<acme:form-textbox code="worker.application.form.label.job" path="job.title" />
	<acme:form-textbox code="worker.application.form.label.reference" path="reference" />
	<acme:form-textbox code="worker.application.form.label.moment" path="moment" />
	<acme:form-textarea code="worker.application.form.label.status" path="status" />
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-money code="worker.application.form.label.skills" path="skills" />
	<acme:form-textbox code="worker.application.form.label.qualifications" path="qualifications" />
	
	<acme:form-return code="worker.application.form.return" />
</acme:form>