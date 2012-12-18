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
    
    <div>
        <span class="label"><spring:message code="label-product-count"/>:</span>
        <span><c:out value="${basket.itemCount}"/></span>
    </div>
    <div class="divider"/>
    <div>
        <span class="label"><spring:message code="label-total-price"/>:</span>
        <span><c:out value="${basket.totalPrice}"/></span>
    </div>
    <div class="buttons">
        <portlet:renderURL var="showBasketURL" windowState="maximized">
            <portlet:param name="<%=PARAM_PAGE%>" value="<%=PAGE_BASKET_MAXIMIZED%>"/>
        </portlet:renderURL>
        <input type="button" onclick="location.href = '${showBasketURL}'" value="<spring:message code='label-show-basket'/>"/>
    </div>
</div>
