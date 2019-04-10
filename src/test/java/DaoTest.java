import com.air.bean.*;
import com.air.dao.*;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;


public class DaoTest {
    Logger logger = Logger.getLogger(DaoTest.class);
    ParamDescDao descDao;
    ItemDao itemDao;
    UserLoginDao userLoginDao;
    HistoryDao historyDao;
    RecommendDao recommendDao;

    @Before
    public void init() {
        String resource = "src/main/resource/spring-dao.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext(resource);
        descDao = context.getBean(ParamDescDao.class);
        itemDao = context.getBean(ItemDao.class);
        userLoginDao = context.getBean(UserLoginDao.class);
        historyDao = context.getBean(HistoryDao.class);
        recommendDao = context.getBean(RecommendDao.class);
    }

    @Test
    public void updateTest() {
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

    @Test
    public void insertTest() {
//        UserLogin userLogin=new UserLogin();
//        userLogin.setId("31822f5a4d6947c7b74e6acb35676d28");
////        userLogin.setName("a");
//        userLogin.setPassword("123");
//        userLogin.setPhone("1234");
//        userLoginDao.insertUserLogin(userLogin);
        History history = new History();
        history.setItemId(3L);
        history.setUserId("1");
        history.setStar(true);
        historyDao.insertHistory(history);
    }

    @Test
    public void selectTest() {
        Item item = recommendDao.getRecommendByHot(1);
        logger.debug(item.toString());

    }

}


