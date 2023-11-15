
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

		<div class="search-wrap">
            <div class="search-content">
                <form action="<%=Const.ROOT%>orders?action=list" method="post"   id="searchForm">
                <input type="hidden" name="pageNo" id="pageNo"/>
                    <table class="search-tab">
                        <tbody>
                        
                        <tr>
                            <th width="70">产品名称:</th>
                            <td><input name="no" class="common-text" type="text" placeholder="订单号" value="${params.no==null?'':params.no}"></td>
                            <td><input name="sub" class="btn btn-primary btn2" type="submit" value="查询"></td>
                        </tr>
                    </tbody></table>
                </form>
            </div>
        </div>

        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
               
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                           
                            <th>ID</th>
                            <th>订单号</th>
                            <th>总价</th>
                            <th>时间</th>
                             <th>状态</th>
                            <th>操作</th>
                        </tr>
                        
                   		<c:forEach items="${ordersList}" var="ol" varStatus="st">
                        <tr>
                         <td>${st.count}</td>
                         <td>${ol.no}</td>
                         <td>${ol.totalprice}</td>
                         <td>${ol.optime}</td>
                         <td>${ol.status}</td>
                            <td>
                               <a class="link-update" href="<%=Const.ROOT%>ordertail?action=list&id=${ol.id}">查看详情</a>
                               <c:if test="${choose==0}">
                              <a class="link-del" href="javascript:del(${ol.id})">删除</a>
                              </c:if>
                              <c:if test="${choose==1}">
                              <a class="link-del" href="javascript:send(${ol.id})">发货</a>
                              </c:if>
                                
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
    	function del(id){
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>orders?action=del&id="+id;
    		}
    	}
    	function send(id){
    		if(confirm("是否发货 ")){
    			location.href="<%=Const.ROOT%>orders?action=update&id="+id;
    		}
    	}
    	function goPage(pageNo){
    		document.getElementById("pageNo").value=pageNo;
    		document.getElementById("searchForm").submit();
    		//$("#searchForm").submit();
    		//location.href="<%=Const.ROOT%>orders?action=list&pageNo="+pageNo;
    	}
    </script>
    <!--/main-->
</div>
</body>
</html>