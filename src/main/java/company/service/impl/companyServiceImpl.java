package company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import company.model.companyBean;
import company.repository.companyDao;
import company.service.companyService;
import useraccount.model.userAccountBean;
@Service
@Transactional
public class companyServiceImpl implements companyService {
	@Autowired
	companyDao dao;
	@Override
	public Integer savecompany(companyBean cb) {		
		return dao.savecompany(cb);
	}
	@Override
	public companyBean loadcompany_boss(String UA_VC) {		
		return dao.loadcompany_boss(UA_VC);
	}
	@Override
	public companyBean loadcompany_teacher(String UA_VC) {
		return dao.loadcompany_teacher(UA_VC);
	}
	@Override
	public companyBean loadcompany_parent(String UA_VC) {
		return dao.loadcompany_parent(UA_VC);
	}
	@Override
	public int updatecom(companyBean cb, long sizeInBytes) {		
		return dao.updatecom(cb, sizeInBytes);
	}
	@Override
	public int updatecom(companyBean cb) {		
		return dao.updatecom(cb);
	}
	@Override
	public boolean CNExists(String name) {		
		return dao.CNExists(name);
	}
	@Override
	public boolean CPExists(String phone) {		
		return dao.CPExists(phone);
	}
	@Override
	public int updateslide(companyBean cb,userAccountBean ub) {
		return dao.updateslide(cb,ub);
	}

}
