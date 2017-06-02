package com.ijiangtao.javastudydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
public class IndexController {
	
	
	@RequestMapping("/user/{name}")
	public String index(@PathVariable("name") String name,Model model){
		
		model.addAttribute("username",name);
		
		return "login";
	}
	
	@RequestMapping("/user/get/{name}")
	@ResponseBody
	public String name(@PathVariable("name") String name){
		
		//TODO 如何支持中文
		
		return "{\"name\":"+name+"\"}";
	}
	
}
