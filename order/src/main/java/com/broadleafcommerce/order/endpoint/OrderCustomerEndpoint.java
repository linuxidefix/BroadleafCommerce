/*
 * #%L
 * BroadleafCommerce Order
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
package com.broadleafcommerce.order.endpoint;

import org.broadleafcommerce.common.api.BaseEndpoint;
import org.broadleafcommerce.common.controller.FrameworkMapping;
import org.broadleafcommerce.common.controller.FrameworkRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.broadleafcommerce.order.common.domain.OrderCustomer;
import com.broadleafcommerce.order.common.dto.OrderCustomerDTO;
import com.broadleafcommerce.order.common.service.OrderCustomerService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@FrameworkRestController
@FrameworkMapping("/customer")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderCustomerEndpoint extends BaseEndpoint {

    @Resource(name = "blOrderCustomerService")
    protected OrderCustomerService orderCustomerService;
    
    @FrameworkMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getCustomerById(HttpServletRequest request, @PathVariable Long customerId) {
        OrderCustomer customer = orderCustomerService.findOrderCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity("No customer exists with id " + customerId, HttpStatus.NOT_FOUND);
        }
        OrderCustomerDTO response = (OrderCustomerDTO) context.getBean(OrderCustomerDTO.class.getName());
        response.wrapDetails(customer, request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @FrameworkMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity createCustomer(HttpServletRequest request, @RequestBody OrderCustomerDTO dto) {
        OrderCustomer customer = dto.unwrap(request, context);
        customer = orderCustomerService.saveOrderCustomer(customer);
        OrderCustomerDTO response = (OrderCustomerDTO) context.getBean(OrderCustomerDTO.class.getName());
        response.wrapDetails(customer, request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
