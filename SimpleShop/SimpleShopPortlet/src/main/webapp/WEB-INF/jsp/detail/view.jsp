<%@include file="../init.jspf" %>
<f:setBundle basename="content.detail"/>
<%@page import="static eu.ibacz.example.simpleshop.portlet.detail.DetailConstants.*" %>


<div id="${ns}detailView">
    <h1><f:message key="ss-msg-product-detail" /></h1>
    <c:choose>
        <c:when test="${product != null}">
            <div>
                <span class= "label"><f:message key="ss-msg-name"/></span>
                <span><c:out value= "${product.name}"/></span>
            </div>
            <div>
                <span class= "label"><f:message key="ss-msg-description"/></span>
                <span><c:out value= "${product.description}"/></span>
            </div>
            <div>
                <span class= "label"><f:message key="ss-msg-price"/></span>
                <span><c:out value= "${product.price}"/></span>
            </div>       
            <div class="buttons">
                <portlet:actionURL name="<%=ACTION_ADD_TO_BASKET%>" var="addToBasketURL">
                    <portlet:param name="<%=PARAM_PRODUCT_ID%>" value="${product.id}"/>
                </portlet:actionURL>
                <input type="button" onclick="location.href = '${addToBasketURL}'" value="<f:message key="ss-msg-buy"/>"/>
            </div>
        </c:when>
        <c:otherwise>
            <f:message key= "ss-msg-no-product-selected"/>
        </c:otherwise>
    </c:choose>
</div>


