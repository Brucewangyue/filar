package com.bruce.filar.service;

import com.bruce.filar.dao.ItemDao;
import com.bruce.filar.entity.Item;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemService {
    @Resource
    private ItemDao itemDao;

    public Item insert(Item item) {
        itemDao.insert(item);
        System.out.println(item.getId());
        return item;
    }

    public Item findById(int id) {
        return itemDao.selectByPrimaryKey(id);
    }
}
