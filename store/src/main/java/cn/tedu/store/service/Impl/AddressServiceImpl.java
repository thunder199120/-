package cn.tedu.store.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IDistrictService districtService;
	
	/**
	 * 添加收货地址信息
	 * @param address 收货地址数据
	 */
	private void  addnew(Address address) throws InsertException{
		Integer rows = addressMapper.addnew(address);
		if(rows!=1){
			throw new InsertException("插入数据时出现未知错误!请联系管理员");
		}
	}
	/**
	 * 统计某用户收货地址数量
	 * @param uid  用户的id
	 * @return  用户收货地址数量
	 */
	private Integer countByUid(Integer uid){
		if(uid == null||uid<1){
			throw new IllegalArgumentException("用户id不存在!");
		}
		return addressMapper.countByUid(uid);
	}
	/**
	 * 依据编号查对应的名字
	 * @param code 编号
	 * @return 对应的名字
	 */
	private String getDistrictNameByCode(String code){
		District result = districtService.getByCode(code);
		return result == null ? "" : result.getName();
	}
	/**
	 * 依据用户id查找所有的收货地址信息
	 * @param uid  用户id
	 * @return  所有的收货地址信息
	 */
	private  List<Address> findByUid(Integer uid){
		return addressMapper.findByUid(uid);
	}
	
	/**
	 * 将指定的收货地址设置为默认收货地址
	 * @param aid 收货地址信息id
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @throws UpdateException 修改失败
	 */
	private void updateDefault(Integer aid,String modifiedUser,Date modifiedTime)
			 											throws UpdateException{
		Integer rows = addressMapper.updateDefault(aid, modifiedUser, modifiedTime);
		if(rows!=1){
			throw new UpdateException("设置默认收货地址失败,更新数据异常!");
		}
	}
	/**
	 * 将id用户的所有收货地址设置为非默认
	 * @param uid 用户id
	 */
	private void  updateNonDefault(Integer uid) throws UpdateException{
		Integer rows = addressMapper.updateNonDefault(uid);
		if(rows==0){
			throw new UpdateException("设置默认收货地址失败,更新数据异常!");
		}
	}
	/**
	 * 根据收货地址信息id查找对应所有信息
	 * @param aid 收货地址信息id
	 * @return 对应id收货地址详细信息
	 */
	private Address findByAid(Integer aid){
		return addressMapper.findByAid(aid);
	}
	
	private void deleteByAid(Integer aid){
		Integer rows = addressMapper.deleteByAid(aid);
		if(rows != 1){
			throw new DeleteException("删除收货地址失败!");
		}
	}
	
	private Address findLastModified(Integer uid){
		return addressMapper.findLastModified(uid);
	};
	
	@Override
	public void create(Integer uid, String username, Address address)
			throws AddressCountLimitException, InsertException {
		// 基于参数uid查询该用户的收货地址数量
		Integer count = countByUid(uid);
	    // 判断数量是否达到上限值
		if(count>= MAX_COUNT){
			// 是：AddressCountLimitException
			throw new AddressCountLimitException("收货地址数量超过了限制");
		}
	    // 补全参数address中的数据：uid
		address.setUid(uid);
	    //补全参数address中的数据：省市区的名称
		String provinceName = getDistrictNameByCode(address.getProvinceCode());
		String cityName = getDistrictNameByCode(address.getCityCode());
		String areaName = getDistrictNameByCode(address.getAreaCode());
		address.setProvinceName(provinceName);
		address.setCityName(cityName);
		address.setAreaName(areaName);
		// 补全参数address中的数据：isDefault，根据收货地址数量确定该属性的值
		Integer isDefault =  count == 0 ? 1 : 0;
		address.setIsDefault(isDefault);
	    // 创建当前时间对象
		
	    // 补全参数address中的数据：4项日志
		address.setCreatedUser(username);
		address.setCreatedTime(new Date());
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
	    // 执行增加
		addnew(address);
		
	}
	@Override
	public List<Address> getByUid(Integer uid) {
		return findByUid(uid);
	}
	@Override
	@Transactional
	public void setDefault(Integer aid, Integer uid, String username) 
			throws AddressNotFoundException,AccessDeniedException,UpdateException{
		//依据收货地址aid查询数据
		Address result = findByAid(aid);
		//判断结果是否为null
		if(result==null){
			//是,抛出AddressNotFoundException
			throw new AddressNotFoundException("设置默认收货地址失败！尝试操作的数据不存在！");
		}
		//判断查询结果的uid与参数uid是否不一致
		if(!result.getUid().equals(uid)){
			//是,抛出AccessDeniedException
			throw new AccessDeniedException("设置默认收货地址失败！不允许访问他人的数据!");
		}
		//将用户的所有地址设置为非默认
		updateNonDefault(uid);
		//将指定的收货地址设置为默认
		updateDefault(aid,username,new Date());
	}
	@Override
	@Transactional
	public void delete(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, UpdateException, DeleteException, AccessDeniedException {
		// 根据aid查询收货地址数据
		Address result = findByAid(aid);
	    // 判断结果是否为null
		if(result == null){
			// 是：抛出AddressNotFoundException
			throw new AddressNotFoundException("删除收货地址失败！尝试操作的数据不存在！");
		}
	    // 判断结果中的uid与参数uid是否不一致
		if(!result.getUid().equals(uid)){
			// 是：抛出AccessDeniedException
			throw new AccessDeniedException("删除收货地址失败！不允许访问他人的数据!");
		}
	    // 执行删除
		deleteByAid(aid);
	    // 判断此前的查询结果中的isDefault是否为0
		if(result.getIsDefault() == 0){
			// return;
			return;
		}

	    // 统计当前用户的收货地址数量：countByUid()
		Integer count = addressMapper.countByUid(uid);
	    // 判断剩余收货地址数量是否为0
		if(count == 0){
			// return;
			return;
		}

	    // 查询当前用户最近修改的收货地址
		Address LastModified = findLastModified(uid);
	    // 将最近修改的收货地址设置为默认
		updateDefault(LastModified.getAid(),username,new Date());
	}
	@Override
	public Address getByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	}
	
	
	
}
