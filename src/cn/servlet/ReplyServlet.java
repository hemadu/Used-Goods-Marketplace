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

import cn.entity.Reply;
import cn.entity.Users;
import cn.service.MessageService;
import cn.service.ReplyService;
import cn.service.impl.MessageServiceImpl;
import cn.service.impl.ReplyServiceImpl;
import cn.util.Const;
import cn.util.PageBean;


@WebServlet("/reply")
public class ReplyServlet extends HttpServlet{
	ReplyService replyService=new ReplyServiceImpl();
	MessageService messageService=new MessageServiceImpl();
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
			case "update":
				update(request,response);
				break;
			case "list":
				list(request,response);
				break;
			case "delmg":
				delmg(request,response);
				break;
			}
			
		}
		@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request,response);
			}
		protected void jsonlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Reply>replyList=replyService.getAllReply(null);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print(JSONArray.toJSONString(replyList));
			out.close();
		}
		protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer uid=Integer.parseInt(request.getParameter("uid"));
			Integer mid=Integer.parseInt(request.getParameter("mid"));
			
			String content=request.getParameter("content");
			if(content==null||content.equals("")){
				response.sendRedirect("reply?action=list");
				return;
			}
			Reply reply=new Reply();
			reply.setUid(uid);
			reply.setMid(mid);
			reply.setContent(content);
			reply.setOptime(Const.getTime());

			int count =replyService.save(reply);
			if(count>0){
				request.getRequestDispatcher("message?action=chat&mid="+mid).forward(request, response);
			}else{
			out.print("<script>alert('添加失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			Integer mid=Integer.parseInt(request.getParameter("mid"));
			int count=replyService.delete(id);
			if(count>0){
					request.getRequestDispatcher("message?action=chat&mid="+mid).forward(request, response);
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

			Reply reply=replyService.getReply(id);

			reply.setUid(uid);
			reply.setMid(mid);
			reply.setContent(content);
			reply.setOptime(optime);

			int count =replyService.update(reply);
			if(count>0){
				response.sendRedirect("reply?action=list");
			}else{
			out.print("<script>alert('修改失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
		}
		protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			request.setCharacterEncoding("UTF-8");
			String mid=request.getParameter("mid");
			Integer uid=users.getId();

			Map<String,Object>params=new HashMap<String,Object>();
			params.put("mid",mid);
			params.put("uid",uid);
			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			pageBean.setTotalCount(replyService.getCount(params));
			List<Reply>replyList=replyService.getReplyByPage(pageBean, params);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("replyList", replyList);
			request.getRequestDispatcher("manage/reply.jsp").forward(request, response);
		
		}
		protected void delmg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			Integer mid=Integer.parseInt(request.getParameter("mid"));
			int count=replyService.deletemg(mid,users.getId());
			if(count>0){
				response.sendRedirect("reply?action=list");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		
}