package com.jiaxingrong.requstov.wx;

import com.jiaxingrong.model.Goods;
import lombok.Data;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:48
 * @Version 1.0
 */
@Data
public class GrouponBean {

    private Double groupon_price;

    private Integer groupon_member;

    private Goods goods;
}
