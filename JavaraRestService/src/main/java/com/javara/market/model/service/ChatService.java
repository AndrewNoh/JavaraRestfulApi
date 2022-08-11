package com.javara.market.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javara.market.model.dao.ChatDAO;
import com.javara.market.model.dto.ChatDTO;

@Service

public class ChatService {

	@Autowired
	ChatDAO chatDAO;

	public List<ChatDTO> chatlist(Map map) {
		return chatDAO.chatlist(map);
	}

	public List<ChatDTO> unreadcount(Map map) {
		return chatDAO.unreadcount(map);
	}

	public List<ChatDTO> joinchat(Map map) {
		return chatDAO.joinchat(map);
	}

	public int readmsg(Map map) {
		return chatDAO.readmsg(map);
	}

	public void insertchat(Map map) {
		int result = 0;
		if (map.get("chatcontent") != null && map.get("chatcontent").equals("사진")) {
			result = chatDAO.insertChatimg(map);
		} else {
			result = chatDAO.insertChatMessage(map);
		}
		result = chatDAO.updateChatRoomnoMsg(map);
	}

	public void updateCall(Map map) {
		chatDAO.updateCall(map);
	}

	public void updateChatMsg(Map map) {
		chatDAO.updateChatMsg(map);
	}

}
