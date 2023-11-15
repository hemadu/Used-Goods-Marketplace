
<%@page import="cn.util.Const"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE %>index.jsp">首页</a><span class="crumb-step">&gt;</span>产品管理</div>
        </div>

		

        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
               
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                        <th>ID</th>
                        	<th>订单号</th>
                        	<th>物品名称</th>
                            <th>物品价格</th>
                            <th>数量</th> 
                            <th>图片</th> 
                            <th>总价</th>
                           	<th>时间</th>
                           	<th>状态</th>
                        </tr>
                   		<c:forEach items="${ordertailList}" var="ol" varStatus="ct">
                        <tr>
                         <td>${ct.count}</td>
                         <td>${ol.orders.no}</td>
                         <td>${ol.goods.name}</td>
                         <td>${ol.goods.price}</td>
                         <td>${ol.quantity}</td>
                         <td><img src="<%=Const.ROOT%>images/${ol.goods.pic}" style="max-width:100px"/></td>
                         <td>${ol.goods.price*ol.quantity}</td>
                         <td>${ol.orders.optime}</td>
                         <td>${ol.orders.status}</td>
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
    	function goPage(pageNo){
    		//document.getElementById("pageNo").value=pageNo;
    		//document.getElementById("searchForm").submit();
    		//$("#searchForm").submit();
    		location.href="<%=Const.ROOT%>ordertail?action=list&id=${ordertailList[0].orders.id}&pageNo="+pageNo;
    	}
    </script>
    <!--/main-->
</div>
</body>
</html>