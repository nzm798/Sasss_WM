package com.ss.service.imp;

import com.ss.bean.Goods;
import com.ss.dao.GoodsRepository;
import com.ss.exception.ServiceException;
import com.ss.service.SystemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品服务实现类
 */
@Service
public class SystemServiceImp implements SystemService {
    @Resource
    private GoodsRepository goodsRepository;

    @Override
    public List<Goods> findGoodsByPage(int i) throws ServiceException {
        Pageable pageable = PageRequest.of(i, 10);
        Page<Goods> page = goodsRepository.findAll(pageable);
        List<Goods> list = new ArrayList<Goods>();
        for (Goods s : page) {
            list.add(s);
        }
        return list;
    }

    @Override
    public Long findGoodsCount() throws ServiceException {
        return goodsRepository.count();
    }

    @Override
    public Long getTotalPage() throws ServiceException {
        Long count = findGoodsCount();
        Long totalPage;
        if (count % 10 == 0) {
            totalPage = count / 10;
        } else {
            totalPage = count / 10 + 1;
        }
        return totalPage;
    }
}
