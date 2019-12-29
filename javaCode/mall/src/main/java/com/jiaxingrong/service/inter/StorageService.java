package com.jiaxingrong.service.inter;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Storage;
import com.jiaxingrong.requstov.admin.DataVo;
import org.springframework.web.multipart.MultipartFile;
//import com.jiaxingrong.bean.type.Storage;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.execption.FileUploadEx;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:52
 * @Version 1.0
 */
public interface StorageService {
    /**
     * 存储文件
     *
     * @param file
     * @return
     */

    Storage storage(MultipartFile file);

    Map list(Laypage laypage);

    Storage updateTopic(Storage storage);

    int deleteTopic(Storage storage);

    Storage multipartFile1(MultipartFile file);

    List<Storage> getStorageListByPage(Laypage laypage);

//    int storageDelete(Integer id);
//
//    Storage storageUpdate(Storage storageReq);
//
//    DataVo storageList(Map<String, Object> mapReq);
}
