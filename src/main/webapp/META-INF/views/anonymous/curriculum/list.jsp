<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.curriculum.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.curriculum.list.label.name" path="name" width="20%"/>
	<acme:list-column code="anonymous.curriculum.list.label.skills" path="skills" width="30%"/>
	<acme:list-column code="anonymous.curriculum.list.label.studies" path="studies" width="30%"/>
</acme:list>

<acme:form>
	<acme:menu-option code="anonymous.curriculum.list.button.add" action="/anonymous/curriculum/create"/>
</acme:form>

