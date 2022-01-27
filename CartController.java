package jp.co.internous.quest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.quest.model.domain.TblCart;
import jp.co.internous.quest.model.domain.dto.CartDto;
import jp.co.internous.quest.model.form.CartForm;
import jp.co.internous.quest.model.mapper.TblCartMapper;
import jp.co.internous.quest.model.session.LoginSession;


@Controller
@RequestMapping("/quest/cart")
public class CartController {

	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	TblCartMapper cartMapper;
	
	Gson gson = new Gson();
	
	//初期表示
	@RequestMapping("/")
	public String index(Model m) {
		
		int userId = loginSession.isLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		
		List<CartDto> cartList = cartMapper.findByUserId(userId);
		
		m.addAttribute("loginSession",loginSession);
		m.addAttribute("cartList", cartList);
		return "cart";
		
	}
	
	//カート追加処理
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		
		int userId = loginSession.isLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		
		f.setUserId(userId);
		
		TblCart cart = new TblCart(f);
		
		int result = 0;
		
		if(cartMapper.findCountByUserIdAndProuductId(userId, f.getProductId()) > 0) {
			result = cartMapper.update(cart);
		} else {
			result = cartMapper.insert(cart);
		}
		if (result > 0) {
			List<CartDto> cartList = cartMapper.findByUserId(userId);
			m.addAttribute("loginSession",loginSession);
			m.addAttribute("cartList", cartList);
		}
		return "cart";
		
	}
	
	//カート削除
	@SuppressWarnings("unchecked")
	@RequestMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		
		int result = 0;
		
		Map<String, List<String>> map = gson.fromJson(checkedIdList, Map.class);
		List<String> checkedIds = map.get("checkedIdList");
		
		
		result = cartMapper.deleteById(checkedIds);
		return result > 0;
		
	}
	
}
