package com.bruce.filar.controller;

import com.bruce.filar.entity.Item;
import com.bruce.filar.service.ItemService;
import com.jfinal.kit.Kv;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Resource
    private ItemService itemService;

    @Value("${static-file-location}")
    private String staticFileLocation;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("addItem")
    public String addItem() {
        return "addItem";
    }

    @PostMapping("addItem")
    public String postAddItem(Model model, String title, String content) {
        Item item = new Item();
        item.setTitle(title);
        item.setContent(content);
        itemService.insert(item);
        model.addAttribute("msg", "添加项目成功");
        model.addAttribute("id", item.getId());
        return "addItem";
    }

    @GetMapping("itemView")
    public String itemView(Model model, int id) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "itemView";
    }

    @GetMapping("itemList")
    public String itemList(Model model) {
        List<Item> items = itemService.findByPage();
        model.addAttribute("items", items);
        return "itemList";
    }

    @GetMapping("generateStaticFile")
    public String generateStaticFile(Model model, int id) {
        Item item = itemService.findById(id);
        item.setLastGenerate(new Date());
        itemService.update(item);

        Engine engine = new Engine();
        engine.setDevMode(true);
        engine.setToClassPathSourceFactory();

//        String filePath = HomeController.class.getClassLoader().getResource("templates/itemView.html").getFile();
        Template template = engine.getTemplate("templates/itemView.html");
        Kv kv = Kv.by("item", item);

        // todo io 用法
        String staticFilePath = staticFileLocation + "item" + id + ".html";
        template.render(kv, staticFilePath);
        model.addAttribute("msg", "成功生成静态文件");
        return "success";
    }

    @GetMapping("staticFileView")
    public String staticFileView(int id) {
        return "item" + id;
    }
}
