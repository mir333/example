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

<div id="${ns}basketView" class="basket-portlet">
    
    <div class="portlet-msg-success">
        <spring:message code="msg-order-success"/>
    </div>
    
    <div class="buttons">
        <portlet:renderURL var="backURL" windowState="normal"/>
        <input type="button" onclick="location.href = '${backURL}'" value="<spring:message code='label-back'/>"/>
    </div>
</div>
