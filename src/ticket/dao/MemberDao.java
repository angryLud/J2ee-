package ticket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ticket.config.Message;
import ticket.model.Member;
import ticket.model.Order;
import ticket.model.Plan;

@Repository
public class MemberDao {

	@Resource
	BaseDao baseDao;

	public Message register(Member member) {
		return baseDao.save(member);
	}

	public Message update(Member member) {
		return baseDao.update(member);
	}

	// 应该写在MemberService中
	// public Message cancelMember(Member member) {
	// member.setState(0);
	// return baseDao.update(member);
	// }
	//
	public Message delete(Member member) {
		return baseDao.delete(member);
	}

	public Message findMemberByEmail(String email) {
		return baseDao.findByProperty(Member.class, "email", email);
	}

	public Message findMemberByPhone(String telephone) {
		return baseDao.findByProperty(Member.class, "telephone", telephone);
	}

	public Message findMemberByDouble(String propname1, Object value1, String propname2, Object value2) {
		Session session = baseDao.getSession();
		List<Plan> list = new ArrayList<Plan>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Member as model where model." + propname1 + " = ? and " + propname2 + " = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value1);
			queryObject.setParameter(1, value2);
			list = queryObject.list();
			tx.commit();
			return new Message(true, list, "数据获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}

	public Message getAllMembers() {
		return baseDao.getAll(Member.class);
	}

	public Message getMemberNum() {
		Message message = baseDao.getAll(Member.class);
		if (message.getResult() == true) {
			List<Member> list = (List<Member>) message.getObject();
			long count = 0;
			for (Member member : list) {
				count++;
			}
			return new Message(true, count, "数据获取成功");
		} else {
			return new Message(false, "数据获取失败");
		}
	}

	public Message getLvlNum(int level) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Member as model where model.level = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, level);
			list = queryObject.list();
			long count = 0;
			for (Order order : list) {
				count++;
			}
			tx.commit();
			return new Message(true, count, "数据获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}

	public Message getOnlineNum(String email) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.email = ? and model.orderMethod = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, email);
			queryObject.setParameter(1, 0);
			list = queryObject.list();
			long count = 0;
			for (Order order : list) {
				count++;
			}
			tx.commit();
			return new Message(true, count, "数据获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}

	public Message getPaidNum(String email) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.hallNo = ? and model.orderMethod = ? and model.payState = ? and model.isCancelled = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, email);
			queryObject.setParameter(1, 0);
			queryObject.setParameter(2, 1);
			queryObject.setParameter(3, 1);
			list = queryObject.list();
			long count = 0;
			for (Order order : list) {
				count++;
			}
			tx.commit();
			return new Message(true, count, "数据获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}

	public Message getCancelledNum(String email) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.hallNo = ? and model.orderMethod = ? and model.isCancelled = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, email);
			queryObject.setParameter(1, 0);
			queryObject.setParameter(2, 0);
			list = queryObject.list();
			long count = 0;
			for (Order order : list) {
				count++;
			}
			tx.commit();
			return new Message(true, count, "数据获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}
}
