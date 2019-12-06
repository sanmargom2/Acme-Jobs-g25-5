<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<acme:form>
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment"/>
	<acme:form-textarea code="authenticated.messageThread.form.label.members" path="members" />
	
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
	
	
	<acme:form-submit code="authenticated.messageThread.form.messages" 	method="get" action="/authenticated/message/list?threadId=${id}"/>

</acme:form>