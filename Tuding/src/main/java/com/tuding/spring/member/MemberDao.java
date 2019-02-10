package com.tuding.spring.member;

import java.util.ArrayList;

public interface MemberDao {
	void insert(Member m);
	Member select(String id);
	ArrayList<Member> selectAll();
	void update(Member m);
	void updateChecked(String id);
	void updateTempPass(Member m);
	void delete(String id);
}