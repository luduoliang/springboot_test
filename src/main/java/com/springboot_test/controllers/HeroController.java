package com.springboot_test.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot_test.models.mappers.HeroMapper;
import com.springboot_test.models.Hero;
   
@Controller
public class HeroController {
    @Autowired HeroMapper heroMapper;
      
    @RequestMapping("/addHero")
    public String addHero(Hero h) throws Exception {
    	heroMapper.save(h);
        return "redirect:listHero";
    }
    @RequestMapping("/deleteHero")
    public String deleteHero(Hero h) throws Exception {
    	heroMapper.delete(h.getId());
        return "redirect:listHero";
    }
    @RequestMapping("/updateHero")
    public String updateHero(Hero h) throws Exception {
    	heroMapper.update(h);
        return "redirect:listHero";
    }
    @RequestMapping("/editHero")
    public String editHero(int id,Model m) throws Exception {
    	Hero h= heroMapper.get(id);
        m.addAttribute("h", h);
        return "hero/editHero";
    }
     
    @RequestMapping("/listHero")
    public String listHero(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"id desc");
        List<Hero> hs=heroMapper.findAll();
        PageInfo<Hero> page = new PageInfo<>(hs);
        m.addAttribute("page", page);        
        return "hero/listHero";
    }
     
}