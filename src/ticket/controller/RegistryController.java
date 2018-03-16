package ticket.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.service.RegistryService;

@Controller
@RequestMapping(value="registry")
public class RegistryController {
	@Resource
	RegistryService registryService;
	
	@RequestMapping(value="regs")
	@ResponseBody
	public Message getRegs(HttpSession session) {
		Message message = registryService.getAllRegs();
		return message;
	}
}
