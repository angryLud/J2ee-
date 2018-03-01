package ticket.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import ticket.config.Message;
import ticket.model.Manager;

@Repository
public class ManagerDao {

	@Resource
	BaseDao baseDao;
	
	public Message findManager(int managerNo) {
		return baseDao.findById(Manager.class, managerNo);
	}
	
	public Message update(Manager manager) {
		return baseDao.update(manager);
	}
}
