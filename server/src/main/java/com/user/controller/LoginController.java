package com.user.controller;

import com.sun.javafx.binding.StringFormatter;
import com.user.constant.CookieConstant;
import com.user.constant.RedisConstant;
import com.user.dataobject.UserInfo;
import com.user.enums.ResultEnum;
import com.user.enums.RoleEnum;
import com.user.service.IUserService;
import com.user.utils.CookieUtil;
import com.user.utils.ResultVOUtil;
import com.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/11/7 09:40
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer (@RequestParam("openid") String openid, HttpServletResponse response){

        // 1、openid与database里的进行比对 是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2、 判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        // 3、 cookie里设置openid = abc 过期时间两小时
        CookieUtil.set(response, CookieConstant.TOKEN,openid,CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller (@RequestParam("openid") String openid, HttpServletResponse response, HttpServletRequest request){

        // 判断卖家是否登录
        //  取出cookie 并判断 redis存到该key是否为空
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie != null){
            String redisValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKENTEMPLATE,cookie.getValue()));
            if (!StringUtils.isEmpty(redisValue)){
                return ResultVOUtil.success();
            }
        }

        // 1、openid与database里的进行比对 是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2、 判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3、 redis设置key=UUID，value=xyz  如果此处写失败了就不会继续了

        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.EXPIRE;
        //  key\ value \过期时间 7200 \单位 s        比较重复存入 需要判断卖家是否登录
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKENTEMPLATE,token),
                 openid,
                 expire,
                 TimeUnit.SECONDS);
        // 4、 cookie里设置openid = xyz 过期时间两小时
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }
}
