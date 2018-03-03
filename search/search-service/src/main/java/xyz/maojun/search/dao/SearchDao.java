package xyz.maojun.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.maojun.common.pojo.SearchItem;
import xyz.maojun.common.pojo.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;
    public SearchResult search(SolrQuery query) throws SolrServerException {
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList results = queryResponse.getResults();
        long numFound = results.getNumFound();
        SearchResult searchResult = new SearchResult();
        searchResult.setRecordCount(numFound);
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<SearchItem> list = new ArrayList<>();
        for (SolrDocument result : results) {
            SearchItem item = new SearchItem();
            item.setId((String) result.get("id"));
            item.setCategory_name((String) result.get("item_Category_name"));
            item.setImage((String) result.get("item_image"));
            item.setPrice((Long) result.get("item_price"));
            item.setSell_point((String) result.get("item_sell_point"));
            List<String> list1 = highlighting.get(result.get("id")).get("item_title");
            String title = null;
            if (list != null && list.size() > 0) {
                title = list1.get(0);
            }else {
                title = (String) result.get("item_title");
            }
            item.setTitle(title);
            list.add(item);
        }
        searchResult.setItemList(list);

        return searchResult;
    }

}
