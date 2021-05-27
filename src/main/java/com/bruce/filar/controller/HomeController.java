package com.bruce.filar.controller;

import com.bruce.filar.entity.Item;
import com.bruce.filar.service.ItemService;
import com.jfinal.kit.Kv;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.io.*;
import java.net.InetAddress;
import java.util.*;

@Controller
public class HomeController {
    @Resource
    private ItemService itemService;

    @Value("${staticFile.nginxRootPath}")
    private String staticFileLocation;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("addItem")
    public String addItem() {
        return "itemAdd";
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

        // 全局引擎
        Engine engine = JFinalViewResolver.engine;
        Template template = engine.getTemplate("itemView.html");
        Kv kv = Kv.by("item", item);

        // todo io 用法
        String staticFilePath = staticFileLocation + "item" + id + ".html";
        template.render(kv, staticFilePath);
        model.addAttribute("msg", "成功生成静态文件");
        return "success";
    }

    @GetMapping("templateList")
    public String staticFileView(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", "商品item模板");
        map.put("id", 1);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);

        model.addAttribute("templates", list);
        return "templateList";
    }

    @GetMapping("editTemplate")
    public String editTemplate(Model model) throws IOException {
        model.addAttribute("msg", "成功编辑模板");

        String filePath = HomeController.class.getClassLoader().getResource("templates/itemView.html").getFile();
        File templateFile = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(templateFile)));
        StringBuilder sb = new StringBuilder();

        String newLine = bufferedReader.readLine();
        while (null != newLine) {
            sb.append(newLine).append("\r\n");

            newLine = bufferedReader.readLine();
        }
        bufferedReader.close();

        Map<String, Object> map = new HashMap<>();
        map.put("content", sb.toString());
        map.put("title", "商品item模板");
        model.addAttribute("template", map);

        System.out.println(sb.toString());
        return "templateEdit";
    }

    @GetMapping("generateAllStaticFile")
    public String generateAllStaticFile(Model model) {

        return "success";
    }

    @GetMapping("health")
    public String health(Model model) throws IOException {

        Map<String, Object> hostMap = new HashMap<>();
        hostMap.put("192.168.177.131", false);
        hostMap.put("192.168.177.132", false);
        hostMap.put("192.168.177.133", false);

        for (Map.Entry<String, Object> entry : hostMap.entrySet()) {
            InetAddress inetAddress = InetAddress.getByName(entry.getKey());
            if (inetAddress.isReachable(1500))
                entry.setValue(true);
        }

        model.addAttribute("items", hostMap);
        return "health";
    }
}
