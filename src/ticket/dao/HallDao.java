package ticket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ticket.config.Message;
import ticket.model.Hall;
import ticket.model.Member;
import ticket.model.Order;
import ticket.model.Plan;
import ticket.model.Registry;
import ticket.model.Update;

@Repository
public class HallDao {

	@Resource
	BaseDao baseDao;

	public Message addHall(Hall hall) {
		return baseDao.save(hall);
	}

	public Message updateHall(Hall hall) {
		return baseDao.update(hall);
	}

	public Message findHallByName(String hallName) {
		return baseDao.findByProperty(Hall.class, "hallName", hallName);
	}

	public Message findHallById(int id) {
		return baseDao.findById(Hall.class, id);
	}

	public Message findHallByDouble(String propname1, Object value1, String propname2, Object value2) {
		Session session = baseDao.getSession();
		List<Hall> list = new ArrayList<Hall>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Hall as model where model." + propname1 + " = ? and " + propname2 + " = ?";
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

	public Message deleteHall(Hall hall) {
		return baseDao.delete(hall);
	}

	public Message getAllHall() {
		return baseDao.getAll(Hall.class);
	}

	public Message getHallNum() {
		Message message = baseDao.getAll(Hall.class);
		if (message.getResult() == false) {
			List<Hall> list = (List<Hall>) message.getObject();
			long count = 0;
			for (Hall hall : list) {
				count++;
			}
			return new Message(true, count, "数据获取成功");
		} else {
			return new Message(false, "数据获取失败");
		}
	}

	public Message getUnsettledOrders(int hallNo) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.hallNo = ? and model.isSettled = ? and model.orderMethod = ? and model.isCancelled = ? and model.payState = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, hallNo);
			queryObject.setParameter(1, 0);
			queryObject.setParameter(2, 0);
			queryObject.setParameter(3, 1);
			queryObject.setParameter(4, 1);
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

	public Message getTotalOrderNum(int hallNo) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.hallNo = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, hallNo);
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

	public Message getOnlineNum(int hallNo) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.hallNo = ? and model.orderMethod = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, hallNo);
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

	public Message getSuccessNum(int hallNo) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.hallNo = ? and model.orderMethod = ? and model.payState = ? and model.allocState = ? and model.isCancelled = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, hallNo);
			queryObject.setParameter(1, 0);
			queryObject.setParameter(2, 1);
			queryObject.setParameter(3, 1);
			queryObject.setParameter(4, 1);
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

	public Message getCancelledNum(int hallNo) {
		Session session = baseDao.getSession();
		List<Order> list = new ArrayList<Order>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Order as model where model.hallNo = ? and model.orderMethod = ? and model.isCancelled = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, hallNo);
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
