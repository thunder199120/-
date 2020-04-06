package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
	@Autowired
	private  IAddressService  addressService;
	@RequestMapping("create")
	public  JsonResult<Void> create(HttpSession session,Address address){
		//从session获取uid和username
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		//调用业务层对象执行新增收货地址
		addressService.create(uid, username, address);
		//返回成功
		return new JsonResult<>(SUCCESS);
	}
	@GetMapping("/")
	public JsonResult<List<Address>>  getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Address> data = addressService.getByUid(uid);
		return new JsonResult<List<Address>>(SUCCESS,data);
	}
	@RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(HttpSession session,
			@PathVariable("aid")Integer aid){
		//从session获取uid和username
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		addressService.setDefault(aid, uid, username);
		return new JsonResult<>(SUCCESS);
	}
	
	@RequestMapping("{aid}/delete")
	public JsonResult<Void> delete(HttpSession session,
			@PathVariable("aid")Integer aid){
		//从session获取uid和username
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		addressService.delete(aid, uid, username);
		return new JsonResult<>(SUCCESS);
	}
}
