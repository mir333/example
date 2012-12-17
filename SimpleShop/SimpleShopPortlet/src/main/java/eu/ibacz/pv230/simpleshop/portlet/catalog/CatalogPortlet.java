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

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static eu.ibacz.pv230.simpleshop.portlet.catalog.CatalogConstants.*;

/**
 * @author Miroslav Ligas
 */
public class CatalogPortlet extends GenericPortlet {

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        PortletRequestDispatcher dispatcher =
            getPortletContext().getRequestDispatcher(JSP_VIEW);
        dispatcher.include(request, response);
    }


}
