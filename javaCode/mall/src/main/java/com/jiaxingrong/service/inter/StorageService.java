package com.jiaxingrong.service.inter;

import com.jiaxingrong.requstov.admin.DataVo;
import org.springframework.web.multipart.MultipartFile;
import com.jiaxingrong.bean.type.Storage;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.execption.FileUploadEx;
import org.springframework.web.multipart.MultipartFile;

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
    Storage uploadFile(MultipartFile file) throws FileUploadEx;

    int storageDelete(Integer id);

    Storage storageUpdate(Storage storageReq);

    DataVo storageList(Map<String, Object> mapReq);
}
