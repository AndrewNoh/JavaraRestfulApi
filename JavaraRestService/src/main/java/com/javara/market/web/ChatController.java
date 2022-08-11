package com.javara.market.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javara.market.model.dto.ChatDTO;
import com.javara.market.model.dto.ShowDTO;
import com.javara.market.model.dto.UserDTO;
import com.javara.market.model.service.BoardService;
import com.javara.market.model.service.ChatService;
import com.javara.market.model.service.UserService;

@RestController
public class ChatController {
	@Autowired
	ChatService chatService;
	@Autowired
	UserService userService;
	@Autowired
	BoardService boardService;

	@CrossOrigin
	@RequestMapping("chat/chattingroom")
	public @ResponseBody Map chattingroom(@RequestBody Map map) {
		UserDTO userNickname = userService.selectOne(map);
		map.put("userno", userNickname.getUserno());
		map.put("userNickname", userNickname.getNickname());
		map.put("profileimage", userNickname.getProfile_img());
		List<ChatDTO> chatlist = chatService.chatlist(map);
		map.put("chatlist", chatlist);
		map.put("unread_count", "1");
		map.put("senduserno", userNickname.getUserno());
		List<ChatDTO> unreadcount = chatService.unreadcount(map);
		map.put("unreadcount", unreadcount);
		return map;
	}

	@CrossOrigin
	@RequestMapping("chat/joinchat")
	public @ResponseBody Map joinchat(@RequestBody Map map) {
		List<ChatDTO> joinchat = chatService.joinchat(map);
		map.put("chatMsgList", joinchat);
		ShowDTO showDTO = boardService.getAuctionListChat(map);
		map.put("chatConnBoard", showDTO);
		UserDTO myInfomation = userService.selectOne(map);
		map.put("userno", myInfomation.getUserno());
		map.put("myInfomation", myInfomation);
		chatService.readmsg(map);
		return map;	
	}

	@CrossOrigin
	@RequestMapping("chat/insertchat")
	public @ResponseBody Map insertchat(@RequestBody Map map) {
		chatService.insertchat(map);
		return map;
	}
	
	


}
