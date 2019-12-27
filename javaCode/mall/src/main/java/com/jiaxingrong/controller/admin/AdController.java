package com.jiaxingrong.controller.admin;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:17
 * @Version 1.0
 */

import com.jiaxingrong.bean.type.ResultVo;
import com.jiaxingrong.execption.AdEx;
import com.jiaxingrong.model.Ad;
import com.jiaxingrong.requstov.admin.DataVo;
import com.jiaxingrong.service.inter.AdService;
import com.jiaxingrong.tools.ResultVoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 推广模块-广告管理
 * @author txyyf233
 * @data 19-11-16 15:07
 */
@RestController
@RequestMapping("admin")
public class AdController {
    @Autowired
    AdService adService;

    /**
     * 广告列表显示及查询
     * @param map
     * @return
     */
    @RequestMapping("ad/list")
    public ResultVo adList(@RequestParam Map<String,String> map){
        DataVo adList = adService.adlist(map);
        return ResultVoTools.successRe(adList, "加载广告成功");
    }

    /**
     * 广告添加
     * @param ad
     * @return
     * @throws AdEx
     */
    @RequestMapping("ad/create")
    public ResultVo adCreate(@RequestBody Ad ad) throws AdEx {
        Ad adCreate = adService.adCreate(ad);
        if(adCreate != null){
            return ResultVoTools.successRe(adCreate, "添加广告成功");
        }
        return ResultVoTools.failedRe("添加广告失败");
    }

    /**
     * 广告编辑
     * @param ad
     * @return
     */
    @RequestMapping("ad/update")
    public ResultVo adUpdate(@RequestBody Ad ad) throws AdEx{
        Ad adUpdate = adService.adUpdate(ad);
        if(adUpdate != null){
            return ResultVoTools.successRe(adUpdate, "编辑广告成功");
        }
        return ResultVoTools.failedRe("编辑广告失败");
    }

    /**
     * 广告删除
     * @param ad
     * @return
     * @throws AdEx
     */
    @RequestMapping("ad/delete")
    public ResultVo adDelete(@RequestBody Ad ad) throws AdEx {
        Integer adDelete = adService.adDelete(ad);
        if(adDelete == 1) {
            return ResultVoTools.successRe(adDelete, "删除广告成功");
        }else{
            return ResultVoTools.failedRe("删除广告失败");
        }
    }
}
