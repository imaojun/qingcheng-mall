package xyz.maojun.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.mapper.TbUserMapper;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbUser;
import xyz.maojun.pojo.TbUserExample;
import xyz.maojun.sso.service.RegisterService;

import javax.imageio.event.IIOReadProgressListener;
import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private TbUserMapper userMapper;
    @Override
    public EgouResult checkDate(String param, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        } else if (type == 2) {
            criteria.andPhoneEqualTo("param");
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return EgouResult.build(400, "数据类型错误");
        }
        List<TbUser> tbUsers = userMapper.selectByExample(example);
        if (tbUsers != null && tbUsers.size() > 0) {
            return EgouResult.ok(false);
        }
        return EgouResult.ok(true);
    }

    @Override
    public EgouResult register(TbUser user) {
        if (StringUtils.isBlank((user.getUsername())) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getPhone())) {
            return EgouResult.build(400, "用户数据不完整,注册失败");
        }
        EgouResult result = checkDate(user.getUsername(), 1);
        if (!(Boolean) result.getData()) {
            return EgouResult.build(400, "此用户名已被注册");
        }

        result = checkDate(user.getPhone(), 3);
        if (!(Boolean) result.getData()) {
            return EgouResult.build(400, "此手机号已被注册");
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        String md5pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5pass);
        userMapper.insert(user);
        return EgouResult.ok();
    }


}
