package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.jiaxingrong.mapper.*;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service// 注册组件，只有service层注册了组件，controll层才能获取这个组件
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    /**
     * 用户模块会员管理功能首页显示及制定查询
     * @param page  分页的当前页数
     * @param limit 分页的数据条目数
     * @param username  用户名
     * @param mobile    用户手机号
     * @param sort  排序列名
     * @param order 升序或降序排列
     * @return  返回符合条件的全部user
     */
    @Override
    public List<User> listuser(int page, int limit, String username, String mobile, String sort, String order) {
        //开启分页
        PageHelper.startPage(page,limit);
        //开始设置限制条件
       //拼接sql语句，Sort要放在前面，Order在后面
        String orderby = sort + " " + order;
        //模糊查询
        String s = "%" + username + "%";
        PageHelper.orderBy(orderby);
        UserExample userExample = new UserExample();
        if (username != null && mobile != null) {
            userExample.createCriteria().andUsernameLike(s).andMobileEqualTo(mobile);
        }else if (username != null) {
            userExample.createCriteria().andUsernameLike(s);
        }else if (mobile != null) {
            userExample.createCriteria().andMobileEqualTo(mobile);
        }
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }

    @Autowired
    AddressMapper addressMapper;

    /**
     * 用户管理模块收货地址功能首页显示和查询具体User地址接口
     * @param page  分页当前页数
     * @param limit 分页的数据条目数
     * @param userId    用户ID
     * @param name  用户名
     * @param sort  排序排名
     * @param order 升序或者降序排列
     * @return  返回符合条件的全部地址信息
     */
    @Override
    public List<Address> listaddress(int page, int limit, Integer userId, String name, String sort, String order) {
        PageHelper.startPage(page,limit);
        String orderby = sort + " " + order;
        String s = "%" + name + "%";
        PageHelper.orderBy(orderby);
        AddressExample addressExample = new AddressExample();
        if (userId != null && name != null) {
            addressExample.createCriteria().andUserIdEqualTo(userId).andNameLike(s);
        }else if (userId != null) {
            addressExample.createCriteria().andUserIdEqualTo(userId);
        }else if (name != null) {
            addressExample.createCriteria().andNameLike(s);
        }
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }

    @Autowired
    CollectMapper collectMapper;

    /**
     * 用户管理模块会员收藏功能显示和查询具体User收藏商品接口
     * @param page  分页的当前页数
     * @param limit 分页的数据条目数
     * @param userId    用户Id
     * @param valueId   商品ID
     * @param sort  排序列名
     * @param order 升序或者降序排列
     * @return  返回符合条件的全部商品收藏信息
     */
    @Override
    public List<Collect> listcollect(int page, int limit, Integer userId, Integer valueId, String sort, String order) {
        PageHelper.startPage(page,limit);
        String orderby = sort + " " + order;
        PageHelper.orderBy(orderby);
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if (userId != null && valueId != null){
            criteria.andUserIdEqualTo(userId);
            criteria.andValueIdEqualTo(valueId);
        }else if (userId != null){
            criteria.andUserIdEqualTo(userId);
        }else if (valueId != null){
            criteria.andValueIdEqualTo(valueId);
        }
        criteria.andDeletedEqualTo(false);
        List<Collect> collectList = collectMapper.selectByExample(collectExample);
        return collectList;
    }

    @Autowired
    FootprintMapper footprintMapper;

    /**
     * 用户管理模块会员足迹功能首页显示及具体User 浏览信息查询接口
     * @param page  分页的当前页数
     * @param limit 分页的数据条目数
     * @param userId    用户ID
     * @param goodsId   商品ID
     * @param sort  排序列名
     * @param order 升序或者降序排列
     * @return  返回符合条件的全部商品浏览信息
     */
    @Override
    public List<Footprint> listfootprint(int page, int limit, Integer userId, Integer goodsId, String sort, String order) {
        PageHelper.startPage(page, limit);
        String orderby = sort + " " + order;
        PageHelper.orderBy(orderby);
        FootprintExample footprintExample = new FootprintExample();
        if (userId != null && goodsId != null) {
            footprintExample.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
        } else if (userId != null) {
            footprintExample.createCriteria().andUserIdEqualTo(userId);
        } else if (goodsId != null) {
            footprintExample.createCriteria().andGoodsIdEqualTo(goodsId);
        }
        List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);
        return footprintList;
    }

    @Autowired
    Search_historyMapper search_historyMapper;
    /**
     * 用户管理模块搜索历史功能首页显示及具体User浏览信息查询接口
     * @param page  分页的当前页数
     * @param limit    分页的数据条目数
     * @param userId    用户ID
     * @param keyword   关键字
     * @param sort  排序列名
     * @param order 升序或者降序排列
     * @return  返回符合条件的全部浏览历史信息
     */
    @Override
    public List<Search_history> listhistory(int page, int limit, Integer userId, String keyword, String sort, String order) {
        PageHelper.startPage(page,limit);
        String orderby = sort + " " + order;
        String s = "%" + keyword + "%";
        PageHelper.orderBy(orderby);
        Search_historyExample searchHistoryExample = new Search_historyExample();
        if (userId != null && keyword != null) {
            searchHistoryExample.createCriteria().andUserIdEqualTo(userId).andKeywordLike(s);
        }else if (userId != null) {
            searchHistoryExample.createCriteria().andUserIdEqualTo(userId);
        }else if (keyword != null) {
            searchHistoryExample.createCriteria().andKeywordLike(s);
        }
        List<Search_history> searchHistories = search_historyMapper.selectByExample(searchHistoryExample);
        return searchHistories;
    }

    @Autowired
    FeedbackMapper feedbackMapper;

    /**
     * 用户管理模块意见反馈功能首页显示及具体User反馈信息查询接口
     * @param page  分页的当前页数
     * @param limit 分页的数据条目数
     * @param id    反馈ID
     * @param username  反馈的用户名
     * @param sort  排序列名
     * @param order 升序或者降序排列
     * @return  返回符合条件的全部User反馈信息
     */
    @Override
    public List<Feedback> listfeedback(int page, int limit, Integer id, String username, String sort, String order) {
        PageHelper.startPage(page,limit);
        String orderby = sort + " " + order;       String s = "%" + username + "%";
        PageHelper.orderBy(orderby);
        FeedbackExample feedbackExample = new FeedbackExample();
        if (id != null && username != null) {
            feedbackExample.createCriteria().andIdEqualTo(id).andUsernameLike(s);
        }else if (id != null) {
            feedbackExample.createCriteria().andIdEqualTo(id);
        }else if (username != null) {
            feedbackExample.createCriteria().andUsernameLike(s);
        }
        List<Feedback> feedbackList = feedbackMapper.selectByExample(feedbackExample);
        return feedbackList;
    }

//    @Override
//    public Map getUserInfomationByPage(Laypage laypage) {
//        //开启分页,取出起始页数，和每页显示页数
//        PageHelper.startPage(laypage.getPage(),laypage.getLimit());
//        // 需要访问mapper,用到逆向工程：是根据数据库的列名，自动生成javabean ，mapper以及example
//        // 需要用到哪个数据库，就在model里面查找，类似userexample
//        // 为什么用到逆向工程：帮我们生成了一些方法，大部分crud操作帮我们写好了，我们只需要调用一些方法
//        // 把sql语句拼接起，少部分是不能使用逆向工程的，需要自己实现sql语句
//        UserExample userExample = new UserExample();
//        //开始设置限制条件
//        //拼接sql语句，Sort要放在前面，Order在后面
//        userExample.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
//      //  userExample.setOrderByClause(laypage.getOrder());
//        //创建内部类
//        UserExample.Criteria criteria = userExample.createCriteria();
//        criteria.andDeletedEqualTo(false);
//        List<User> userList = userMapper.selectByExample(userExample);
//        Map<Object, Object> map1 = new HashMap<>();
//        map1.put("total",userList.size());
//        map1.put("items",userList);
//
//        return map1;
//    }


}
