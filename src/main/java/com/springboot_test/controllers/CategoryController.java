package com.springboot_test.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot_test.models.mappers.CategoryMapper;
import com.springboot_test.models.Category;
   
@Controller
public class CategoryController {
    @Autowired CategoryMapper categoryMapper;
      
    @RequestMapping("/addCategory")
    public String addCategory(Category c) throws Exception {
        categoryMapper.save(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category c) throws Exception {
        categoryMapper.delete(c.getId());
        return "redirect:listCategory";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception {
        categoryMapper.update(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/editCategory")
    public String editCategory(int id,Model m) throws Exception {
        Category c= categoryMapper.get(id);
        m.addAttribute("c", c);
        return "category/editCategory";
    }
     
    @RequestMapping("/listCategory")
    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"id desc");
        List<Category> cs=categoryMapper.findAll();
        PageInfo<Category> page = new PageInfo<>(cs);
        m.addAttribute("page", page);        
        return "category/listCategory";
    }
     
}