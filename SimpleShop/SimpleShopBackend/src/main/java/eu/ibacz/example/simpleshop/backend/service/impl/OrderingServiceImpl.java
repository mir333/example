/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.service.impl;

import eu.ibacz.example.simpleshop.backend.dao.CustomerDAO;
import eu.ibacz.example.simpleshop.backend.dao.OrderDAO;
import eu.ibacz.example.simpleshop.backend.dao.ProductDAO;
import eu.ibacz.example.simpleshop.backend.dto.AddressDTO;
import eu.ibacz.example.simpleshop.backend.dto.BasketItemDTO;
import eu.ibacz.example.simpleshop.backend.dto.CustomerDTO;
import eu.ibacz.example.simpleshop.backend.dto.OrderDTO;
import eu.ibacz.example.simpleshop.backend.entity.Address;
import eu.ibacz.example.simpleshop.backend.entity.Customer;
import eu.ibacz.example.simpleshop.backend.entity.Order;
import eu.ibacz.example.simpleshop.backend.entity.OrderItem;
import eu.ibacz.example.simpleshop.backend.service.BasketService;
import eu.ibacz.example.simpleshop.backend.service.OrderingService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rastislav Papp
 */
@Service
@Transactional
public class OrderingServiceImpl implements OrderingService {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private BasketService basketService;
    @Autowired 
    private ProductDAO productDAO;
    
    public OrderDTO order(CustomerDTO customer) {
        validateCustomer(customer);
        
        Customer c = customerDAO.getCustomerByUserId(customer.getUserId());
        Validate.isTrue(c != null);
        
        updateCustomer(c, customer);
        OrderDTO dto = saveOrder(basketService.getBasket(customer.getUserId()).getItems(), c);
        basketService.removeAllProducts(customer.getUserId());
        return dto;
    }
    
    public CustomerDTO getCustomer(String userId) {
        Validate.notEmpty(userId);
        Customer c = customerDAO.getCustomerByUserId(userId);
        if (c == null) {
            c = createNewCustomer(userId);
        }
        return ConversionUtils.customerDTO(c);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getOrders(String userId) {
        List<Order> orders = orderDAO.findByUserId(userId);
        return ConversionUtils.orderDTOs(orders);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderDAO.getAll();
        return ConversionUtils.orderDTOs(orders);
    }

    private Customer createNewCustomer(String userId) {
        Customer c = new Customer();
        c.setUserId(userId);
        customerDAO.insert(c);
        return c;
    }

    private void validateCustomer(CustomerDTO customer) {
        Validate.notNull(customer);
        Validate.notNull(customer.getUserId());
        Validate.notNull(customer.getEmail());
        Validate.notNull(customer.getFirstName());
        Validate.notNull(customer.getLastName());
        validateAddress(customer.getAddress());
    }

    private void validateAddress(AddressDTO address) {
        Validate.notNull(address);
        Validate.notNull(address.getCity());
        Validate.notNull(address.getStreet());
        Validate.notNull(address.getZipCode());
    }

    private void updateCustomer(Customer c, CustomerDTO customer) {
        c.setEmail(customer.getEmail());
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setBirthday(customer.getBirthday());
        
        Address a = c.getAddress();
        if (a == null) {
            a = new Address();
            c.setAddress(a);
        }
        AddressDTO ad = customer.getAddress();
        a.setCity(ad.getCity());
        a.setStreet(ad.getStreet());
        a.setZipCode(ad.getZipCode());
        
        customerDAO.update(c);
    }

    private OrderDTO saveOrder(List<BasketItemDTO> items, Customer c) {
        Order entity = new Order();
        entity.setCustomer(c);
        entity.setItems(getItems(items));
        entity.setOrderDate(new Date());
        orderDAO.insert(entity);
        return ConversionUtils.orderDTO(entity);
    }

    private List<OrderItem> getItems(List<BasketItemDTO> items) {
        List<OrderItem> itemEntities = new ArrayList<OrderItem>();
        for (BasketItemDTO item : items) {
            OrderItem oe = new OrderItem();
            oe.setAmount(item.getAmount());
            oe.setProduct(productDAO.findById(item.getProduct().getId()));
            itemEntities.add(oe);
        }
        return itemEntities;
    }

}
