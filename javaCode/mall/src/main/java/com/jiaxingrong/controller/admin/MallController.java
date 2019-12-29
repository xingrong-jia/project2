package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.*;
import com.jiaxingrong.service.*;
import com.jiaxingrong.service.inter.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author WY
 */
@RestController
public class MallController {

    @Autowired
    RegionService regionService;

    @Autowired
    BrandService brandService;

    @Autowired
    StorageService storageService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @Autowired
    IssueService issueService;

    @Autowired
    KeywordService keywordService;


    /**
     * 返回所有的行政区域信息
     *
     * @return 省一级行政区域code码：11~65  type码：1
     * 市一级行政区域code码：省一级code码 + 01~99 type码：2
     * 县一级行政区域code码：市一级code码 + 01~99 type码：3
     */
    @RequestMapping("admin/region/list")
    public BaseReqVo<Object> regionList() {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        List<Region> regionList = regionService.regionList();
        baseReqVo.setData(regionList);
        return baseReqVo;
    }

    /**
     * 显示所有品牌制造商信息
     *
     * @return
     */
    @RequestMapping("admin/brand/list")
    public BaseReqVo<Object> brandList(Laypage laypage) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = new HashMap();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        if (laypage.getId() == null && laypage.getName() == null) {
            map = brandService.brandList(laypage.getPage(), laypage.getLimit());
        } else {
            map = brandService.queryBrandList(laypage);
        }
        baseReqVo.setData(map);
        return baseReqVo;
    }

    /**
     * 上传图片
     *
     * @param file

     * @return 1.浏览器文件标签名得和形参名一致*/
  /*  @RequestMapping("admin/storage/create")
    public BaseReqVo<Object> storageCreate(MultipartFile file) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        Storage storage = storageService.storage(file);
        baseReqVo.setData(storage);
        return baseReqVo;
    }*/

//    @RequestMapping("admin/storage/create")
//    public BaseReqVo<Object> storageCreate(MultipartFile file) {
//        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
//        baseReqVo.setErrno(0);
//        baseReqVo.setErrmsg("成功");
//        Storage storage = storageService.storage(file);
//        baseReqVo.setData(storage);
//        return baseReqVo;
//    }


    /**
     * 添加一个品牌制造商信息
     *
     * @param brand
     * @return
     */
    @RequestMapping("admin/brand/create")
    public BaseReqVo<Object> brandCreate(@RequestBody Brand brand) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        // Map map = brandService.brandCreat(brand);
        //baseReqVo.setData(map);
        Brand brand1 = brandService.brandCreat(brand);
        baseReqVo.setData(brand1);
        return baseReqVo;
    }

    /**
     * 更新一个品牌制造商信息
     *
     * @param brand
     * @return
     */
    @RequestMapping("admin/brand/update")
    public BaseReqVo<Object> brandUpdate(@RequestBody Brand brand) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        // Map map = brandService.brandCreat(brand);
        //baseReqVo.setData(map);
        Brand brand1 = brandService.brandUpdate(brand);
        baseReqVo.setData(brand1);
        return baseReqVo;
    }

    /**
     * 删除一条品牌制造商信息
     *
     * @param brand
     * @return
     */
    @RequestMapping("admin/brand/delete")
    public Map brandDelete(@RequestBody Brand brand) {
        Map map = new HashMap();
        boolean flag = brandService.brandDelete(brand);
        if (!flag) {
            return null;
        }
        map.put("errno", 0);
        map.put("errmsg", "成功");
        return map;
    }

    /**
     * 获取所有的商品类目信息
     *
     * @return
     */
    @RequestMapping("admin/category/list")
    public BaseReqVo<Object> categoryList() {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        List<CateGory> cateGoryList = categoryService.categoryList();
        baseReqVo.setData(cateGoryList);
        return baseReqVo;
    }

    /**
     * 显示所有标签信息
     *
     * @return
     */
    @RequestMapping("admin/category/l1")
    public BaseReqVo<Object> categoryL1() {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        List<L1> l1List = categoryService.categoryL1();
        baseReqVo.setData(l1List);
        return baseReqVo;
    }


    /**
     * 添加一个新的类目
     *
     * @param cateGory
     * @return
     */
    @RequestMapping("admin/category/create")
    public BaseReqVo<Object> categoryCreate(@RequestBody CateGory cateGory) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        CateGory cateGory1 = categoryService.categoryCreate(cateGory);
        baseReqVo.setData(cateGory1);
        return baseReqVo;
    }

    /**
     * 更新一个类目的信息
     *
     * @param cateGory
     * @return
     */
    @RequestMapping("admin/category/update")
    public Map categoryUpdate(@RequestBody CateGory cateGory) {
        Map map = new HashMap();
        boolean flag = categoryService.categoryUpdate(cateGory);
        if (!flag) {
            return null;
        }
        map.put("errno", 0);
        map.put("errmsg", "成功");
        return map;
    }

    /**
     * 删除一个类目的信息
     *
     * @param cateGory
     * @return
     */
    @RequestMapping("admin/category/delete")
    public Map categoryDelete(@RequestBody CateGory cateGory) {
        Map map = new HashMap();
        boolean flag = categoryService.categoryDelete(cateGory);
        if (!flag) {
            return null;
        }
        map.put("errno", 0);
        map.put("errmsg", "成功");
        return map;
    }

    /**
     * 显示所有订单信息
     *
     * @param laypage
     * @return
     */
    @RequestMapping("admin/order/list")
    public BaseReqVo<Object> orderList(Laypage laypage) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = new HashMap();
        if (laypage.getUserId() == null && (laypage.getOrderSn() == null || laypage.getOrderSn() == "") && laypage.getOrderStatusArray() == null ) {
             map = orderService.orderList(laypage);
        }else {
            map = orderService.queryOrderListByUserIdAndOrderAndOrOrderStatusArray(laypage);
        }
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    /**
     * 订单详情信息
     *
     * @param id
     * @return
     */
    @RequestMapping("admin/order/detail")
    public BaseReqVo<Object> orderDetail(Integer id) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = orderService.orderDetail(id);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    /**
     * 显示所有问题信息
     *
     * @param laypage
     * @return
     */
    @RequestMapping("admin/issue/list")
    public BaseReqVo<Object> issueList(Laypage laypage) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = new HashMap();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        if (laypage.getQuestion() == null) {
            //显示所有问题信息
            map = issueService.issueList(laypage);
        } else {
            //根据question的条件显示所有信息
            map = issueService.queryIssueList(laypage);
        }
        baseReqVo.setData(map);
        return baseReqVo;
    }

    /**
     * 添加一个新的问题
     *
     * @param issue
     * @return
     */
    @RequestMapping("admin/issue/create")
    public BaseReqVo<Object> issueCreate(@RequestBody Issue issue) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        Issue issue1 = issueService.issueCreate(issue);
        baseReqVo.setData(issue1);
        return baseReqVo;
    }

    /**
     * 更新一个问题
     *
     * @param issue
     * @return
     */
    @RequestMapping("admin/issue/update")
    public BaseReqVo<Object> issueUpdate(@RequestBody Issue issue) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        Issue issue1 = issueService.issueUpdate(issue);
        baseReqVo.setData(issue1);
        return baseReqVo;
    }

    /**
     * 删除一个问题
     *
     * @param issue
     * @return
     */
    @RequestMapping("admin/issue/delete")
    public BaseReqVo<Object> issueDelete(@RequestBody Issue issue) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        boolean flag = issueService.issueDelete(issue);
        if (!flag) {
            return null;
        }
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 显示所有关键词信息
     * 和根据keyword 和 url查询
     *
     * @param laypage
     * @return
     */
    @RequestMapping("admin/keyword/list")
    public BaseReqVo<Object> keywordList(Laypage laypage) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = new HashMap();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        if (laypage.getKeyword() == null && laypage.getUrl() == null) {
            //显示所有问题信息
            map = keywordService.keywordList(laypage);
        } else {
            //根据question的条件显示所有信息
            map = keywordService.queryKeywordList(laypage);
        }
        baseReqVo.setData(map);
        return baseReqVo;
    }

    /**
     * 添加一个关键词信息
     *
     * @param keyword
     * @return
     */
    @RequestMapping("admin/keyword/create")
    public BaseReqVo<Object> keywordCreate(@RequestBody Keyword keyword) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Keyword keyword1 = keywordService.keywordCreate(keyword);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(keyword1);
        return baseReqVo;
    }

    /**
     * 更新一个关键词的信息
     *
     * @param keyword
     * @return
     */
    @RequestMapping("admin/keyword/update")
    public BaseReqVo<Object> keywordUpdate(@RequestBody Keyword keyword) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Keyword keyword1 = keywordService.keywordUpdate(keyword);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(keyword1);
        return baseReqVo;
    }

    @RequestMapping("admin/keyword/delete")
    public Map keywordDelete(@RequestBody Keyword keyword) {
        Map map = new HashMap();
        boolean flag = keywordService.keywordDelete(keyword);
        if (!flag) {
            return null;
        }
        map.put("errno", 0);
        map.put("errmsg", "成功");
        return map;
    }


}
