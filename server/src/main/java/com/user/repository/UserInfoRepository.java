package com.user.repository;

import com.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/11/7 09:26
 * @Description:
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    UserInfo findByOpenid (String openid);
}
