package com.air.common.utils;

import com.air.bean.Item;
import com.air.bean.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolrUtils {
    private HttpSolrClient solrClient;

    private static class SingletonHolder {
        private static SolrUtils SOLR_UTILS = new SolrUtils();
    }

    public static SolrUtils getUtils() {
        return SingletonHolder.SOLR_UTILS;
    }

    private SolrUtils() {
        solrClient = new HttpSolrClient.Builder("http://120.78.81.63:8983/solr/airmail").build();
    }

    public Page<Item> getItemList(Integer page, Integer size, String keyword, Integer subcategoryId) {
        String queryString = "item_keywords:";
        queryString += keyword != null ? keyword : "*";
        if (subcategoryId != null) {
            queryString += " AND item_subcategory_id:" + subcategoryId;
        }
        SolrQuery solrQuery = new SolrQuery(queryString);

        solrQuery.addField("id");
        solrQuery.addField("item_image");
        solrQuery.addField("item_name");
        solrQuery.addField("item_title");
        solrQuery.addField("item_price");
        solrQuery.set("defType", "edismax");
        solrQuery.set("qf", "item_name^10 item_brand^10");

        solrQuery.setStart((page - 1) * size);
        solrQuery.setRows(size);

        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        assert queryResponse != null;
        SolrDocumentList documents = queryResponse.getResults();

        List<Item> itemList = new ArrayList<>();
        for (SolrDocument document : documents) {
            Item item = new Item();
            item.setId(Long.valueOf((String) document.get("id")));
            item.setImage((String) document.get("item_image"));
            item.setName((String) document.get("item_name"));
            item.setTitle((String) document.get("item_title"));
            item.setPrice(Long.valueOf((Integer) document.get("item_price")));
            itemList.add(item);
        }

        Page<Item> itemPage = new Page<>();
        itemPage.setPage(page);
        itemPage.setRows(itemList);
        itemPage.setSize(size);
        itemPage.setTotal((int) documents.getNumFound());

        return itemPage;
    }
}
