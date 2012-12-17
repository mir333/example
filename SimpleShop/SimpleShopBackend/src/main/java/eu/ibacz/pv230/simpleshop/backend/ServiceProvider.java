/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */

package eu.ibacz.pv230.simpleshop.backend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rastislav Papp
 */
@Component
public class ServiceProvider {

    private static Catalog catalog;

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
    public void setCatalog(Catalog catalog) {
        ServiceProvider.catalog = catalog;
    }

    public static Catalog getCatalog() {
        return catalog;
    }

}
