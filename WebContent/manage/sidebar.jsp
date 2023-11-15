<%@page import="cn.entity.Users"%>
<%@page import="cn.util.Const"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Users users=(Users)session.getAttribute("users");
if(users==null){
	response.sendRedirect(Const.ROOTMANAGE+"login.jsp");
	return;
}
%>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="<%=Const.ROOTMANAGE%>index.jsp" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="<%=Const.ROOTMANAGE%>index.jsp">首页</a></li>
                <li><a href="<%=Const.ROOT%>goods?action=products" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">欢迎你:${users.name}</a></li>
                <li><a href="<%=Const.ROOTMANAGE%>updatepwd.jsp">修改密码</a></li>
                <li><a href="<%=Const.ROOT%>users?action=logout">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                    	<li><a href="<%=Const.ROOT%>goods?action=list&need=0"><i class="icon-font">&#xe001;</i>出售产品管理</a></li>
                        <li><a href="<%=Const.ROOT%>goods?action=list&need=1"><i class="icon-font">&#xe05e;</i>求购产品管理</a></li>
                    	<c:if test="${users.role==1}">
                    	<li><a href="<%=Const.ROOT%>goodstype?action=list"><i class="icon-font">&#xe002;</i>产品类型管理</a></li>
                        <li><a href="<%=Const.ROOT%>users?action=list"><i class="icon-font">&#xe01e;</i>用户管理</a></li>
                        <li><a href="<%=Const.ROOT%>users?action=checklist"><i class="icon-font">&#xe003;</i>用户申请</a></li>
                        <li><a href="<%=Const.ROOT%>goods?action=checklist"><i class="icon-font">&#xe03e;</i>产品申请</a></li>
                        </c:if>
                        <c:if test="${users.role==0}">
                        <li><a href="<%=Const.ROOT%>users?action=person"><i class="icon-font">&#xe01e;</i>个人信息</a></li>
                        
                        <li><a href="<%=Const.ROOT%>carts?action=price&type=0"><i class="icon-font">&#xe005;</i>我的购物车</a></li>
                         <!--<li><a href="<%=Const.ROOT%>carts?action=price&type=1"><i class="icon-font">&#xe006;</i>出售产品列</a></li> -->
                        <li><a href="<%=Const.ROOT%>orders?action=list&choose=0"><i class="icon-font">&#xe03e;</i>购物订单</a></li>
                         <li><a href="<%=Const.ROOT%>orders?action=list&choose=1"><i class="icon-font">&#xe003;</i>出售订单</a></li>
                        <li><a href="<%=Const.ROOT%>message?action=chatlist&change=0"><i class="icon-font">&#xe004;</i>留言信息</a></li>
                         </c:if>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul class="sub-menu">
                        <li><a href="<%=Const.ROOTMANAGE%>updatepwd.jsp"><i class="icon-font">&#xe017;</i>修改密码</a></li>
                      <li><a href="<%=Const.ROOT%>users?action=logout"><i class="icon-font">&#xe046;</i>退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->