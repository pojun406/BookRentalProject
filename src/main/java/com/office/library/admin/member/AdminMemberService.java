package com.office.library.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	final static public int ADMIN_ACCOUNT_ALREADY_EXIST = 0;
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1;
	final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;
	
	@Autowired
	AdminMemberDao adminMemberDao;
	
	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] createAccountConfirm()");
		
		boolean isMember = adminMemberDao.isAdminMember(adminMemberVo.getA_m_id());
		
		if (!isMember) {
			int result = adminMemberDao.insertAdminAccount(adminMemberVo);
			
			if (result > 0)
				return ADMIN_ACCOUNT_CREATE_SUCCESS;
			
			else
				return ADMIN_ACCOUNT_CREATE_FAIL;
			
		} else {
			return ADMIN_ACCOUNT_ALREADY_EXIST;
			
		}
		
	}

	public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminmemberService] loginConfirm()");
		
		AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo);
		
		if (loginedAdminMemberVo != null)
			System.out.println("[AdminmemberService] ADMIN MEMBER LOGIN SUCCESS");
		else
			System.out.println("[AdminmemberService] ADMIN MEMBER LOGIN FAIL");
		return loginedAdminMemberVo;
	}

	public List<AdminMemberVo> listupAdmin() {
		System.out.println("[AdminmemberService] listupAdmin()");
		return adminMemberDao.selectAdmins();
	}

	public void setAdminApproval(int a_m_no) {
		System.out.println("[AdminmemberService] setAdminApproval()");
		
		int result = adminMemberDao.updateAdminAccount(a_m_no);
	}

	public int modifyAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminmemberService] modifyAccountConfirm()");
		return AdminMemberDao.updateAdminAccount(adminMemberVo);
	}

	public AdminMemberVo getLoginedAdminMemberVo(int a_m_no) {
		System.out.println("[AdminmemberService] getLoginedAdminMemberVo()");
		return AdminMemberDao.selectAdmin(a_m_no);
	}
}
