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
package eu.ibacz.example.simpleshop.portlet.catalog;

import eu.ibacz.example.simpleshop.backend.ServiceProvider;
import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import static eu.ibacz.example.simpleshop.portlet.catalog.CatalogConstants.*;

/**
 * @author František Hartman <frantisek.hartman@ibacz.eu>
 */
public class CatalogPortlet extends GenericPortlet {

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String query = request.getParameter(PARAM_QUERY);
        if (query == null) {
            request.setAttribute(ATTRIBUTE_PRODUCTS,
                    ServiceProvider.getCatalogService().getAllProducts());
        } else {
            request.setAttribute(ATTRIBUTE_PRODUCTS,
                    ServiceProvider.getCatalogService().findProducts(query));
        }

        PortletRequestDispatcher dispatcher =
                getPortletContext().getRequestDispatcher(JSP_VIEW);
        dispatcher.include(request, response);
    }

    @ProcessAction(name = ACTION_SEARCH)
    public void actionSearch(ActionRequest request, ActionResponse response) throws PortletException, IOException {
        response.setRenderParameters(request.getParameterMap());
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws
            PortletException, IOException {
        String resourceID = request.getResourceID();
        if (RESOURCE_QUERY.equals(resourceID)) {
            serveAutocompleteQuery(request, response);
        } else {
            super.serveResource(request, response);
        }
    }

    private void serveAutocompleteQuery(ResourceRequest request, ResourceResponse response) throws IOException {
         String searchString = request.getParameter(PARAM_AUTOCOMPLETE_QUERY);
         if (searchString == null) {
             return;
         }
         PrintWriter out = null;
         try {
             response.setContentType("text/json");
             out = response.getWriter();
             out.print("[");

             Iterator<ProductDTO> it = ServiceProvider.getCatalogService().findProducts(searchString).iterator();
             while (it.hasNext()) {
                ProductDTO p = it.next();
                out.print("[\"" + p.getName() + "\"]");
                if (it.hasNext()) {
                   out.print(",");
                }

             }
             out.print("]");
         } catch (IOException ex) {
             // log error
         } finally {
             out.close();
         }
     }
}
