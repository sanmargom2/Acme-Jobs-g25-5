<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.worker" path="worker.authorityName" />
	<acme:form-textbox code="employer.application.form.label.job" path="job.title" />
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" />
	<acme:form-textbox code="employer.application.form.label.moment" path="moment" />
	<acme:form-textarea code="employer.application.form.label.status" path="status" />
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" />
	<acme:form-money code="employer.application.form.label.skills" path="skills" />
	<acme:form-textbox code="employer.application.form.label.qualifications" path="qualifications" />
	
	<acme:form-return code="employer.application.form.return" />
</acme:form>