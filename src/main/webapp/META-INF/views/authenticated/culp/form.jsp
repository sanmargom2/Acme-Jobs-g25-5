
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="authenticated.culp.form.label.text" path="text"/>
	<acme:form-textbox code="authenticated.culp.form.label.moreInfo" path="moreInfo"/>
  	<acme:form-return code="authenticated.culp.form.button.return"/>
</acme:form>