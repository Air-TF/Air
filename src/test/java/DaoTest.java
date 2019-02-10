import com.air.bean.Item;
import com.air.bean.ParamDesc;
import com.air.dao.ItemDao;
import com.air.dao.ParamDescDao;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;


public class DaoTest {
    private Logger logger = Logger.getLogger(DaoTest.class);
    private ParamDescDao descDao;
    private ItemDao itemDao;

    @Before
    public void init() {
        String resource = "src/main/resource/spring-dao.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext(resource);
        descDao = context.getBean(ParamDescDao.class);
        itemDao = context.getBean(ItemDao.class);
    }

    @Test
    public void UpdateTest() {
        Item item = itemDao.selectDetailedItemById(1L);
        List<ParamDesc> descList = item.getParamCategoryList().get(0).getParamDescList();
        item.getParamCategoryList().get(0).getParamDescList().get(0).setDescribe("2018年12月27日");
        int a = descDao.updateParamDescList(item);

//        ParamDesc paramDesc = descList.get(0);
//        2018年12月27日
//        paramDesc.setDescribe("2018年12月27日");
//        int a = descDao.updateParam(descList.get(0), item.getId());

        logger.info(a);
    }

}


