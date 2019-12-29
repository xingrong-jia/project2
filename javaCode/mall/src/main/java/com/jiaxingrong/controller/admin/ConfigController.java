package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.ExpressRequestBody;
import com.jiaxingrong.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("admin/config")
public class ConfigController {

    @Autowired
    ConfigService configService;
    @RequestMapping(value = "mall",method = RequestMethod.GET)
    public BaseReqVo ListMall(){
       Map map= configService.listmall();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        return baseReqVo;
    }

    @RequestMapping(value = "mall",method = RequestMethod.POST)
    public BaseReqVo UpdateMall(@RequestBody ExpressRequestBody requestBody){
        Integer update = configService.updateMall(requestBody);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping(value = "express",method = RequestMethod.GET)
    public BaseReqVo listExpress(){
        Map map = configService.listexpress();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    @RequestMapping(value = "express", method = RequestMethod.POST)
    public BaseReqVo PostExpress(@RequestBody ExpressRequestBody requestBody) {
        Integer integer = configService.updateExpress(requestBody);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping(value = "order",method = RequestMethod.GET)
    public BaseReqVo listOrder() {
        Map map = configService.listOrder();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    public BaseReqVo PostOrder(@RequestBody ExpressRequestBody requestBody) {
        Integer integer = configService.updateOrder(requestBody);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public BaseReqVo listWx() {
        Map map = configService.listWx();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public BaseReqVo PostWx(@RequestBody ExpressRequestBody requestBody) {
        Integer integer = configService.updateWx(requestBody);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

}
