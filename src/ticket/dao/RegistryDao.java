package ticket.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import ticket.config.Message;
import ticket.model.Registry;

@Repository
public class RegistryDao {

	@Resource
	BaseDao baseDao;
	
	public Message addReg(Registry reg) {
		return baseDao.save(reg);
	}
	
//	public Message updateReg(Registry reg) {
//		return baseDao.update(reg);
//	}
	
	public Message deleteReg(Registry reg) {
		return baseDao.delete(reg);
	}
	
	public Message findRegById(int hallNo) {
		return baseDao.findById(Registry.class, hallNo);
	}
	
	public Message getAllReg() {
		return baseDao.getAll(Registry.class);
	}
}
