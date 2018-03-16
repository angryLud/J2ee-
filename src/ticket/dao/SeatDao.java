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
import ticket.model.Seat;

@Repository
public class SeatDao {

	@Resource
	BaseDao baseDao;

	public Message addSeat(Seat seat) {
		return baseDao.save(seat);
	}

	public Message updateSeat(Seat seat) {
		return baseDao.update(seat);
	}

	public Message getSeatById(int planNo) {
		return baseDao.findByProperty(Seat.class, "planNo", planNo);
	}

	public Message findSeatByQuadra(String propname1, Object value1, String propname2, Object value2, String propname3,
			Object value3, String propname4, Object value4) {
		Session session = baseDao.getSession();
		List<Hall> list = new ArrayList<Hall>();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from Seat as model where model." + propname1 + " = ? and model." + propname2
					+ " = ? and model." + propname3 + " = ? and model." + propname4 + " = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value1);
			queryObject.setParameter(1, value2);
			queryObject.setParameter(2, value3);
			queryObject.setParameter(3, value4);
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
