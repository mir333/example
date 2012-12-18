/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.dto;

import org.apache.commons.lang.Validate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Rastislav Papp
 */
public final class BasketDTO {

    private final String userId;
    private final List<BasketItemDTO> items;

    public BasketDTO(String userId, List<BasketItemDTO> items) {
        Validate.notNull(items);
        Validate.notEmpty(userId);
        
        this.userId = userId;
        this.items = items;
    }

    public List<BasketItemDTO> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    public int getItemCount() {
        int size = 0;
        for (BasketItemDTO item : items) {
            size += item.getAmount();
        }
        return size;
    }
    
    public BigDecimal getTotalPrice() {
        BigDecimal price = BigDecimal.ZERO;
        for (BasketItemDTO item : items) {
            price = price.add(item.getPrice());
        }
        return price;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasketDTO other = (BasketDTO) obj;
        if ((this.userId == null) ? (other.userId != null) : !this.userId.equals(other.userId)) {
            return false;
        }
        if (this.items != other.items && (this.items == null || !this.items.equals(other.items))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        hash = 97 * hash + (this.items != null ? this.items.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "BasketDTO{" +
                "userId='" + userId + '\'' +
                ", items=" + items +
                '}';
    }
}
