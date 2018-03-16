package ticket.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import ticket.config.Message;
import ticket.model.PayAccount;

@Repository
public class PayAccountDao {
	
	@Resource
	BaseDao baseDao;
	
	public Message add(PayAccount account) {
		return baseDao.save(account);
	}
	
	public Message update(PayAccount account) {
		return baseDao.update(account);
	}
	
	public Message delete(PayAccount account) {
		return baseDao.delete(account);
	}
	
	public Message findByEmail(String email) {
		return baseDao.findByProperty(PayAccount.class, "email", email);
	}

}
