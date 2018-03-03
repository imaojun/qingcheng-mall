package xyz.maojun.search.service.Impl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.pojo.SearchItem;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.search.mapper.ItemMapper;
import xyz.maojun.search.service.SearchItemService;

import java.io.IOException;
import java.util.List;
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SolrServer solrServer;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public EgouResult importALLItem() {
        // search itemlsit
        try {
            List<SearchItem> itemList = itemMapper.getItemList();
            for (SearchItem searchItem : itemList) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", searchItem.getId());
                document.addField("item_title", searchItem.getTitle());
                document.addField("item_sell_point", searchItem.getSell_point());
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_image", searchItem.getImage());
                document.addField("item_category_name", searchItem.getCategory_name());
                solrServer.add(document);
            }} catch(SolrServerException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        try {
            solrServer.commit();
            return EgouResult.ok();
        } catch (SolrServerException e) {
            e.printStackTrace();
            return EgouResult.build(500, "失败了,请重试!");
        } catch (IOException e) {
            e.printStackTrace();
            return EgouResult.build(500, "失败了,请重试!");
        }
    }
}
