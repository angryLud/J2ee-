package ticket.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ticket.config.Message;
import ticket.dao.MemberDao;
import ticket.dao.OrderDao;
import ticket.model.Member;
import ticket.model.Order;
import ticket.vo.MemberFinanceVo;
import ticket.vo.MemberRegistryVo;
import ticket.vo.MemberUpdateVo;
import ticket.vo.OrderTicketVo;
import ticket.vo.ViewOrderVo;

@Service
public class MemberService {
	@Resource
	MemberDao memberDao;
	@Resource
	OrderDao orderDao;

	public Message registerMember(MemberRegistryVo vo) {
		String email = vo.getEmail();
		Message message = memberDao.getAllMembers();
		if (message.getResult()) {
			List<Member> list = (List<Member>) message.getObject();
			boolean flag = true;
			for (Member member : list) {
				if (member.getEmail().equals(email)) {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				Member member = new Member();
				member.setEmail(vo.getEmail());
				member.setPassword(vo.getPassword());
				member.setTelephone(vo.getTelephone());
				member.setName(vo.getName());
				member.setSex(vo.getSex());
				member.setState(1);
				member.setBalance(0.0);
				member.setConsumption(0.0);
				member.setLevel(0);
				member.setPoint(0);
				member.setDiscount(1.0);
				return memberDao.register(member);
			} else {
				return new Message(false, "该邮箱已被使用");
			}
		} else {
			return new Message(false, "注册用户失败");
		}
	}

	public Message stopMember(String email) {
		Message message = memberDao.findMemberByEmail(email);
		if (message.getResult()) {
			Member member = (Member) message.getObject();
			member.setState(0);
			return memberDao.update(member);
		} else {
			return new Message(false, "停止会员资格失败");
		}
	}

	// 完成支付之后才能提升等级
	public Message updateLevel(String email) {
		Message message = memberDao.findMemberByEmail(email);
		if (message.getResult()) {
			Member member = (Member) message.getObject();
			double con = member.getConsumption();
			if (con < 100.0) {
				member.setLevel(0);
				member.setDiscount(1.0);
			} else if (con < 500.0) {
				member.setLevel(1);
				member.setDiscount(0.95);
			} else if (con < 1000.0) {
				member.setLevel(2);
				member.setDiscount(0.9);
			} else if (con < 2000.0) {
				member.setLevel(3);
				member.setDiscount(0.85);
			} else {
				member.setLevel(4);
				member.setDiscount(0.8);
			}
			return memberDao.update(member);
		} else {
			return new Message(false, "用户信息有误");
		}
	}

	// 支付完成之后才能增加积分
	public Message updatePoint(String email) {
		Message message = memberDao.findMemberByEmail(email);
		if (message.getResult()) {
			Member member = (Member) message.getObject();
			double con = member.getConsumption();
			int point = (int) con;
			member.setPoint(member.getPoint() + point);
			return memberDao.update(member);
		} else {
			return new Message(false, "用户信息有误");
		}
	}

	public Message updateMemberInfo(MemberUpdateVo vo) {
		Message message = memberDao.findMemberByEmail(vo.getEmail());
		if (message.getResult()) {
			Member member = (Member) message.getObject();
			member.setPassword(vo.getPassword());
			member.setTelephone(vo.getTelephone());
			member.setName(vo.getName());
			member.setSex(vo.getSex());
			return memberDao.update(member);
		} else {
			return new Message(false, "用户信息更新失败");
		}
	}

	public Message viewMemberOrderStatus(String email) {
		Message message = orderDao.findOrderBySingle("email", email);
		if (message.getResult()) {
			List<Order> list = (List<Order>) message.getObject();
			List<ViewOrderVo> result = new ArrayList<ViewOrderVo>();
			for (Order order : list) {
				ViewOrderVo vo = new ViewOrderVo(order.getOrderid(), order.getOrderMethod(), order.getOption(),
						order.getTicketNum(), order.getTotalPrice(), order.getOrderDate(), order.getShowDate(),
						order.getPayState(), order.getAllocState(), order.getHallNo(), order.getHallName(),
						order.getShowType());
				result.add(vo);
			}
			return new Message(true, result, "获取用户（所有）订单信息成功");
		} else {
			return new Message(false, "用户信息有误");
		}
	}

	public Message getMemberInfo(String email) {
		Message message = memberDao.findMemberByEmail(email);
		if (message.getResult()) {
			Member member = (Member) message.getObject();
			long totalNum = (long) memberDao.getOnlineNum(member.getEmail()).getObject();
			long paidNum = (long) memberDao.getPaidNum(member.getEmail()).getObject();
			long cancelledNum = (long) memberDao.getCancelledNum(member.getEmail()).getObject();
			MemberFinanceVo vo = new MemberFinanceVo(member.getEmail(), totalNum, paidNum, cancelledNum,
					member.getConsumption());
			return new Message(true, vo, "获取用户统计信息成功");
		} else {
			return new Message(false, "用户信息有误");
		}
	}

}