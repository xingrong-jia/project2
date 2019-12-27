package com.jiaxingrong.controller.wx;

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.controller.admin.MallController;
import com.jiaxingrong.execption.FileUploadEx;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jiaxingrong.bean.type.Storage;
import java.util.HashMap;

/**
 * @Author:luchang
 * @Date: 2019/12/26 22:42
 * @Version 1.0
 */
@RestController
@RequestMapping("wx/storage")
public class StorageController {
    @Autowired
    MallController mallController;

    @RequestMapping("/upload")
    public ResultVo imgUpload(MultipartFile file) throws FileUploadEx {
        ResultVo<Storage> resultVo = mallController.storageCreate(file);
        String url = resultVo.getData().getUrl();
        HashMap<String, String> map = new HashMap<>(1);
        map.put("url", url);
        return ResultVoTools.successRe(map, "上传成功");
    }
}
