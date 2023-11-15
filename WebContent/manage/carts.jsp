
<%@page import="cn.util.Const"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<script type="text/javascript" src="<%=Const.ROOT%>js/jquery.min.js"></script>
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

		<div class="search-wrap">
            <div class="search-content">
                <form action="<%=Const.ROOT%>carts?action=price"  method="post" id="searchForm">
                   
                </form>
            </div>
        </div>

        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
               
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                        <th>ID</th>
                            <th>物品价格</th>
                            <th>产品名称</th>
                           	<th>数量</th> 
                           	<th>图片</th> 
                            <th>操作</th>
                        </tr>
                        
                   		<c:forEach items="${cartsList}" var="cl" varStatus="ct">
                        <tr>
                         <td>${ct.count}</td>
                         <td>${cl.price}</td>
                         <td>${cl.goods.name}</td>
                          <td> 
                          <input  class="btn btn6"  style="width:40px;text-align:center;"  onclick="jia(${cl.id},${cl.quantity},${cl.goods.quantity})" value="+"type="button"> 
                          <input style="width:40px;text-align:center;"  onclick="" readonly value="${cl.quantity}" id="quantity"> 
                          <input  class="btn btn6"  style="width:40px;text-align:center;"  onclick="jian(${cl.id},${cl.quantity})" value="-" type="button"> 
                          </td>
                              <td><img src="<%=Const.ROOT%>images/${cl.goods.pic}" style="max-width:100px"/></td>
                            <td>
                              <a class="link-del" href="javascript:del(${cl.id})">删除</a>
                                
                            </td>
                        </tr>
                        </c:forEach>
                      
                    </table>
                </div>
                <input  style="float:right"  class="btn " onclick="jiesuan(${type})" readonly value="结算 总价:${price}元" > 
            </form>
        </div>
    </div>

<script type="text/javascript">
    
    function jia(id,quantity,a){
    	
    	location.href="<%=Const.ROOT%>carts?action=addquantity&id="+id+"&quantity="+quantity;
    	
    
    	
    	//$.getJSON("<%=Const.ROOT%>carts",{"action":"addquantity","id":id,"quantity":quantity},function(data){
    		//$("#quantity").val(data.quantity);
    		//$("#price").val("结算 总价:"+data.price+"元");
    	//});
   }
   // }
    function jian(id,quantity){
    
    	location.href="<%=Const.ROOT%>carts?action=delquantity&id="+id+"&quantity="+quantity;
    
    	
    //	$.getJSON("<%=Const.ROOT%>carts",{"action":"delquantity","id":id,"quantity":quantity},function(data){
    	//	$("#quantity").val(data.quantity);
        //	$("#price").val("结算 总价:"+data.price+"元");
    	//});
    	
    }
    
    
    	function del(id){
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>carts?action=del&id="+id;
    		}
    	}
    	function jiesuan(type){
    		if(confirm("是否结算 ")){
    			location.href="<%=Const.ROOT%>orders?action=add&type="+type;
    		}
    	}
    
    </script>
    <!--/main-->

</body>
</html>