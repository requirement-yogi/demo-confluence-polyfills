package com.requirementyogi.polyfills.confluence.c719.cql;

import bucket.core.persistence.hibernate.HibernateHandle;
import com.atlassian.confluence.core.SpaceContentEntityObject;
import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.search.v2.*;
import com.atlassian.confluence.search.v2.searchfilter.SiteSearchPermissionsSearchFilter;
import com.atlassian.extras.common.log.Logger;
import com.requirementyogi.polyfills.confluence.base.cql.AbstractCQLSearch;
import com.requirementyogi.polyfills.confluence.base.cql.CQLSearch;
import com.requirementyogi.polyfills.confluence.base.cql.SearchResultAdaptor;
import com.requirementyogi.polyfills.confluence.base.cql.SearchResultsAdaptor;

public class CQLSearchC713 extends AbstractCQLSearch implements CQLSearch {

    private static final Logger.Log log = Logger.getInstance(CQLSearchC713.class);

    public SearchResultsAdaptor search(SearchQuery query, SearchSort sort, int offset, int limit, boolean bypassPermissions) throws InvalidSearchException {
        SiteSearchPermissionsSearchFilter securityFilter;
        if (bypassPermissions) {
            securityFilter = null;
        } else {
            securityFilter = SiteSearchPermissionsSearchFilter.getInstance();
        }

        ContentSearch search = new ContentSearch(query, sort, securityFilter, offset, limit);
        return this.search(search);
    }

    @Override
    public SearchResultAdaptor convert(SearchResult searchResult) {
        if (searchResult.getHandle() instanceof HibernateHandle) {
            HibernateHandle handle = (HibernateHandle) searchResult.getHandle();
            // We need to check the handle is actually a CEO
            String className = handle.getClassName();
            try {
                Class<?> clazz = Class.forName(className);
                if (SpaceContentEntityObject.class.isAssignableFrom(clazz)
                    || Comment.class.isAssignableFrom(clazz)) {
                    // We return the ID, so that the page ID is treated
                    return new SearchResultAdaptor(
                        handle.getId(),
                        searchResult.getType(),
                        searchResult.getSpaceKey(),
                        searchResult.getDisplayTitle()
                    ) {
                        // Just so we can debug
                        HibernateHandle hibernateHandle = handle;
                    };
                }
            } catch (ClassNotFoundException e) {
                // Do nothing. The class is not available probably because it's in another plugin,
                // therefore we can't make sure it inherits from CEO.
            }
        }
        log.debug("Search result is not an instance of HibernateHandle: " + searchResult);
        return null;
    }
}
