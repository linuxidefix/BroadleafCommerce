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
package com.broadleafcommerce.order.common.service;

import org.broadleafcommerce.common.util.TransactionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.broadleafcommerce.order.common.dao.OrderCustomerDao;
import com.broadleafcommerce.order.common.domain.OrderCustomer;

import javax.annotation.Resource;

/**
 * Created by brandon on 12/7/16.
 */
@Service("blOrderCustomerService")
public class OrderCustomerServiceImpl implements OrderCustomerService {

    @Resource(name = "blOrderCustomerDao")
    OrderCustomerDao orderCustomerDao;

    @Override
    @Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
    public OrderCustomer saveOrderCustomer(OrderCustomer orderCustomer) {
        return orderCustomerDao.save(orderCustomer);
    }

    @Override
    public OrderCustomer findOrderCustomerById(Long orderCustomerId) {
        return orderCustomerDao.readOrderCustomerById(orderCustomerId);
    }
    
    @Override
    public OrderCustomer findOrderCustomerByExternalId(Long externalId) {
        return orderCustomerDao.readOrderCustomerByExternalId(externalId);
    }

    @Override
    public OrderCustomer create() {
        return orderCustomerDao.create();
    }

    @Override
    @Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
    public void delete(OrderCustomer orderCustomer) {
        orderCustomerDao.delete(orderCustomer);
    }
}
