package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
/**
 * 处理订单和订单商品的数据的持久层接口
 * @author JAVA
 *
 */
public interface OrderMapper {
	Integer insertOrder(Order order);
	
	Integer insertOrderItem(OrderItem orderItem);
	
	Integer updateStatus(
			@Param("oid")Integer oid,
			@Param("status")Integer status,
			@Param("username")String username,
			@Param("modifiedTime")Date modifiedTime);
	Order findByOid(Integer oid);
}
