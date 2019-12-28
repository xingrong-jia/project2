package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.jiaxingrong.mapper.RegionMapper;
import com.jiaxingrong.model.Brand;
import com.jiaxingrong.model.BrandExample;
import com.jiaxingrong.model.Region;
import com.jiaxingrong.model.RegionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * 返回所有的省、市、县一级的信息
     *
     * @return
     */
    @Override
    public List<Region> regionList() {
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andTypeEqualTo((byte)1);
        List<Region> regionList = regionMapper.selectByExample(regionExample);
        for (Region region : regionList) {
            RegionExample regionExample1 = new RegionExample();
            regionExample1.createCriteria().andPidEqualTo(region.getId());
            List<Region> regionList1 = regionMapper.selectByExample(regionExample1);
            for (Region region1 : regionList1) {
                RegionExample regionExample2 = new RegionExample();
                regionExample2.createCriteria().andPidEqualTo(region1.getId());
                List<Region> regionList2 = regionMapper.selectByExample(regionExample2);
                region1.setChildren(regionList2);
            }
            region.setChildren(regionList1);
        }
        return regionList;
    }




}
