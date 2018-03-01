package ticket.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ticket.config.Message;
import ticket.dao.HallDao;
import ticket.dao.ManagerDao;
import ticket.dao.MemberDao;
import ticket.dao.OrderDao;
import ticket.dao.RegistryDao;
import ticket.dao.UpdateDao;
import ticket.model.Hall;
import ticket.model.Manager;
import ticket.model.Member;
import ticket.model.Order;
import ticket.model.Registry;
import ticket.model.Update;
import ticket.vo.HallFinanceVo;
import ticket.vo.MemberFinanceVo;
import ticket.vo.SystemFinanceVo;

@Service
public class ManagerService {

	@Resource
	HallDao hallDao;
	@Resource
	OrderDao orderDao;
	@Resource
	MemberDao memberDao;
	@Resource
	RegistryDao registryDao;
	@Resource
	UpdateDao updateDao;
	@Resource
	ManagerDao managerDao;

	public Message allowRegister(int hallNo) {
		Message message = registryDao.findRegById(hallNo);
		if (message.getResult() == true) {
			Registry reg = (Registry) message.getObject();
			Hall hall = new Hall();
			hall.setHallNo(reg.getHallNo());
			hall.setPassword(reg.getPassword());
			hall.setHallName(reg.getHallName());
			hall.setAddress(reg.getAddress());
			hall.setJuniorNum(reg.getJuniorNum());
			hall.setSeniorNum(reg.getSeniorNum());
			hall.setIncome(reg.getIncome());
			hallDao.addHall(hall);
			return new Message(true, "场馆注册已通过");
		} else {
			return new Message(false, "场馆注册审批失败");
		}
	}

	public Message denyRegister(int hallNo) {
		Message message = registryDao.findRegById(hallNo);
		if (message.getResult() == true) {
			Registry reg = (Registry) message.getObject();
			registryDao.deleteReg(reg);
			return new Message(true, "场馆注册已拒绝");
		} else {
			return new Message(false, "场馆注册审批失败");
		}
	}

	public Message allowUpdate(int hallNo) {
		Message message = updateDao.findUpdateById(hallNo);
		if (message.getResult() == true) {
			Update up = (Update) message.getObject();
			Hall hall = new Hall();
			hall.setHallNo(up.getHallNo());
			hall.setPassword(up.getPassword());
			hall.setHallName(up.getHallName());
			hall.setAddress(up.getAddress());
			hall.setJuniorNum(up.getJuniorNum());
			hall.setSeniorNum(up.getSeniorNum());
			hall.setIncome(up.getIncome());
			hallDao.updateHall(hall);
			return new Message(true, "场馆更新已通过");
		} else {
			return new Message(false, "场馆更新审批失败");
		}
	}

	public Message denyUpdate(int hallNo) {
		Message message = updateDao.findUpdateById(hallNo);
		if (message.getResult() == true) {
			Update up = (Update) message.getObject();
			updateDao.deleteUpdate(up);
			return new Message(true, "场馆更新已拒绝");
		} else {
			return new Message(false, "场馆更新审批失败");
		}
	}

	public Message settleOrders(int hallNo) {
		Message hallMessage = hallDao.findHallById(hallNo);
		Message orderMessage = hallDao.getUnsettledOrders(hallNo);
		Message managerMessage = managerDao.findManager(0001);
		if (hallMessage.getResult() && orderMessage.getResult()) {
			Hall hall = (Hall) hallMessage.getObject();
			List<Order> orders = (List<Order>) orderMessage.getObject();
			Manager manager = (Manager) managerMessage.getObject();
			for (Order order : orders) {
				hall.setIncome(hall.getIncome() + order.getTotalPrice() * hall.getPercent());
				manager.setIncome(manager.getIncome() + order.getTotalPrice() * (1 - hall.getPercent()));
				order.setIsSettled(1);
				orderDao.update(order);
			}
			hallDao.updateHall(hall);
			managerDao.update(manager);
			return new Message(true, "场馆编号" + hall.getHallNo() + "结算成功");
		} else {
			return new Message(false, "场馆结算失败");
		}
	}

	public Message getHallFinances() {
		Message hallMessage = hallDao.getAllHall();
		if (hallMessage.getResult() == true) {
			List<Hall> hallList = (List<Hall>) hallMessage.getObject();
			List<HallFinanceVo> list = new ArrayList<HallFinanceVo>();
			for (Hall hall : hallList) {
				long totalNum = (long) hallDao.getOnlineNum(hall.getHallNo()).getObject();
				long successNum = (long) hallDao.getSuccessNum(hall.getHallNo()).getObject();
				long cancelledNum = (long) hallDao.getCancelledNum(hall.getHallNo()).getObject();
				HallFinanceVo vo = new HallFinanceVo(hall.getHallNo(), hall.getHallName(), totalNum, successNum,
						cancelledNum, hall.getIncome());
				list.add(vo);
			}
			return new Message(true, list, "获取场馆统计成功");
		} else {
			return new Message(false, "获取场馆统计失败");
		}
	}

	public Message getMemberFinances() {
		Message memberMessage = memberDao.getAllMembers();
		if (memberMessage.getResult() == true) {
			List<Member> memberList = (List<Member>) memberMessage.getObject();
			List<MemberFinanceVo> list = new ArrayList<MemberFinanceVo>();
			for (Member member : memberList) {
				long totalNum = (long) memberDao.getOnlineNum(member.getEmail()).getObject();
				long paidNum = (long) memberDao.getPaidNum(member.getEmail()).getObject();
				long cancelledNum = (long) memberDao.getCancelledNum(member.getEmail()).getObject();
				MemberFinanceVo vo = new MemberFinanceVo(member.getEmail(), totalNum, paidNum, cancelledNum,
						member.getConsumption());
				list.add(vo);
			}
			return new Message(true, list, "获取会员统计成功");
		} else {
			return new Message(false, "获取会员统计失败");
		}
	}

	public Message getSystemFinances() {
		Message memberMessage = memberDao.getAllMembers();
		Message hallMessage = hallDao.getAllHall();
		Message orderMessage = orderDao.getAllOrders();
		Message managerMessage = managerDao.findManager(0001);
		if (orderMessage.getResult() && memberMessage.getResult() && hallMessage.getResult()
				&& managerMessage.getResult()) {
			long totalMember = (long) memberMessage.getObject();
			long totalHall = (long) hallMessage.getObject();
			long totalOrder = (long) orderMessage.getObject();
			long[] lvlMember = new long[5];
			for (int i = 0; i < 5; i++) {
				Message message = memberDao.getLvlNum(i);
				long count = (long) message.getObject();
				lvlMember[i] = count;
			}
			double totalConsumption = (long) orderDao.getTotalConsumption().getObject();
			double totalIncome = ((Manager) managerMessage.getObject()).getIncome();
			SystemFinanceVo vo = new SystemFinanceVo(totalMember, totalHall, totalOrder, lvlMember, totalConsumption,
					totalIncome);
			return new Message(true, vo, "获取财务情况成功");
		} else {
			return new Message(false, "获取财务情况失败");
		}
	}
}
