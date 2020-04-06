package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileStateException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	//注册
	@PostMapping("reg")
	public JsonResult<Void> reg(User user){
		userService.reg(user);
		return new JsonResult<>(SUCCESS);
	}
	//登录
	@PostMapping("login")
	public JsonResult<User> login(String username,String password,HttpSession session){
		//调用业务层对象的"登录"方法,获取返回结果
		User data = userService.login(username, password);
		//向session中存入用户id和用户名
		session.setAttribute("uid", data.getUid());
		session.setAttribute("username",data.getUsername());
		//返回
		return new JsonResult<>(SUCCESS,data);
	}
	//修改密码
	@PostMapping("change_password")
	public JsonResult<Void> changePassword(
			@RequestParam("old_password")String oldPassword, 
			@RequestParam("new_password")String newPassword, HttpSession session){
		//从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String modifiedUser = session.getAttribute("username").toString();
		//调用UserService对象执行修改密码
		userService.changePassword(uid, oldPassword, newPassword, modifiedUser);
		return new JsonResult<>(SUCCESS);
	}
	//修改个人资料
	@PostMapping("change_info")
	public JsonResult<Void> changeInfo(User user,HttpSession session){
		//从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		//调用UserService对象执行个人资料密码
		userService.changeInfo(uid, username, user);
		//返回成功
		return new JsonResult<>(SUCCESS);
	}
	@GetMapping("get_by_uid")
	public JsonResult<User> getByUid(HttpSession session){
		//从session中获取uid
		Integer uid = getUidFromSession(session);
		//调用UserService对象显示用户信息
		User data = userService.getByUid(uid);
		//返回成功和数据
		return new JsonResult<>(SUCCESS,data);
	}
	
	/*
	 * 允许上传头像文件的最大大小
	 */
	public static final long AVATAR_MAX_SIZE = 800*1024;
	/*
	 * 允许上传头像文件的类型列表
	 */
	public static final List<String> AVATAR_CONTENT_TYPE = new ArrayList<String>();
	static{
		AVATAR_CONTENT_TYPE.add("image/jpeg");
		AVATAR_CONTENT_TYPE.add("image/png");
		AVATAR_CONTENT_TYPE.add("image/gif");
	}
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(
			@RequestParam("file")MultipartFile file,
			HttpServletRequest request){
		//上传文件为空
		if(file.isEmpty()){
			throw new FileEmptyException("上传文件失败!上传文件为空!");
		}
		//上传文件大小超出限制
		if(file.getSize() > AVATAR_MAX_SIZE){
			throw new FileSizeException("上传文件失败!不允许上传超过"+(AVATAR_MAX_SIZE/1024)+"的图片文件!");
		}
		//上传文件类型超出限制
		String contentType  = file.getContentType();
		if(!AVATAR_CONTENT_TYPE.contains(contentType)){
			throw new FileTypeException("上传图像失败!上传文件类型超出了限制!");
		}
		//确定文件夹
		String parentPath = request.getServletContext().getRealPath("upload");
		File parent = new File(parentPath);
		if(!parent.exists()){
			parent.mkdirs();
		}
		//确定文件名
		String filename = UUID.randomUUID().toString();
		String originalFilename = file.getOriginalFilename();
		int beginIndex = originalFilename.lastIndexOf(".");
		String suffix = originalFilename.substring(beginIndex);
		String child = filename + suffix;
		//保存用户上传的文件
		File dest = new File(parent, child);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			throw new FileStateException("上传文件错误!文件状态有误!请重新尝试上传!");
		} catch (IOException e) {
			throw new FileUploadIOException("上传文件错误!文件读写有误!请重新尝试上传!");
		}
		//将文件路径记录到数据库
		String avatarPath = "/upload/"+child;
		Integer uid = getUidFromSession(request.getSession());
		String modifiedUser = request.getSession().getAttribute("username").toString();
		userService.changeAvatar(uid, avatarPath, modifiedUser);
		return new JsonResult<>(SUCCESS,avatarPath);
	}
}
