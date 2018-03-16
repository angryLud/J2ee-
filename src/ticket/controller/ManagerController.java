package ticket.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.model.Manager;
import ticket.service.ManagerService;
import ticket.vo.HallFinanceVo;
import ticket.vo.MemberFinanceVo;
import ticket.vo.SystemFinanceVo;

@Controller
@RequestMapping(value = "manager")
public class ManagerController {
	@Resource
	ManagerService managerService;

	@RequestMapping(value = "hall_statistic")
	@ResponseBody
	public Message hallStatistic(HttpSession session) {
		Message message = null;
		Manager manager = (Manager) session.getAttribute("manager");
		message = managerService.getHallFinances();
		if (message.getResult()) {
			List<HallFinanceVo> list = (List<HallFinanceVo>) message.getObject();
			return new Message(true, list, "获取场馆统计成功");
		} else {
			return new Message(false, "获取场馆统计失败");
		}
	}

	@RequestMapping(value = "member_statistic")
	@ResponseBody
	public Message memberStatistic(HttpSession session) {
		Message message = null;
		Manager manager = (Manager) session.getAttribute("manager");
		message = managerService.getMemberFinances();
		if (message.getResult()) {
			List<MemberFinanceVo> list = (List<MemberFinanceVo>) message.getObject();
			return new Message(true, list, "获取会员统计成功");
		} else {
			return new Message(false, "获取会员统计失败");
		}
	}

	@RequestMapping(value = "system_finance")
	@ResponseBody
	public Message systemFinance(HttpSession session) {
		Message message = null;
		Manager manager = (Manager) session.getAttribute("manager");
		message = managerService.getSystemFinances();
		if (message.getResult()) {
			SystemFinanceVo vo = (SystemFinanceVo) message.getObject();
			return new Message(true, vo, "获取系统财务成功");
		} else {
			return new Message(false, "获取系统财务失败");
		}

	}

	@RequestMapping(value = "settle_order")
	@ResponseBody
	public Message settleOrders(HttpSession session, int hallNo) {
		Message message = null;
		message = managerService.settleOrders(hallNo);
		return message;

	}

	@RequestMapping(value = "approve_reg")
	@ResponseBody
	public Message approveReg(HttpSession session, int hallNo) {
		Message message = null;
		message = managerService.allowRegister(hallNo);
		return message;

	}

	@RequestMapping(value = "approve_update")
	@ResponseBody
	public Message approveUpdate(HttpSession session, int hallNo) {
		Message message = null;
		message = managerService.allowUpdate(hallNo);
		return message;

	}

	@RequestMapping(value = "deny_reg")
	@ResponseBody
	public Message denyReg(HttpSession session, int hallNo) {
		Message message = null;
		message = managerService.denyRegister(hallNo);
		return message;

	}

	@RequestMapping(value = "deny_update")
	@ResponseBody
	public Message denyUpdate(HttpSession session, int hallNo) {
		Message message = null;
		message = managerService.denyUpdate(hallNo);
		return message;

	}

}
