package cn.tedu.store.service.Impl;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public void reg(User user) throws UsernameDuplicateException,InsertException{
		//根据参数user中的getUsername()获得尝试注册的用户名
		String username = user.getUsername();
		//根据获取的用户名查询用户数据
		User result  =  userMapper.findByUsername(username);
		//判断结果是否不为null
		if(result != null){
			//是：用户名已经被占用，抛出UsernameDuplicateException
			throw new UsernameDuplicateException("注册失败!尝试注册的用户名("+username +")已存在");
		}
		
		// 向参数user中不全属性:盐值
		String salt = UUID.randomUUID().toString().toUpperCase();
		user.setSalt(salt);
		// 取出参数user中的原始密码
		String password = user.getPassword();
		//  将原始密码加密
		String md5Password = getMd5Password(password,salt);
		// 向参数user中补全属性:加密后的密码
		user.setPassword(md5Password);
		//向参数user中补全属性：isDelete-0
		user.setIsDelete(0);
		Date date = new Date();
		//向参数user中补全属性：4项日志
		user.setCreatedUser(username);
		user.setCreatedTime(date);
		user.setModifiedUser(username);
		user.setModifiedTime(date);
		//执行注册
		Integer rows = userMapper.addnew(user);
		if(rows != 1){
			throw new InsertException("注册失败!插入数据时出现未知错误!请联系管理员!");
		}
		
	}
	
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		//根据username去查询,返回查询结果
		User result = userMapper.findByUsername(username);
		//判断查询结果是否为null
		if(result == null){
			//是,抛出UserNotFoundException异常
			throw new UserNotFoundException("登录失败!用户数据不存在!");
		}
		
		//判断查询结果中isDelete是否为1
		if(result.getIsDelete() == 1){
			//是,抛出UserNotFoundException异常
			throw new UserNotFoundException("登录失败!用户数据不存在!");
		}
		//依据查询结果获得盐值
		String salt = result.getSalt();
		//根据password和盐值进行加密
		String md5Password = getMd5Password(password,salt);
		//判断加密密码与查询结果中的password是否不一致
		if(!result.getPassword().equals(md5Password)){
			//是,抛出PasswordNotMatchException异常
			throw new PasswordNotMatchException("登录失败!密码错误!");
		}
		//将查询结果中的password设为null
		result.setPassword(null);
		//将查询结果中的salt设为null
		result.setSalt(null);
		//将查询结果中的isDelete设为null
		result.setIsDelete(null);
		//返回查询结果
		return result;
	}
	
	/**
	 * 
	 */
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword, String modifiedUser)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		//根据uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null){
		//是,抛出UserNotFoundException
			throw new UserNotFoundException("修改密码失败!用户数据不存在!");
		}
		//判断查询结果中isDelete是否为1
		if(result.getIsDelete() == 1){
			//是,抛出UserNotFoundException
			throw new UserNotFoundException("修改密码失败!用户数据不存在!");
		}
		
		//从查询结果中获取盐值
		String salt = result.getSalt();
		//对参数oldPassword进行加密,得到oldMd5Password
		String oldMd5Password = getMd5Password(oldPassword,salt);
		//判断查询结果中的密码与oldMd5Password是否不匹配
		if(!result.getPassword().equals(oldMd5Password)){
			//是,抛出PasswordNotMatchException
			throw new PasswordNotMatchException("修改密码失败!原密码错误!");
		}
		//对参数newPassword进行加密,得到newMd5Password
		String newMd5Password = getMd5Password(newPassword,salt);
		//执行更新,得到受影响的行数
		Date modifiedTime = new Date(); 
		Integer rows = userMapper.updatePassword(uid, newMd5Password,modifiedUser, modifiedTime);
		//判断受影响的行数是否不为1
		if(rows!=1){
		//是,抛出UpdateException
			throw new UpdateException("修改密码失败!更新数据出现未知错误!");
		}
	}
	
	

	@Override
	public void changeInfo(Integer uid, String username, User user) throws UserNotFoundException, UpdateException {
		//根据uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null){
		//是,抛出UserNotFoundException
			throw new UserNotFoundException("修改个人资料失败!用户数据不存在!");
		}
		//判断查询结果中isDelete是否为1
		if(result.getIsDelete() == 1){
			//是,抛出UserNotFoundException
			throw new UserNotFoundException("修改个人资料失败!用户数据不存在!");
		}
		//将参数uid和参数username封装到参数user的uid和modifiedUser属性中
		user.setUid(uid);
		user.setModifiedUser(username);
		//执行更新,获得受影响的行数
		user.setModifiedTime(new Date());
		Integer rows = userMapper.updateInfo(user);
		//判断受影响的行数是否不为1
		if(rows!=1){
			//是,抛出UpdateException
			throw new UpdateException("修改个人资料失败!更新数据出现未知错误!");
		}
	}

	@Override
	public User getByUid(Integer uid) throws UserNotFoundException {
		//根据uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null){
		//是,抛出UserNotFoundException
			throw new UserNotFoundException("获取个人资料失败!用户数据不存在!");
		}
		//判断查询结果中isDelete是否为1
		if(result.getIsDelete() == 1){
			//是,抛出UserNotFoundException
			throw new UserNotFoundException("获取个人资料失败!用户数据不存在!");
		}
		// 将查询结果中不相关的数据设置为null
		User user = new User();
		user.setUsername(result.getUsername());
		user.setPhone(result.getPhone());
		user.setEmail(result.getEmail());
		user.setGender(result.getGender());
		/*result.setPassword(null);
		result.setSalt(null);
		result.setIsDelete(null);*/
		//返回查询结果
		return user;
	}
	
	@Override
	public void changeAvatar(Integer uid, String avatar, String modifiedUser)
			throws UserNotFoundException, UpdateException {
		//根据uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null){
			//是,抛出UserNotFoundException
			throw new UserNotFoundException("修改图像失败!用户数据不存在!");
		}
		//判断查询结果中isDelete是否为1
		if(result.getIsDelete() == 1){
			//是,抛出UserNotFoundException
			throw new UserNotFoundException("修改图像失败!用户数据不存在!");
		}
		//执行更新,获得受影响的行数
		Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, new Date());
		//判断受影响的行数是否不为1
		if(rows!=1){
			//是,抛出UpdateException
			throw new UpdateException("修改图像失败!更新数据出现未知错误!");
		}
	}
	
	/**
	 * @param password
	 * @param salt
	 * @return  加密后的密码
	 */
	private String getMd5Password(String password,String salt){
		//加密规则:使用"salt+password+salt"作为消息,执行3次摘要运算
		String str = salt + password + salt;
		for(int i=0;i<3;i++){
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}

	

}
