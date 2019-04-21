import com.air.bean.*;
import com.air.common.utils.MD5Utils;
import com.air.common.utils.RecommendUtils;
import com.air.dao.*;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.solr.common.util.Hash;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import sun.security.provider.MD5;

import java.util.*;


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

    @Test
    public void UserCFTest() {
        String s = "1";
        List<History> histories = historyDao.listHistoryByUser(s);

        //Map<userId,<itemId,score>>
//        Map<String, Map<Long, Double>> userMap = new HashMap<>();
//        for (int i = 0; i < histories.size(); i++) {
//            String userId = histories.get(i).getUserId();
//            if (userMap.get(userId) == null) {
//                HashMap<Long, Double> hashMap = new HashMap<>();
//                userMap.put(userId, hashMap);
//            }
//            userMap.get(userId).put(histories.get(i).getItemId(), RecommendUtils.getUtils().getScore(histories.get(i)));
//        }

        List<Long> longList = RecommendUtils.getUtils().UserBased(histories, s, 10);
        List<Item> itemList = recommendDao.listRecommendByIds(longList);
        logger.info(itemList);
    }

    @Test
    public void ItemCFTest() {
        Long id = 1L;
        List<History> histories = historyDao.listHistoryByItem(id);

        List<Long> longList = RecommendUtils.getUtils().ItemBased(histories, id, 10);

        List<Item> itemList = recommendDao.listRecommendByIds(longList);

        logger.debug(itemList);
    }

    @Test
    public void insertHistory() {
        String userId = "86c072affb204c668afa70aa0c239782";
        List<Item> itemList = itemDao.listItemByKeyWord("魅族", 31);
        for (int i = 0; i < itemList.size(); i++) {
            History history = new History();
            int items = (int) (Math.random() * 100 + 1);
            boolean star = Math.random() < 0.5;
            boolean favorite = Math.random() < 0.5;

            history.setItemId(itemList.get(i).getId());
            history.setUserId(userId);
            history.setTimes(items);
            history.setStar(star);
            history.setFavorite(favorite);
            historyDao.addHistory(history);
        }
    }

    @Test
    public void addSalt() {
        List<UserLogin> userLoginList = userLoginDao.selectUserLoginList(0, 20, "");
        for (UserLogin user : userLoginList) {
            String password = user.getPassword();
            password = MD5Utils.md5(password);
            password = MD5Utils.md5(password + MD5Utils.addSalt(password));
            user.setPassword(password);
            userLoginDao.updateUser(user);
        }


    }

}