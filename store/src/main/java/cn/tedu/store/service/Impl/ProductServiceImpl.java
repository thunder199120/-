package cn.tedu.store.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.ProductNotFoundException;
import cn.tedu.store.service.ex.ProductOutOfStockException;
import cn.tedu.store.service.ex.UpdateException;
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private  ProductMapper productMapper;
	private List<Product> findHotList(){
		return productMapper.findHotList(); 
	}
	private Product findById(Integer id){
		return productMapper.findById(id);
	}
	
	private void updateNum(Integer id,Integer num){
		Integer rows = productMapper.updateNum(id, num);
		if(rows != 1){
			throw new UpdateException("更新商品数量失败!更新商品出现未知错误!");
		}
	}
	@Override
	public List<Product> getHotList() {
		List<Product> list  = findHotList();
		for (Product product : list) {
			product.setCategoryId(null);
	        product.setItemType(null);
	        product.setSellPoint(null);
	        product.setNum(null);
	        product.setStatus(null);
	        product.setPriority(null);
	        product.setCreatedUser(null);
	        product.setCreatedTime(null);
	        product.setModifiedUser(null);
	        product.setModifiedTime(null);
		}
		return  list;
	}
	@Override
	public Product getById(Integer id) throws ProductNotFoundException{
		Product result = findById(id);
		if(result == null){
			throw new ProductNotFoundException("查询商品详情失败!查询数据不存在!");
		}
		result.setPriority(null);
		result.setCreatedUser(null);
		result.setCreatedTime(null);
		result.setModifiedUser(null);
		result.setModifiedTime(null);
		return result;
	}
	@Override
	public void reduceNum(Integer pid, Integer amount) {
		// 通过参数pid查询商品数据
		Product result = findById(pid);
	    // 判断查询结果是否为null：ProductNotFoundException
		if(result == null){
			throw new ProductNotFoundException("更新商品库存失败!尝试访问的数据不存在!");
		}
		// 判断查询结果中的num(当前库存)是否小于参数amount(将要购买或减少的库存量)：ProductOutOfStockException
		if(result.getNum()<amount){
			throw new ProductOutOfStockException("更新商品库存失败！当前商品库存已经不足!");
		}
		// 执行减少库存	
		updateNum(pid,result.getNum()-amount);
	}
	@Override
	public void addNum(Integer pid, Integer amount) {
		// 通过参数pid查询商品数据
		Product result = findById(pid);
		// 判断查询结果是否为null：ProductNotFoundException
		if(result == null){
			throw new ProductNotFoundException("更新商品库存失败!尝试访问的数据不存在!");
		}
		// 执行增加库存	
		updateNum(pid,result.getNum()+amount);
	}

}
