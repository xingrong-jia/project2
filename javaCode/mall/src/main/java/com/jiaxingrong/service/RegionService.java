package com.jiaxingrong.service;

import com.jiaxingrong.mapper.RegionMapper;
import com.jiaxingrong.model.Brand;
import com.jiaxingrong.model.Region;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RegionService {

    List<Region> regionList();

}
