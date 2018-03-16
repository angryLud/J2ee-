package ticket.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import ticket.config.Message;
import ticket.dao.HallDao;
import ticket.dao.ManagerDao;
import ticket.dao.MemberDao;
import ticket.dao.RegistryDao;
import ticket.model.Hall;
import ticket.model.Manager;
import ticket.model.Member;
import ticket.model.Registry;
import ticket.utils.MailUtil;
import ticket.vo.MemberRegistryVo;

@Service
public class LogAndRegService {

	@Resource
	MemberDao memberDao;

	@Resource
	HallDao hallDao;

	@Resource
	ManagerDao managerDao;

	@Resource
	RegistryDao registryDao;

	public Message memberLogin(String email, String password) {
		Message message = memberDao.findMemberByEmail(email);
		if (message.getResult() == false) {
			return new Message(false, "该用户不存在");
		} else {
			List<Member> list = (List<Member>) message.getObject();
			Member member = list.get(0);
			return checkPasswd(member, member.getPassword(), password);
		}
	}

	public Message hallLogin(int hallNo, String password) {
		Message message = hallDao.findHallById(hallNo);
		if (message.getResult() == false) {
			return new Message(false, "该场馆不存在");
		} else {
			Hall hall = (Hall) message.getObject();
			return checkPasswd(hall, hall.getPassword(), password);
		}

	}

	public Message managerLogin(int managerNo, String password) {
		Message message = managerDao.findManager(managerNo);
		if (message.getResult() == false) {
			return new Message(false, "该经理不存在");
		} else {
			Manager manager = (Manager) message.getObject();
			return checkPasswd(manager, manager.getPassword(), password);
		}
	}

	public Message memberRegister(MemberRegistryVo vo) {
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
				member.setState(0);
				member.setConsumption(0.0);
				member.setLevel(0);
				member.setPoint(0);
				member.setDiscount(1.0);
				try {
					member = MailUtil.activateMail(member,email);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message memberMessage = memberDao.register(member);
				if (memberMessage.getResult() == true) {
					return new Message(true, "会员注册成功");
				}
			}
			return new Message(false, "该邮箱已被使用");

		} else {
			return new Message(false, "注册用户失败");
		}
	}

	public Message hallRegister(Registry reg) {
		String hallName = reg.getHallName();
		String address = reg.getAddress();
		Message message = hallDao.getAllHall();
		if (message.getResult()) {
			List<Hall> list = (List<Hall>) message.getObject();
			boolean flag = true;
			for (Hall hall : list) {
				if (hall.getHallName().equals(hallName) && hall.getAddress().equals(address)) {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				Message regMessage = registryDao.addReg(reg);
				if (regMessage.getResult() == true) {
					return new Message(true, "场馆注册成功");
				}
			}
			return new Message(false, "该场馆已存在");
		} else {
			return new Message(false, "注册场馆失败");
		}

	}

	private Message checkPasswd(Object object, String correct, String input) {
		if (correct.equals(input)) {
			return new Message(true, object, "登录成功");
		} else {
			return new Message(false, "用户名或密码错误");
		}
	}

}
