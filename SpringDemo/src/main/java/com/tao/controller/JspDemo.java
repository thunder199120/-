package com.tao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tao.enity.Student;

@Controller
public class JspDemo {
	@RequestMapping("/show")
	public String showUser(Model model){
		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "张三", 18,"北京"));
		list.add(new Student(2, "李四", 19,"上海"));
		list.add(new Student(3, "王五", 20,"广州"));
		list.add(new Student(4, "赵六", 21,"深圳"));
		model.addAttribute("list", list);
		return "index";
	}
}
