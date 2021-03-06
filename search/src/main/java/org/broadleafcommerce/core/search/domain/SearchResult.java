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
package org.broadleafcommerce.core.search.domain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

/**
 * Container that holds the result of a ProductSearch or a SkuSearch
 * 
 * @author Andre Azzolini (apazzolini)
 */
public class SearchResult {
    
    protected List<SearchResultItem> resultItems;
    protected List<SearchFacetDTO> facets;
    
    protected Integer totalResults;
    protected Integer page;
    protected Integer pageSize;

    protected QueryResponse queryResponse;

    public List<SearchResultItem> getResultItems() {
        return resultItems;
    }

    public void setResultItems(List<SearchResultItem> resultItems) {
        this.resultItems = resultItems;
    }

    public List<SearchFacetDTO> getFacets() {
        return facets;
    }

    public void setFacets(List<SearchFacetDTO> facets) {
        this.facets = facets;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public Integer getStartResult() {
        return (CollectionUtils.isEmpty(getResultItems())) ? 0 : ((page - 1) * pageSize) + 1;
    }
    
    public Integer getEndResult() {
        return Math.min(page * pageSize, totalResults);
    }
    
    public Integer getTotalPages() {
        return (CollectionUtils.isEmpty(getResultItems())) ? 1 : (int) Math.ceil(totalResults * 1.0 / pageSize);
    }

    public QueryResponse getQueryResponse() {
        return queryResponse;
    }

    public void setQueryResponse(QueryResponse queryResponse) {
        this.queryResponse = queryResponse;
    }
}
