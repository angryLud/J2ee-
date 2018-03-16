package ticket.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ticket.config.Message;
import ticket.dao.MemberDao;
import ticket.dao.OrderDao;
import ticket.dao.PayAccountDao;
import ticket.dao.PlanDao;
import ticket.model.Member;
import ticket.model.Order;
import ticket.model.PayAccount;

@Service
public class OrderService {

	@Resource
	MemberDao memberDao;
	@Resource
	OrderDao orderDao;
	@Resource
	MemberService memberService;
	@Resource
	PlanDao planDao;
	@Resource
	PayAccountDao payAccountDao;

	

	public Message cancelOrder(int orderNo) {
		Message message = orderDao.findOrderById(orderNo);
		if (message.getResult()) {
			Order order = (Order) message.getObject();
			Message memberMessage = memberDao.findMemberByEmail(order.getEmail());
			List<Member> list = (List<Member>) memberMessage.getObject();
			Member member = list.get(0);
			Message accountMessage = payAccountDao.findByEmail(order.getEmail());
			List<PayAccount> list2 = (List<PayAccount>) accountMessage.getObject();
			PayAccount account = list2.get(0);
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
				account.setBalance(account.getBalance() + order.getTotalPrice() * 0.5);
			} else if (pastHour < 6) {
				account.setBalance(account.getBalance() + order.getTotalPrice() * 0.6);
			} else if (pastHour < 12) {
				account.setBalance(account.getBalance() + order.getTotalPrice() * 0.7);
			} else if (pastHour < 24) {
				account.setBalance(account.getBalance() + order.getTotalPrice() * 0.8);
			} else if (pastHour < 48) {
				account.setBalance(account.getBalance() + order.getTotalPrice() * 0.9);
			} else {
				account.setBalance(account.getBalance() + order.getTotalPrice());
			}
			member.setPoint((int) (member.getPoint() - order.getTotalPrice() * 10 + order.getMinused() * 100));
			member.setConsumption(member.getConsumption() - order.getTotalPrice());
			order.setIsCancelled(0);

			Message mes1 = memberDao.update(member);
			Message mes2 = orderDao.update(order);
			Message mes3 = payAccountDao.update(account);
			if (mes3.getResult() && mes2.getResult() && mes1.getResult()) {
				memberService.updateLevel(order.getEmail());
				return new Message(true, "退订成功");
			} else {
				return new Message(false, "退订失败");
			}

		} else {
			return new Message(false, "订单信息有误");
		}
	}

	public Message searchOrder(int orderid) {
		return orderDao.searchOrder(orderid);
	}
	

}
