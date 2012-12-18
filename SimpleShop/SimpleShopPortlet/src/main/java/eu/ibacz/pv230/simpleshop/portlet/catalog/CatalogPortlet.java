/*
 * ===========================================================================
 * IBA CZ Confidential
 * 
 * © Copyright IBA CZ 2010 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 * 
 * ===========================================================================
 */
package eu.ibacz.pv230.simpleshop.portlet.catalog;

import eu.ibacz.pv230.simpleshop.backend.ServiceProvider;

import javax.portlet.*;
import java.io.IOException;

import static eu.ibacz.pv230.simpleshop.portlet.catalog.CatalogConstants.*;

/**
 * @author Miroslav Ligas
 */
public class CatalogPortlet extends GenericPortlet {

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String query = request.getParameter(PARAM_QUERY);
               if (query == null) {
                   request.setAttribute(ATTRIBUTE_PRODUCTS,
                           ServiceProvider.getCatalog().getAllProducts());
               } else {
                   request.setAttribute(ATTRIBUTE_PRODUCTS,
                           ServiceProvider.getCatalog().findProducts(query));
               }

        PortletRequestDispatcher dispatcher =
                getPortletContext().getRequestDispatcher(JSP_VIEW);
        dispatcher.include(request, response);
    }


    @ProcessAction(name = ACTION_SEARCH)
    public void actionSearch(ActionRequest request, ActionResponse response) throws PortletException, IOException {
        response.setRenderParameters(request.getParameterMap());
    }
}
