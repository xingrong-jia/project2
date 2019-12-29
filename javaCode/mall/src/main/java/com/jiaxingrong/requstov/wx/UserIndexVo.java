package com.jiaxingrong.requstov.wx;

import lombok.Data;

/**
 * @Author:luchang
 * @Date: 2019/12/27 15:25
 * @Version 1.0
 */
@Data
public class UserIndexVo {

    private Integer uncomment;

    private Integer unrecv;

    private Integer unpaid;

    private Integer unship;

    public UserIndexVo() {
    }

    public UserIndexVo(Integer uncomment, Integer unrecv, Integer unpaid, Integer unship) {
        this.uncomment = uncomment;
        this.unrecv = unrecv;
        this.unpaid = unpaid;
        this.unship = unship;
    }
}
