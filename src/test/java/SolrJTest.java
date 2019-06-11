import com.air.bean.Item;
import com.air.bean.Page;
import com.air.common.utils.SolrUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SolrJTest {

    @Test
    public void testCreateIndex() throws IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://api.airblog.top:8983/solr/airmail").build();

        SolrQuery solrQuery = new SolrQuery("*:*");
        solrQuery.add("id");
        solrQuery.add("item_name");

        solrQuery.setStart(0);
        solrQuery.setRows(10);

        QueryResponse queryResponse = solrClient.query(solrQuery);

        SolrDocumentList documents = queryResponse.getResults();

        for (SolrDocument document : documents) {
            System.out.println(document.get("id"));
            System.out.println(document.get("item_name"));
        }

    }

    @Test
    public void solrJTest() {
        Page<Item> itemList = SolrUtils.getUtils().getItemList(1, 10, "小米 6", null);
        System.out.println(itemList.toString());
    }
}
