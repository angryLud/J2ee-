package ticket.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.service.UpdateService;

@RequestMapping(value="update")
@Controller
public class UpdateController {
	
	@Resource
	UpdateService updateService;
	
	@RequestMapping(value="ups")
	@ResponseBody
	public Message getAllUps(HttpSession session) {
		return updateService.getAllUps();
	}
}
