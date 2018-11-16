package com.user.service;

import com.user.dataobject.UserInfo;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/11/7 09:30
 * @Description:
 */
public interface IUserService {

    /**
     * 通过openid 查询用户
     * @param openid
     * @return
     */
    UserInfo findByOpenid (String openid);
}
