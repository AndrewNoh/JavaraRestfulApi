package com.javara.market.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javara.market.model.dto.PayDTO;
import com.javara.market.model.dto.UserDTO;
import com.javara.market.model.service.ChatService;
import com.javara.market.model.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@GetMapping("/Hello")
	public String hello() {
		return "asdfasdf";
	}

	@CrossOrigin
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String home(HttpServletRequest request) throws Exception {
		Map json = new HashMap();

		json.put("success", true);
		json.put("data", 10);
		json.put(null, 10);

		return json.get("success").toString();
	}

	@CrossOrigin
	@RequestMapping("/users/login")
	public @ResponseBody Map<String, Boolean> login(@RequestBody UserDTO loginDTO) {
		boolean isLogin = userService.isLogin(loginDTO);
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("isLogin", isLogin);
		return result;
	}

	@CrossOrigin
	@RequestMapping("users/userinfo")
	public @ResponseBody Map userInfo(@RequestParam String email) {
		Map map = new HashMap();
		map.put("email", email);
		UserDTO dto = userService.selectOne(map);
		map.put("userDTO", dto);
		map.put("userno", dto.getUserno());
		PayDTO balance = userService.payBalance(map);
		map.put("payDTO", balance);
		return map;
	}

	@CrossOrigin
	@RequestMapping("users/userAddr")
	public @ResponseBody Map<String, Boolean> userAddr(@RequestBody Map map) {
		UserDTO dto = userService.selectOne(map);
		map.put("userno", dto.getUserno());
		boolean isUpdate = userService.UpdateAddr(map);
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("isUpdate", isUpdate);
		return result;
	}

	@CrossOrigin
	@RequestMapping("users/deposit")
	public @ResponseBody Map deposit(@RequestBody Map map) {
		System.out.println(map.get("room_no"));
		if (map.get("room_no") != null) {
			chatService.updateChatMsg(map);
		}
		map.put("deposit", map.get("balance"));
		UserDTO dto = userService.selectOne(map);
		map.put("userno", dto.getUserno());
		PayDTO balance = userService.payBalance(map);
		int depositBalance = Integer.parseInt(balance.getBalance()) + Integer.parseInt(map.get("balance").toString());
		map.put("balance", depositBalance);
		map.put("payno", balance.getPayno());
		PayDTO payDTO = userService.payDeposit(map);
		map.put("payDTO", payDTO);
		return map;
	}

	@CrossOrigin
	@RequestMapping("users/remittance")
	public @ResponseBody Map remittance(@RequestBody Map map) {
		map.put("userno", map.get("userno"));
		PayDTO balance = userService.payBalance(map);
		int depositBalance = Integer.parseInt(balance.getBalance()) - (int) map.get("withdraw");
		map.put("balance", depositBalance);
		map.put("payno", balance.getPayno());
		userService.payWithdraw(map);
		return map;
	}
	@CrossOrigin
	@RequestMapping("users/getbalance")
	public @ResponseBody Map getbalance(@RequestBody Map map) {
		PayDTO balance = userService.payBalance(map);
		map.put("balance", balance.getBalance());
		return map;
	}
}
