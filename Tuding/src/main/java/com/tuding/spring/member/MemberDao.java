package com.tuding.spring.member;

public interface MemberDao {
	void insert(Member m);
	Member select(String id);
	void update(Member m);
	void delete(String id);
}