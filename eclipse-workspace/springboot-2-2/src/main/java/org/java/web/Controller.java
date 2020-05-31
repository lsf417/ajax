package org.java.web;

import org.java.entity.Inf;
import org.java.service.InfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	private InfService infService;
	
	@RequestMapping("/add")
	public String add(Inf f,Model model) {
		infService.add(f);
		return "/show";
	}
	@RequestMapping("/show")
	public String getList(Model model) {
		
		model.addAttribute("list", infService.getList());
		return "/list";
	}
}
