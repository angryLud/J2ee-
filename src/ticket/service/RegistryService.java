package ticket.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ticket.config.Message;
import ticket.dao.RegistryDao;

@Service
public class RegistryService {
	
	@Resource
	RegistryDao registryDao;
	
	public Message getAllRegs() {
		return registryDao.getAllReg();
	}
	

}
