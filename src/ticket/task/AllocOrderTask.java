package ticket.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ticket.config.Message;
import ticket.dao.HallDao;
import ticket.dao.MemberDao;
import ticket.dao.OrderDao;
import ticket.dao.PayAccountDao;
import ticket.dao.PlanDao;
import ticket.dao.SeatDao;
import ticket.model.Member;
import ticket.model.Order;
import ticket.model.PayAccount;
import ticket.model.Plan;
import ticket.model.Seat;

@Component
public class AllocOrderTask {

	@Resource
	OrderDao orderDao;
	@Resource
	HallDao hallDao;
	@Resource
	PlanDao planDao;
	@Resource
	MemberDao memberDao;
	@Resource
	SeatDao seatDao;
	@Resource
	PayAccountDao payAccountDao;

	// 每天的8点、12点和16点进行配票工作，令每个场馆在某个时刻只能举办一场演出
	// @Scheduled(cron = "0 0 8,12,16 * * ?")
	public void allocOrder() {
		Message orderMessage = orderDao.getUnallocOrders();
		if (orderMessage.getResult()) {
			List<Order> list = (List<Order>) orderMessage.getObject();
			for (Order order : list) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date nowDate = new Date();
				String showdate = order.getShowDate();
				Date showDate = null;
				try {
					showDate = dateFormat.parse(showdate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long timetemp = showDate.getTime() - nowDate.getTime();
				long pastDay = timetemp / (1000 * 60 * 60 * 24);
				if (pastDay < 0) {
					order.setAllocState(0);
					order.setIsCancelled(0);
					if (order.getPayState() == 1) {
						Message memberMessage = memberDao.findMemberByEmail(order.getEmail());
						List<Member> memberList = (List<Member>) memberMessage.getObject();
						Member member = memberList.get(0);
						
						Message accountMessage = payAccountDao.findByEmail(order.getEmail());
						List<PayAccount> accountList = (List<PayAccount>) accountMessage.getObject();
						PayAccount account = accountList.get(0);
						
						account.setBalance(account.getBalance() + order.getTotalPrice());
						member.setPoint((int) (member.getPoint() - order.getTotalPrice() * 10));
						
						memberDao.update(member);
						payAccountDao.update(account);
					}
					orderDao.update(order);

				} else if (pastDay <= 14) {
					if (order.getPayState() == 1) {
						int ticketNum = order.getTicketNum();
						int ticketType = order.getTicketType();
						int hallNo = order.getHallNo();
						Message planMessage = planDao.findPlanByDouble("hallNo", hallNo, "showDate", showDate);
						Plan plan = (Plan) planMessage.getObject();
						int planNo = plan.getPlanNo();
						int seatNum = 0;
						if (ticketType == 0) {
							seatNum = plan.getJuniorNum();
							if (ticketNum <= seatNum) {
								int maxRow = seatNum % 7 == 0 ? seatNum / 7 : seatNum / 7 + 1;
								for (int i = 0; i < ticketNum; i++) {
									for (int j = 0; j < maxRow; i++) {
										for (int m = 0; m < 7; m++) {
											Message seatMessage = seatDao.findSeatByQuadra("planNo", planNo, "srow", j,
													"scolumn", m, "type", 0);
											if (seatMessage.getResult() == true) {
												Seat seat = (Seat) seatMessage.getObject();
												if (seat.getOccupied() == 0) {
													seat.setOccupied(1);
													seatDao.updateSeat(seat);
												}
											}
										}
									}
								}
								// plan.setJuniorNum(seatNum - ticketNum);
								order.setAllocState(1);
								orderDao.update(order);
								// planDao.update(plan);
							} else {
								order.setAllocState(0);
								order.setIsCancelled(0);
								Message memberMessage = memberDao.findMemberByEmail(order.getEmail());
								List<Member> memberList = (List<Member>) memberMessage.getObject();
								Member member = memberList.get(0);
								
								Message accountMessage = payAccountDao.findByEmail(order.getEmail());
								List<PayAccount> accountList = (List<PayAccount>) accountMessage.getObject();
								PayAccount account = accountList.get(0);
								
								account.setBalance(account.getBalance() + order.getTotalPrice());
								member.setPoint((int) (member.getPoint() - order.getTotalPrice() * 10));
								orderDao.update(order);
								memberDao.update(member);
								payAccountDao.update(account);
							}
						} else if (ticketType == 1) {
							seatNum = plan.getSeniorNum();
							if (ticketNum <= seatNum) {
								int maxRow = seatNum % 7 == 0 ? seatNum / 7 : seatNum / 7 + 1;
								for (int i = 0; i < ticketNum; i++) {
									for (int j = 0; j < maxRow; i++) {
										for (int m = 0; m < 7; m++) {
											Message seatMessage = seatDao.findSeatByQuadra("planNo", planNo, "srow", j,
													"scolumn", m, "type", 1);
											if (seatMessage.getResult() == true) {
												Seat seat = (Seat) seatMessage.getObject();
												if (seat.getOccupied() == 0) {
													seat.setOccupied(1);
													seatDao.updateSeat(seat);
												}
											}
										}
									}
								}

								// plan.setSeniorNum(seatNum - ticketNum);
								order.setAllocState(1);
								orderDao.update(order);
								// planDao.update(plan);
							} else {
								order.setAllocState(0);
								order.setIsCancelled(0);
								Message memberMessage = memberDao.findMemberByEmail(order.getEmail());
								List<Member> memberList = (List<Member>) memberMessage.getObject();
								Member member = memberList.get(0);
								
								Message accountMessage = payAccountDao.findByEmail(order.getEmail());
								List<PayAccount> accountList = (List<PayAccount>) accountMessage.getObject();
								PayAccount account = accountList.get(0);
								
								account.setBalance(account.getBalance() + order.getTotalPrice());
								member.setPoint((int) (member.getPoint() - order.getTotalPrice() * 10));
								orderDao.update(order);
								memberDao.update(member);
								payAccountDao.update(account);
							}
						}
					}
				}
			}
		}
	}

}
