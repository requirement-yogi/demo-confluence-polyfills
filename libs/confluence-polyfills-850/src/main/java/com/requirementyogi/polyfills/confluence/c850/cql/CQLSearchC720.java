package com.requirementyogi.polyfills.confluence.c850.cql;

import com.atlassian.confluence.search.v2.*;
import com.requirementyogi.polyfills.confluence.base.cql.AbstractCQLSearch;
import com.requirementyogi.polyfills.confluence.base.cql.CQLSearch;
import com.requirementyogi.polyfills.confluence.base.cql.SearchResultAdaptor;
import com.requirementyogi.polyfills.confluence.base.cql.SearchResultsAdaptor;

import static com.atlassian.confluence.search.v2.query.BooleanQuery.andQuery;

public class CQLSearchC720 extends AbstractCQLSearch implements CQLSearch {

    private final SiteSearchPermissionsQueryFactory siteSearchPermissionsQueryFactory;

    public CQLSearchC720(SiteSearchPermissionsQueryFactory siteSearchPermissionsQueryFactory) {
        this.siteSearchPermissionsQueryFactory = siteSearchPermissionsQueryFactory;
    }

    @Override
    public SearchResultsAdaptor search(SearchQuery query,
                                       SearchSort sort,
                                       int offset,
                                       int limit,
                                       boolean bypassPermissions
    ) throws InvalidSearchException {
        if (!bypassPermissions) {
            SearchQuery permissionQuery = siteSearchPermissionsQueryFactory.create();
            if (permissionQuery == null) {
                throw new RuntimeException(
                        "Couldn't create a siteSearchPermissionsQueryFactory, as expected in C7.20 or C8.0+.");
            }
            query = andQuery(query, permissionQuery);
        }

        ContentSearch search = new ContentSearch(query, sort, offset, limit);
        return search(search);
    }

    @Override
    public SearchResultAdaptor convert(SearchResult searchResult) {
        long id = searchResult.getHandleId();
        return new SearchResultAdaptor(
            id,
            searchResult.getType(),
            searchResult.getSpaceKey(),
            searchResult.getDisplayTitle()
        );
    }
}
