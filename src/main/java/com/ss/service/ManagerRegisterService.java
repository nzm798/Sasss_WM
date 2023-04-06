package com.ss.service;

import com.ss.bean.Managers;

/**
 * 管理员注册类接口
 */
public interface ManagerRegisterService {
    /**
     * 注册管理员
     * @param manager
     * @return
     */
    public int Register(Managers manager);
}
