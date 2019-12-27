package com.jiaxingrong.service.inter;

import com.jiaxingrong.bean.type.Region;
import com.jiaxingrong.execption.DbException;

import java.util.List;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:40
 * @Version 1.0
 */
public interface RegionService {
    /**
     * 获取所有区域信息
     *
     * @return
     */
    List<Region> getList() throws DbException;
    //---------------------------------------------------------------------------------------------------
    List<Region> regionList(Integer pid);
}
