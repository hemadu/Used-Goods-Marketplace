package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.entity.Goods;
import cn.entity.Message;
import cn.entity.Reply;
import cn.entity.Users;
import cn.service.GoodsService;
import cn.service.MessageService;
import cn.service.ReplyService;
import cn.service.UsersService;
import cn.service.impl.GoodsServiceImpl;
import cn.service.impl.MessageServiceImpl;
import cn.service.impl.ReplyServiceImpl;
import cn.service.impl.UsersServiceImpl;
import cn.util.Const;
import cn.util.PageBean;


@WebServlet("/message")
public class MassageServlet extends HttpServlet{
	MessageService messageService=new MessageServiceImpl();
	ReplyService replyService=new ReplyServiceImpl();
	GoodsService goodsService=new GoodsServiceImpl();
	UsersService usersService=new UsersServiceImpl();
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String action=request.getParameter("action");
			switch(action){
			case "jsonlist":
				jsonlist(request,response);
				break;
			case "add":
				add(request,response);
				break;
			case "del":
				del(request,response);
				break;
			case "delmg":
				delmg(request,response);
				break;
			case "update":
				update(request,response);
				break;
			case "list":
				list(request,response);
				break;
			case "chat":
				chat(request,response);
				break;
			case "chatlist":
				chatlist(request,response);
				break;
			
			}
			
		}
		@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request,response);
			}
		protected void jsonlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Message>messageList=messageService.getAllMessage(null);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print(JSONArray.toJSONString(messageList));
			out.close();
		}
		protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer uid=Integer.parseInt(request.getParameter("uid"));
			Integer pid=Integer.parseInt(request.getParameter("pid"));
			String content=request.getParameter("content");

			
			Message message=new Message();
			message.setUid(uid);
			message.setPid(pid);
			message.setContent(content);
			message.setOptime(Const.getTime());

			int count =messageService.save(message);
			if(count>0){
				response.sendRedirect("message?action=list");
			}else{
			out.print("<script>alert('添加失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			Integer pid=messageService.getMessage(id).getPid();
			int count=messageService.delete(id);
			String change=(String)session.getAttribute("change");
			if(count>0){
				if(change.equals("0")){
					response.sendRedirect("message?action=chatlist");
				}else{
					response.sendRedirect("message?action=list&pid="+pid);
				}
				
				//response.sendRedirect("message?action=list");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void delmg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession();
			Integer id=Integer.parseInt(request.getParameter("id"));
			Message message=messageService.getMessage(id);
			String change=(String)session.getAttribute("change");
			int count=messageService.delete(id);
			if(count>0){
				if("0".equals(change)){
						response.sendRedirect("message?action=chatlist");
				}else{
					response.sendRedirect("message?action=list&pid="+message.getPid());
				}
				
				//response.sendRedirect("message?action=list");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer uid=Integer.parseInt(request.getParameter("uid"));
			Integer mid=Integer.parseInt(request.getParameter("mid"));
			String content=request.getParameter("content");
			String optime=request.getParameter("optime");
			Integer id=Integer.parseInt(request.getParameter("id"));

			Message message=messageService.getMessage(id);

			message.setPid(uid);
			message.setPid(mid);
			message.setContent(content);
			message.setOptime(optime);

			int count =messageService.update(message);
			if(count>0){
				response.sendRedirect("message?action=list");
			}else{
			out.print("<script>alert('修改失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
		}
		protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			request.setCharacterEncoding("UTF-8");
			Integer pid=Integer.parseInt(request.getParameter("pid"));
			if(pid==0){
				pid=(Integer)session.getAttribute("pid");
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("pid",pid);

			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			pageBean.setTotalCount(messageService.getCount(params));
			List<Message>messageList=messageService.getMessageByPage(pageBean, params);
			session.setAttribute("pid", pid);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("messageList", messageList);
			request.getRequestDispatcher("manage/goodsmessage.jsp").forward(request, response);
		}
	protected void chat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//PrintWriter out=response.getWriter();	
			HttpSession session=request.getSession();
			
			String change=request.getParameter("change");
			if(StringUtils.isNullOrEmpty(change)){
				change=(String)session.getAttribute("change");
			}
			
			Integer mid=Integer.parseInt(request.getParameter("mid"));
			List<Reply> replyList=replyService.getAllChat(mid);
			Message message=messageService.getMessage(mid);
			
			Goods goods =goodsService.getGoods(message.getPid());
			Users musers=usersService.getUsers(message.getUid());
			session.setAttribute("change", change);
			request.setAttribute("musers", musers);
			request.setAttribute("message", message);
			request.setAttribute("replyList", replyList);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("manage/message.jsp").forward(request, response);
		}
	
		protected void chatlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			String change=request.getParameter("change");
			if(StringUtils.isNullOrEmpty(change)){
				change=(String)session.getAttribute("change");
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("uid",users.getId());
			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			pageBean.setTotalCount(messageService.getCount(params));
			List<Message>messageList=messageService.getMessageByPage(pageBean, params);
			session.setAttribute("change", change);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("messageList", messageList);
			request.getRequestDispatcher("manage/chatmessage.jsp").forward(request, response);
		
		
	
	}
		
}