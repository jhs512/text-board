package com.sbs.example.textboard.session;

import com.sbs.example.textboard.dto.Member;

public class Session {
	public int loginedMemberId;
	public Member loginedMember;

	public Session() {
		loginedMemberId = -1;
	}

	public boolean isLogined() {
		return loginedMemberId != -1;
	}

	public void logout() {
		loginedMemberId = -1;
		loginedMember = null;
	}

	public void login(Member member) {
		loginedMemberId = member.id;
		loginedMember = member;
	}
}
