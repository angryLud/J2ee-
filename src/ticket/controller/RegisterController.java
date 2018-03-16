package ticket.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.dao.MemberDao;
import ticket.model.Member;
import ticket.model.Registry;
import ticket.service.LogAndRegService;
import ticket.service.MemberService;
import ticket.vo.MemberRegistryVo;

@Controller
@RequestMapping(value = "register")
public class RegisterController {
	@Resource
	LogAndRegService logAndRegService;
	@Resource
	MemberService memberService;
	@Resource
	MemberDao memberDao;

	@RequestMapping(value = "member_reg")
	@ResponseBody
	public Message memberRegister(HttpSession session, String username, String password1, String password2,
			String telephone, String name, int sex) {
		Message message = null;
		if (password1.equals(password2)) {
			MemberRegistryVo vo = new MemberRegistryVo(username, password1, telephone, name, sex);
			message = logAndRegService.memberRegister(vo);
			return message;
		} else {
			return new Message(false, "两次输入的密码不一致");
		}
	}

	@RequestMapping(value = "hall_reg")
	@ResponseBody
	public Message hallRegister(HttpSession session, String password1, String password2, String hallName,
			String address, int juniorNum, int seniorNum, double percent) {
		Message message = null;
		Registry reg = new Registry();
		reg.setPassword(password1);
		reg.setHallName(hallName);
		reg.setAddress(address);
		reg.setJuniorNum(juniorNum);
		reg.setSeniorNum(seniorNum);
		reg.setPercent(percent);
		reg.setIncome(0.0);
		message = logAndRegService.hallRegister(reg);

		return message;
	}
	
	@RequestMapping(value = "check_reg")
	@ResponseBody
	public Message checkRegister(HttpSession session, String email,String token) {
		Message memberMessage = memberService.getMember(email);
		if(memberMessage.getResult()) {
			List<Member> list = (List<Member>)memberMessage.getObject();
			Member member = list.get(0);
			if(member.getState()==0&&member.getCheckCode().equals(token)) {
				member.setState(1);
//				member.setCheckCode(token.replace('1', 'c'));
				Message message = memberDao.update(member);
				if(message.getResult()) {
					return new Message(true,"会员验证成功");
				}else {
					return new Message(false,"会员验证失败");
				}
			}
			else {
				return new Message(false,"验证码有误");
			}
		}else {
			return memberMessage;
		}
	}

}
