package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Product;
/**
 * 处理商品数据的持久层接口
 * @author JAVA
 *
 */
public interface ProductMapper {

	List<Product> findHotList();
	
	Product findById(Integer id);
	
	Integer updateNum(
			@Param("id")Integer id,
			@Param("num")Integer num);
}