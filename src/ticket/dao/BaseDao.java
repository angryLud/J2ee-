
package ticket.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import ticket.config.Message;

@Repository
public class BaseDao {
	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public Message save(Object entity) {
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
			session.clear();
			return new Message(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return new Message(false, "数据库保存错误");
		} finally {
			session.close();
		}
	}

	public Message update(Object entity) {
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
			session.clear();
			return new Message(true, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据库更新错误");
		} finally {
			session.close();
		}

	}

	public Message delete(Object entity) {
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
			session.clear();
			return new Message(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "删除异常");
		} finally {
			session.close();
		}
	}

	public Message deleteById(Class<?> className, int id) {
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			Object instance = session.get(className, id);
			session.delete(instance);
			tx.commit();
			session.clear();
			return new Message(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "删除异常");
		} finally {
			session.close();
		}
	}

	public Message getPageAll(Class<?> className, int up, int size) {
		List<?> list = null;
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(className);
			criteria.addOrder(Order.desc("id"));
			criteria.setFirstResult(up);
			criteria.setMaxResults(size);
			list = criteria.list();
			tx.commit();
			session.clear();
			return new Message(true, list, "数据获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据获取异常");
		} finally {
			session.close();
		}
	}

	public Message getAll(Class<?> className) {
		List<?> list = null;
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			String queryString = "from " + className.getSimpleName();
			Query queryObject = session.createQuery(queryString);
			list = queryObject.list();
			tx.commit();
			session.clear();
			return new Message(true, list, "数据获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(false, "数据获取异常");
		} finally {
			session.close();
		}
	}

	public Message findById(Class<?> className, int id) {
		Session session = getSession();
		try {
			Object instance = session.get(className, id);
			return new Message(true, instance, "数据获取成功");
		} catch (Exception re) {
			re.printStackTrace();
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}

	public Message findByProperty(Class<?> className, String propertyName, Object value) {

		Session session = getSession();
		try {
			String queryString = "from " + className.getSimpleName() + " as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List<?> list = queryObject.list();
			return new Message(true, list, "数据获取成功");
		} catch (Exception re) {
			re.printStackTrace();
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}

	public Message findByPropertySingle(Class<?> className, String propertyName, Object value) {

		Session session = getSession();
		try {
			String queryString = "from " + className.getSimpleName() + " as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List<?> list = queryObject.list();
			if (list.size() > 0) {
				return new Message(true, list.get(0), "数据获取成功");
			} else {
				return new Message(false, "数据获取失败,没有该数据");
			}
		} catch (Exception re) {
			re.printStackTrace();
			return new Message(false, "数据获取失败");
		} finally {
			session.close();
		}
	}

	public Message execSqlQuery(String sql) {
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			List<Object[]> objects = session.createSQLQuery(sql).list();
			tx.commit();
			session.clear();
			return new Message(true, objects, "SQL执行成功");
		} catch (Exception re) {
			re.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			return new Message(true, "SQL执行失败");
		} finally {
			session.close();
		}
	}

}
