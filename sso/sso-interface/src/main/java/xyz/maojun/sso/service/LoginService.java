package xyz.maojun.sso.service;

import xyz.maojun.common.util.EgouResult;

public interface LoginService {
    EgouResult userLogin(String username, String password);
}
