package xyz.maojun.search.service.Impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.pojo.SearchResult;
import xyz.maojun.search.dao.SearchDao;
import xyz.maojun.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private SearchDao searchDao;
    @Override
    public SearchResult search(String keyword, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(keyword);
        if (page <= 0) {
            page = 1;
        }
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        query.set("df", "item_title");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        SearchResult searchResult = searchDao.search(query);
        long recordCount = searchResult.getRecordCount();
        int totalPage = (int) (recordCount / rows);
        if (recordCount % rows > 0) {
            totalPage++;
        }
        searchResult.setTotalPages(totalPage);

        return searchResult;
    }

}
