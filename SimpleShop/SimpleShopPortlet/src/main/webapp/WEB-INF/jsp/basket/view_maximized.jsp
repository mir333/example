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

    <table>
        <thead class="portlet-section-header results-header">
        <tr>
            <th>
                <spring:message code="label-product-name"/>
            </th>
            <th>
                <spring:message code="label-amount"/>
            </th>
            <th>
                <spring:message code="label-price-per-item"/>
            </th>
            <th>
                <spring:message code="label-price"/>
            </th>
            <th>
                <spring:message code="label-action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty basket.items}">
                <c:forEach items="${basket.items}" var="item" varStatus="loop">
                    <tr class="results-row ${(loop.index mod 2 == 0) ? 'alt' : ''}">
                        <td>
                            <c:out value="${item.product.name}"/>
                        </td>
                        <td>
                            <c:out value="${item.amount}"/>
                        </td>
                        <td>
                            <c:out value="${item.product.price}"/>
                        </td>
                        <td>
                            <c:out value="${item.price}"/>
                        </td>
                        <td>
                            <portlet:actionURL name="<%=DELETE_ITEM%>" var="delUrl">
                                <portlet:param name="<%=PRODUCT_ID%>" value="${item.product.id}"/>
                            </portlet:actionURL>
                            <a href="${delUrl}"><spring:message code="label-delete"/></a>
                        </td>
                    </tr>
                </c:forEach>
                <tr class="results-row ${fn:length(basket.items) mod 2 == 0 ? 'alt' : ''}">
                    <td colspan="4">
                        <spring:message code="label-price-total"/>
                    </td>
                    <td><c:out value="${basket.totalPrice}"/></td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr class="results-row alt">
                    <td colspan="5" style="text-align: center;">
                        <spring:message code="label-no-items"/>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>


        </tbody>
    </table>


    <div class="buttons">

        <c:if test="${!empty basket.items}">
            <portlet:renderURL var="continueURL">
                <portlet:param name="<%=PARAM_CONTROLLER%>" value="<%=CONTROLLER_ORDER%>"/>
            </portlet:renderURL>
            <input type="button" onclick="location.href = '${continueURL}'"
                   value="<spring:message code='label-continue'/>"/>
        </c:if>


        <portlet:renderURL var="backURL" windowState="normal">
        </portlet:renderURL>
        <input type="button" onclick="location.href = '${backURL}'" value="<spring:message code='label-back'/>"/>

    </div>

</div>
