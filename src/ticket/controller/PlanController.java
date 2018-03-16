package ticket.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.model.Member;
import ticket.model.Plan;
import ticket.service.PlanService;
import ticket.vo.OrderTicketVo;
import ticket.vo.UserOrderVo;

@RequestMapping(value = "plan")
@Controller
public class PlanController {

	@Resource
	PlanService planService;

	@RequestMapping(value = "info")
	@ResponseBody
	public Message getPlanInfo(HttpSession session, int planNo) {
		Message message = planService.getPlanInfo(planNo);
		return message;
	}

	@RequestMapping(value = "infoforuser")
	@ResponseBody
	public Message getPlanInfoForUser(HttpSession session, int planNo) {
		Message message = planService.getPlanInfo(planNo);
		Member member = (Member) session.getAttribute("member");
		if (message.getResult()) {
			Plan plan = (Plan) message.getObject();
			UserOrderVo vo = new UserOrderVo(member.getEmail(),member.getPoint(),plan);
			return new Message(true, vo, "获取计划信息成功");
		} else {
			return new Message(false, "获取计划信息失败");
		}
	}

}
