package com.tuding.spring.member;

import java.util.ArrayList;

public interface MemberService {
	void joinMember(Member m);
	Member getMember(String id);
	ArrayList<Member> getAll();
	void editMember(Member m);
	void checkedEmail(String id);
	void delMember(String id);
}
