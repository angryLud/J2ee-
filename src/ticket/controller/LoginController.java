package ticket.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.model.Hall;
import ticket.model.Manager;
import ticket.model.Member;
import ticket.service.LogAndRegService;

@Controller
@RequestMapping("login")
public class LoginController {

	@Resource
	LogAndRegService logAndRegService;

	@RequestMapping(value = "user_login")
	@ResponseBody
	public Message userLogin(HttpSession session, String username, String password) {

		Message message = null;

		message = logAndRegService.memberLogin(username, password);

		if (message.getResult() == true) {
			Member member = (Member) message.getObject();
			if (member.getState() == 1) {
				session.setAttribute("member", member);
				return message;
			}else {
				return new Message(false,"该账户尚未激活");
			}
		}else {
			return new Message(false,"数据库访问失败");
		}

		
	}

	@RequestMapping(value = "hall_login")
	@ResponseBody
	public Message hallLogin(HttpSession session, int hallNo, String password) {
		Message message = null;

		message = logAndRegService.hallLogin(hallNo, password);

		Hall hall = (Hall) message.getObject();
		if (message.getResult() == true) {
			session.setAttribute("hall", hall);
		}

		return message;
	}

	@RequestMapping(value = "manager_login")
	@ResponseBody
	public Message managerLogin(HttpSession session, int managerNo, String password) {
		Message message = null;
		System.out.println(managerNo);
		message = logAndRegService.managerLogin(managerNo, password);

		Manager manager = (Manager) message.getObject();
		if (message.getResult() == true) {
			session.setAttribute("manager", manager);
		}

		return message;
	}

}
