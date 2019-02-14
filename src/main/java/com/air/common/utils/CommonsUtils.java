package com.air.common.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.UUID;

public class CommonsUtils {

    //生成UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @param clazz 需要解析成的bean类型 改bean类型必须实现Serializable接口
     * @param json  json文本
     * @return 返回解析成的bean对象 例: Djl p = JackSonUtil.parse(Djl.class, is);
     */
    public static <T> T parse(Class<T> clazz, String json) {
        ObjectMapper mapper = new ObjectMapper().setVisibility(
                JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        T result = null;
        try {
            /**
             * true可以设置私有变量
             */
            mapper.configure(
                    DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                    true);
            /**
             * 与未定义的属相有关
             */
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            result = mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
