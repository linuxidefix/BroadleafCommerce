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
package org.broadleafcommerce.core.order.service.call;

import org.broadleafcommerce.core.order.domain.FulfillmentOption;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.type.FulfillmentType;

import com.broadleafcommerce.order.common.domain.OrderAddress;

import java.util.ArrayList;
import java.util.List;

public class FulfillmentGroupRequest {

    protected OrderAddress orderAddress;
    protected Order order;
    
    /**
     * Both of these fields uses are superceded by the FulfillmentOption paradigm
     */
    @Deprecated
    protected String method;
    @Deprecated
    protected String service;
    
    protected FulfillmentOption option;
    
    protected List<FulfillmentGroupItemRequest> fulfillmentGroupItemRequests = new ArrayList<FulfillmentGroupItemRequest>();

    protected FulfillmentType fulfillmentType;

    public OrderAddress getAddress() {
        return orderAddress;
    }

    public void setAddress(OrderAddress address) {
        this.orderAddress = address;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public FulfillmentOption getOption() {
        return option;
    }
    
    public void setOption(FulfillmentOption option) {
        this.option = option;
    }

    public List<FulfillmentGroupItemRequest> getFulfillmentGroupItemRequests() {
        return fulfillmentGroupItemRequests;
    }

    public void setFulfillmentGroupItemRequests(List<FulfillmentGroupItemRequest> fulfillmentGroupItemRequests) {
        this.fulfillmentGroupItemRequests = fulfillmentGroupItemRequests;
    }

    public FulfillmentType getFulfillmentType() {
        return fulfillmentType;
    }

    public void setFulfillmentType(FulfillmentType fulfillmentType) {
        this.fulfillmentType = fulfillmentType;
    }

    /**
     * Deprecated in favor of {@link #getOption()}
     * @see {@link FulfillmentOption}
     */
    @Deprecated
    public String getMethod() {
        return method;
    }

    /**
     * Deprecated in favor of {@link #setOption(FulfillmentOption)}
     * @see {@link FulfillmentOption}
     */    
    @Deprecated
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Deprecated in favor of {@link #getOption()}
     * @see {@link FulfillmentOption}
     */
    @Deprecated
    public String getService() {
        return service;
    }

    /**
     * Deprecated in favor of {@link #setOption(FulfillmentOption)}
     * @see {@link FulfillmentOption}
     */    
    @Deprecated
    public void setService(String service) {
        this.service = service;
    }

}
