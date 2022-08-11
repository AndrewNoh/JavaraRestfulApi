package com.javara.market.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javara.market.model.dto.ChatDTO;

@Repository
public class ChatDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<ChatDTO> chatlist(Map map) {
		return sqlSession.selectList("chatlist", map);
	}

	public List<ChatDTO> unreadcount(Map map) {
		return sqlSession.selectList("unreadcount", map);
	}

	public List<ChatDTO> joinchat(Map map) {
		return sqlSession.selectList("joinchat", map);
	}

	public int readmsg(Map map) {
		return sqlSession.update("readmsg", map);
	}

	public int insertChatimg(Map map) {
		return sqlSession.insert("insertChatimg", map);
	}

	public int insertChatMessage(Map map) {
		return sqlSession.insert("insertChatMessage", map);
	}

	public int updateChatRoomnoMsg(Map map) {
		return sqlSession.update("updateChatRoomno", map);
	}

	public void updateCall(Map map) {
		sqlSession.update("updateChatMsg", map);
	}

	public void updateChatMsg(Map map) {
		sqlSession.update("updateChatMsg", map);
	}

}
