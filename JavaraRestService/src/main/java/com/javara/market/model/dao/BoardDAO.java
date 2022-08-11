package com.javara.market.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javara.market.model.dto.BoardDTO;
import com.javara.market.model.dto.ImageDTO;
import com.javara.market.model.dto.ShowDTO;
import com.javara.market.model.dto.TenBoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public List<BoardDTO> selectList(Map map) {
		return sessionTemplate.selectList("getAuctionList", map);
	}

	public List<ImageDTO> selectListImage(Map map) {
		return sessionTemplate.selectList("getAuctionImage", map);
	}

	public BoardDTO selectOne(Map map) {
		return sessionTemplate.selectOne("getAuctionListOne", map);
	}

	public List<TenBoardDTO> selectAuctionList(Map map) {
		return sessionTemplate.selectList("getAuctionTen", map);
	}

	public List<TenBoardDTO> getRecommendTen(Map map) {
		return sessionTemplate.selectList("getRecommendTen", map);
	}

	public boolean isLike(Map data) {
		return (Integer) sessionTemplate.selectOne("isLike", data) == 1 ? true : false;
	}

	public boolean updateLike(Map map) {
		return sessionTemplate.update("updateLike", map) == 1 ? true : false;
	}

	public int updateAuctionLike(Map map) {
		return sessionTemplate.update("updateAuctionLike", map);
	}

	public boolean deleteLike(Map map) {
		return sessionTemplate.update("deleteLike", map) == 1 ? true : false;
	}

	public int updatePrice(Map map) {
		return sessionTemplate.update("updatePrice", map);
	}

	public int updateStatus(Map map) {
		return sessionTemplate.update("updateAuctionStatus", map);
	}

	public List<ShowDTO> mypageSelllist(Map map) {

		return sessionTemplate.selectList("mypageSelllist", map);
	}

	public List<ShowDTO> mypagelikelist(Map map) {
		return sessionTemplate.selectList("mypagelikelist", map);
	}

	public List<ShowDTO> mypagepurchaselist(Map map) {
		return sessionTemplate.selectList("mypagepurchaselist", map);
	}

	public List<ShowDTO> categoryShow(Map map) {
		return sessionTemplate.selectList("categoryShow", map);
	}
	
	public List<ShowDTO> searchAuction(Map map) {
		return sessionTemplate.selectList("searchAuction", map);
	}

	public ShowDTO getAuctionListChat(Map map) {
		return sessionTemplate.selectOne("getAuctionListChat", map);
	}

}
