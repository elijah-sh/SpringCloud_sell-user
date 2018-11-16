package com.user.service.impl;

import com.user.dataobject.UserInfo;
import com.user.repository.UserInfoRepository;
import com.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/11/7 09:38
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoRepository repository;
    @Override
    public UserInfo findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
