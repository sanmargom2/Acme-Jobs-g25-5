<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="jobId" />
	

	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="worker.application.form.label.job" path="job.title" readonly="true"/>
		<acme:form-textbox code="worker.application.form.label.worker" path="worker.authorityName" readonly="true"/>
		<acme:form-moment code="worker.application.form.label.moment" path="moment" readonly="true"/>
		<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.reference" path="reference" />
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textbox code="worker.application.form.label.skills" path="skills" />
	<acme:form-textbox code="worker.application.form.label.qualifications" path="qualifications" />
	<acme:form-hidden path="status" />
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="worker.application.form.label.contrasena" path="contrasena"/>
		<acme:form-textbox code="worker.application.form.label.symbol" path="symbol"/>
	</jstl:if>
	<jstl:if test="${hasACulp}">
  		<acme:form-submit code="authenticated.job.form.button.culp" method="get" action="/authenticated/culp/show?id=${culpId}"/>
		<acme:form-textbox code="employer.job.form.label.answer" path="answer"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
	   	<jstl:if test="${contrasena == ''}">
	   	<jstl:if test="${symbol != '' }">
		<acme:form-textbox code="worker.application.form.label.symbol" path="symbol"/>
	</jstl:if>
	</jstl:if>
	<jstl:if test="${contrasena!=''}">
            <acme:form-password code="worker.application.form.label.contrasena" path="contrasena" />
            <acme:form-password code="worker.application.form.label.symbol" path="symbol" />
        </jstl:if>
	</jstl:if>
		<jstl:if test="${answer!=''}">
            <acme:form-password code="worker.application.form.label.answer" path="answer" />
        </jstl:if>
	<jstl:if test="${status == 'REJECTED'}">
		<acme:form-textarea code="worker.application.form.label.justification" path="justification"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="worker.application.form.button.create" action="/worker/application/create" />
	<acme:form-return code="worker.application.form.return" />
</acme:form>