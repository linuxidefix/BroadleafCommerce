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
package org.broadleafcommerce.core.search.service;

import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.core.search.domain.SearchCriteria;
import org.broadleafcommerce.core.search.domain.SearchFacetDTO;
import org.broadleafcommerce.core.search.domain.SearchResult;
import org.broadleafcommerce.core.search.service.solr.index.SolrIndexService;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Andre Azzolini (apazzolini)
 */
public interface SearchService {

    /**
     * This method delegates to {@link SolrIndexService#rebuildIndex()}. It is here to preserve backwards-compatibility
     * with sites that were originally configured to run Broadleaf with Solr before 2.2.0.
     * 
     * @throws ServiceException
     * @throws IOException
     */
    public void rebuildIndex() throws ServiceException, IOException;
    
    /**
     * Performs a search for search results based on the given SearchCriteria, if SearchCriteria has a category, the category
     * is considering for the search.
     *
     * @param searchCriteria contains the information about this given search
     * @return the SearchResult
     */
    public SearchResult findSearchResults(SearchCriteria searchCriteria) throws ServiceException;

    /**
     * Gets all available facets for search results page
     * 
     * @return the available facets
     */
    public List<SearchFacetDTO> getSearchFacets();

    //TODO: microservices figure out category facets
//    /**
//     * Gets all available facets for the given category and global search
//     *
//     * @param category
//     * @return
//     */
//    public List<SearchFacetDTO> getSearchFacets(Category category);
//
//    /**
//     * Gets all available facets for a given category
//     * 
//     * @param category
//     * @return the available facets
//     */
//    public List<SearchFacetDTO> getCategoryFacets(Category category);

    /**
     * Determines whether or not the necessary configuration is in place
     *
     * @return whether or not the necessary configuration is in place
     */
    public boolean isActive();

}
