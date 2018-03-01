package ticket.service;

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
import ticket.model.Registry;
import ticket.model.Update;
import ticket.vo.HallInfoVo;
import ticket.vo.HallRegistryVo;
import ticket.vo.HallUpdateVo;
import ticket.vo.OrderTicketVo;

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

	public Message buyTicket(OrderTicketVo vo) {
		Message hallMessage = hallDao.findHallById(vo.getHallNo());
		Message memberMessage = memberDao.findMemberByEmail(vo.getEmail());
		if (memberMessage.getResult()) {
			Hall hall = (Hall) hallMessage.getObject();
			Member member = (Member) memberMessage.getObject();
			Order order = new Order();
			order.setEmail(vo.getEmail());
			order.setOrderMethod(1);
			order.setOption(0);
			order.setTicketNum(vo.getTicketNum());
			order.setTotalPrice(vo.getTotalPrice() * member.getDiscount());
			order.setOrderDate(vo.getOrderDate());
			order.setShowDate(vo.getShowDate());
			order.setPayState(1);
			order.setAllocState(1);
			//允许退订
			order.setIsCancelled(1);
			order.setIsSettled(1);
			order.setHallNo(vo.getHallNo());
			order.setHallName(vo.getHallName());
			order.setShowType(vo.getShowType());
			hall.setIncome(hall.getIncome() + order.getTotalPrice());
			member.setConsumption(member.getConsumption() + order.getTotalPrice());
			memberDao.update(member);
			memberService.updateLevel(member.getEmail());
			
			hallDao.updateHall(hall);
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

	public Message checkTicket(Order order, String telephone) {
		Message memberMessage = memberDao.findMemberByEmail(order.getEmail());
		if (memberMessage.getResult()) {
			Member member = (Member) memberMessage.getObject();
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

	public Message registerHall(HallRegistryVo vo) {
		Registry reg = new Registry();
		reg.setPassword(vo.getPassword());
		reg.setHallName(vo.getHallName());
		reg.setAddress(vo.getAddress());
		reg.setJuniorNum(vo.getJuniorNum());
		reg.setSeniorNum(vo.getSeniorNum());
		reg.setIncome(vo.getIncome() == 0.0 ? vo.getIncome() : 0.0);
		reg.setPercent(vo.getPercent());
		return registryDao.addReg(reg);
	}

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

}
