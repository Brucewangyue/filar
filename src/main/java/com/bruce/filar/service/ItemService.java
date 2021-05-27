package com.bruce.filar.service;

import com.bruce.filar.dao.ItemDao;
import com.bruce.filar.entity.Item;
import com.bruce.filar.entity.ItemExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ItemService {
    @Resource
    private ItemDao itemDao;

    public Item insert(Item item) {
        itemDao.insert(item);
        System.out.println(item.getId());
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return item;
    }

    public Item findById(int id) {
        return itemDao.selectByPrimaryKey(id);
    }

    public List<Item> findByPage() {
        ItemExample itemExample = new ItemExample();
        return itemDao.selectByExample(itemExample);
    }

    public boolean update(Item item) {
        return itemDao.updateByPrimaryKey(item) > 0;
    }
}
