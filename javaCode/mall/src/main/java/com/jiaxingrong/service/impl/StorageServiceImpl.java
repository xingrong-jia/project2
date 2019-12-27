package com.jiaxingrong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.bean.type.Storage;
import com.jiaxingrong.execption.FileUploadEx;
import com.jiaxingrong.mapper.StorageMapper;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/26 22:36
 * @Version 1.0
 */
@Service
public class StorageServiceImpl implements StorageService {


    @Override
    public Storage uploadFile(MultipartFile file) throws FileUploadEx {
        return null;
    }

    @Override
    public int storageDelete(Integer id) {
        return 0;
    }

    @Override
    public Storage storageUpdate(Storage storageReq) {
        return null;
    }

    @Override
    public DataVo storageList(Map<String, Object> mapReq) {
        return null;
    }
}
