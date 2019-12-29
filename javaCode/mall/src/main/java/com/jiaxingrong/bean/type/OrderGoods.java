package com.jiaxingrong.bean.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:luchang
 * @Date: 2019/12/28 15:53
 * @Version 1.0
 */

@Data
public class OrderGoods {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private String goodsSn;

    private Integer productId;

    private Short number;

    private BigDecimal price;

    private String[] specifications;

    private String picUrl;

    private Integer comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private Date updateTime;

    private Boolean deleted;

}
