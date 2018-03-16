package ticket.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.dao.OrderDao;
import ticket.model.Order;
import ticket.service.OrderService;
import ticket.vo.ViewOrderVo;

@RequestMapping(value = "order")
@Controller
public class OrderController {
	@Resource
	OrderDao orderDao;
	@Resource
	OrderService orderService;

	@RequestMapping(value = "info")
	@ResponseBody
	public Message getOrderInfo(HttpSession session, int orderid) {
		Message message = orderDao.findOrderById(orderid);
		if (message.getResult()) {
			Order order = (Order) message.getObject();
			ViewOrderVo vo = new ViewOrderVo(order);
			return new Message(true, vo, "获取订单信息成功");
		} else {
			return new Message(false, "获取订单信息失败");
		}
	}

	@RequestMapping(value = "search")
	@ResponseBody
	public Message searchOrder(int orderid) {
		Message message = orderService.searchOrder(orderid);
		if (message.getResult()) {
			List<Order> list = (List<Order>) message.getObject();
			return new Message(true, list, "查询成功");
		} else {
			return new Message(false, "查询失败");
		}

	}

	// @RequestMapping(value = "instantOrderTicket")
	// @ResponseBody
	// public Message instantTicket(HttpSession session, String email, int option,
	// int ticketNum, int ticketType,
	// double ticketPrice, String showDate, String showType) {
	// Message message = null;
	// return message;
	// }
}
