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
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE%>index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">修改密码</span></div>
        </div>
        
        <div class="result-wrap">
            <div class="result-content">
                <form action="<%=Const.ROOT%>users" method="post" id="myform" name="myform" onsubmit="return check()">
                	<input type="hidden" name="id" value="${users.id}"/>
                		<input type="hidden" name="action" value="updatepwd"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
	                              <th>原始密码：</th>
	                                <td><input class="common-text" name="oldpass" id="oldpass"  size="50" type="password" required="required"/></td>
	                   </tr>
	                    <tr>
	                              <th>新密码：</th>
	                                <td><input class="common-text" name="newpass" id="newpass" size="50" type="password" required="required"/></td>
	                   </tr>
	                    <tr>
	                              <th>确认密码：</th>
	                                <td><input class="common-text" name="newpass2"  id="newpass2"  size="50" type="password" required="required"/></td>
	                   </tr>
                            
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
    <script src="<%=Const.ROOT %>js/jquery-3.2.1.js"></script>
<script>
function check(){
	
 if($("#oldpass").val()!='${users.password}'){
	 alert("原密码错误");
	 return false;
 }else if($("#newpass").val()!=$("#newpass2").val()){
	 alert("两次密码不一致");
	 return false;
 }else{
	 return true;
 }

}
</script>

</body>
</html>