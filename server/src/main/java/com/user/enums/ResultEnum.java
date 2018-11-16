package com.user.enums;

import lombok.Getter;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/11 13:58
 * @Description:
 */
@Getter
public enum ResultEnum {
    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2,"角色权限不匹配")
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
