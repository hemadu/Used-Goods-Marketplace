    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>后台管理</title>
     <link rel="stylesheet" type="text/css" href="<%=Const.ROOTMANAGE %>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=Const.ROOTMANAGE %>css/main.css"/>
</head>
<body>
<%@include file="sidebar.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">
		
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE%>index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="<%=Const.ROOTMANAGE%>message.jsp">新闻类型管理</a><span class="crumb-step">&gt;</span><span class="crumb-name">修改用户</span></div>
        </div>
        
        <div class="result-wrap">
            <div class="result-content">
                <form action="<%=Const.ROOT%>message" method="post" id="myform" name="myform">
                	<input type="hidden" name="action" value="update"/>
                	<input type="hidden" name="id" value="${message.id}"/>
                	<input type="hidden" name="optime" value="${message.optime}"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
	                              <th>发布人姓名：</th>
	                                <td><input class="common-text required"  required="required"  value="${message.name}" name="name"  size="50" type="text"></td>
	                   </tr>
	                   <tr>
	                              <th>标题：</th>
	                                <td><input class="common-text required"  required="required"  value="${message.title}"  name="title"  size="50" type="text"></td>
	                   </tr>
	                   
	                    <tr>
	                              <th>内容：</th>
	                                <td><input class="common-text required"  required="required"  value="${message.content}"  name="content"  size="50" type="text"></td>
	                   </tr>
                            <tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody>
                        </table>
                </form>
            </div>
        </div>
        
    </div>
    <!--/main-->


</body>
</html>