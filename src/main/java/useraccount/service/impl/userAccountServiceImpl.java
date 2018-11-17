package useraccount.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.model.studentBean;
import useraccount.model.userAccountBean;
import useraccount.repository.userAccountDao;
import useraccount.service.userAccountService;

@Service
@Transactional
public class userAccountServiceImpl implements userAccountService {
	@Autowired
	userAccountDao dao;

	public userAccountServiceImpl() {
	}

	@Override
	public userAccountBean queryMember(String id) {
		return dao.queryMember(id);
	}

	@Override
	public boolean idExists(String id) {
		
		return dao.idExists(id);
	}

	@Override
	public Integer saveMember(userAccountBean ub) {
		
		return dao.saveMember(ub);
	}

	@Override
	public String VerificationCode(String UA_PL) {
		return dao.VerificationCode(UA_PL);
	}

	@Override
	public userAccountBean checkIDPassword(String UA_Acu, String UA_Psw) {
		userAccountBean uad = dao.checkIDPassword(UA_Acu, UA_Psw);
		return uad;
	}

	@Override
	public userAccountBean checkVC(String UA_VC) {
		userAccountBean uad = dao.checkVC(UA_VC);
		return uad;
	}

	@Override
	public int updatemem(userAccountBean ub) {
		
		return dao.updatemem(ub);
	}

	@Override
	public int updatemem(userAccountBean ub, long sizeInBytes) {
		
		return dao.updatemem(ub, sizeInBytes);
	}

	@Override
	public boolean phoneExists(String phone) {
		
		return dao.phoneExists(phone);
	}

	@Override
	public List<userAccountBean> findParentsName(List<studentBean> list) {
		return dao.findParentsName(list);
	}

	@Override
	public userAccountBean checkID(String UA_Acu) {

		return dao.checkID(UA_Acu);
	}

}
