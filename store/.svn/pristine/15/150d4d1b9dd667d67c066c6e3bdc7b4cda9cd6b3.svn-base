package cn.tedu.store.service;


import java.util.List;
import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

public interface IAddressService {
	/*
	 * 每个用户允许创建的最大收货地址数量
	 */
	public static final int MAX_COUNT = 10;
	/**
	 * 添加收货地址信息
	 * @param uid 用户id
	 * @param username 用户名
	 * @param address 添加的地址信息
	 * @throws AddressCountLimitException 用户收货地址数量超出了限制
	 * @throws InsertException 插入数据未知异常
	 */
	void create(Integer uid,String username,Address address) 
			throws AddressCountLimitException,InsertException;
	/**
	 * 依据用户id查所有收货地址信息
	 * @param uid 用户id
	 * @return  所有收货地址信息
	 */
	List<Address> getByUid(Integer uid);
	
	/**
	 * 设置收货地址的默认值
	 * @param aid 收货地址信息id
	 * @param uid 登录用户id
	 * @param username 修改人
	 */
	void  setDefault(Integer aid,Integer uid ,String username) 
						throws AddressNotFoundException,AccessDeniedException,UpdateException;
	/**
	 * 删除收货地址数据
	 * @param aid 即将删除收货地址的id
	 * @param uid 用户的id
	 * @param username 修改人
	 * @throws AddressNotFoundException  
	 * @throws UpdateException 更新数据异常
	 * @throws DeleteException 删除数据异常
	 * @throws AccessDeniedException 
	 */
	void delete(Integer aid,Integer uid,String username)
			throws AddressNotFoundException,UpdateException,DeleteException,AccessDeniedException;
	
	Address getByAid(Integer aid);
}
