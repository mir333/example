/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.ibacz.example.simpleshop.backend.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author petr
 */
@Entity
@NamedQueries({
    @NamedQuery(name=Product.FIND_ALL, query="SELECT p from Product p"),
    @NamedQuery(name=Product.FIND_BY_NAME, query="SELECT p from Product p where LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')")
})
public class Product extends BaseEntity<Long> {

    public static final String FIND_ALL = "findAll";
    public static final String FIND_BY_NAME = "findByName";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
