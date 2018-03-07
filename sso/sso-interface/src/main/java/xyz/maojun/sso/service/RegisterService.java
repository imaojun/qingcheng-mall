package xyz.maojun.sso.service;

import xyz.maojun.common.util.EgouResult;
import xyz.maojun.pojo.TbUser;

public interface RegisterService {
    EgouResult checkDate(String param,int type);
    EgouResult register(TbUser user);
}
