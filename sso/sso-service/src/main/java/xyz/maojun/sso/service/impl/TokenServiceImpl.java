package xyz.maojun.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.maojun.common.jedis.JedisClient;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.JsonUtils;
import xyz.maojun.pojo.TbUser;
import xyz.maojun.sso.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;
    @Override
    public EgouResult getUserByToken(String token) {
        String json = jedisClient.get("SESSION:" + token);
        if (StringUtils.isBlank(json)) {
            return EgouResult.build(201, "登入过期,请重写登入");
        }
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        return EgouResult.ok(user);
    }

}
