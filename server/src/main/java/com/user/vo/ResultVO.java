package com.user.vo;

import lombok.Data;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/11 16:40
 * @Description:
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
