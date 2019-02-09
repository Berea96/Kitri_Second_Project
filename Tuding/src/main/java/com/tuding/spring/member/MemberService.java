package com.tuding.spring.member;

public interface MemberService {
	void joinMember(Member m);
	Member getMember(String id);
	void editMember(Member m);
	void delMember(String id);
}
