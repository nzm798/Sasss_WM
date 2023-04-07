package com.ss.service;

import com.ss.bean.Goods;
import com.ss.exception.ServiceException;

import java.util.List;

/**
 * 商品服务接口
 */
public interface SystemService {
    public List<Goods> findGoodsByPage(int i)throws ServiceException;

    public Long findGoodsCount()throws ServiceException;

    public Long getTotalPage()throws ServiceException;
}
