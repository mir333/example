/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.ibacz.pv230.simpleshop.backend;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author petr
 */
@Component
public class DemoData {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    private void add(String name, String description, BigDecimal price) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        productDAO.insertProduct(p);
    }

    @Transactional
    public void createDemoData() {
        add("Ryzlink Rýnský 2010", "Skvělý Rýňák", BigDecimal.valueOf(250));
        add("Ryzlink Vlašský 1998", "Skvělý Rýňák", BigDecimal.valueOf(250));
    }

}
