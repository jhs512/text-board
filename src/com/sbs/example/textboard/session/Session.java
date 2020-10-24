package com.sbs.example.textboard.session;

import com.sbs.example.textboard.dto.Member;

public class Session {
	public int loginedMemberId;
	public Member loginedMember;
	
	public Session() {
		loginedMemberId = -1;
	}
}
