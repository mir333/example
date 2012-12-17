<%@include file="../init.jspf" %>

<f:setBundle basename="content.catalog"/>

<%@page import="static eu.ibacz.pv230.simpleshop.portlet.catalog.CatalogConstants.*" %>

<div id="${ns}catalogView">

    <h1><f:message key="ss-msg-products"/></h1>

</div>

<%--

TAKTO NIE!!!

<%
    List<ProductDTO> dto = (List<ProductDTO>) renderRequest.getAttribute(ATTRIBUTE_PRODUCTS);
    for (ProductDTO productDTO : dto) {
%>
<%=productDTO.getName()%>
<br/>
<%
    }
%>
--%>


<c:forEach var="product" items="${products}">
    <c:out value="${product.name}"/> <br>
</c:forEach>