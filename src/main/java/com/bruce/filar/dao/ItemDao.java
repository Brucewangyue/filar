package com.bruce.filar.dao;

import com.bruce.filar.entity.Item;
import com.bruce.filar.entity.ItemExample;
import org.springframework.stereotype.Repository;

/**
 * ItemDao继承基类
 */
@Repository
public interface ItemDao extends MyBatisBaseDao<Item, Integer, ItemExample> {
}