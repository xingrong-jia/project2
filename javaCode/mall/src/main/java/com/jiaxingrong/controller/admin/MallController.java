package com.jiaxingrong.controller.admin;

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.bean.type.Storage;
import com.jiaxingrong.execption.FileUploadEx;
import com.jiaxingrong.service.inter.StorageService;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:luchang
 * @Date: 2019/12/26 22:34
 * @Version 1.0
 */

@RestController
@RequestMapping("/admin")
public class MallController {
    @Autowired
    StorageService storageService;
    @RequestMapping("/storage/create")
    public ResultVo storageCreate(MultipartFile file) throws FileUploadEx {
        Storage storage = storageService.uploadFile(file);
        return ResultVoTools.successRe(storage, "文件上传成功");

}
}