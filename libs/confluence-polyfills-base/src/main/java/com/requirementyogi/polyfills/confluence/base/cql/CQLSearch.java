package com.requirementyogi.polyfills.confluence.base.cql;

import com.atlassian.confluence.search.v2.*;

public interface CQLSearch {


    SearchResultsAdaptor search(SearchQuery query, SearchSort sort, int offset, int limit, boolean bypassPermissions) throws InvalidSearchException;
    Iterable<SearchResultAdaptor> searchAll(SearchQuery query, SearchSort sort, boolean bypassPermissions);

    /**
     * Converts Confluence-specific search results into generic results
     */
    SearchResultAdaptor convert(SearchResult result);

}
