package com.javara.market.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javara.market.model.dto.BoardDTO;
import com.javara.market.model.dto.ImageDTO;
import com.javara.market.model.dto.ShowDTO;
import com.javara.market.model.dto.TenBoardDTO;
import com.javara.market.model.dto.UserDTO;
import com.javara.market.model.service.BoardService;
import com.javara.market.model.service.UserService;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

	@CrossOrigin
	@RequestMapping("board/selectList")
	public @ResponseBody Map userAddr(@RequestBody Map map) {
		UserDTO dto = userService.selectOne(map);
		map.put("simpleAddress", dto.getSimpleaddress());
		List<BoardDTO> boardList = boardService.selectList(map);
		List<List<ImageDTO>> imageList = new Vector<List<ImageDTO>>();
		List<UserDTO> userLists = new Vector<UserDTO>();
		map.clear();
		for (int i = 0; i < boardList.size(); i++) {
			map.put("auction_no", boardList.get(i).getAuction_no());
			List<ImageDTO> images = boardService.selectListImage(map);
			map.put("userno", boardList.get(i).getUserNo());
			UserDTO userList = userService.selectOne(map);
			userLists.add(userList);
			imageList.add(images);
		}

		map.clear();
		map.put("simpleAddress", dto.getSimpleaddress());
		map.put("userLists", userLists);
		map.put("imageList", imageList);
		map.put("boardList", boardList);

		return map;
	}

	@CrossOrigin
	@RequestMapping("board/viewSelect")
	public @ResponseBody Map viewSelect(@RequestBody Map map) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("auction_no", String.valueOf(map.get("auction_no")));
		BoardDTO boardNoselectOne = boardService.selectOne(data);
		data.put("userno", boardNoselectOne.getUserNo());
		List<TenBoardDTO> getAuctionTen = boardService.selectAuctionList(data);
		data.put("category", boardNoselectOne.getCategory());
		List<TenBoardDTO> getRecommendTen = boardService.getRecommendTen(data);
		List<ImageDTO> images = boardService.selectListImage(data);
		UserDTO userInfo = userService.selectOne(data);
		UserDTO myInfo = userService.selectOne(map);
		data.put("myno", String.valueOf(myInfo.getUserno()));
		map.put("isLike", boardService.isLike(data));
		map.put("userInfo", userInfo);
		map.put("imageList", images);
		map.put("boardNoselectOne", boardNoselectOne);
		map.put("getAuctionTen", getAuctionTen);
		map.put("getRecommendTen", getRecommendTen);
		return map;
	}

	@CrossOrigin
	@RequestMapping("board/likeOne")
	public @ResponseBody Map likeOne(@RequestBody Map map) {
		UserDTO myInfo = userService.selectOne(map);
		map.put("userno", String.valueOf(myInfo.getUserno()));
		if((Boolean) map.get("isLike")) {
			boolean updateLike = boardService.updateLike(map);
			System.out.println(updateLike);
			map.put("updateLike", updateLike);
		}else {
			boolean deleteLike = boardService.deleteLike(map);
			map.put("deleteLike", deleteLike);
		}
		return map;
	}
	
	@CrossOrigin
	@RequestMapping("board/updatePrice")
	public @ResponseBody Map updatePrice(@RequestBody Map map) {
		UserDTO myInfo = userService.selectOne(map);
		map.put("userno", String.valueOf(myInfo.getUserno()));
		int affected = boardService.updatePrice(map);
		if(affected ==1) {
			map.put("sucess", true);
		}
		return map;
	}
	
	@CrossOrigin
	@RequestMapping("board/changeStatus")
	public @ResponseBody Map changeStatus(@RequestBody Map map) {
		boardService.updateStatus(map);
		return map;
	}
	
	@CrossOrigin
	@RequestMapping("board/choseMenu")
	public @ResponseBody Map choseMenu(@RequestBody Map map) {
		UserDTO myInfo = userService.selectOne(map);
		map.put("userno", String.valueOf(myInfo.getUserno()));
		map.put("simpleAddress", String.valueOf(myInfo.getSimpleaddress()));
		List<ShowDTO> showList = boardService.choseMenu(map);
		map.put("showDTO", showList);
		return map;
	}
	
}
