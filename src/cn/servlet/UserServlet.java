package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

import cn.entity.Users;
import cn.service.UsersService;
import cn.service.impl.UsersServiceImpl;
import cn.util.Const;
import cn.util.PageBean;
@WebServlet("/users")
public class UserServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String action=request.getParameter("action");
	switch(action){
	case "beforelogin":
		beforelogin(request,response);
		break;
	case "checkusername":
		checkusername(request,response);
		break;
	case "ajaxlogin":
		ajaxlogin(request,response);
		break;
	case "check":
		check(request,response);
		break;
	case "checklist":
		checklist(request,response);
		break;
	case "update":
		update(request,response);
		break;
	case "updatepwd":
		updatepwd(request,response);
		break;
	case "beforelogout":
		beforelogout(request,response);
		break;
	case "del":
		del(request,response);
		break;
	case "reg":
		reg(request,response);
		break;
	case "checkusers":
		checkusers(request,response);
		break;
	case "login":
		login(request,response);
		break;
	case "tologin":
		tologin(request,response);
		break;
	case "logout":
		logout(request,response);
		break;
	case "sidebar":
		sidebar(request,response);
		break;
	case "list":
		list(request,response);
		break;
	case "showupdate":
		showupdate(request,response);
		break;
	case "delcheck":
		delcheck(request,response);
		break;
	case "person":
		person(request,response);
		break;
	}
	
}
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	HttpSession session=request.getSession();
	
	//用户输入的验证码
	String code=request.getParameter("code");
	//随机产生的验证码 
	String vcode=(String)session.getAttribute("vcode");
	if(!code.equals(vcode)){
		out.print("<script>alert('验证码错误!');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
		return;
	}
	String phone=request.getParameter("phone");
	String password=request.getParameter("password"); 
	UsersService usersService=new UsersServiceImpl();
	Users users=usersService.login(phone, password);
	if(users!=null){
		//登录成功
		session.setAttribute("users", users);
		session.setAttribute("role", users.getRole());//role==1为有权限 即管理员身份
		 request.getRequestDispatcher("manage/index.jsp").forward(request, response);
		
	}else{
		out.print("<script>alert('用户名密码错误! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
	}
	out.close();
}

protected void tologin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
	Users users=(Users)session.getAttribute("users");
	if(users!=null){
		session.setAttribute("users", users);
		session.setAttribute("role", users.getRole());//role==1为有权限 即管理员身份
		 request.getRequestDispatcher("manage/index.jsp").forward(request, response);
		 return;
	}else{
		response.sendRedirect(Const.ROOTMANAGE+"login.jsp");
	}
}

protected void ajaxlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	HttpSession session=request.getSession();
	
	String phone=request.getParameter("phone");
	String password=request.getParameter("password"); 
	UsersService usersService=new UsersServiceImpl();
	Users users=usersService.login(phone, password);
	if(users!=null){
		//登录成功
		session.setAttribute("users", users);
		session.setAttribute("role", 0);
		out.print("0");
	}else{
		out.print("1");
	}
	out.close();
}

protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	Integer id=Integer.parseInt(request.getParameter("id"));
	UsersService usersService=new UsersServiceImpl();
	int count=usersService.delete(id);
	if(count>0){
		 request.getRequestDispatcher("users?action=list").forward(request, response);

	}else{
	out.print("<script>alert(' 操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
	}
	out.close();
	}
protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	Integer id=Integer.parseInt(request.getParameter("id"));
	Integer uid=Integer.parseInt(request.getParameter("uid"));
	String pic=request.getParameter("pic");
	Integer role=Integer.parseInt(request.getParameter("role"));
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String grade=request.getParameter("grade");
	String school=request.getParameter("school");
	String major=request.getParameter("major");

	UsersService usersService=new UsersServiceImpl();
	Users users=usersService.getUsers(id);
	Users users1=usersService.getUsers(uid);
	
	if(!StringUtils.isNullOrEmpty(pic)){
		users.setPic(pic);
	}
	
	users.setRole(role);
	users.setName(name);
	users.setSex(sex);
	users.setGrade(grade);
	users.setSchool(school);
	users.setMajor(major);

	int count =usersService.update(users);
	if(count>0){
		if(users1.getRole()==1){
			 request.getRequestDispatcher("users?action=list").forward(request, response);
		}else{
			request.getRequestDispatcher("users?action=person").forward(request, response);
		}
		

	}else{
	out.print("<script>alert('修改失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
	}
	out.close();
	}
protected void updatepwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	HttpSession session=request.getSession();
String oldpass=request.getParameter("oldpass");
String newpass=request.getParameter("newpass");
UsersService usersService=new UsersServiceImpl();
Users users=(Users)session.getAttribute("users");
if(oldpass!=null){
  users.setPassword(newpass);
}else{
	out.print("<script>alert('原密码不正确! ');location.href='"+Const.ROOTMANAGE+"updatepwd.jsp';</script>");
	return ;
} 

int count =usersService.update(users);
if(count>0){
	session.invalidate();
	out.print("<script>alert('修改成功，请使用新密码重新登录! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
}else{
	 request.getRequestDispatcher(Const.ROOTMANAGE+"updatepwd.jsp").forward(request, response);
}
	out.close();
	}
protected void beforelogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	HttpSession session=request.getSession();
	session.invalidate();
	response.sendRedirect(Const.ROOT+"goods?action=products");
	out.close();
	}
protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	
	//String pic=request.getParameter("pic");
	String phone=request.getParameter("phone");
	String password=request.getParameter("password");
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String grade=request.getParameter("grade");
	String school=request.getParameter("school");
	String major=request.getParameter("major");

	UsersService usersService=new UsersServiceImpl();
	 
	Users users=new Users();
	users.setPic("");
	users.setPhone(phone);
	users.setPassword(password);
	users.setName(name);
	users.setSex(sex);
	users.setGrade(grade);
	users.setSchool(school);
	users.setMajor(major);

	int count =usersService.save(users);
	if(count>0){
		out.print("<script>alert('注册成功，请等待审核通过! ');location.href='"+Const.ROOT+"login.jsp';</script>");
	}else{
		out.print("<script>alert('注册失败! ');location.href='"+Const.ROOT+"reg.jsp';</script>");
	}
}
protected void checkusers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	
	Integer id=Integer.parseInt(request.getParameter("id"));

	UsersService usersService=new UsersServiceImpl();

	int count =usersService.check(id);
	if(count>0){
		response.sendRedirect(Const.ROOT+"users?action=checklist");
	}else{
		out.print("<script>alert(' 操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
	}
}
protected void beforelogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	HttpSession session=request.getSession();
	String phone=request.getParameter("phone");
	String password=request.getParameter("password");
	String rem=request.getParameter("rem");
	UsersService usersService=new UsersServiceImpl();
	Users users=usersService.login(phone, password);
	
	if(users!=null){
		//登录成功
		if("1".equals(rem)){
			//记录用户名密码一周  cookie只能保存字符串
			Cookie ucookie=new Cookie("Phone",phone);
			Cookie pcookie=new Cookie("password",password);
			//有效期
			ucookie.setMaxAge(7*24*60*60);
			pcookie.setMaxAge(7*24*60*60);
			//保存
			response.addCookie(ucookie);
			response.addCookie(pcookie);
		}
		session.setAttribute("users", users);
		session.setAttribute("role", users.getRole());
		   request.getRequestDispatcher("goods?action=products").forward(request, response);
	}else{
		out.print("<script>alert('用户名密码错误! ');location.href='"+Const.ROOT+"login.jsp';</script>");
	}
	out.close();
	
}//Users users=(Users)session.getAttribute("users"); 
protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession session=request.getSession();
	session.invalidate();
	response.sendRedirect(Const.ROOTMANAGE+"login.jsp");
	
}
protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
    Integer role=(Integer)session.getAttribute("role");
    if(role!=null&&role==0){
    	 request.getRequestDispatcher(Const.ROOTMANAGE+"index.jsp").forward(request, response);
    }
}
protected void sidebar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
    Integer role=(Integer)session.getAttribute("role");
    
    if(role==null||role!=0){
    	response.sendRedirect(Const.ROOTMANAGE+"index.jsp");
    	return;
    }
    Users users=(Users)session.getAttribute("users");	
    request.setAttribute("users", users);
    request.getRequestDispatcher(Const.ROOTMANAGE+"sidebar.jsp").forward(request, response);
}

protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UsersService usersService=new UsersServiceImpl(); 
	request.setCharacterEncoding("UTF-8");
	String stype=request.getParameter("type");
	Integer type=3;
	if(stype!=null&&!"".equals(stype)){
		type=Integer.parseInt(stype);
	}
	String name=request.getParameter("name");

	Map<String,Object>params=new HashMap<String,Object>();
	params.put("name",name);
	params.put("type", type);

	String spageNo=request.getParameter("pageNo");
	PageBean pageBean=new PageBean();
	if(spageNo!=null&&!"".equals(spageNo)){
		pageBean.setPageNo(Integer.parseInt(spageNo));
	}
	pageBean.setTotalCount(usersService.getCount(params));
	List<Users>usersList=usersService.getUsersByPage(pageBean, params);
	request.setAttribute("usersList", usersList);
	request.setAttribute("pageBean", pageBean);
	request.setAttribute("params", params);
    request.getRequestDispatcher("manage/users.jsp").forward(request, response);
}
protected void showupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	Integer id=Integer.parseInt(request.getParameter("id"));
	Integer uid=Integer.parseInt(request.getParameter("uid"));
	UsersService usersService=new UsersServiceImpl();
	Users u=usersService.getUsers(id);
	Users users1=usersService.getUsers(uid);
	
	request.setAttribute("users", u);
	request.setAttribute("users1", users1);
    request.getRequestDispatcher("manage/updateUser.jsp").forward(request, response);

}

protected void checkusername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	
	String phone=request.getParameter("phone");

	UsersService usersService=new UsersServiceImpl();
	 List<Users> usersList= usersService.getAllUsers();
	 for(Users u:usersList){
		if( u.getPhone().equals(phone)){
			out.print("true");
			return;
		}
			
	 }
}
protected void checklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UsersService usersService=new UsersServiceImpl(); 
	request.setCharacterEncoding("UTF-8");
	String stype=request.getParameter("type");
	Integer type=3;
	if(stype!=null&&!"".equals(stype)){
		type=Integer.parseInt(stype);
	}
	String name=request.getParameter("name");

	Map<String,Object>params=new HashMap<String,Object>();
	params.put("name",name);
	params.put("type", type);

	String spageNo=request.getParameter("pageNo");
	PageBean pageBean=new PageBean();
	if(spageNo!=null&&!"".equals(spageNo)){
		pageBean.setPageNo(Integer.parseInt(spageNo));
	}
	pageBean.setTotalCount(usersService.getCheckCount(params));
	List<Users>usersList=usersService.getCheckUsersByPage(pageBean, params);
	request.setAttribute("usersList", usersList);
	request.setAttribute("pageBean", pageBean);
	request.setAttribute("params", params);
    request.getRequestDispatcher("manage/checkusers.jsp").forward(request, response);
}
protected void delcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	Integer id=Integer.parseInt(request.getParameter("id"));
	UsersService usersService=new UsersServiceImpl();
	int count=usersService.delete(id);
	if(count>0){
		 request.getRequestDispatcher("users?action=checklist").forward(request, response);

	}else{
	out.print("<script>alert(' 操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
	}
	out.close();
	}
protected void person(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
	Users users1=(Users)session.getAttribute("users");
	UsersService usersService=new UsersServiceImpl(); 
	Users users=usersService.getUsers(users1.getId());
	session.setAttribute("users", users);
    request.getRequestDispatcher("manage/users.jsp").forward(request, response);
}

}
