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
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE %>index.jsp">首页</a><span class="crumb-step">&gt;</span>信息管理</div>
        </div>


        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
               
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <td>
                            
                          <c:if test="${goods.type==0}">
                            物品图片
                         </c:if>
                         <c:if test="${goods.type==1}">
                            描述
                         </c:if>
                            
                            </td> 
                            <td>物品名称</td>
                            <td>价格</td> 
                            
                           
                             
                               <td>
                               <c:if test="${change!=1}">
                               <c:if test="${goods.type==1}">
                            	求购人姓名
                         		</c:if>
                         		<c:if test="${goods.type==0}">
                               商家
                               </c:if>
                               </c:if>
                               <c:if test="${change==1}">
                               求购数量
                               </c:if>
                               </td>
                         	
                            
                        </tr>
                       
                        <tr>
                        <td>
                         <c:if test="${goods.type==0}">
                           <img src="<%=Const.ROOT%>images/${goods.pic}" style="max-width:100px"/>
                         </c:if>
                         <c:if test="${goods.type==1}">
                            ${goods.content}
                         </c:if>
                        </td>
                         <td>${goods.name}</td>
                            <td>${goods.price}</td>
                            <c:if test="${change==1}">
                              <td>${goods.quantity}</td>
                               </c:if>
                            <c:if test="${change!=1}">
                               <td>${goods.users.name}</td>
                               </c:if>
                            </tr>
                         <tr>
                         <td>留言用户姓名</td>
                         <td>留言时间</td> 	
                         <td>留言内容</td>
                         <td>操作</td>
                        </tr>
                        
                       <tr>
                         <td>${musers.name}</td>
                        <td>${message.optime}</td>
                            <td>${message.content}</td>
                            <td><a class="link-del" href="javascript:del(${message.id})">删除留言</a></td>
                       </tr> 
                        <tr>
                        <td>回复人</td> 
                         <td>回复内容</td> 
                         <td>回复时间</td>
                         <td>操作</td>
                        </tr>
                        <c:forEach items="${replyList}" var="rl" varStatus="st">
                        <tr>
                         <td>${rl.users.name}</td>
                        <td>${rl.content}</td>
                        <td>${rl.optime}</td>
                        <td><a class="link-del" href="javascript:delReply(${rl.id},${message.id})">删除</a></td>
                       </tr>
                       </c:forEach>
                    </table>
                </div>
            </form>
            <div class="result-content">
                     <form action="<%=Const.ROOT%>reply?action=add&mid=${message.id}&uid=${users.id}" method="post"  name="myform" >
                      <textarea name="content" class="common-textarea" id="content" cols="30" style="width: 99%;" rows="10"></textarea>
                      <input class="btn btn-primary btn6 mr10"  value="回复" type="submit">
                    </form>
                    </div>
        </div>
    </div>
    <script>
    	function del(id){
    		<c:if test="${goods.sid==users.id||musers.id==users.id}">
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>message?action=del&id="+id;
    		}
    		return;
    		</c:if>
    		
    		<c:if test="${goods.sid!=users.id}">
    		alert("无法删除他人商品的留言！");
    		</c:if>
    	}
    	function delReply(id,mid){
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>reply?action=del&mid="+mid+"&id="+id;
    		}
    	}
    	function goPage(pageNo){
    		document.getElementById("pageNo").value=pageNo;
    		document.getElementById("searchForm").submit();
    		//$("#searchForm").submit();
    		//location.href="<%=Const.ROOTMANAGE%>goods.jsp?pageNo="+pageNo;
    	}
    </script>
    <!--/main-->
</div>
</body>
</html>