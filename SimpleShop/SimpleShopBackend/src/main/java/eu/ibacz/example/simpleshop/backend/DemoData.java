package eu.ibacz.example.simpleshop.backend;

import eu.ibacz.example.simpleshop.backend.dao.ProductDAO;
import eu.ibacz.example.simpleshop.backend.entity.Product;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author petr
 */
@Component
public class DemoData {
    
    @Autowired
    private ProductDAO productDAO;
    
    private static int startingProductCount = 0;

    public static int getStartingProductCount() {
        return startingProductCount;
    }

    @Transactional
    private void add(String name, String description, BigDecimal price) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        productDAO.insert(p);
        startingProductCount++;
    }

    @Transactional
    public void createDemoData() {
        add("Ryzlink Rýnský 2010","Skvělý Rýňák", BigDecimal.valueOf(250));
        add("Ryzlink Vlašský 1998","Skvělý Rýňák", BigDecimal.valueOf(250));
        add("Chrupka 2000", "", BigDecimal.valueOf(199));
        add("Rulandské Šedé 2007", "", BigDecimal.valueOf(499));
    }

}
