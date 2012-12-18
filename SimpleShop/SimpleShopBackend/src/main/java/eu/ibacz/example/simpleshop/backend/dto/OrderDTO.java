/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.Validate;

/**
 *
 * @author Rastislav Papp
 */
public final class OrderDTO {
    
    private final CustomerDTO customer;
    private final List<OrderItemDTO> items = new ArrayList<OrderItemDTO>();
    private final Date orderDate;

    public OrderDTO(CustomerDTO customer, Collection<BasketItemDTO> items, Date orderDate) {
        Validate.notNull(customer);
        Validate.notEmpty(items);
        this.customer = customer;
        this.items.addAll(processBasketItems(items));
        this.orderDate = orderDate;
    }
    
    public OrderDTO(CustomerDTO user, List<OrderItemDTO> items, Date orderDate) {
        Validate.notNull(user);
        Validate.notEmpty(items);
        this.customer = user;
        this.items.addAll(items);
        this.orderDate = orderDate;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<OrderItemDTO> getItems() {
        return Collections.unmodifiableList(items);
    }

    private Collection<OrderItemDTO> processBasketItems(Collection<BasketItemDTO> items) {
        List<OrderItemDTO> result = new ArrayList<OrderItemDTO>(items.size());
        for (BasketItemDTO item : items) {
            OrderItemDTO orderItem = new OrderItemDTO(item.getProduct(), item.getAmount());
            result.add(orderItem);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderDTO other = (OrderDTO) obj;
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        if (this.items != other.items && (this.items == null || !this.items.equals(other.items))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 61 * hash + (this.items != null ? this.items.hashCode() : 0);
        return hash;
    }
    
}
