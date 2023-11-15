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

import cn.entity.Carts;
import cn.entity.Goods;
import cn.entity.Orders;
import cn.entity.Ordertail;
import cn.entity.Users;
import cn.service.CartsService;
import cn.service.GoodsService;
import cn.service.OrdersService;
import cn.service.OrdertailService;
import cn.service.impl.CartsServiceImpl;
import cn.service.impl.GoodsServiceImpl;
import cn.service.impl.OrdersServiceImpl;
import cn.service.impl.OrdertailServiceImpl;
import cn.util.Const;
import cn.util.PageBean;


@WebServlet("/orders")
public class OrdersServlet extends HttpServlet{
	GoodsService goodsService=new GoodsServiceImpl();
	OrdersService ordersService=new OrdersServiceImpl();
	OrdertailService orderdetailService=new OrdertailServiceImpl();
	CartsService cartsService=new CartsServiceImpl();
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
				
			}
			
		}
		@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request,response);
			}
		protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			//当前用户
			HttpSession session=request.getSession();
			Users users=(Users)session.getAttribute("users");
			String type=(String)session.getAttribute("type");
			//查询当前用户的购物车
			List<Carts> carts=cartsService.getAllCarts(users.getId(),type);
			double price=0;
			int sid=0;
			int oid=0;
			for(Carts c:carts){
				
				//判断sid是否为同一个商家
				if(sid!=c.getSid()){
					if(price!=0){//修改上一个订单的价格
						ordersService.updatePriceById(oid, price);
						price=0;
					}
					sid=c.getSid();
					Orders orders=new Orders();
					orders.setNo(Const.getNo());
					
					orders.setOptime(Const.getTime());
					orders.setStatus("未发货");
					
					if(type.equals("1")){
						
						
						orders.setUid(sid);
						orders.setSid(users.getId());
					}else{
						orders.setUid(users.getId());
						orders.setSid(sid);
					}
					oid=ordersService.save(orders);
				}
				// 详情
				Ordertail detail=new Ordertail();
				detail.setPid(c.getPid());
				detail.setQuantity(c.getQuantity());
				detail.setOid(oid);
				orderdetailService.save(detail);	
				
				price+=c.getQuantity()*c.getPrice();
				
				cartsService.delete(c.getId());
				Goods goods=goodsService.getGoods(c.getPid());
				goods.setQuantity(goods.getQuantity()-c.getQuantity());
				goodsService.update(goods);
			}
			if(price!=0){//修改最后一个订单的价格
				ordersService.updatePriceById(oid, price);
			}
				out.print("<script>alert('添加成功! ');location.href='"+Const.ROOT+"orders?action=list&choose="+type+"';</script>");
			
			out.close();
			}
		protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			if(ordersService.getOrders(id).getStatus().equals("未发货")){
				out.print("<script>alert('只能删除已发货的订单! ');location.href='"+Const.ROOT+"orders?action=list';</script>");
				return;
			}
			int count=ordersService.delete(id);
			if(count>0){
				response.sendRedirect("orders?action=list");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			Orders orders=ordersService.getOrders(id);
			
			if(orders.getStatus().equals("已发货")){
				out.print("<script>alert('请勿重复发货! ');location.href='"+Const.ROOT+"orders?action=list';</script>");
				return;
			}else{
				orders.setStatus("已发货");
			}
			int count =ordersService.update(orders);
			if(count>0){
				response.sendRedirect("orders?action=list");
			}else{
			out.print("<script>alert('修改失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		
		protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			String choose;
			choose=request.getParameter("choose");
			if(choose==null){
				choose=(String)session.getAttribute("choose");
			}
			String no=request.getParameter("no");
			
			Users users=(Users)session.getAttribute("users");
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("no",no);
			if(choose.equals("1")){
				params.put("sid",users.getId());
				params.put("uid",0);
			}else{
				params.put("uid",users.getId());
				params.put("sid",0);
			}
			
			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			pageBean.setTotalCount(ordersService.getCount(params));
			List<Orders>ordersList=ordersService.getOrdersByPage(pageBean, params);
			session.setAttribute("choose", choose);
			request.setAttribute("spageNo", spageNo);
			request.setAttribute("ordersList", ordersList);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			request.getRequestDispatcher("manage/orders.jsp").forward(request, response);
		
		}
			}