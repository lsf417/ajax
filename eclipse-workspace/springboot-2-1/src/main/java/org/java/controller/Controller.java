package org.java.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@org.springframework.stereotype.Controller
public class Controller {
	
	@RequestMapping("/add")
	public String add(Integer x,Integer y,Model model) {
		int sum=x+y;
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		model.addAttribute("sum", sum);
		
		return "/show";
	}
}
