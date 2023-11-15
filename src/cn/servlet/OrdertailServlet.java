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


import cn.entity.Orders;
import cn.entity.Ordertail;
import cn.service.OrdersService;
import cn.service.OrdertailService;
import cn.service.impl.OrdersServiceImpl;
import cn.service.impl.OrdertailServiceImpl;
import cn.util.Const;
import cn.util.PageBean;


@WebServlet("/ordertail")
public class OrdertailServlet extends HttpServlet{
	OrdertailService ordertailService=new OrdertailServiceImpl();
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String action=request.getParameter("action");
			switch(action){
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
	
		
		protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String oid=request.getParameter("id");
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("oid",oid);
			String spageNo=request.getParameter("pageNo");
			PageBean pageBean=new PageBean();
			if(spageNo!=null&&!"".equals(spageNo)){
				pageBean.setPageNo(Integer.parseInt(spageNo));
			}
			pageBean.setTotalCount(ordertailService.getCount(params));
			List<Ordertail>ordertailList=ordertailService.getOrdertailByPage(pageBean, params);
			request.setAttribute("spageNo", spageNo);
			request.setAttribute("ordertailList", ordertailList);
			request.setAttribute("params", params);
			request.setAttribute("pageBean", pageBean);
			//request.getRequestDispatcher("product_list.jsp").forward(request, response);
			request.getRequestDispatcher("manage/ordertail.jsp").forward(request, response);
		
		}
			}