package com.ss.service;

import com.ss.bean.Goods;
import com.ss.bean.Stock;
import com.ss.exception.ServiceException;

import java.util.List;

public interface StockService {
    public int stockAdd(Stock stock, Goods goods) throws ServiceException;

    public List<Stock> findAllStock() throws ServiceException;

    public List<Stock> findStockByPage(int pageNum) throws ServiceException;

    public Long findStockCount() throws ServiceException;

    public Long getTotalPage() throws ServiceException;

    public int updateStock(Long count, Long goodsId) throws ServiceException;

    public Stock findStockByGoodsId(Long goodsId) throws ServiceException;

    public int updateStockAreaByGoodsId(String area, Long goodsId) throws ServiceException;

    public Goods saveGoods(Goods goods) throws ServiceException;

    public Long findGoodsIdByGoodsName(String name) throws ServiceException;

    public int stockAdd(Stock stock) throws ServiceException;

    public int updateStockCount(String name, Long count) throws ServiceException;
    public int updateStockCountAfterSale(String name,Long count) throws ServiceException;
}
