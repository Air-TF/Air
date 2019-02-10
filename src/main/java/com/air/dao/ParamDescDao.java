package com.air.dao;


import com.air.bean.Item;
import com.air.bean.ParamDesc;
import org.apache.ibatis.annotations.Param;

public interface ParamDescDao {

    Integer updateParamDescList(Item item);

    Integer updateParam(@Param("paramDesc") ParamDesc paramDesc,@Param("item_id") Long item_id);
}