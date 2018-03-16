package ticket.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ticket.config.Message;
import ticket.dao.UpdateDao;

@Service
public class UpdateService {
	@Resource
	UpdateDao updateDao;
	
	public Message getAllUps() {
		return updateDao.getAllUpdate();
	}
}
