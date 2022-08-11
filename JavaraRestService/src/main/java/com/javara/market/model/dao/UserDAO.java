package com.javara.market.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javara.market.model.dto.PayDTO;
import com.javara.market.model.dto.UserDTO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public boolean isLogin(UserDTO loginDTO) {
		return (Integer)sqlSession.selectOne("UsersLogin",loginDTO)==1?true:false;
	}

	public UserDTO selectOne(Map map) {
		return sqlSession.selectOne("userInfo",map);
	}

	public boolean updateAddr(Map<String, String> map) {
		return sqlSession.update("updateAddr",map)==1?true:false;
	}

	public int getPayNo(Map map) {
		return sqlSession.selectOne("payLogNo", map);
	}

	public PayDTO payBalance(Map map) {
		return sqlSession.selectOne("payBalance", map);	
	}

	public int payDeposit(Map map) {
		return sqlSession.insert("payDeposit", map);	
	}


	public int payWithdraw(Map map) {
		return sqlSession.insert("payWithdraw", map);	
	}

}
