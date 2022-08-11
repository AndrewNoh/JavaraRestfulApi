package com.javara.market.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javara.market.model.dao.BoardDAO;
import com.javara.market.model.dto.BoardDTO;
import com.javara.market.model.dto.ImageDTO;
import com.javara.market.model.dto.ShowDTO;
import com.javara.market.model.dto.TenBoardDTO;

@Service
public class BoardService {
	@Autowired
	BoardDAO dao;

	public List<BoardDTO> selectList(Map map) {
		return dao.selectList(map);
	}

	public List<ImageDTO> selectListImage(Map map) {
		return dao.selectListImage(map);
	}

	public BoardDTO selectOne(Map map) {
		return dao.selectOne(map);
	}

	public List<TenBoardDTO> selectAuctionList(Map map) {
		return dao.selectAuctionList(map);
	}

	public List<TenBoardDTO> getRecommendTen(Map map) {
		return dao.getRecommendTen(map);
	}

	public boolean isLike(Map<String, String> data) {
		return dao.isLike(data);
	}

	public boolean updateLike(Map map) {
		map.put("isPlus", true);
		dao.updateAuctionLike(map);
		return dao.updateLike(map);
	}

	public boolean deleteLike(Map map) {
		map.put("isPlus", false);
		dao.updateAuctionLike(map);
		return dao.deleteLike(map);
	}

	public int updatePrice(Map map) {
		return dao.updatePrice(map);
	}

	public int updateStatus(Map map) {
		return dao.updateStatus(map);
	}

	public List<ShowDTO> choseMenu(Map map) {
		String str = (String) map.get("choseMenu");
		switch (str) {
		case "sell":
			return dao.mypageSelllist(map);
		case "like":
			return dao.mypagelikelist(map);
		case "category":
			return dao.categoryShow(map);
		case "search":
			return dao.searchAuction(map);
		default:
			return dao.mypagepurchaselist(map);
		}
	}

	public ShowDTO getAuctionListChat(Map map) {
		return dao.getAuctionListChat(map);
	}

}
