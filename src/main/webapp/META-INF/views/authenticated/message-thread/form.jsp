<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<acme:form>

	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment 
		code="authenticated.messageThread.form.label.moment" 
		path="moment"
		readonly="true" />
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
		<acme:form-textarea code="authenticated.messageThread.form.label.members" path="members" />
	</jstl:if>

<!-- Elegir entre todos los authenticateds del sistema -->	
	<jstl:if test="${command == 'create'}">
	<sql:setDataSource var="snapshot"
	url="jdbc:mysql://localhost:3306/acme-jobs?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
	user="acme-user" password="ACME-Us3r-P@ssw0rd" />
	<sql:query dataSource="${snapshot}" var="result">
		select * from user_account;
	</sql:query>
	
	
	<%-- <jstl:out value="${result.rows}"></jstl:out> --%>
	<jstl:forEach var="row" items="${result.rows}">
			
		<acme:form-option code="authenticated.messageThread.form.label.members" value="${row.username}" />
			
			
	</jstl:forEach>
		
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}"
	code="authenticated.messageThread.form.button.create"
	action="/authenticated/message-thread/create/" />
	
	
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
	
	<jstl:if test="${command == 'show'}">
		<acme:form-submit code="authenticated.messageThread.form.messages" 	method="get" action="/authenticated/message/list?threadId=${id}"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
		<acme:menu-option code="authenticated.messageThread.form.button.create" action="/authenticated/message/create?threadId=${id}" />
	</jstl:if>


</acme:form>