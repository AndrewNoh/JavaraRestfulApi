package com.javara.market.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javara.market.model.dao.UserDAO;
import com.javara.market.model.dto.PayDTO;
import com.javara.market.model.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;

	public boolean isLogin(UserDTO loginDTO) {
		return userDAO.isLogin(loginDTO);
	}

	public UserDTO selectOne(Map map) {
		return userDAO.selectOne(map);
	}

	public boolean UpdateAddr(Map<String, String> map) {
		return userDAO.updateAddr(map);
	}

	public PayDTO payBalance(Map map) {
		map.put("payno", (int) userDAO.getPayNo(map));
		return userDAO.payBalance(map);
	}

	public PayDTO payDeposit(Map map) {
		if(userDAO.payDeposit(map)!=1)
			System.out.println("입금실패");
		return userDAO.payBalance(map);
	}
	
	public int payWithdraw(Map map) {
		return userDAO.payWithdraw(map);
	}

}
