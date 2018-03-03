package xyz.maojun.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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

}
