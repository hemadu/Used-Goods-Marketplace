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

import cn.entity.Carts;
import cn.entity.Goods;
import cn.entity.Goodstype;
import cn.entity.Users;
import cn.service.CartsService;
import cn.service.GoodsService;
import cn.service.impl.CartsServiceImpl;
import cn.service.impl.GoodsServiceImpl;
import cn.util.Const;
import cn.util.PageBean;


@WebServlet("/carts")
public class CartsServlet extends HttpServlet{
	CartsService cartsService=new CartsServiceImpl();
	GoodsService goodsService=new GoodsServiceImpl();
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
			case "list":
				list(request,response);
				break;
			case "addquantity":
				addquantity(request,response);
				break;
			case "delquantity":
				delquantity(request,response);
				break;
			case "price":
				price(request,response);
				break;
			}
			
		}
		@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request,response);
			}
		protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			int count=0;
			
			Integer id=Integer.parseInt(request.getParameter("id"));
			Goods goods=goodsService.getGoods(id);
			if(goods.getType()==0){// 如果是购买则运行添加购物车
			if(goods.getQuantity()==0){
				count=4;
				out.print(JSONArray.toJSONString(count));
				//out.print("<script>alert('库存不足! ');location.href='"+Const.ROOT+"goods?action=products';</script>");//Const.ROOT+"goods?action=detail&id='"+id+";</script>");
			}else if(goods.getSid()==users.getId()){
				count=3;
				out.print(JSONArray.toJSONString(count));
			}else{
				List<Carts>cartsList=cartsService.getAllCarts(users.getId(),"0");
				for(Carts c:cartsList){
					if(	c.getPid()==id){
						count=4;
						if(c.getGoods().getQuantity()>c.getQuantity()){
							c.setQuantity(c.getQuantity()+1);
							count=cartsService.update(c);
							break;
						}
					}
				}
				if(count==0){
					Carts carts=new Carts();
					carts.setType("0");
					carts.setUid(users.getId());
					carts.setPid(goods.getId());
					carts.setSid(goods.getSid());
					carts.setQuantity(1);
					carts.setPrice(goods.getPrice());
					count=cartsService.save(carts);
				}
				
				//goods.setQuantity(goods.getQuantity()-1);
				//int count1 =goodsService.update(goods);
				
				out.print(JSONArray.toJSONString(count));
				
				//if(count>0&&count1>0){
					//out.print("<script>alert('已成功添加购物车! ');location.href='"+Const.ROOT+"goods?action=products';</script>");
				//}else{
				//	out.print("<script>alert('操作失败! ');location.href='"+Const.ROOT+"goods?action=products';</script>");
				//}
			}
			}else{//否则加到出售列
				if(goods.getQuantity()==0){
					count=2;
					out.print(JSONArray.toJSONString(count));
					//out.print("<script>alert('库存不足! ');location.href='"+Const.ROOT+"goods?action=products';</script>");//Const.ROOT+"goods?action=detail&id='"+id+";</script>");
				}else if(goods.getSid()==users.getId()){
					count=3;
					out.print(JSONArray.toJSONString(count));
				}else{
					List<Carts>cartsList=cartsService.getAllCarts(users.getId(),"1");
					for(Carts c:cartsList){
						if(	c.getPid()==id){
							count=2;
							if(c.getGoods().getQuantity()>c.getQuantity()){
								c.setQuantity(c.getQuantity()+1);
								count=cartsService.update(c);
								break;
							}
							
						}
					}
					if(count==0){
						Carts carts=new Carts();
						carts.setType("1");
						carts.setUid(users.getId());
						carts.setPid(goods.getId());
						carts.setSid(goods.getSid());
						carts.setQuantity(1);
						carts.setPrice(goods.getPrice());
						count=cartsService.save(carts);
					}
					out.print(JSONArray.toJSONString(count));
				}
			}
			out.close();
		} 
		protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			Integer id=Integer.parseInt(request.getParameter("id"));
			CartsService cartsService=new CartsServiceImpl();
			int count=cartsService.delete(id);
			if(count>0){
				response.sendRedirect("carts?action=price");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			
			
			Integer id=Integer.parseInt(request.getParameter("id"));
			Integer uid=Integer.parseInt(request.getParameter("uid"));
			Integer pid=Integer.parseInt(request.getParameter("pid"));
			Integer quantity=Integer.parseInt(request.getParameter("quantity"));
			Carts carts=cartsService.getCarts(id);

			carts.setUid(uid);
			carts.setPid(pid);
			carts.setQuantity(quantity);

			int count =cartsService.update(carts);
			if(count>0){
				response.sendRedirect("carts?action=list");
			}else{
			out.print("<script>alert('修改失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		
		protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			String type=(String)session.getAttribute("type");
			List<Carts>cartsList=cartsService.getAllCarts(users.getId(),type);//getGoodsByPage(pageBean, params);
			request.setAttribute("cartsList", cartsList);
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			request.getRequestDispatcher("manage/carts.jsp").forward(request, response);
		
		}
		protected void addquantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			Integer id=Integer.parseInt(request.getParameter("id"));
			Integer quantity=Integer.parseInt(request.getParameter("quantity"));
			Carts carts=cartsService.getCarts(id);
			if(carts.getGoods().getQuantity()>quantity){
				quantity++;
			}else{
				out.print("<script>alert('库存不足! ');location.href='"+Const.ROOT+"carts?action=price';</script>");
				return;
			}
			carts.setQuantity(quantity);
			int count =cartsService.update(carts);
			if(count>0){
				response.sendRedirect("carts?action=price");
				//request.getRequestDispatcher("carts?action=price").forward(request, response);
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void delquantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			Integer quantity=Integer.parseInt(request.getParameter("quantity"));
			if(quantity!=1){
				quantity--;
			}else{
				out.print("<script>alert('数量不能少于1! ');location.href='"+Const.ROOT+"carts?action=price';</script>");
				return;
			}
			
			Carts carts=cartsService.getCarts(id);
			carts.setQuantity(quantity);
			int count =cartsService.update(carts);
			if(count>0){
				response.sendRedirect("carts?action=price");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void price(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			if(users==null){
				out.print("<script>alert('您还未登录! ');location.href='"+Const.ROOT+"login.jsp';</script>");
				return;
			}
			if(users.getRole()==1){
				session.invalidate();
				out.print("<script>alert('仅供普通用户访问! ');location.href='"+Const.ROOT+"login.jsp';</script>");
				return;
			}
			String type=null;
			type=request.getParameter("type");
			if(type==null){
				type=(String)session.getAttribute("type");
			}
			List<Carts>cartsList=cartsService.getAllCarts(users.getId(),type);
			double price=0;
			for(Carts c:cartsList){
				price+=c.getQuantity()*c.getPrice();
			}
			request.setAttribute("price",price);
			session.setAttribute("type", type);
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			request.getRequestDispatcher("carts?action=list").forward(request, response);
			out.close();
		} 
}