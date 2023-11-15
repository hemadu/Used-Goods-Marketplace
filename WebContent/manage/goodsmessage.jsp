    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=Const.ROOTMANAGE%>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=Const.ROOTMANAGE%>css/main.css"/>
</head>
<%@ include file="sidebar.jsp"%>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE %>index.jsp">首页</a><span class="crumb-step">&gt;</span>留言管理</div>
        </div>

		<div class="search-wrap">
            <div class="search-content">
           
            </div>
        </div>

        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
              
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            
                            <th>ID</th>
                            <th>留言内容</th> 
                            <th>留言时间</th>
                            <th>操作</th>
                        </tr>
                        
                   <c:forEach items="${messageList}" var="ml" varStatus="st">
                        <tr>
                         <td>${st.count}</td>
                            <td>${ml.content}</td>
                            <td>${ml.optime}</td>
                            <td>
                               <a class="link-update" href="<%=Const.ROOT%>message?action=chat&mid=${ml.id}&change=1">查看详情</a>
                              <a class="link-del" href="javascript:delmg(${ml.id})">删除此栏</a>
                                
                            </td>
                        </tr>
                        </c:forEach>
                      
                    </table>
                    <div class="list-page"> 
                    <input class="btn btn6" onclick="goPage(1)" value="首页" type="button"> 
                    <input class="btn btn6" onclick="goPage(${pageBean.prevPage})" value="上一页" type="button">
                     【${pageBean.pageNo}/${pageBean.totalPage}】
                    <input class="btn btn6" onclick="goPage(${pageBean.nextPage})"  value="下一页" type="button">
                  <input class="btn btn6" onclick="goPage(${pageBean.totalPage})" value="末页" type="button"> 
                    
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>
    	function delmg(id){
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>message?action=delmg&id="+id;
    		}
    	}
    	function goPage(pageNo){
    		//document.getElementById("pageNo").value=pageNo;
    		//document.getElementById("searchForm").submit();
    		//$("#searchForm").submit();
    		location.href="<%=Const.ROOT%>message?action=list&pageNo="+pageNo+"&pid="+${pid};
    	}
    </script>
    <!--/main-->
</div>
</body>
</html>