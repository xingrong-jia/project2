package com.jiaxingrong.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.StorageMapper;
<<<<<<< HEAD
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.StorageExample;
import com.jiaxingrong.requstov.admin.DataVo;
=======
import com.jiaxingrong.model.*;
>>>>>>> 936581aedbd38c24ef86bc197b8541e58c330acc
import com.jiaxingrong.service.inter.StorageService;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.ArrayList;
=======
import java.io.File;
import java.io.IOException;
import java.util.Date;
>>>>>>> 936581aedbd38c24ef86bc197b8541e58c330acc
import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
/**
 * @Author:luchang
 * @Date: 2019/12/26 22:36
 * @Version 1.0
 */


=======
>>>>>>> 936581aedbd38c24ef86bc197b8541e58c330acc
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Value("D://File/Pic/")
    String picFile;

    @Value("http://localhost:8080/Pic")
    String url;

    /**
     * 保存图片的逻辑
     *
     * @param file
     * @return
     */
    @Override
    public Storage storage(MultipartFile file) {
        Storage storage = multipartFile(file);
        storage.setName(file.getOriginalFilename());
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setDeleted(false);
        int i = storageMapper.insertSelective(storage);
        if (i!=1) {
            return null;
        }
        return storage;
    }

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        StorageExample.Criteria criteria = storageExample.createCriteria();
        if (StringTool.isNotNull(laypage.getKey())) {
            criteria.andKeyEqualTo(laypage.getKey());
        }
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andNameEqualTo(laypage.getName());
        }
        criteria.andDeletedEqualTo(false);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        PageInfo<Storage> storagePageInfo = new PageInfo<>(storages);
        long total = storagePageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", storages);
        return map;
    }

    @Override
    public Storage updateTopic(Storage storage) {
        storage.setUpdateTime(new Date());
        storageMapper.updateByPrimaryKeySelective(storage);
        return storage;
    }

    @Override
    public int deleteTopic(Storage storage) {
        storage.setUpdateTime(new Date());
        storage.setDeleted(true);
        return storageMapper.updateByPrimaryKeySelective(storage);
    }

    @Override
    public Storage multipartFile(MultipartFile file) {
        String hashFile = StringTool.hashFile(file.getOriginalFilename());
        String filePath = picFile + hashFile;

        File file1 = new File(filePath);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Storage storage = new Storage();
        storage.setKey(hashFile);
        storage.setUrl(url+hashFile);
        storage.setName(file.getOriginalFilename());
        return storage;
    }

    @Override
    public List<Storage> getStorageListByPage(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        StorageExample example = new StorageExample();
        example.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
        StorageExample.Criteria criteria = example.createCriteria();
        if (laypage.getKey() != null) {
            criteria.andKeyEqualTo(laypage.getKey());
        }
        if (laypage.getName() != null) {
            criteria.andNameEqualTo(laypage.getName());
        }
        List<Storage> storageList = storageMapper.selectByExample(example);
        return  storageList;
    }

}
