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
import eu.ibacz.pv230.simpleshop.portlet.SimpleShopConstants;

import javax.portlet.*;
import java.io.IOException;

import static eu.ibacz.pv230.simpleshop.portlet.catalog.CatalogConstants.JSP_VIEW;

/**
 * @author Miroslav Ligas
 */
public class CatalogPortlet extends GenericPortlet {

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {

        request.setAttribute(SimpleShopConstants.ATTRIBUTE_PRODUCTS,
                ServiceProvider.getCatalog().getAllProducts());

        PortletRequestDispatcher dispatcher =
                getPortletContext().getRequestDispatcher(JSP_VIEW);
        dispatcher.include(request, response);
    }


}
