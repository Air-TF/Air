package com.air.common.utils;

import java.util.UUID;

public class CommonsUtils {

    //生成UUID
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
