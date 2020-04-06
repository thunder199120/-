package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
/**
 * 处理用户数据的业务层接口
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户数据
	 * @throws UsernameDuplicateException 用户名冲突异常，例如尝试注册已经被占用的用户名
	 * @throws InsertException 插入数据异常
	 */
	void reg(User user) throws UsernameDuplicateException,InsertException;
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return  成功登录的用户信息
	 * @throws UserNotFoundException  用户名不存在
	 * @throws PasswordNotMatchException  密码错误
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	/**
	 * 密码修改
	 * @param uid
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @param modifiedUser 当前登录的用户名
	 * @throws UserNotFoundException 用户名未找到
	 * @throws PasswordNotMatchException 
	 * @throws UpdateException
	 */
	void changePassword(Integer uid,String oldPassword,String newPassword,String modifiedUser)
			throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 修改个人信息
	 * @param uid 
	 * @param username
	 * @param user 修改的数据
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(Integer uid,String username,User user) 
				throws UserNotFoundException,UpdateException;
	
	/**
	 * 更新图像
	 * @param uid
	 * @param avatar  新图像路径
	 * @param modifiedUser
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid,String avatar,String modifiedUser)
			throws UserNotFoundException,UpdateException;
	
	/**
	 * 返回用户信息于页面
	 * @param uid
	 * @return
	 * @throws UserNotFoundException
	 */
	User getByUid(Integer uid) throws UserNotFoundException;
}
