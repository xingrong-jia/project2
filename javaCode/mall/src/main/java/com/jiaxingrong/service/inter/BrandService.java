package com.jiaxingrong.service.inter;

import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.execption.InputException;
import com.jiaxingrong.model.Brand;
import com.jiaxingrong.requstov.admin.DataVo;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:44
 * @Version 1.0
 */
public interface BrandService {
    /**
     * 获取所有品牌商信息
     *
     * @param map
     * @return
     */
    DataVo getList(Map<String, String> map) throws InputException, DbException;

    /**
     * 增加品牌商
     *
     * @param brand
     * @return
     */
    Brand insertBrand(Brand brand) throws DbException, InputException;

    /**
     * 更新brand
     *
     * @param brand
     * @return
     */
    Brand updateBrand(Brand brand) throws InputException, DbException;

    /**
     * 删除品牌商
     *
     * @param brand
     * @return
     */
    int deleteBrand(Brand brand);

    List<Brand> getAllList() throws DbException;

    Brand brandDetail(Integer id);


}
