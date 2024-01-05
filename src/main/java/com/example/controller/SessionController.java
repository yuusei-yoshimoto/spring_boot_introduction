package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionController {

	private HttpSession session;

	@Autowired
	public SessionController(HttpSession session) {
		this.session = session;
	}

	@GetMapping("/set")
	@ResponseBody
	public String set(@RequestParam("name") String name, @RequestParam("bloodType") String bloodType) {
		this.session.setAttribute("name", name);
		this.session.setAttribute("bloodType", bloodType);
		return "保存しました";
	}

	@GetMapping("/get")
	@ResponseBody
	public String get() {
		String name = (String) this.session.getAttribute("name");
		String bloodType = (String) this.session.getAttribute("bloodType");
		if (name == null) {
			name = "名無し";
		}
		if (bloodType == null) {
			bloodType = "不明";
		}
		return "名前: " + name + "<br>血液型: " + bloodType;
	}

	@GetMapping("/delete")
	@ResponseBody
	public String delete() {
		this.session.removeAttribute("name");
		this.session.removeAttribute("bloodType");
		return "削除しました";
	}

}
