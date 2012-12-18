
<%@include file="../init.jspf" %>

<f:setBundle basename="content.catalog"/>

<%@page import="static eu.ibacz.example.simpleshop.portlet.catalog.CatalogConstants.*" %>

<portlet:resourceURL var="queryURL" id="<%= RESOURCE_QUERY %>"/>


<div id="<portlet:namespace/>autoComplete">
</div>


<aui:script use="aui-autocomplete">

    var dataSource = new A.DataSource.IO(
    {
    source: '${queryURL}'
    }
    );

    var autocomplete = new A.AutoComplete(
    {
    contentBox: '#<portlet:namespace/>autoComplete',
    dataSource: dataSource,
    delimChar: ',',

    matchKey: 'name',
    schema: {
    resultFields: ['name']
    },
    schemaType:'json',
    <%--typeAhead: false--%>
    }
    );

    autocomplete.generateRequest = function(query) {
    return {
    request: '&q=' + query
    };
    }
    autocomplete.render();

</aui:script>



<div id="${ns}catalogView">

    <h1><f:message key="ss-msg-products" /></h1>


    <div>
        <portlet:actionURL var="searchURL" name="<%=ACTION_SEARCH %>"/>
        <form action="${searchURL}" method="post">
            <input type="text" name="<%= PARAM_QUERY %>" id="${ns}query" autocomplete="off">
            <input type="submit" value="<f:message key="ss-msg-search"/>">
        </form>
    </div>

    <%-- pridat --%>
    <c:forEach var="product" items="${products}">

        <portlet:renderURL var="showDetailURL">
            <portlet:param name= "<%= PARAM_PRODUCT_ID %>" value="${product.id}"/>
        </portlet:renderURL>
        <a href="${showDetailURL}">
            <c:out value= "${product.name}"/> <img src="${resourcesPath}<%=ICON_DETAIL %>" alt="detail"> <br>
        </a>




    </c:forEach>

</div>