package com.sbs.example.textboard.service;

import java.sql.Connection;

import com.sbs.example.textboard.dao.MemberDao;
import com.sbs.example.textboard.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService(Connection conn) {
		memberDao = new MemberDao(conn);
	}

	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}