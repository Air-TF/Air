import com.air.common.utils.CommonsUtils;
import org.junit.Test;

import java.util.UUID;

public class CommonTest {
    @Test
    public void fun() {
        String[] strings = UUID.randomUUID().toString().split("-");
        for (String s : strings) {
            System.out.print(Long.parseLong(s,16));
        }
    }
}
