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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.util.StringUtil;
import org.broadleafcommerce.core.catalog.domain.ProductOption;
import org.broadleafcommerce.core.catalog.service.type.ProductOptionValidationStrategyType;
import org.broadleafcommerce.core.catalog.service.type.ProductOptionValidationType;
import org.broadleafcommerce.core.order.service.call.ActivityMessageDTO;
import org.broadleafcommerce.core.order.service.exception.ProductOptionValidationException;
import org.broadleafcommerce.core.order.service.type.MessageType;
import org.broadleafcommerce.core.workflow.ActivityMessages;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service("blProductOptionValidationService")
public class ProductOptionValidationServiceImpl implements ProductOptionValidationService  {

    private static final Log LOG = LogFactory.getLog(ProductOptionValidationServiceImpl.class);
    protected static final Integer ADD_TYPE_RANK = ProductOptionValidationStrategyType.ADD_ITEM.getRank();
    protected static final Integer SUBMIT_TYPE_RANK = ProductOptionValidationStrategyType.SUBMIT_ORDER.getRank();

    /* (non-Javadoc)
     * @see org.broadleafcommerce.core.order.service.ProductOptionValidationService#validate(org.broadleafcommerce.core.catalog.domain.ProductOption, java.lang.String)
     */
    @Override
    public Boolean validate(ProductOption productOption, String value) {
        if (requiresValidation(productOption) && !validateRegex(productOption.getValidationString(), value)) {
            String message = StringUtil.sanitize(productOption.getErrorMessage()) + ". Value [" + StringUtil.sanitize(value)
                    + "] does not match regex string [" + productOption.getValidationString() + "]";
            LOG.error(message);
            String exceptionMessage = productOption.getAttributeName() + " " + productOption.getErrorMessage()
                    + ". Value [" + value + "] does not match regex string ["
                    + productOption.getValidationString() + "]";
            throw new ProductOptionValidationException(exceptionMessage, productOption.getErrorCode(),
                    productOption.getAttributeName(), value, productOption.getValidationString(),
                    productOption.getErrorMessage());
        }

        return true;
    }

    protected Boolean requiresValidation(ProductOption productOption) {
        ProductOptionValidationType validationType = productOption.getProductOptionValidationType();

        Boolean typeRequiresValidation = validationType == null || validationType == ProductOptionValidationType.REGEX;
        Boolean validationStringExists = productOption.getValidationString() != null;

        return typeRequiresValidation && validationStringExists;
    }

    protected Boolean validateRegex(String regex, String value) {
        return value != null && Pattern.matches(regex, value);
    }

    @Override
    public boolean hasProductOptionValidationStrategy(ProductOption productOption) {
        return productOption.getProductOptionValidationStrategyType() != null;
    }

    @Override
    public boolean isSubmitType(ProductOption productOption) {
        boolean hasStrategy = hasProductOptionValidationStrategy(productOption);

        return hasStrategy && productOption.getProductOptionValidationStrategyType().getRank().equals(SUBMIT_TYPE_RANK);
    }

    @Override
    public boolean isAddOrNoneType(ProductOption productOption) {
        boolean hasStrategy = hasProductOptionValidationStrategy(productOption);

        return hasStrategy && productOption.getProductOptionValidationStrategyType().getRank() <= ADD_TYPE_RANK;
    }

    @Override
    public void validateWithoutException(ProductOption productOption, String attributeValue, ActivityMessages messages) {
        try {
            validate(productOption, attributeValue);
        } catch (ProductOptionValidationException e) {
            ActivityMessageDTO msg = new ActivityMessageDTO(MessageType.PRODUCT_OPTION.getType(), 1, e.getMessage());
            msg.setErrorCode(productOption.getErrorCode());
            messages.getActivityMessages().add(msg);
        }
    }
}
