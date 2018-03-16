package ticket.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ticket.config.Message;
import ticket.dao.PlanDao;
import ticket.dao.SeatDao;
import ticket.model.Hall;
import ticket.model.Member;
import ticket.model.Plan;
import ticket.model.Seat;
import ticket.service.HallService;
import ticket.service.PlanService;
import ticket.vo.HallInfoVo;
import ticket.vo.HallUpdateVo;
import ticket.vo.OrderTicketVo;

@Controller
@RequestMapping(value = "hall")
public class HallController {
	@Resource
	HallService hallService;
	@Resource
	PlanService planService;
	@Resource
	PlanDao planDao;
	@Resource
	SeatDao seatDao;

	@RequestMapping(value = "statistic")
	@ResponseBody
	public Message hallStatistic(HttpSession session) {
		Message message = null;
		Hall hall = (Hall) session.getAttribute("hall");
		message = hallService.getHallInfo(hall.getHallNo());
		if (message.getResult() == true) {
			HallInfoVo vo = (HallInfoVo) message.getObject();
			return new Message(true, vo, "获取场馆统计成功");
		} else {
			return message;
		}
	}

	@RequestMapping(value = "info")
	@ResponseBody
	public Message hallInfo(HttpSession session) {
		Hall hall = (Hall) session.getAttribute("hall");
		return new Message(true, hall, "获取场馆信息成功");
	}

	@RequestMapping(value = "edit")
	@ResponseBody
	public Message updateInfo(HttpSession session, String password, String hallName, String address, int juniorNum,
			int seniorNum, double percent) {
		Message message = null;
		Hall hall = (Hall) session.getAttribute("hall");
		HallUpdateVo vo = new HallUpdateVo(hall.getHallNo(), password, hallName, address, juniorNum, seniorNum,
				percent);
		message = hallService.updateHall(vo);
		if (message.getResult()) {
			return new Message(true, "修改场馆信息成功");
		} else {
			return new Message(false, "修改场馆信息失败");
		}

	}

	@RequestMapping(value = "makeplan")
	@ResponseBody
	public Message makePlan(HttpSession session, String showDate, String showType, int juniorNum, int seniorNum,
			double juniorPrice, double seniorPrice, String description) {
		Message message = null;
		Hall hall = (Hall) session.getAttribute("hall");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		Date showdate = null;
		try {
			showdate = dateFormat.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long timetemp = showdate.getTime() - nowDate.getTime();
		if (timetemp < 0) {
			return new Message(false, "演出时间应是未来某个时刻");
		} else {
			int juniornum = juniorNum > hall.getJuniorNum() ? hall.getJuniorNum() : juniorNum;
			int seniornum = seniorNum > hall.getSeniorNum() ? hall.getSeniorNum() : seniorNum;
			Plan plan = new Plan(hall.getHallNo(), hall.getHallName(), hall.getAddress(), showDate, juniornum,
					seniornum, juniorPrice, seniorPrice, showType, description);
			message = planService.makePlan(plan);
			Message planMessage = planDao.findPlanByDouble("hallNo", hall.getHallNo(), "showDate", showDate);
			List<Plan> list = (List<Plan>) planMessage.getObject();
			Plan thePlan = list.get(0);
			planService.arrangeSeats(thePlan.getPlanNo());
			if (message.getResult()) {
				return new Message(true, "发布计划成功");
			} else {
				return message;
			}
		}

	}

	@RequestMapping(value = "plan")
	@ResponseBody
	public Message getPlan(HttpSession session) {
		Message message = null;
		Hall hall = (Hall) session.getAttribute("hall");
		message = planService.getHallPlans(hall.getHallNo());
		if (message.getResult()) {
			List<Plan> list = (List<Plan>) message.getObject();

			return new Message(true, list, "获取场馆计划成功");
		} else {
			return new Message(false, "获取场馆计划失败");
		}

	}

	@RequestMapping(value = "plan2")
	@ResponseBody
	public Message getPlans(HttpSession session) {
		Message message = null;
		Hall hall = (Hall) session.getAttribute("hall");
		message = planService.getHallPlans(hall.getHallNo());
		if (message.getResult()) {
			List<Plan> list = (List<Plan>) message.getObject();
			List<Plan> result = new ArrayList<Plan>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate = new Date();
			for (Plan plan : list) {
				String showdate = plan.getShowDate();
				Date showDate = null;
				try {
					showDate = dateFormat.parse(showdate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long timetemp = showDate.getTime() - nowDate.getTime();
				if (timetemp > 0) {
					result.add(plan);
				}
			}
			return new Message(true, result, "获取场馆计划成功");
		} else {
			return new Message(false, "获取场馆计划失败");
		}
	}

	@RequestMapping(value = "unsettled")
	@ResponseBody
	public Message showUnsettled(HttpSession session) {
		Message message = hallService.getEachUnsettledOrders();
		return message;
	}

	@RequestMapping(value = "halls")
	@ResponseBody
	public Message getAllHalls(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Message message = hallService.getAllHalls(member.getEmail());
		return message;
	}

	@RequestMapping(value = "check")
	@ResponseBody
	public Message checkTicket(HttpSession session, int orderid, String telephone) {
		Message message = hallService.checkTicket(orderid, telephone);
		return message;
	}

	@RequestMapping(value = "selectBuyTicket")
	@ResponseBody
	public Message selectTicket(HttpSession session, String email, int option, int ticketNum, int ticketType,
			double totalPrice, String showDate, String showType) {
		Hall hall = (Hall) session.getAttribute("hall");
		Date orderdate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderDate = dateFormat.format(orderdate);
		double minused = 0.0;
		OrderTicketVo vo = new OrderTicketVo(email, option, ticketNum, ticketType, totalPrice, orderDate, showDate,
				hall.getHallNo(), hall.getHallName(), showType, minused);
		Message message = hallService.buyTicket(vo);
		return message;
	}

	@RequestMapping(value = "getJunSeatCondition")
	@ResponseBody
	public Message getJunSeatCondition(HttpSession session, int planNo) {
		return planService.getJunSeatCondition(planNo);
	}

	@RequestMapping(value = "getSenSeatCondition")
	@ResponseBody
	public Message getSenSeatCondition(HttpSession session, int planNo) {
		return planService.getSenSeatCondition(planNo);
	}

	@RequestMapping(value = "junUpdateSeat")
	@ResponseBody
	public Message junUpdateSeat(HttpSession session, int srow, int scolumn, int planNo) {
		Message message = seatDao.findSeatByQuadra("planNo", planNo, "srow", srow - 1, "scolumn", scolumn - 1, "type",
				0);
		Message planMessage = planDao.findPlanById(planNo);
		// Plan plan = (Plan) planMessage.getObject();
		// plan.setJuniorNum(plan.getJuniorNum() - 1);
		// planDao.update(plan);
		List<Seat> list = (List<Seat>) message.getObject();
		Seat seat = list.get(0);
		seat.setOccupied(1);
		return seatDao.updateSeat(seat);

	}

	@RequestMapping(value = "senUpdateSeat")
	@ResponseBody
	public Message senUpdateSeat(HttpSession session, int srow, int scolumn, int planNo) {
		Message message = seatDao.findSeatByQuadra("planNo", planNo, "srow", srow - 1, "scolumn", scolumn - 1, "type",
				1);
		Message planMessage = planDao.findPlanById(planNo);
		// Plan plan = (Plan) planMessage.getObject();
		// plan.setSeniorNum(plan.getSeniorNum() - 1);
		// planDao.update(plan);
		List<Seat> list = (List<Seat>) message.getObject();
		Seat seat = list.get(0);
		seat.setOccupied(1);
		return seatDao.updateSeat(seat);

	}

	@RequestMapping(value = "searchPlan")
	@ResponseBody
	public Message searchPlan(HttpSession session, int planNo) {
		Hall hall = (Hall) session.getAttribute("hall");
		Message message = planService.searchPlan(hall.getHallNo(), planNo);
		if (message.getResult()) {
			List<Plan> list = (List<Plan>) message.getObject();
			return new Message(true, list, "搜索计划成功");
		} else {
			return new Message(false, "搜索计划失败");
		}

	}

	@RequestMapping(value = "searchPlan2")
	@ResponseBody
	public Message searchPlans(HttpSession session, int planNo) {
		Hall hall = (Hall) session.getAttribute("hall");
		Message message = planService.searchPlan(hall.getHallNo(), planNo);
		if (message.getResult()) {
			List<Plan> list = (List<Plan>) message.getObject();
			List<Plan> result = new ArrayList<Plan>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate = new Date();
			for (Plan plan : list) {
				String showdate = plan.getShowDate();
				Date showDate = null;
				try {
					showDate = dateFormat.parse(showdate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long timetemp = showDate.getTime() - nowDate.getTime();
				if (timetemp > 0) {
					result.add(plan);
				}
			}
			return new Message(true, result, "获取场馆计划成功");
		} else {
			return new Message(false, "获取场馆计划失败");
		}
	}

}
