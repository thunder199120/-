package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

/**
 * 处理用户相关数据的持久层接口
 * @author thunder
 *
 */
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param 插入数据
	 * @return 返回数据库受影响的行数
	 */
	Integer addnew(User user);
	

	/**
	 * 修改密码
	 * @param uid  用户id
	 * @param password 新密码
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 返回数据库受影响的行数
	 */
	Integer updatePassword(
			@Param("uid")Integer uid,
			@Param("password")String password,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 更新图像
	 * @param uid  用户id
	 * @param avatar 新图像的路径
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 返回数据库受影响的行数
	 */
	Integer updateAvatar(
			@Param("uid")Integer uid,
			@Param("avatar")String avatar,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 修改个人资料
	 * @param user  用户的个人资料
	 * @return 返回数据库受影响的行数
	 */
	Integer updateInfo(User user);
	
	/**
	 * 根据用户名查询用户名数据
	 * @param 依据用户输入的用户名,去数据库查找是否存在同名的
	 * @return 返回对应查找结果判断是否重名
	 */
	User findByUsername(String username);
	
	/**
	 * 根据用户id查 用户数据
	 * @param uid
	 * @return 
	 */
	User findByUid(Integer uid);
	
	
}
