package com.jiaxingrong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.execption.DbException;
import com.jiaxingrong.mapper.AdMapper;
import com.jiaxingrong.model.Ad;
import com.jiaxingrong.model.AdExample;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/26 20:33
 * @Version 1.0
 */

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdMapper adMapper;

    //广告显示
    @Override
    public DataVo adlist(Map<String, String> map) {
        //分页设置
        Integer page = Integer.parseInt(map.get("page"));
        Integer limit = Integer.parseInt(map.get("limit"));
        PageHelper.startPage(page, limit);

        AdExample adExample = new AdExample();
        //OrderByClause对应排序方式
        adExample.setOrderByClause(map.get("sort") + " " + map.get("order"));
        //搜索条件（与）
        //判断有没有请求此key
        AdExample.Criteria criteria = adExample.createCriteria();
        if (map.containsKey("name")) {
            criteria.andNameLike("%" + map.get("name") + "%");
        }
        if (map.containsKey("content")) {
            criteria.andContentLike("%" + map.get("content") + "%");
        }
        List<Ad> adList = adMapper.selectByExample(adExample);
        PageInfo<Ad> adPageInfo = new PageInfo<>(adList);
        long total = adPageInfo.getTotal();
        DataVo<Ad> adDataVo = new DataVo<>();
        adDataVo.setTotal(total);
        adDataVo.setItems(adList);
        return adDataVo;
    }

    //广告添加
    @Override
    public Ad adCreate(Ad ad) throws AdEx {
        //时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new Date());
        Date addTime = null;
        try {
            addTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ad.setAddTime(addTime);
        //修建url
        String url = ad.getUrl();
        int indexOf = url.indexOf("?");
        if (indexOf != -1) {
            String substring = url.substring(0, indexOf);
            ad.setUrl(substring);
        }
        //添加
        int insert = adMapper.insert(ad);
      int id = adMapper.selectLastInsertId();
        //查询数据库得到响应Date
        Ad ad1 = adMapper.selectByPrimaryKey(id);
        return ad1;
    }

    //广告编辑
    @Override
    public Ad adUpdate(Ad ad) throws AdEx {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fd = sdf.format(new java.util.Date());
        Date updateTime = null;
        try {
            updateTime = sdf.parse(fd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ad.setUpdateTime(updateTime);

        String url = ad.getUrl();
        int indexOf = url.indexOf("?");
        if (indexOf != -1) {
            String substring = url.substring(0, indexOf);
            ad.setUrl(substring);
        }

        int update = adMapper.updateByPrimaryKey(ad);
        Ad ad1 = adMapper.selectByPrimaryKey(ad.getId());
        return ad1;
    }

    //广告删除
    @Override
    public Integer adDelete(Ad ad) throws AdEx {
        int delete = adMapper.deleteByPrimaryKey(ad.getId());
        return delete;
    }

    @Override
    public List<Ad> getAllList() throws DbException {
        try {
            PageHelper.startPage(1, 8);
            AdExample adExample = new AdExample();
            adExample.createCriteria().andEnabledEqualTo(true);
            return adMapper.selectByExample(adExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("服务器异常");
        }
    }
}
