<%-- ===========================================================================
 *   IBA CZ Confidential
 *
 *    � Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 *    The source code for this program is not published or otherwise
 *    divested of its trade secrets.
 *
 * =========================================================================== --%>


<%@include file="../init.jspf" %>
<%@page import="static eu.ibacz.example.simpleshop.portlet.basket.BasketConstants.*" %>


<div id="${ns}basketView" class="basket-portlet order">


    <portlet:actionURL var="orderURL" name="<%=ACTION_ORDER%>">
        <portlet:param name="<%=PARAM_CONTROLLER%>" value="<%=CONTROLLER_ORDER%>"/>
    </portlet:actionURL>
    <form:form id="${ns}customerForm" method="POST" action="${orderURL}" commandName="<%=ATTRIBUTE_CUSTOMER%>"
               cssClass="iba-form">
        <fieldset>
            <legend>
                <spring:message code="label-customer-info"/>
            </legend>

            <form:hidden path="userId"/>
            <p>
                <form:label path="firstName"><spring:message code="label-first-name"/>:</form:label>
                <form:input path="firstName"/>
                <form:errors path="firstName" element="span" cssClass="${errorClass}"/>
            </p>

            <p>
                <form:label path="lastName"><spring:message code="label-last-name"/>:</form:label>
                <form:input path="lastName"/>
                <form:errors path="lastName" element="span" cssClass="${errorClass}"/>
            </p>

            <p>
                <form:label path="birthday"><spring:message code="label-birthday"/>:</form:label>
                <form:input path="birthday" id="${ns}birthday"/>
                <form:errors path="birthday" element="span" cssClass="${errorClass}"/>
            </p>

            <p>
                <form:label path="email"><spring:message code="label-email"/>:</form:label>
                <form:input path="email"/>
                <form:errors path="email" element="span" cssClass="${errorClass}"/>
            </p>

            <p>
                <form:label path="address.street"><spring:message code="label-street"/>:</form:label>
                <form:input path="address.street"/>
                <form:errors path="address.street" element="span" cssClass="${errorClass}"/>
            </p>

            <p>
                <form:label path="address.city"><spring:message code="label-city"/>:</form:label>
                <form:input path="address.city"/>
                <form:errors path="address.city" element="span" cssClass="${errorClass}"/>
            </p>

            <p>
                <form:label path="address.zipCode"><spring:message code="label-zipcode"/>:</form:label>
                <form:input path="address.zipCode"/>
                <form:errors path="address.zipCode" element="span" cssClass="${errorClass}"/>
            </p>

            <div class="buttons">
                <input type="submit" value="<spring:message code="label-submit-order"/>"/>

                <portlet:renderURL var="backURL" windowState="maximized">
                    <portlet:param name="<%=PARAM_PAGE%>" value="<%=PAGE_BASKET_MAXIMIZED%>"/>
                </portlet:renderURL>
                <input type="button" value="<spring:message code='label-back'/>"
                       onclick="location.href = '${backURL}'"/>
            </div>
        </fieldset>
    </form:form>

</div>