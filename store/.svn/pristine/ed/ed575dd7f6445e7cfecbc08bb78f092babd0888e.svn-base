package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;
/**
 * 处理收货地址相关的持久层
 * @author JAVA
 *
 */
public interface AddressMapper {
	/**
	 * 插入收货地址数据
	 * @param address  收货地址数据
	 * @return  返回数据库受影响的行数
	 */
	Integer addnew(Address address);
	
	/**
	 * 根据aid删除指定的收货地址信息
	 * @param aid  收货地址aid
	 * @return 返回数据库受影响的行数
	 */
	Integer deleteByAid(Integer aid);
	
	
	/**
	 * 将指定的收货地址设置为默认
	 * @param aid 收货地址id
	 * @param modifiedUser 修改 人
	 * @param modifiedTime 修改时间
	 * @return  受影响的行数
	 */
	Integer updateDefault(
			@Param("aid")Integer aid,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	/**
	 * 将用户id对应的所有收货地址设置为非默认
	 * @param uid 用户id
	 * @return   受影响的行数
	 */
	Integer updateNonDefault(Integer uid);
	
	/**
	 * 依据uid统计收货地址条数
	 * @param uid 用户uid
	 * @return  收货地址条数
	 */
	Integer countByUid(Integer uid);
	/**
	 * 依据收货地址对应id查找收货地址详细信息
	 * @param aid  收货地址id
	 * @return  收货地址详细信息
	 */
	Address findByAid(Integer aid);
	
	/**
	 * 依据用户uid查询收货地址信息
	 * @param uid  用户uid
	 * @return 所有收货地址数据列表
	 */
	List<Address> findByUid(Integer uid);
	
	/**
	 * 根据用户id查找对应的收货地址信息并依据修改时间倒序排列取最大的
	 * @param uid 用户id
	 * @return  最近修改的收货地址的详细信息
	 */
	Address findLastModified(Integer uid);
}
