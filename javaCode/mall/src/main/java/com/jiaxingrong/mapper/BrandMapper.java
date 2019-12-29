package com.jiaxingrong.mapper;

import com.jiaxingrong.model.Brand;
import com.jiaxingrong.model.BrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BrandMapper {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    //查询所有品牌制造商信息
    List<Brand> selectBrands();
    //根据品牌商名称查询出品牌制造商
    List<Brand> selectBrandsByLikeName(@Param("name") String name);
}