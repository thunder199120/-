package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

public interface IOrderService {
	public static interface Status{
		int UNPAID = 0;
		int PAID = 1;
		int CANCEL = 2;
		int CLOSED = 3;
	}
	
	Order create(Integer aid,Integer[] cids,Integer uid,String username);
	
	void changeStatus(Integer oid,Integer status,String username);
	/**
	 * 关闭订单
	 * @param oid
	 * @param orderItem
	 * @param username
	 */
	void close(Integer oid,List<OrderItem> orderItem,String username);

}
