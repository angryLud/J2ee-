package ticket.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import ticket.config.Message;
import ticket.dao.MemberDao;
import ticket.dao.OrderDao;
import ticket.model.Member;
import ticket.model.Order;
import ticket.vo.OrderTicketVo;

import ticket.utils.*;

public class OrderService {

	@Resource
	MemberDao memberDao;
	@Resource
	OrderDao orderDao;
	@Resource
	MemberService memberService;

	// 很多细节需要实现，暂时只是大体操作
	public Message orderTicket(OrderTicketVo vo) {
		Message message = memberDao.findMemberByEmail(vo.getEmail());
		if (message.getResult()) {
			Member member = (Member) message.getObject();
			Order order = new Order();
			order.setEmail(vo.getEmail());
			order.setOrderMethod(0);
			order.setOption(vo.getOption());
			order.setTicketNum(vo.getTicketNum());
			order.setTotalPrice(vo.getTotalPrice() * member.getDiscount());
			order.setOrderDate(vo.getOrderDate());
			order.setShowDate(vo.getShowDate());
			order.setPayState(0);
			order.setAllocState(order.getOption() == 0 ? 1 : 0);
			order.setIsCancelled(1);
			order.setIsSettled(0);
			order.setHallNo(vo.getHallNo());
			order.setHallName(vo.getHallName());
			order.setShowType(vo.getShowType());

			// 支付完成之后才能增加消费额和提升会员等级，暂时放在这里
			member.setConsumption(member.getConsumption() + order.getTotalPrice());
			memberDao.update(member);
			memberService.updateLevel(member.getEmail());

			Message result = orderDao.makeOrder(order);
			if (result.getResult()) {
				return new Message(true, "预订成功");
			} else {
				return new Message(false, "预订失败");
			}
		} else {
			return new Message(false, "用户信息有误");
		}
	}

	public Message cancelOrder(int orderNo) {
		Message message = orderDao.findOrderById(orderNo);
		if (message.getResult()) {
			Order order = (Order) message.getObject();
			Message memberMessage = memberDao.findMemberByEmail(order.getEmail());
			Member member = (Member) memberMessage.getObject();
			String showdate = order.getShowDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date showDate = null;
			try {
				showDate = dateFormat.parse(showdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date nowDate = new Date();
			long timetemp = showDate.getTime() - nowDate.getTime();
			long pastHour = timetemp / (60 * 60 * 1000);
			if (pastHour < 0) {
				return new Message(false, "演出已开始，无法退票");
			} else if (pastHour < 3) {
				member.setBalance(member.getBalance() + order.getTotalPrice() * 0.5);
			} else if (pastHour < 6) {
				member.setBalance(member.getBalance() + order.getTotalPrice() * 0.6);
			} else if (pastHour < 12) {
				member.setBalance(member.getBalance() + order.getTotalPrice() * 0.7);
			} else if (pastHour < 24) {
				member.setBalance(member.getBalance() + order.getTotalPrice() * 0.8);
			} else if (pastHour < 48) {
				member.setBalance(member.getBalance() + order.getTotalPrice() * 0.9);
			} else {
				member.setBalance(member.getBalance() + order.getTotalPrice());
			}
			order.setIsCancelled(0);

			Message mes1 = memberDao.update(member);
			Message mes2 = orderDao.update(order);
			if (mes1.getResult() && mes2.getResult()) {
				return new Message(true, "退订成功");
			} else {
				return new Message(false, "退订失败");
			}

		} else {
			return new Message(false, "订单信息有误");
		}
	}

	// 订单支付的方法
	public Message payOrder(int orderNo) {

	}

	// 订单配票的方法
	public Message allocOrder(int orderNo) {
		Message orderMessage = orderDao.findOrderById(orderNo);
		if (orderMessage.getResult()) {
			Order order = (Order) orderMessage.getObject();
			String showdate = order.getShowDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date showDate = null;
			try {
				showDate = dateFormat.parse(showdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date nowDate = new Date();
			long timetemp = showDate.getTime() - nowDate.getTime();
			long pastHour = timetemp / (60 * 60 * 10000);
			

		} else {
			return new Message(false, "订单信息有误");
		}
	}

}
