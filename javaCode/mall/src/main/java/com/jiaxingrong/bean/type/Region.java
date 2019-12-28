package com.jiaxingrong.bean.type;

import lombok.Data;

import java.util.List;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:43
 * @Version 1.0
 */
@Data
public class Region {
    private Integer id;

    private Integer pid;

    private String name;

    private Byte type;

    private Integer code;

    private List<Region> children;
}
