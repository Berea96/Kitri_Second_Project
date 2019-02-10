package com.tuding.spring.member;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	private MemberDao dao;

	@Override
	public void joinMember(Member m) {
		dao = sqlSession.getMapper(MemberDao.class);
		dao.insert(m);
	}

	@Override
	public Member getMember(String id) {
		dao = sqlSession.getMapper(MemberDao.class);
		return dao.select(id);
	}
	
	@Override
	public ArrayList<Member> getAll() {
		dao = sqlSession.getMapper(MemberDao.class);
		return dao.selectAll();
	}

	@Override
	public void editMember(Member m) {
		dao = sqlSession.getMapper(MemberDao.class);
		dao.update(m);
	}

	@Override
	public void checkedEmail(String id) {
		dao = sqlSession.getMapper(MemberDao.class);
		dao.updateChecked(id);
	}
	
	@Override
	public void editTempPass(Member m) {
		dao = sqlSession.getMapper(MemberDao.class);
		dao.updateTempPass(m);
	}

	@Override
	public void delMember(String id) {
		dao = sqlSession.getMapper(MemberDao.class);
		dao.delete(id);
	}
}
