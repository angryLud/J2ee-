package ticket.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ticket.config.Message;

import ticket.model.Plan;

/**
 * @author Administrator
 *
 */
@Repository
public class PlanDao {

	@Resource
	BaseDao baseDao;

	public Message addPlan(Plan plan) {
		return baseDao.save(plan);
	}

	public Message update(Plan plan) {
		return baseDao.update(plan);
	}

	public Message delete(Plan plan) {
		return baseDao.delete(plan);
	}

	public Message findPlanById(int planNo) {
		return baseDao.findById(Plan.class, planNo);
	}

	public Message findPlanByDouble(String propname1, Object value1, String propname2, Object value2) {
		Session session = baseDao.getSession();
		List<Plan> list = new ArrayList<Plan>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Plan as model where model." + propname1 + " = ? and model." + propname2 + " = ?";
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

	/**
	 * 根据实际需要继续添加查询方法
	 */
	public Message findPlanByHotel(int hallNo) {
		return baseDao.findByProperty(Plan.class, "hallNo", hallNo);
	}
	
	public Message searchPlan(int hallNo,int planNo) {
		Session session = baseDao.getSession();
		List<Plan> list = new ArrayList<Plan>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Plan as model where model.hallNo = " + hallNo + " and model.planNo like '%" + planNo + "%'";
			Query queryObject = session.createQuery(queryString);
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

}
