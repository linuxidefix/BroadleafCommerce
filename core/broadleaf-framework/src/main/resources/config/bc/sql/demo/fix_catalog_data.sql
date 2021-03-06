UPDATE BLC_PRODUCT_ATTRIBUTE SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_SKU_ATTRIBUTE SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_SKU_BUNDLE_ITEM SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_SEARCH_FACET_RANGE SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_CATEGORY SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null AND CATEGORY_ID != -99999;
UPDATE BLC_CATEGORY_XREF SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_SEARCH_FACET SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_CAT_SEARCH_FACET_XREF SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_INDEX_FIELD SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_PRODUCT SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_SKU SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_PRODUCT_OPTION SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_PRODUCT_OPTION_VALUE SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_SKU_OPTION_VALUE_XREF SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_PRODUCT_OPTION_XREF SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_CATEGORY_PRODUCT_XREF SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_MEDIA SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_SKU_MEDIA_MAP SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_PRODUCT_FEATURED SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_OFFER SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_OFFER_ITEM_CRITERIA SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_TAR_CRIT_OFFER_XREF SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_QUAL_CRIT_OFFER_XREF SET CATALOG_DISC = '-1' WHERE CATALOG_DISC is null;
UPDATE BLC_STATIC_ASSET SET SITE_DISC = '-1' WHERE SITE_DISC is null;
UPDATE BLC_FULFILLMENT_OPTION SET SITE_DISC = '-2' WHERE SITE_DISC is null;
UPDATE BLC_TRANSLATION SET CATALOG_DISC = -1 WHERE CATALOG_DISC IS NULL AND ENTITY_TYPE IN ('Category', 'ProdOption', 'ProdOptionVal', 'Sku');
UPDATE BLC_TRANSLATION SET SITE_DISC = -1 WHERE SITE_DISC IS NULL AND ENTITY_TYPE IN ('SearchFacet', 'FulfillmentOption', 'MenuItem', 'Page');
UPDATE BLC_TRANSLATION SET CATALOG_DISC = -1 WHERE CATALOG_DISC IS NULL AND ENTITY_TYPE IN ('Category', 'ProdOption', 'ProdOptionVal', 'Sku');
UPDATE BLC_TRANSLATION SET SITE_DISC = -1 WHERE SITE_DISC IS NULL AND ENTITY_TYPE IN ('SearchFacet', 'FulfillmentOption', 'MenuItem', 'Page');
UPDATE BLC_URL_HANDLER SET SITE_DISC = -2 WHERE SITE_DISC IS NULL;
UPDATE BLC_PRODUCT SET OVERRIDE_GENERATED_URL = FALSE;

