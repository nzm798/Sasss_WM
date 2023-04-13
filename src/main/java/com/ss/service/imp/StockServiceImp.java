package com.ss.service.imp;

import com.ss.bean.Goods;
import com.ss.bean.Message;
import com.ss.bean.Stock;
import com.ss.dao.GoodsRepository;
import com.ss.dao.MessageRepository;
import com.ss.dao.StockRepository;
import com.ss.exception.ServiceException;
import com.ss.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class StockServiceImp implements StockService {
    @Resource
    private StockRepository stockRepository;
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private Message message;
    public int isCount(){
        List<Stock> gods=stockRepository.findAllGoodsCount();
        for (Stock stock:gods){
            if (stock.getCounts()<100) {
                String name = goodsRepository.findGoodsNameById(stock.getGoodsId());
                message.setDate(new Date());
                message.setFlag("未查看");
                message.setMsg(name+"库存不足100，现在剩余"+stock.getCounts()+"件！！！");
                messageRepository.save(message);
            }
        }
        return 1;
    }
    @Override
    public int stockAdd(Stock stock, Goods goods) throws ServiceException {
        Goods gods=goodsRepository.findGoodsByName(goods.getName());
        if (gods==null){
            gods=goodsRepository.save(goods);
        }
        stock.setGoodsId(gods.getId());
        Stock stock1=stockRepository.findStockByGoodsId(gods.getId());
        //判断库存
        isCount();
        if (stock1==null){
            stockRepository.save(stock);
        }else {
            stockRepository.updateStockCountByGoodsId(stock1.getCounts()+stock.getCounts(),stock.getGoodsId());
        }
        return 1;
    }

    @Override
    public List<Stock> findAllStock() throws ServiceException {
        isCount();
        List<Stock> list=stockRepository.findAll();
        return list;
    }

    @Override
    public List<Stock> findStockByPage(int pageNum) throws ServiceException {
        Pageable pageable= PageRequest.of(pageNum,10);
        Page<Stock> page=stockRepository.findAll(pageable);
        List<Stock> list=new ArrayList<>();
        for (Stock stock:page){
            list.add(stock);
        }
        return list;
    }

    @Override
    public Long findStockCount() throws ServiceException {
        Long count=stockRepository.count();
        return count;
    }

    @Override
    public Long getTotalPage() throws ServiceException {
        Long count = findStockCount();
        Long totalPage;
        if(count%10==0){
            totalPage = count/10;
        }else{
            totalPage = count/10 +1;
        }
        return totalPage;
    }

    /**
     * 有问题
     * @param count
     * @param goodsId
     * @return
     * @throws ServiceException
     */
    @Override
    public int updateStock(Long count, Long goodsId) throws ServiceException {
        Long counts=stockRepository.findCountByGoodsId(goodsId);
        isCount();
        stockRepository.updateStockCountByGoodsId(counts-count,goodsId);
        return 0;
    }

    @Override
    public Stock findStockByGoodsId(Long goodsId) throws ServiceException {
        isCount();
        return stockRepository.findStockByGoodsId(goodsId);
    }

    @Override
    public int updateStockAreaByGoodsId(String area, Long goodsId) throws ServiceException {
        isCount();
        stockRepository.updateStockAreaByGoodsId(area,goodsId);
        return 1;
    }

    @Override
    public Goods saveGoods(Goods goods) throws ServiceException {
        Goods goods1=goodsRepository.findGoodsByName(goods.getName());
        if (goods1==null){
            return goodsRepository.save(goods);
        }
        return goods1;
    }

    @Override
    public Long findGoodsIdByGoodsName(String name) throws ServiceException {
        return goodsRepository.findGoodsIdByGoodsName(name);
    }

    @Override
    public int stockAdd(Stock stock) throws ServiceException {
        Stock stock1=findStockByGoodsId(stock.getGoodsId());
        isCount();
        if (stock1==null){
            stockRepository.save(stock);
        }else {
            stockRepository.updateStockCountByGoodsId(stock1.getCounts()+stock.getCounts(),stock.getGoodsId());
        }
        return 1;
    }

    @Override
    public int updateStockCount(String name, Long count) throws ServiceException {
        Goods goods=goodsRepository.findGoodsByName(name);
        isCount();
        stockRepository.updateStockCount(count,goods.getId());
        return 1;
    }

    @Override
    public int updateStockCountAfterSale(String name, Long count) throws ServiceException {
        Goods goods=goodsRepository.findGoodsByName(name);
        Long counts=stockRepository.findCountByGoodsId(goods.getId());
        isCount();
        stockRepository.updateStockCount(counts-count,goods.getId());
        return 1;
    }
}
