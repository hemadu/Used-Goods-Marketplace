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
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.mysql.jdbc.StringUtils;

import cn.entity.Goods;
import cn.entity.Goodstype;
import cn.entity.Message;
import cn.entity.Reply;
import cn.entity.Users;
import cn.service.GoodsService;
import cn.service.GoodstypeService;
import cn.service.MessageService;
import cn.service.ReplyService;
import cn.service.UsersService;
import cn.service.impl.GoodsServiceImpl;
import cn.service.impl.GoodstypeServiceImpl;
import cn.service.impl.MessageServiceImpl;
import cn.service.impl.ReplyServiceImpl;
import cn.service.impl.UsersServiceImpl;
import cn.util.Const;
import cn.util.PageBean;


@WebServlet("/goods")
public class GoodsServlet extends HttpServlet{
	GoodsService goodsService=new GoodsServiceImpl();
	GoodstypeService goodstypeService=new GoodstypeServiceImpl();
	MessageService massageService=new MessageServiceImpl();
	ReplyService replyService=new ReplyServiceImpl();
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String action=request.getParameter("action");
			switch(action){
			case "add":
				add(request,response);
				break;
			case "del":
				del(request,response);
				break;
			case "update":
				update(request,response);
				break;
			case "beforelist":
				beforelist(request,response);
				break;
			case "list":
				list(request,response);
				break;
			case "products":
				products(request,response);
			break;
			case "ajaxproducts":
				ajaxproducts(request,response);
			break;
			case "showupdate":
				showupdate(request,response);
				break;
			case "showgoodstype":
				showgoodstype(request,response);
				break;
			case "checklist":
				checklist(request,response);
				break;
			case "delcheck":
				delcheck(request,response);
				break;
			case "checkgoods":
				checkgoods(request,response);
				break;
			case "detail":
				detail(request,response);
				break;
			}
			
		}
		@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request,response);
			}
		protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			Integer cid=Integer.parseInt(request.getParameter("cid"));
			String name=request.getParameter("name");
			String pic=request.getParameter("pic");
			Double price=Double.parseDouble(request.getParameter("price"));
			Integer quantity=Integer.parseInt(request.getParameter("quantity"));
			String content=request.getParameter("content");
			Integer type=Integer.parseInt(request.getParameter("type"));
			Goods goods=new Goods();

			goods.setSid(users.getId());
			goods.setCid(cid);
			goods.setName(name);
			goods.setPrice(price);
			goods.setQuantity(quantity);
			goods.setPic(pic);
			goods.setContent(content);
			goods.setType(type);
			goods.setIsdel(2);

			int count =goodsService.save(goods);
			if(count>0){
				out.print("<script>alert('添加成功，正在审核！ ');location.href='"+Const.ROOT+"goods?action=list';</script>");
			}else{
			out.print("<script>alert('添加失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			GoodsService goodsService=new GoodsServiceImpl();
			List<Message> messageList=massageService.getAllMessage(id);
			for(Message m:messageList){
				massageService.delete(m.getId());
			}
			int count=goodsService.delete(id);
			if(count>0){
				response.sendRedirect("goods?action=list");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			Integer cid=Integer.parseInt(request.getParameter("cid"));
			String name=request.getParameter("name");
			Double price=Double.parseDouble(request.getParameter("price"));
			Integer quantity=Integer.parseInt(request.getParameter("quantity"));
			String pic=request.getParameter("pic");
			String content=request.getParameter("content");
			Integer type=Integer.parseInt(request.getParameter("type"));
			Goods goods=goodsService.getGoods(id);

			if(!StringUtils.isNullOrEmpty(pic)){
				goods.setPic(pic);
			}
			if(!StringUtils.isNullOrEmpty(content)){
				goods.setContent(content);
			}
			
			goods.setCid(cid);
			goods.setName(name);
			goods.setPrice(price);
			goods.setQuantity(quantity);
			goods.setType(type);

			int count =goodsService.update(goods);
			if(count>0){
				response.sendRedirect("goods?action=list");
			}else{
			out.print("<script>alert('修改失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void beforelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			String type=request.getParameter("id");
			List<Goods> goodsList=goodsService.getAllGoods(type, null,null);
			List<Goodstype> goodstypeList =goodstypeService.getAllGoodstype();
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("goodstypeList", goodstypeList);
			request.getRequestDispatcher("product_list.jsp").forward(request, response);
			out.close();
			}
		protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			
			String stype=request.getParameter("type");
			Integer type=0;
			if(stype!=null&&!"".equals(stype)){
				type=Integer.parseInt(stype);
			}
			String name=request.getParameter("name");
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("name",name);
			params.put("type", type);
			Users users=(Users)session.getAttribute("users");
			if(users.getRole()==0){
				Integer sid=users.getId();
				params.put("sid", sid);
			}
			String need=request.getParameter("need");
			
			if(StringUtils.isNullOrEmpty(need)){
				need=(String)session.getAttribute("need");
			}
			
			params.put("need", need);
			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			
			pageBean.setTotalCount(goodsService.getCount(params));
			List<Goods>goodsList=goodsService.getGoodsByPage(pageBean, params);
			List<Goodstype>goodstypeList=goodstypeService.getAllGoodstype();
			
			session.setAttribute("need", need);
			request.setAttribute("goodstypeList", goodstypeList);
			request.setAttribute("spageNo", spageNo);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			request.getRequestDispatcher("manage/goods.jsp").forward(request, response);
		
		}
		protected void products(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			String stype=request.getParameter("type");
			Integer type=0;
			if(stype!=null&&!"".equals(stype)){
				type=Integer.parseInt(stype);
			}
			String name=request.getParameter("name");
			String need=request.getParameter("need");
			if(StringUtils.isNullOrEmpty(need)){
				need=(String)session.getAttribute("need");
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("name",name);
			params.put("type", type);
			params.put("need", need);
			
			
			
			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			pageBean.setTotalCount(goodsService.getCount(params));
			List<Goods>goodsList=goodsService.getGoodsByPage1(pageBean, params);
			List<Goodstype>goodstypeList=goodstypeService.getAllGoodstype();
			List<Goods>goodsList1=goodsService.getAllGoods(null,null,need);
			Integer maxId=goodsList1.size()-1;
			request.setAttribute("maxId", maxId);
			session.setAttribute("need", need);
			request.setAttribute("goodstypeList", goodstypeList);
			request.setAttribute("spageNo", spageNo);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("goodsList1", goodsList1);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			if("1".equals(need)){
				request.getRequestDispatcher("qiugou.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("products.jsp").forward(request, response);
			}
			
		
		}
		protected void ajaxproducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			String stype=request.getParameter("type");
			Integer type=0;
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
			pageBean.setTotalCount(goodsService.getCount(params));
			List<Goods>goodsList=goodsService.getGoodsByPage1(pageBean, params);
			List<Goodstype>goodstypeList=goodstypeService.getAllGoodstype();
			request.setAttribute("goodstypeList", goodstypeList);
			request.setAttribute("spageNo", spageNo);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			response.setContentType("application/json;charset=UTF-8");
			//out.print(JSONArray.toJSONString(goodsList,goodstypeList));
			out.close();
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			out.print("1");
			out.close();
		}
		protected void showupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    request.setCharacterEncoding("UTF-8");
		   	Integer id=Integer.parseInt(request.getParameter("id"));
		   	Goods goods=goodsService.getGoods(id);
		    List<Goodstype>goodstypeList=goodstypeService.getAllGoodstype();
			 request.setAttribute("goodstypeList", goodstypeList);
		   	request.setAttribute("goods", goods);
			request.getRequestDispatcher("manage/updateGoods.jsp").forward(request, response);
		
		}

		protected void showgoodstype(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 List<Goodstype>goodstypeList=goodstypeService.getAllGoodstype();
			 request.setAttribute("goodstypeList", goodstypeList);
			 request.getRequestDispatcher("manage/addGoods.jsp").forward(request, response);
		}
		
		protected void checklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String stype=request.getParameter("type");
			Integer type=0;
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
			pageBean.setTotalCount(goodsService.getCheckCount(params));
			List<Goods>goodsList=goodsService.getCheckGoodsByPage(pageBean, params);
			List<Goodstype>goodstypeList=goodstypeService.getAllGoodstype();
			request.setAttribute("goodstypeList", goodstypeList);
			request.setAttribute("spageNo", spageNo);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			request.getRequestDispatcher("manage/checkgoods.jsp").forward(request, response);
		
		}
		protected void delcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			GoodsService goodsService=new GoodsServiceImpl();
			int count=goodsService.delete(id);
			if(count>0){
				response.sendRedirect("goods?action=checklist");
			}else{
				out.print("<script>alert(' 操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void checkgoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			
			Integer id=Integer.parseInt(request.getParameter("id"));

			int count =goodsService.check(id);
			if(count>0){
				response.sendRedirect(Const.ROOT+"goods?action=checklist");
			}else{
				out.print("<script>alert(' 操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
		}
		protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			Integer id=Integer.parseInt(request.getParameter("id"));
			String select=request.getParameter("select");
			String content=request.getParameter("content");
			String mid=request.getParameter("mid");
			String need=request.getParameter("need");
			if(StringUtils.isNullOrEmpty(need)){
				need=(String)session.getAttribute("need");
			}
			List<Reply>replyList=null;
			Message message1=null;
			if(!StringUtils.isNullOrEmpty(mid)){
				if(!StringUtils.isNullOrEmpty(content)){
						Reply reply=new Reply();
						reply.setUid(users.getId());
						reply.setMid(Integer.parseInt(mid));
						reply.setContent(content);
						reply.setOptime(Const.getTime());
						replyService.save(reply);
				}
				message1=massageService.getMessage(Integer.parseInt(mid));
				replyList=replyService.getAllReply(Integer.parseInt(mid));
			}else{
				if(!StringUtils.isNullOrEmpty(content)){
					Message message=new Message();
					message.setUid(users.getId());
					message.setPid(id);
					message.setContent(content);
					message.setOptime(Const.getTime());
					massageService.save(message);
				}
			}
			Goods goods=goodsService.getGoods(id);
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("pid", id);
			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			pageBean.setTotalCount(massageService.getCount(params));
			List<Message>messageList=massageService.getMessageByPage(pageBean, params);//.getAllMessage(id);
			//List<Goods>goodsList=goodsService.getCheckGoodsByPage(pageBean, params);
			List<Goods>goodsList1=goodsService.getAllGoods(null,null,goods.getType()+"");
			Integer maxId=goodsList1.size()-1;
			session.setAttribute("need", need);
			request.setAttribute("maxId", maxId);
			request.setAttribute("goodsList1", goodsList1);
			request.setAttribute("message", message1);
			request.setAttribute("mid", mid);
			request.setAttribute("spageNo", spageNo);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("select", select);
			request.setAttribute("goods", goods);
			request.setAttribute("messageList", messageList);
			request.setAttribute("replyList", replyList);
			request.setAttribute("users", users);
			request.getRequestDispatcher("detail.jsp").forward(request, response);
			
			out.close();
		}
		
			}