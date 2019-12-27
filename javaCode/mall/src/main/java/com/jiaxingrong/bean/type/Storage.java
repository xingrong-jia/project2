package com.jiaxingrong.bean.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
/**
 * @Author:luchang
 * @Date: 2019/12/26 21:57
 * @Version 1.0
 */
@Data
public class Storage {
    private Integer id;

    private String key;

    private String name;

    private String type;

    private Long size;

    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;

}
