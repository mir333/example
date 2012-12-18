<%@include file="../init.jspf" %>

<f:setBundle basename="content.catalog"/>

<%@page import="static eu.ibacz.pv230.simpleshop.portlet.catalog.CatalogConstants.*" %>

<div id="${ns}catalogView">

    <h1><f:message key="ss-msg-products"/></h1>
    <div>
        <portlet:actionURL var="searchURL" name="<%=ACTION_SEARCH %>"/>
        <form action="${searchURL}" method="post">
            <input type="text" name="<%= PARAM_QUERY %>" id="${ns}query" autocomplete="off">
            <input type="submit" value="<f:message key="ss-msg-search"/>">
        </form>
    </div>


    <c:forEach var="product" items="${products}">
        <c:out value="${product.name}"/> <br>
    </c:forEach>
</div>


