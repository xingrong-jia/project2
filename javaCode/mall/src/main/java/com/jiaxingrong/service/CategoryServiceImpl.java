package com.jiaxingrong.service;

import com.jiaxingrong.mapper.CateGoryMapper;
import com.jiaxingrong.model.CateGory;
import com.jiaxingrong.model.CateGoryExample;
import com.jiaxingrong.model.L1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CateGoryMapper cateGoryMapper;

    /**
     * 显示所有商品类目信息
     *
     * @return
     */
    @Override
    public List<CateGory> categoryList() {
        CateGoryExample cateGoryExample = new CateGoryExample();
        Integer index = 0;
        cateGoryExample.createCriteria().andPidEqualTo(index);
        List<CateGory> cateGoryList = cateGoryMapper.selectByExample(cateGoryExample);
        for (CateGory cateGory : cateGoryList) {
            CateGoryExample cateGoryExample1 = new CateGoryExample();
            cateGoryExample1.createCriteria().andPidEqualTo(cateGory.getId());
            List<CateGory> cateGoryList1 = cateGoryMapper.selectByExample(cateGoryExample1);
            cateGory.setChildren(cateGoryList1);
        }
        return cateGoryList;
    }

    /**
     * 显示所有的标签信息
     *
     * @return
     */
    @Override
    public List<L1> categoryL1() {
        List<L1> l1List = new ArrayList<>();
        CateGoryExample cateGoryExample = new CateGoryExample();
        Integer index = 0;
        cateGoryExample.createCriteria().andPidEqualTo(index);
        List<CateGory> cateGoryList = cateGoryMapper.selectByExample(cateGoryExample);
        for (CateGory cateGory : cateGoryList) {
            L1 l1 = new L1();
            l1.setValue(cateGory.getId());
            l1.setLabel(cateGory.getName());
            l1List.add(l1);
        }
        return l1List;
    }

    /**
     * 添加一个类目
     *
     * @param cateGory
     * @return
     */
    @Override
    public CateGory categoryCreate(CateGory cateGory) {
        cateGory.setAddTime(new Date());
        cateGory.setUpdateTime(new Date());
        int insert = cateGoryMapper.insert(cateGory);
        if (insert == 0) {
            return null;
        }
        return cateGory;
    }

    /**
     * 更新一个商品的信息
     *
     * @param cateGory
     * @return
     */
    @Override
    public boolean categoryUpdate(CateGory cateGory) {
        cateGory.setUpdateTime(new Date());
        int i = cateGoryMapper.updateByPrimaryKey(cateGory);
        if (i == 0) {
            return false;
        }
        return true;
    }

    /**
     * 删除一个类目的信息
     *
     * @param cateGory
     * @return
     */
    @Override
    public boolean categoryDelete(CateGory cateGory) {
        int i = 0;
        if (cateGory.getChildren() == null) {
            i = cateGoryMapper.deleteByPrimaryKey(cateGory.getId());
        } else {
            CateGoryExample cateGoryExample = new CateGoryExample();
            cateGoryExample.createCriteria().andPidEqualTo(cateGory.getId());
            i = cateGoryMapper.deleteByExample(cateGoryExample);
            i += cateGoryMapper.deleteByPrimaryKey(cateGory.getId());
        }
        if (i == 0) {
            return false;
        }
        return true;
    }
}
