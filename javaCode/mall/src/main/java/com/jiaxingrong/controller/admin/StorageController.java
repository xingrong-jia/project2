package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Storage;
import com.jiaxingrong.respvobean.BaseRespVo;
import com.jiaxingrong.service.inter.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/storage")
public class StorageController {

    @Autowired
    StorageService storageService;
    @RequestMapping("list")
    public BaseRespVo getStorageListByPage(Laypage laypage) {
        List<Storage> storageList = storageService.getStorageListByPage(laypage);
        Map<String, Object> map = new HashMap<>();
        map.put("items", storageList);
        map.put("total", storageList.size());
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(map);
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("create")
    public BaseRespVo uploadAvatar(MultipartFile file) {
        Storage storage = storageService.storage(file);
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData(storage);
        respVo.setErrmsg("成功");
        return respVo;
    }
}
