/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.service.impl;

import eu.ibacz.example.simpleshop.backend.dto.AddressDTO;
import eu.ibacz.example.simpleshop.backend.dto.BasketItemDTO;
import eu.ibacz.example.simpleshop.backend.dto.CustomerDTO;
import eu.ibacz.example.simpleshop.backend.dto.OrderDTO;
import eu.ibacz.example.simpleshop.backend.dto.OrderItemDTO;
import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;
import eu.ibacz.example.simpleshop.backend.entity.Address;
import eu.ibacz.example.simpleshop.backend.entity.BasketItem;
import eu.ibacz.example.simpleshop.backend.entity.Customer;
import eu.ibacz.example.simpleshop.backend.entity.Order;
import eu.ibacz.example.simpleshop.backend.entity.OrderItem;
import eu.ibacz.example.simpleshop.backend.entity.Product;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

/**
 *
 * @author Rastislav Papp
 */
public class ConversionUtils {

    public static List<ProductDTO> productDTOs(List<Product> entityList) throws BeansException {
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        if (entityList != null) {
            for (Product p : entityList) {
                result.add(productDTO(p));
            }
        }
        return result;
    }

    public static ProductDTO productDTO(Product p) throws BeansException {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(p, productDTO);
        return productDTO;
    }
    
    public static List<BasketItemDTO> basketItemDTOs(List<BasketItem> items) {
        List<BasketItemDTO> result = new ArrayList<BasketItemDTO>();
        if (items != null) {
            for (BasketItem item : items) {
                result.add(basketItemDTO(item));
            }
        }
        return result;
    }

    public static BasketItemDTO basketItemDTO(BasketItem item) {
        BasketItemDTO result = new BasketItemDTO();
        result.setAmount(item.getAmount());
        result.setId(item.getId());
        result.setProduct(productDTO(item.getProduct()));
        return result;
    }

    public static CustomerDTO customerDTO(Customer c) {
        CustomerDTO result = new CustomerDTO();
        if (c.getAddress() != null) {
            result.setAddress(addressDTO(c.getAddress()));
        }
        result.setEmail(c.getEmail());
        result.setFirstName(c.getFirstName());
        result.setLastName(c.getLastName());
        result.setUserId(c.getUserId());
        result.setBirthday(c.getBirthday());
        return result;
    }

    public static AddressDTO addressDTO(Address address) {
        AddressDTO result = new AddressDTO();
        result.setCity(address.getCity());
        result.setStreet(address.getStreet());
        result.setZipCode(address.getZipCode());
        return result;
    }

    public static List<OrderDTO> orderDTOs(List<Order> orders) {
        List<OrderDTO> result = new ArrayList<OrderDTO>();
        for (Order order : orders) {
            result.add(orderDTO(order));
        }
        return result;
    }
    
    public static OrderDTO orderDTO(Order order) {
        OrderDTO result = new OrderDTO(customerDTO(order.getCustomer()), orderItemDTOs(order.getItems()), order.getOrderDate());
        return result;
    }
    
    public static List<OrderItemDTO> orderItemDTOs(List<OrderItem> items) {
        List<OrderItemDTO> result = new ArrayList<OrderItemDTO>();
        for (OrderItem orderItem : items) {
            result.add(orderItem(orderItem));
        }
        return result;
    }

    public static OrderItemDTO orderItem(OrderItem orderItem) {
        OrderItemDTO result = new OrderItemDTO(productDTO(orderItem.getProduct()), orderItem.getAmount());
        return result;
    }

}
