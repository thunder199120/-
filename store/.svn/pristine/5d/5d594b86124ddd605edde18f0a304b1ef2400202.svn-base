package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Product;
import cn.tedu.store.service.ex.ProductNotFoundException;
/**
 * 处理商品数据的持久层接口
 * @author JAVA
 *
 */
public interface IProductService {
	
	List<Product> getHotList();
	
	Product getById(Integer id) throws ProductNotFoundException;
	
	void reduceNum(Integer pid, Integer amount);
	
	void addNum(Integer pid, Integer amount);
}
