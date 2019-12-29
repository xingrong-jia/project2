package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class DeployController {


    @RequestMapping("admin/config/mall")
    //public BaseRespVo listmall(@RequestBody ListMallBean listMallBean){
    public BaseReqVo listMall(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        HashMap<String,Object> map = new HashMap<>();
        map.put("litemall_mall_phone","");
        map.put("litemall_mall_address","");
        map.put("litemall_mall_name","");
        map.put("litemall_mall_qq","");
        baseReqVo.setData(map);
        return baseReqVo;
    }
    @RequestMapping("admin/mall/update")
    public BaseReqVo updateMall(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    
}
