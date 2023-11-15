package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Goods;
import cn.entity.Goodstype;
import cn.service.GoodsService;
import cn.service.GoodstypeService;
import cn.service.impl.GoodsServiceImpl;
import cn.service.impl.GoodstypeServiceImpl;
import cn.util.Const;
import cn.util.PageBean;


@WebServlet("/goodstype")
public class GoodstypeServlet extends HttpServlet{
	GoodstypeService goodstypeService=new GoodstypeServiceImpl();
	GoodsService goodsService=new GoodsServiceImpl();
	Goodstype goodstype=new Goodstype();
	
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
			case "showgoodstype":
				showgoodstype(request,response);
				break;
			case "list":
				list(request,response);
				break;
			case "showupdate":
				showupdate(request,response);
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
			request.setCharacterEncoding("UTF-8");
			String typename=request.getParameter("typename");

			
			goodstype.setName(typename);
			goodstype.setIsdel(0);
			int count =goodstypeService.save(goodstype);
			if(count>0){
				response.sendRedirect("goodstype?action=list");
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			GoodstypeService goodstypeService=new GoodstypeServiceImpl();
			int count=goodstypeService.delete(id);
			if(count>0){
				request.getRequestDispatcher("goodstype?action=list").forward(request, response);
			}else{
			out.print("<script>alert('操作失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
		protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Integer id=Integer.parseInt(request.getParameter("id"));
			String typename=request.getParameter("typename");

			
			Goodstype goodstype=goodstypeService.getGoodstype(id);

			goodstype.setName(typename);

			int count =goodstypeService.update(goodstype);
			if(count>0){
				request.getRequestDispatcher("goodstype?action=list").forward(request, response);
			}else{
			out.print("<script>alert('修改失败! ');location.href='"+Const.ROOTMANAGE+"login.jsp';</script>");
			}
			out.close();
			}
	
			protected void beforelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			List<Goodstype> goodstypeList =goodstypeService.getAllGoodstype();
			List<Goods>goodsList=goodsService.getAllGoods(null, null,null);
	    	request.setAttribute("goodsList",goodsList);
	    	request.setAttribute("goodstypeList",goodstypeList);
	    	request.getRequestDispatcher("product_list.jsp").forward(request, response);
			}
			protected void showgoodstype(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 List<Goodstype>goodstypeList=goodstypeService.getAllGoodstype();
				 request.setAttribute("goodstypeList", goodstypeList);
				 request.getRequestDispatcher("manage/addGoods.jsp").forward(request, response);
			}
			protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("UTF-8");
				PageBean pageBean=new PageBean();
				String spageNo=request.getParameter("pageNo");
				if(spageNo!=null&&!"".equals(spageNo)){
					pageBean.setPageNo(Integer.parseInt(spageNo));
				}
				pageBean.setTotalCount(goodstypeService.getCount());
				List<Goodstype>goodstypeList=goodstypeService.getGoodstypeByPage(pageBean);
				request.setAttribute("goodstypeList", goodstypeList);
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("spageNo", spageNo);
				request.getRequestDispatcher("manage/goodstype.jsp").forward(request, response);
			}
			protected void showupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			    request.setCharacterEncoding("UTF-8");
			   	Integer id=Integer.parseInt(request.getParameter("id"));
			   	Goodstype u=goodstypeService.getGoodstype(id);
			   	request.setAttribute("goodstype", u);
			   	request.getRequestDispatcher("manage/updateGoodstype.jsp").forward(request, response);
				
			}
}