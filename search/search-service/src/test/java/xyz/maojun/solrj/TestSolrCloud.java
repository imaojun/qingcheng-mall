package xyz.maojun.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestSolrCloud {
    @Test
    public void testAddDocument() throws IOException, SolrServerException {
        CloudSolrServer solrServer = new CloudSolrServer("172.20.10.13:2181,172.20.10.13:2182,172.20.10.13:2183");
        solrServer.setDefaultCollection("collection2");
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", "solrclound1");
        document.setField("item_title", "test001");
        document.setField("item_price", 100);
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void testQueryDocument() throws SolrServerException {
        CloudSolrServer solrServer = new CloudSolrServer("172.20.10.13:2181,172.20.10.13:2182,172.20.10.13:2183");
        solrServer.setDefaultCollection("collection2");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        QueryResponse queryResponse = solrServer.query(solrQuery);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("totallist" + solrDocumentList.getNumFound());
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("title"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
        }

    }

}
