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
package org.broadleafcommerce.admin.server.service.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.admin.condition.ConditionalOnAdmin;
import org.broadleafcommerce.common.currency.domain.BroadleafCurrency;
import org.broadleafcommerce.common.currency.util.BroadleafCurrencyUtils;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.presentation.client.OperationType;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.domain.OfferAdminPresentation;
import org.broadleafcommerce.core.offer.service.type.OfferItemRestrictionRuleType;
import org.broadleafcommerce.openadmin.dto.BasicFieldMetadata;
import org.broadleafcommerce.openadmin.dto.ClassMetadata;
import org.broadleafcommerce.openadmin.dto.CriteriaTransferObject;
import org.broadleafcommerce.openadmin.dto.DynamicResultSet;
import org.broadleafcommerce.openadmin.dto.Entity;
import org.broadleafcommerce.openadmin.dto.FieldMetadata;
import org.broadleafcommerce.openadmin.dto.MergedPropertyType;
import org.broadleafcommerce.openadmin.dto.PersistencePackage;
import org.broadleafcommerce.openadmin.dto.PersistencePerspective;
import org.broadleafcommerce.openadmin.dto.Property;
import org.broadleafcommerce.openadmin.server.dao.DynamicEntityDao;
import org.broadleafcommerce.openadmin.server.service.handler.ClassCustomPersistenceHandlerAdapter;
import org.broadleafcommerce.openadmin.server.service.persistence.module.InspectHelper;
import org.broadleafcommerce.openadmin.server.service.persistence.module.RecordHelper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Jon on 11/23/15.
 */
@Component("blOfferCustomPersistenceHandler")
@ConditionalOnAdmin
public class OfferCustomPersistenceHandler extends ClassCustomPersistenceHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(OfferCustomPersistenceHandler.class);

    protected static final String SHOW_ADVANCED_VISIBILITY_OPTIONS = "showAdvancedVisibilityOptions";
    protected static final String QUALIFIERS_CAN_BE_QUALIFIERS = "qualifiersCanBeQualifiers";
    protected static final String QUALIFIERS_CAN_BE_TARGETS = "qualifiersCanBeTargets";
    protected static final String OFFER_ITEM_QUALIFIER_RULE_TYPE = "offerItemQualifierRuleType";
    protected static final String STACKABLE = "stackableWithOtherOffers";
    protected static final String OFFER_ITEM_TARGET_RULE_TYPE = "offerItemTargetRuleType";

    public OfferCustomPersistenceHandler() {
        super(Offer.class);
    }

    @Override
    public Boolean canHandleInspect(PersistencePackage persistencePackage) {
       return classIsAssignableFrom(persistencePackage) && isBasicOperation(persistencePackage);
    }

    @Override
    public Boolean canHandleFetch(PersistencePackage persistencePackage) {
        return classIsAssignableFrom(persistencePackage) && isBasicOperation(persistencePackage);
    }

    @Override
    public Boolean canHandleUpdate(PersistencePackage persistencePackage) {
        return classIsAssignableFrom(persistencePackage) && isBasicOperation(persistencePackage);
    }

    @Override
    public DynamicResultSet inspect(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, InspectHelper helper) throws ServiceException {
        Class ceilingEntityClass = getClassForName(persistencePackage.getCeilingEntityFullyQualifiedClassname());

        PersistencePerspective persistencePerspective = persistencePackage.getPersistencePerspective();
        Map<MergedPropertyType, Map<String, FieldMetadata>> allMergedProperties = new HashMap<MergedPropertyType, Map<String, FieldMetadata>>();

        //retrieve the default properties for WorkflowEvents
        Map<String, FieldMetadata> properties = helper.getSimpleMergedProperties(ceilingEntityClass.getCanonicalName(), persistencePerspective);

        properties.put(SHOW_ADVANCED_VISIBILITY_OPTIONS, buildAdvancedVisibilityOptionsFieldMetaData());
        properties.put(QUALIFIERS_CAN_BE_QUALIFIERS, buildQualifiersCanBeQualifiersFieldMetaData());
        properties.put(QUALIFIERS_CAN_BE_TARGETS, buildQualifiersCanBeTargetsFieldMetaData());
        properties.put(STACKABLE, buildStackableFieldMetaData());

        allMergedProperties.put(MergedPropertyType.PRIMARY, properties);
        Class<?>[] entityClasses = dynamicEntityDao.getAllPolymorphicEntitiesFromCeiling(ceilingEntityClass);
        ClassMetadata mergedMetadata = helper.buildClassMetadata(entityClasses, persistencePackage, allMergedProperties);

        return new DynamicResultSet(mergedMetadata, null, null);
    }

    protected FieldMetadata buildAdvancedVisibilityOptionsFieldMetaData() {
        BasicFieldMetadata advancedLabelMetadata = new BasicFieldMetadata();
        advancedLabelMetadata.setFieldType(SupportedFieldType.BOOLEAN_LINK);
        advancedLabelMetadata.setForeignKeyCollection(false);
        advancedLabelMetadata.setMergedPropertyType(MergedPropertyType.PRIMARY);
        advancedLabelMetadata.setName("test");
        advancedLabelMetadata.setFriendlyName("OfferImpl_View_Visibility_Options");
        advancedLabelMetadata.setGroup(OfferAdminPresentation.GroupName.ActivityRange);
        advancedLabelMetadata.setOrder(5000);
        advancedLabelMetadata.setDefaultValue("true");
        return advancedLabelMetadata;
    }

    protected FieldMetadata buildQualifiersCanBeQualifiersFieldMetaData() {
        BasicFieldMetadata qualifiersCanBeQualifiers = new BasicFieldMetadata();
        qualifiersCanBeQualifiers.setFieldType(SupportedFieldType.BOOLEAN);
        qualifiersCanBeQualifiers.setName(QUALIFIERS_CAN_BE_QUALIFIERS);
        qualifiersCanBeQualifiers.setFriendlyName("OfferImpl_Qualifiers_Can_Be_Qualifiers");
        qualifiersCanBeQualifiers.setGroup(OfferAdminPresentation.GroupName.QualifierRuleRestriction);
        qualifiersCanBeQualifiers.setOrder(OfferAdminPresentation.FieldOrder.QualifiersCanBeQualifiers);
        qualifiersCanBeQualifiers.setDefaultValue("false");
        return qualifiersCanBeQualifiers;
    }

    protected FieldMetadata buildQualifiersCanBeTargetsFieldMetaData() {
        BasicFieldMetadata qualifiersCanBeTargets = new BasicFieldMetadata();
        qualifiersCanBeTargets.setFieldType(SupportedFieldType.BOOLEAN);
        qualifiersCanBeTargets.setName(QUALIFIERS_CAN_BE_TARGETS);
        qualifiersCanBeTargets.setFriendlyName("OfferImpl_Qualifiers_Can_Be_Targets");
        qualifiersCanBeTargets.setGroup(OfferAdminPresentation.GroupName.QualifierRuleRestriction);
        qualifiersCanBeTargets.setOrder(OfferAdminPresentation.FieldOrder.QualifiersCanBeTargets);
        qualifiersCanBeTargets.setDefaultValue("false");
        return qualifiersCanBeTargets;
    }

    protected FieldMetadata buildStackableFieldMetaData() {
        BasicFieldMetadata qualifiersCanBeTargets = new BasicFieldMetadata();
        qualifiersCanBeTargets.setFieldType(SupportedFieldType.BOOLEAN);
        qualifiersCanBeTargets.setName(STACKABLE);
        qualifiersCanBeTargets.setFriendlyName("OfferImpl_Stackable");
        qualifiersCanBeTargets.setTooltip("OfferImpl_Stackable_tooltip");
        qualifiersCanBeTargets.setGroup(OfferAdminPresentation.GroupName.CombineStack);
        qualifiersCanBeTargets.setOrder(OfferAdminPresentation.FieldOrder.StackableWithOtherOffers);
        qualifiersCanBeTargets.setDefaultValue("false");
        return qualifiersCanBeTargets;
    }

    @Override
    public DynamicResultSet fetch(PersistencePackage persistencePackage, CriteriaTransferObject cto, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
        DynamicResultSet resultSet = helper.getCompatibleModule(OperationType.BASIC).fetch(persistencePackage, cto);
        String customCriteria = "";
        if (persistencePackage.getCustomCriteria().length > 0) {
            customCriteria = persistencePackage.getCustomCriteria()[0];
        }

        for (Entity entity : resultSet.getRecords()) {
            Property discountType = entity.findProperty("discountType");
            Property discountValue = entity.findProperty("value");

            String value = discountValue.getValue();
            if (discountType == null || StringUtils.isBlank(discountType.getValue())) {
                discountValue.setValue("");
            } else if (discountType.getValue().equals("PERCENT_OFF")) {
                value = !value.contains(".") ? value : value.replaceAll("0*$", "").replaceAll("\\.$", "");
                discountValue.setValue(value + "%");
            } else if (discountType.getValue().equals("AMOUNT_OFF")) {
                Locale locale =  BroadleafRequestContext.getBroadleafRequestContext().getLocale();
                BroadleafCurrency currency =  BroadleafRequestContext.getBroadleafRequestContext().getBroadleafCurrency();
                NumberFormat nf = BroadleafCurrencyUtils.getNumberFormatFromCache(locale.getJavaLocale(), currency.getJavaCurrency());
                discountValue.setValue(nf.format(new BigDecimal(value)));
            }

            Property timeRule = entity.findProperty("offerMatchRules---TIME");
            entity.addProperty(buildAdvancedVisibilityOptionsProperty(timeRule));

            Property offerItemQualifierRuleType = entity.findProperty(OFFER_ITEM_QUALIFIER_RULE_TYPE);
            entity.addProperty(buildQualifiersCanBeQualifiersProperty(offerItemQualifierRuleType));
            entity.addProperty(buildQualifiersCanBeTargetsProperty(offerItemQualifierRuleType));

            Property offerItemTargetRuleType = entity.findProperty(OFFER_ITEM_TARGET_RULE_TYPE);
            entity.addProperty(buildStackableProperty(offerItemTargetRuleType));

            if (!"listGridView".equals(customCriteria)) {
                String setValue = discountValue.getValue();
                setValue = setValue.replaceAll("\\%", "").replaceAll("\\$", "");
                discountValue.setValue(setValue);
            }
        }
        return resultSet;
    }

    protected Property buildAdvancedVisibilityOptionsProperty(Property timeRule) {
        Property advancedLabel = new Property();
        advancedLabel.setName(SHOW_ADVANCED_VISIBILITY_OPTIONS);
        advancedLabel.setValue((timeRule.getValue() == null) ? "true" : "false");
        return advancedLabel;
    }

    protected Property buildQualifiersCanBeQualifiersProperty(Property offerItemQualifierRuleType) {
        boolean qualifiersCanBeQualifiers = isQualifierType(offerItemQualifierRuleType) || isQualifierTargetType(offerItemQualifierRuleType);

        Property property = new Property();
        property.setName(QUALIFIERS_CAN_BE_QUALIFIERS);
        property.setValue(String.valueOf(qualifiersCanBeQualifiers));
        return property;
    }

    protected Property buildQualifiersCanBeTargetsProperty(Property offerItemQualifierRuleType) {
        boolean qualifiersCanBeTargets = isTargetType(offerItemQualifierRuleType) || isQualifierTargetType(offerItemQualifierRuleType);

        Property property = new Property();
        property.setName(QUALIFIERS_CAN_BE_TARGETS);
        property.setValue(String.valueOf(qualifiersCanBeTargets));
        return property;
    }

    protected Property buildStackableProperty(Property offerItemTargetRuleType) {
        boolean stackable = isTargetType(offerItemTargetRuleType) || isQualifierTargetType(offerItemTargetRuleType);

        Property property = new Property();
        property.setName(STACKABLE);
        property.setValue(String.valueOf(stackable));
        return property;
    }

    protected boolean isQualifierType(Property offerItemQualifierRuleType) {
        return offerItemQualifierRuleType != null && Objects.equals(offerItemQualifierRuleType.getValue(), OfferItemRestrictionRuleType.QUALIFIER.getType());
    }

    protected boolean isTargetType(Property offerItemQualifierRuleType) {
        return offerItemQualifierRuleType != null && Objects.equals(offerItemQualifierRuleType.getValue(), OfferItemRestrictionRuleType.TARGET.getType());
    }

    protected boolean isQualifierTargetType(Property offerItemQualifierRuleType) {
        return offerItemQualifierRuleType != null && Objects.equals(offerItemQualifierRuleType.getValue(), OfferItemRestrictionRuleType.QUALIFIER_TARGET.getType());
    }

    @Override
    public Entity update(PersistencePackage persistencePackage, DynamicEntityDao dynamicEntityDao, RecordHelper helper) throws ServiceException {
        Entity entity = persistencePackage.getEntity();

        Property qualifiersCanBeQualifiers = entity.findProperty(QUALIFIERS_CAN_BE_QUALIFIERS);
        if (qualifiersCanBeQualifiers != null) {
            qualifiersCanBeQualifiers.setIsDirty(true);
        }
        Property qualifiersCanBeTargets = entity.findProperty(QUALIFIERS_CAN_BE_TARGETS);
        if (qualifiersCanBeTargets != null) {
            qualifiersCanBeTargets.setIsDirty(true);
        }
        Property offerItemQualifierRuleType = buildOfferItemQualifierRuleTypeProperty(qualifiersCanBeQualifiers, qualifiersCanBeTargets);
        entity.addProperty(offerItemQualifierRuleType);

        Property stackable = entity.findProperty(STACKABLE);
        if (stackable != null) {
            stackable.setIsDirty(true);
        }
        Property offerItemTargetRuleType = buildOfferItemTargetRuleTypeProperty(stackable);
        entity.addProperty(offerItemTargetRuleType);

        OperationType updateType = persistencePackage.getPersistencePerspective().getOperationTypes().getUpdateType();
        return helper.getCompatibleModule(updateType).update(persistencePackage);
    }

    protected Property buildOfferItemQualifierRuleTypeProperty(Property qualifiersCanBeQualifiers, Property qualifiersCanBeTargets) {
        String offerItemQualifierRuleType;
        boolean canBeQualifiers = qualifiersCanBeQualifiers == null ? false : Boolean.parseBoolean(qualifiersCanBeQualifiers.getValue());
        boolean canBeTargets = qualifiersCanBeTargets == null ? false : Boolean.parseBoolean(qualifiersCanBeTargets.getValue());

        if (canBeTargets && canBeQualifiers) {
            offerItemQualifierRuleType = OfferItemRestrictionRuleType.QUALIFIER_TARGET.getType();
        } else if (canBeTargets) {
            offerItemQualifierRuleType = OfferItemRestrictionRuleType.TARGET.getType();
        } else if (canBeQualifiers){
            offerItemQualifierRuleType = OfferItemRestrictionRuleType.QUALIFIER.getType();
        } else {
            offerItemQualifierRuleType = OfferItemRestrictionRuleType.NONE.getType();
        }

        Property property = new Property();
        property.setName(OFFER_ITEM_QUALIFIER_RULE_TYPE);
        property.setValue(offerItemQualifierRuleType);
        return property;
    }

    protected Property buildOfferItemTargetRuleTypeProperty(Property stackable) {
        String offerItemTargetRuleType;
        boolean isStackable = stackable == null ? false : Boolean.parseBoolean(stackable.getValue());

        if (isStackable) {
            offerItemTargetRuleType = OfferItemRestrictionRuleType.QUALIFIER_TARGET.getType();
        } else {
            offerItemTargetRuleType = OfferItemRestrictionRuleType.NONE.getType();
        }

        Property property = new Property();
        property.setName(OFFER_ITEM_TARGET_RULE_TYPE);
        property.setValue(offerItemTargetRuleType);
        return property;
    }
}
