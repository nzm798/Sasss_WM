package com.ss.service.imp;

import com.ss.bean.Managers;
import com.ss.dao.ManagerRepository;
import com.ss.service.ManagerRegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员注册服务
 */
@Service
public class ManagerRegisterImp implements ManagerRegisterService {
    @Resource //默认按名字注入实例
    private ManagerRepository managerRepository;

    /**
     * 管理员注册
     * @param manager 管理员信息
     * @return
     */
    @Override
    public int Register(Managers manager) {
        managerRepository.save(manager);
        return 0;
    }
}
