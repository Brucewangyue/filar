package com.bruce.filar.controller;

import com.bruce.filar.entity.Item;
import com.bruce.filar.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class HomeController {
    @Resource
    private ItemService itemService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("addItem")
    public String addItem(){
        return "addItem";
    }

    @PostMapping("addItem")
    public String postAddItem(Model model,String title,String content){
        Item item = new Item();
        item.setTitle(title);
        item.setContent(content);
        itemService.insert(item);
        model.addAttribute("msg","添加项目成功");
        model.addAttribute("id",item.getId());
        return "addItem";
    }

    @GetMapping("itemView")
    public String itemView(Model model,int id){
        Item item = itemService.findById(id);
        model.addAttribute("item",item);
        return "itemView";
    }
}
