package com.user.enums;

import lombok.Getter;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/11 13:58
 * @Description:
 */
@Getter
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2,"卖家")
    ;
    private Integer code;

    private String message;

    RoleEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
