
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
                <form action="<%=Const.ROOT%>goods?action=checklist" method="post"   id="searchForm">
                <input type="hidden" name="pageNo" id="pageNo"/>
                    <table class="search-tab">
                        <tbody><tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="type">
                                <option value="">全部</option>
                               
                    	   <c:forEach items="${goodstypeList}" var="gtl">
                    	    <option value="${gtl.id}"<c:if test="${gtl.id==params.type}">selected</c:if>>${gtl.name}</option>
                             </c:forEach>
                                </select>
                            </td>
                            <th width="70">产品名称:</th>
                            <td><input name="name" class="common-text" type="text" placeholder="产品名称" value="${params.name==null?'':params.name}"></td>
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
                            <th>用户</th>
                            <th>产品类型</th>
                            <th>产品名称</th>
                             <th>单价</th>
                              <th>库存数量</th>
                            <th>图片/头像</th>
                            <th>描述</th>
                           <th>需求</th> 
                            <th>操作</th>
                        </tr>
                        
                   		<c:forEach items="${goodsList}" var="gl" varStatus="st">
                        <tr>
                         <td>${st.count}</td>
                         <td>${gl.users.name}</td>
                         <td>${gl.goodstype.name}</td>
                            <td>${gl.name}</td>
                              <td>${gl.price}</td>
                              <td>${gl.quantity}</td>
                              <td><img src="<%=Const.ROOT%>images/${gl.pic}" style="max-width:100px"/></td>
                            <td>${gl.content}</td>
                            <c:choose>
                            <c:when test="${gl.type==1}">
                            <td>求购</td>
                            </c:when>
                            <c:otherwise>
                            <td>出售</td>
                            </c:otherwise>
                           </c:choose>
                            <td>
                               <a class="link-update" href="<%=Const.ROOT%>goods?action=checkgoods&id=${gl.id}">同意申请</a>
                              <a class="link-del" href="javascript:delcheck(${gl.id})">忽略</a>
                                
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
    	function delcheck(id){
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>goods?action=delcheck&id="+id;
    		}
    	}
    	function goPage(pageNo){
    		document.getElementById("pageNo").value=pageNo;
    		document.getElementById("searchForm").submit();
    		//$("#searchForm").submit();
    		//location.href="<%=Const.ROOT%>goods?action=list&pageNo="+pageNo;
    	}
    </script>
    <!--/main-->
</div>
</body>
</html>