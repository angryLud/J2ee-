package ticket.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ticket.config.Message;
import ticket.dao.HallDao;
import ticket.dao.MemberDao;
import ticket.dao.OrderDao;
import ticket.dao.RegistryDao;
import ticket.dao.UpdateDao;
import ticket.model.Hall;
import ticket.model.Member;
import ticket.model.Order;
import ticket.model.Update;
import ticket.vo.HallInfoVo;
import ticket.vo.HallListVo;
import ticket.vo.HallUpdateVo;
import ticket.vo.OrderTicketVo;
import ticket.vo.SettleOrderVo;

@Service
public class HallService {

	@Resource
	HallDao hallDao;
	@Resource
	MemberDao memberDao;
	@Resource
	OrderDao orderDao;
	@Resource
	RegistryDao registryDao;
	@Resource
	UpdateDao updateDao;
	@Resource
	MemberService memberService;
	
	//线下只能选座购买
	public Message buyTicket(OrderTicketVo vo) {
		Message hallMessage = hallDao.findHallById(vo.getHallNo());
		Message memberMessage = memberDao.findMemberByEmail(vo.getEmail());
		if (memberMessage.getResult()) {
			Hall hall = (Hall) hallMessage.getObject();
			List<Member> list = (List<Member>) memberMessage.getObject();
			Member member = list.get(0);
			Order order = new Order();
			order.setEmail(vo.getEmail());
			order.setOrderMethod(1);
			order.setToption(0);
			order.setTicketNum(vo.getTicketNum());
			order.setTicketType(vo.getTicketType());
			order.setTotalPrice(vo.getTotalPrice() * member.getDiscount());
			order.setOrderDate(vo.getOrderDate());
			order.setShowDate(vo.getShowDate());
			order.setPayState(1);
			order.setAllocState(1);
			// 允许退订
			order.setIsCancelled(1);
			order.setIsSettled(1);
			order.setHallNo(vo.getHallNo());
			order.setHallName(vo.getHallName());
			order.setShowType(vo.getShowType());
			//要结算以后才能增加场馆收入
//			hall.setIncome(hall.getIncome() + order.getTotalPrice());
			//要支付以后才能增加用户消费额
//			member.setConsumption(member.getConsumption() + order.getTotalPrice());
//			memberDao.update(member);
//			updateLevel(member.getEmail());

//			hallDao.updateHall(hall);
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

	public Message checkTicket(int orderid, String telephone) {
		Message orderMessage = orderDao.findOrderById(orderid);
		if (orderMessage.getResult()) {
			Order order = (Order) orderMessage.getObject();
			Message memberMessage = memberDao.findMemberByEmail(order.getEmail());

			List<Member> list = (List<Member>) memberMessage.getObject();
			Member member = list.get(0);
			if (member.getTelephone().equals(telephone)) {
				return new Message(true, "检票成功");
			} else {
				return new Message(false, "检票未通过");
			}
		} else {
			return new Message(false, "票务信息有误");
		}

	}

	public Message getHallInfo(int hallNo) {
		Message hallMessage = hallDao.findHallById(hallNo);
		if (hallMessage.getResult()) {
			Hall hall = (Hall) hallMessage.getObject();
			long totalOrder = (long) hallDao.getTotalOrderNum(hallNo).getObject();
			long onlineOrder = (long) hallDao.getOnlineNum(hallNo).getObject();
			long successOrder = (long) hallDao.getSuccessNum(hallNo).getObject();
			long cancelledOrder = (long) hallDao.getCancelledNum(hallNo).getObject();
			HallInfoVo vo = new HallInfoVo(hallNo, hall.getHallName(), totalOrder, onlineOrder, successOrder,
					cancelledOrder, hall.getIncome());
			return new Message(true, vo, "获取场馆信息成功");
		} else {
			return new Message(false, "获取场馆信息失败");
		}
	}

	// public Message registerHall(HallRegistryVo vo) {
	// Registry reg = new Registry();
	// reg.setPassword(vo.getPassword());
	// reg.setHallName(vo.getHallName());
	// reg.setAddress(vo.getAddress());
	// reg.setJuniorNum(vo.getJuniorNum());
	// reg.setSeniorNum(vo.getSeniorNum());
	// reg.setIncome(0.0);
	// reg.setPercent(vo.getPercent());
	// return registryDao.addReg(reg);
	// }

	public Message updateHall(HallUpdateVo vo) {
		Update up = new Update();
		up.setHallNo(vo.getHallNo());
		up.setPassword(vo.getPassword());
		up.setHallName(vo.getHallName());
		up.setAddress(vo.getAddress());
		up.setJuniorNum(vo.getJuniorNum());
		up.setSeniorNum(vo.getSeniorNum());
		up.setPercent(vo.getPercent());
		return updateDao.addUpdate(up);
	}

	public Message getEachUnsettledOrders() {
		Message hallMessage = hallDao.getAllHall();
		List<Hall> list = (List<Hall>) hallMessage.getObject();
		List<SettleOrderVo> result = new ArrayList<SettleOrderVo>();
		for (Hall hall : list) {
			int unsettledNum = 0;
			Message message = hallDao.getUnsettledNum(hall.getHallNo());
			unsettledNum = (int) message.getObject();
			if (unsettledNum > 0) {
				String hallName = hall.getHallName();
				SettleOrderVo vo = new SettleOrderVo(hall.getHallNo(), hallName, unsettledNum);
				result.add(vo);
			}
		}
		if (hallMessage.getResult()) {
			return new Message(true, result, "获取各场馆未结算订单成功！");
		} else {
			return new Message(false, "获取各场馆未结算订单失败！");
		}
	}

	public Message getAllHalls(String email) {
		Message message = hallDao.getAllHall();
		if (message.getResult()) {
			List<Hall> list = (List<Hall>) message.getObject();
			List<HallListVo> result = new ArrayList<HallListVo>();
			for (Hall hall : list) {
				HallListVo vo = new HallListVo(email, hall.getHallNo(), hall.getPassword(), hall.getHallName(),
						hall.getAddress(), hall.getJuniorNum(), hall.getSeniorNum(), hall.getIncome(),
						hall.getPercent());
				result.add(vo);
			}
			return new Message(true, result, "获取场馆列表成功");
		} else {
			return new Message(false, "获取场馆列表失败");
		}
	}
	
	public Message searchHall(int hallNo) {
		return hallDao.searchHall(hallNo);
	}

}
