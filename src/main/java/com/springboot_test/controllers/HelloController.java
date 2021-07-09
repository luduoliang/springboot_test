package com.springboot_test.controllers;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.springboot_test.models.Hero;
import com.springboot_test.models.mappers.HeroMapper;
import com.springboot_test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import com.springboot_test.configs.PassToken;
import com.springboot_test.utils.JwtUtils;
import com.springboot_test.utils.ResponseUtils;

@RestController
public class HelloController {
	@Autowired HeroMapper heroMapper;
	
	@Autowired
    private RedisUtil redisUtil;
	
	
	//读取配置文件
	@Value("${demo.name}")
    private String name;
	
	//读取配置文件
    @Value("${demo.age}")
    private int age;

	
	@RequestMapping("/hello")
	public String hello() throws Exception {
		/*if(true){
            throw new Exception("some exception");
        }*/
		return "Hello World22";
	}
	
	@RequestMapping("/token")
	@PassToken
    public String token(Model m,@RequestParam(value = "user_id", defaultValue = "0") String user_id) {
		return JwtUtils.createToken(user_id);
    }
	
	@RequestMapping("/api/heros")
	@PassToken
    public HashMap<String, Object> heros(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
		List<Hero> hs;
		PageHelper.startPage(start,size,"id desc");
        hs=heroMapper.findAll();
        
        //错误返回
        //return ResponseUtils.fail("参数错误");
        
        //使用redis缓存
		/*String cacheKey = "heros" + ((Integer)start).toString();
		System.out.println(name);
		System.out.println(age);
		if(redisUtil.hasKey(cacheKey)) {
			hs = (List<Hero>)redisUtil.get(cacheKey);
			System.out.println("2222");
		}else {
			PageHelper.startPage(start,size,"id desc");
	        hs=heroMapper.findAll();
	        redisUtil.set(cacheKey, hs);
	        System.out.println("1111");
		}*/

        return ResponseUtils.success(hs);
    }
}
