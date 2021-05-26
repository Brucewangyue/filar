package com.bruce.filar.service;

import com.bruce.filar.dao.ItemDao;
import com.bruce.filar.entity.Item;
import com.bruce.filar.entity.ItemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public List<Item> findByPage() {
        ItemExample itemExample = new ItemExample();
        return itemDao.selectByExample(itemExample);
    }

    public boolean update(Item item) {
        return itemDao.updateByPrimaryKey(item) > 0;
    }
}
