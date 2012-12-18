/*
 * ===========================================================================
 * IBA CZ Confidential
 * 
 * © Copyright IBA CZ 2011 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 * 
 * ===========================================================================
 */
package eu.ibacz.example.simpleshop.portlet.detail;

import eu.ibacz.example.simpleshop.backend.ServiceProvider;

import javax.portlet.*;
import java.io.IOException;

import static eu.ibacz.example.simpleshop.portlet.detail.DetailConstants.*;
/**
 *
 * @author František Hartman <frantisek.hartman@ibacz.eu>
 */
public class DetailPortlet extends GenericPortlet {

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException,
            IOException {
        String productId = request.getParameter(PARAM_PRODUCT_ID);
        if (productId != null) {
            long id = Long.parseLong(productId);
            request.setAttribute(ATTRIBUTE_PRODUCT,
                    ServiceProvider.getCatalogService().getProductById(id));
        }
        PortletRequestDispatcher dispatcher =
                getPortletContext().getRequestDispatcher(JSP_VIEW);
        dispatcher.include(request, response);
    }


    
}
