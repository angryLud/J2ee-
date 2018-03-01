package ticket.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import ticket.config.Message;
import ticket.model.Update;

@Repository
public class UpdateDao {

	@Resource
	BaseDao baseDao;

	public Message addUpdate(Update up) {
		return baseDao.save(up);
	}

	public Message deleteUpdate(Update up) {
		return baseDao.save(up);
	}

	public Message findUpdateById(int hotelNo) {
		return baseDao.findById(Update.class, hotelNo);
	}

	public Message getAllUpdate() {
		return baseDao.getAll(Update.class);
	}
}
