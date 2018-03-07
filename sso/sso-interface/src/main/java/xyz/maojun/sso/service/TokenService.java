package xyz.maojun.sso.service;

import xyz.maojun.common.util.EgouResult;

public interface TokenService {
    EgouResult getUserByToken(String token);
}
