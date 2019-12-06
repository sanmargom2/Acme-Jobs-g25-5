<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="auditor.job.list.label.referenceNumber" path="referenceNumber" width="10%" />
	<acme:list-column code="auditor.job.list.label.title" path="title" width="80%" />
	<acme:list-column code="auditor.job.list.label.deadline" path="deadline" width="10%" />
</acme:list>