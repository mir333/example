/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend;

import eu.ibacz.example.simpleshop.backend.service.CatalogService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rastislav Papp
 */
@Component
@Deprecated
public class ServiceProvider {

    private static CatalogService catalog;

    private static DemoData demoData;

    @PostConstruct
    public void postConstruct() {
        demoData.createDemoData();
    }

    @Autowired
    public void setDemoData(DemoData demoData) {
        ServiceProvider.demoData = demoData;
    }
    
    @Autowired
    public void setCatalogService(CatalogService catalog) {
        ServiceProvider.catalog = catalog;
    }

    public static CatalogService getCatalogService() {
        return catalog;
    }

}
