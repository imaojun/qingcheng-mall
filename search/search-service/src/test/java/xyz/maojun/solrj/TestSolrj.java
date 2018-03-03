package xyz.maojun.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestSolrj {
    @Test
    public void addDocument() throws IOException, SolrServerException {
        // create solr object with  connection .
       SolrServer solrServer = new HttpSolrServer("http://172.20.10.13:8080/solr/collection1");
        // create document object solrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        // add filed
        document.addField("id","doc01");
        document.addField("item_title","测试商品01");
        document.addField("item_price",1000);
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void deleteDocument() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://172.20.10.13:8080/solr/collection1");
        // choose one way to delete by id
//        solrServer.deleteById("doc01");
        solrServer.deleteByQuery("id:doc01");
        solrServer.commit();
    }

    @Test
    public void queryIndex() throws SolrServerException {
        // create a SolrServer object.
        SolrServer solrServer = new HttpSolrServer("http://172.20.10.13:8080/solr/collection1");
        // create a SolrQuery object.
        SolrQuery query = new SolrQuery();
        // set query conditions
        // choose one way to query
//        query.setQuery("*:*");
        query.set("q", "*:*");
        // execute the queryResponse object
        QueryResponse queryResponse = solrServer.query(query);
        // traverse the document list to g et the data in the field
        SolrDocumentList results = queryResponse.getResults();
        System.out.println("query allList" + results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            System.out.println(result.get("item_title"));
            System.out.println(result.get("item_sell_point"));
            System.out.println(result.get("item_price"));
            System.out.println(result.get("item_image"));
            System.out.println(result.get("item_category_name"));
        }
    }

    @Test
    public void queryIndexByAdvance() throws SolrServerException {
        // create a SolrServer object.
        SolrServer solrServer = new HttpSolrServer("http://172.20.10.13:8080/solr/collection1");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("手机");
        solrQuery.setStart(0);
        solrQuery.setRows(20);
        solrQuery.set("df", "item_title");
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");
        QueryResponse queryResponse = solrServer.query(solrQuery);
        // traverse the document list to g et the data in the field
        SolrDocumentList results = queryResponse.getResults();
        System.out.println("query allList" + results.getNumFound());
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            List<String> list = highlighting.get(result.get("id")).get("item_title");
            String title = null;
            if (list != null && list.size() > 0) {
                title = list.get(0);
            }else {
                title = (String) result.get("item_title");
            }
            System.out.println(title);
            System.out.println(result.get("item_title"));
            System.out.println(result.get("item_sell_point"));
            System.out.println(result.get("item_price"));
            System.out.println(result.get("item_image"));
            System.out.println(result.get("item_category_name"));
        }
    }

}
