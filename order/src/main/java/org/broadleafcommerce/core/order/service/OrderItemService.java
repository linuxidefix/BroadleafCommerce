/*
 * #%L
 * BroadleafCommerce Framework
 * %%
 * Copyright (C) 2009 - 2016 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.core.order.service;

import org.broadleafcommerce.core.order.domain.GiftWrapOrderItem;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.order.domain.PersonalMessage;
import org.broadleafcommerce.core.order.service.call.ConfigurableOrderItemRequest;
import org.broadleafcommerce.core.order.service.call.GiftWrapOrderItemRequest;
import org.broadleafcommerce.core.order.service.call.OrderItemRequest;
import org.broadleafcommerce.core.order.service.call.OrderItemRequestDTO;

import java.util.Date;
import java.util.List;

public interface OrderItemService {
    
    public OrderItem readOrderItemById(Long orderItemId);

    public OrderItem saveOrderItem(OrderItem orderItem);
    
    public void delete(OrderItem item);
    
    public PersonalMessage createPersonalMessage();

    //public DiscreteOrderItem createDynamicPriceDiscreteOrderItem(final DiscreteOrderItemRequest itemRequest, @SuppressWarnings("rawtypes") HashMap skuPricingConsiderations);

    public GiftWrapOrderItem createGiftWrapOrderItem(GiftWrapOrderItemRequest itemRequest);

    /**
     * Creates an OrderItemRequestDTO object that most closely resembles the given OrderItem.
     * That is, it will copy the SKU and quantity and attempt to copy the product and category
     * if they exist.
     * 
     * @param item the item to copy
     * @return the OrderItemRequestDTO that mirrors the item
     */
    public OrderItemRequestDTO buildOrderItemRequestDTOFromOrderItem(OrderItem item);

    public OrderItem updateOrderItem(OrderItem orderItem, OrderItemRequest itemRequest);

    public OrderItem createOrderItem(OrderItemRequest itemRequest);

    public OrderItem buildOrderItemFromDTO(Order order, OrderItemRequestDTO orderItemRequestDTO);

    public void priceOrderItem(OrderItem item);

    public void applyAdditionalOrderItemProperties(OrderItem orderItem);

//TODO: microservices - deal with finding products and creating products related to configurable item requests
    //public Set<Product> findAllProductsInRequest(ConfigurableOrderItemRequest itemRequest);

    //public ConfigurableOrderItemRequest createConfigurableOrderItemRequestFromProduct(Product product);

    public void modifyOrderItemRequest(ConfigurableOrderItemRequest itemRequest);

    public void mergeOrderItemRequest(ConfigurableOrderItemRequest itemRequest, OrderItem orderItem);

    public List<OrderItem> findOrderItemsForCustomersInDateRange(List<Long> customerIds, Date startDate, Date endDate);
}
