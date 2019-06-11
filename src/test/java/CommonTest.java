import com.air.bean.UserLogin;
import com.air.common.utils.CommonsUtils;
import com.air.common.utils.EmailUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CommonTest {
    @Test
    public void fun() {
//        String[] strings = UUID.randomUUID().toString().split("-");
//        for (String s : strings) {
//            System.out.print(Long.parseLong(s,16));
//        }

        int verificationCode = (int) ((Math.random() * 9 + 1) * 100000);
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("1318860027@qq.com");
        userLogin.setId("1234567890");

        String context = EmailUtils.EmailContext(userLogin.getEmail(), userLogin.getId(), String.valueOf(verificationCode));
        EmailUtils.sendEmail(userLogin.getEmail(), "账号激活", context, null);
//        int verificationCode = (int) ((Math.random() * 9 + 1) * 100000);
//        EmailUtils.sendEmail("1318860027@qq.com", "验证码", String.valueOf(verificationCode), null);
    }

    @Test
    public  void fi1(){
        String s = "华为mate20Pro";
        s= s.replaceAll("([\u4e00-\u9fa5]+|[a-zA-Z]+|\\d+)","$1 ").trim();
//        for(String s1:split) System.out.println(s1);
        System.out.println(s);
    }

    @Test
    public void fun1(){
        List<String> itemList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        List<String> subList = itemList.subList(0, 1);
        subList.clear();
        List<String> subList2 = itemList.subList(1, 2);
        subList.remove("a");
        subList2.remove("b");
        System.out.println("total:"+itemList);
        System.out.println("subList:"+subList);
        System.out.println("subList2:"+subList2);
    }
}
