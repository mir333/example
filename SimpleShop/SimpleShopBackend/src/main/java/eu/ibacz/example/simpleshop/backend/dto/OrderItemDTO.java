/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.dto;

/**
 *
 * @author Rastislav Papp
 */
public final class OrderItemDTO {

    private final ProductDTO product;
    private final int amount;

    public OrderItemDTO(ProductDTO product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public ProductDTO getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItemDTO other = (OrderItemDTO) obj;
        if (this.product != other.product && (this.product == null || !this.product.equals(other.product))) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.product != null ? this.product.hashCode() : 0);
        hash = 83 * hash + this.amount;
        return hash;
    }
    
}
