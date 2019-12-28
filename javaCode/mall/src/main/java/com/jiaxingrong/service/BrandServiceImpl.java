package com.jiaxingrong.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.BrandMapper;
import com.jiaxingrong.model.Brand;
import com.jiaxingrong.model.BrandExample;
import com.jiaxingrong.model.Laypage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    /**
     * 返回所有的品牌制造商信息
     *
     * @return 需要查询出来的信息：
     * 查询出所有品牌的总数：total
     * 查询出所有品牌的详细信息
     */
    @Override
    public Map brandList(int pageNumber, int size) {
        Map map = new HashMap();
        //开启分页
        PageHelper.startPage(pageNumber, size);
        List<Brand> brandList = brandMapper.selectBrands();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        long total = pageInfo.getTotal();
        map.put("total", total);
        map.put("items", brandList);
        return map;
    }

    /**
     * 添加一条品牌信息
     *
     * @param brand
     */
    @Override
    public Brand brandCreat(Brand brand) {
        BrandExample brandExample = new BrandExample();
        long l = brandMapper.countByExample(brandExample);
        byte index = (byte) l;
        Byte i = new Byte(index);
        brand.setSortOrder(i);
        brand.setAddTime(new Date());
        brand.setUpdateTime(new Date());
        brand.setDeleted(false);
        int insert = brandMapper.insert(brand);
        if (insert == 0) {
            return null;
        }
        return brand;
    }

    /**
     * 更新一条品牌制造商的信息
     *
     * @param brand
     * @return
     */
    @Override
    public Brand brandUpdate(Brand brand) {
        //更新 updateTime
        brand.setUpdateTime(new Date());
        int i = brandMapper.updateByPrimaryKey(brand);
        if (i == 0) {
            return null;
        }
        return brand;
    }

    /**
     * 删除一条品牌制造商信息
     *
     * @param brand
     */
    @Override
    public boolean brandDelete(Brand brand) {
        int i = brandMapper.deleteByPrimaryKey(brand.getId());
        if (i == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据id和品牌商名称查询出 品牌商信息
     *
     * @param laypage
     * @return 如果有id的话必须根据id进行详细查询，如果没有id的话可以根据品牌商名称进行模糊查询
     */
    @Override
    public Map queryBrandList(Laypage laypage) {
        Map map = new HashMap();
        List<Brand> brandList = new ArrayList<>();
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());

        if (laypage.getId() != null) {
            Brand brand = brandMapper.selectByPrimaryKey(laypage.getId());
            if (brand != null) {
                brandList.add(brand);
                PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
                long total = pageInfo.getTotal();
                map.put("total", total);
                map.put("items", brandList);
            }else {
                PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
                long total = pageInfo.getTotal();
                map.put("total", total);
                map.put("items", brandList);
            }
            return map;
        }
        if (laypage.getName()!=null) {
            List<Brand> brandList1 = brandMapper.selectBrandsByLikeName(laypage.getName());
            PageInfo<Brand> pageInfo = new PageInfo<>(brandList1);
            long total = pageInfo.getTotal();
            map.put("total", total);
            map.put("items", brandList1);
        }
        return map;
    }


   /* @Override
    public Map brandCreat(Brand brand) {
        BrandExample brandExample = new BrandExample();
        long l = brandMapper.countByExample(brandExample);
        byte index = (byte) l;
        Byte i = new Byte(index);
        brand.setSortOrder(i);
        brand.setAddTime(new Date());
        brand.setUpdateTime(new Date());
        brand.setDeleted(false);
        int insert = brandMapper.insert(brand);
        if (insert == 0){
            return null;
        }
        Map map = new HashMap();
        map.put("id",brand.getId());
        map.put("name",brand.getName());
        map.put("desc",brand.getDesc());
        map.put("picUrl",brand.getPicUrl());
        map.put("flootPrice",brand.getFloorPrice());
        map.put("addTime",brand.getAddTime());
        map.put("updateTime",brand.getUpdateTime());
        return map;
    }*/


}
