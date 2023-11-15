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
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE %>index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">用户管理</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
            <c:if test="${users.role==1}">
                <form action="<%=Const.ROOT %>users?action=list" method="post"   id="searchForm">
                <input type="hidden" name="pageNo" id="pageNo"/>
                   <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="type" id="">
                                    <option value="3"<c:if test="${params.type==3|| params.type==null}">selected</c:if>>全部</option>
                                    <option value="1" <c:if test="${params.type==1}">selected</c:if>>管理员 </option><option value="0" <c:if test="${params.type==0}">selected</c:if>>普通用户</option>
                                </select>
                            </td>
                            <th width="70">姓名:</th>
                            <td><input class="common-text" placeholder="姓名" name="name" value="${params.name==null?'':params.name}" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
                </c:if>  
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
               
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                             <c:if test="${users.role==1}">
                            <th>ID</th>
                            </c:if>
                            <th>身份</th>
                            <th>电话</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>年级</th>
                             <th>头像</th>
                            <th>学校</th>
                            <th>专业</th>
                            <th>操作</th>
                        </tr>
                        <c:if test="${users.role==1}">
                        <c:forEach items="${usersList}" var="u" varStatus="st">
                        <tr>
                         <td>${st.count}</td>
                            <td>
                            <c:choose>
                            <c:when test="${u.role==1}">
                            管理员
                            </c:when>
                            <c:otherwise>
                            普通用户
                            </c:otherwise>
                            </c:choose>
                            </td>
                            <td>${u.phone}</td>
                            <td>${u.name}</td>
                            <td>${u.sex}</td>
                            <td>${u.grade}</td>
                            <td><img src="<%=Const.ROOT%>images/${u.pic}" style="max-width:100px"/></td>
                            <td>${u.school}</td>
                            <td>${u.major}</td>
                            
                            <td>
                                <a class="link-update" href="<%=Const.ROOT%>users?action=showupdate&id=${u.id}&uid=${users.id}">修改</a>
                                 <c:if test="${u.role==0}">
                               <a class="link-del" href="javascript:del(${u.id})">删除</a>
                                </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                  </c:if>
                  <c:if test="${users.role==0}">
                    <tr>
                            <td> 普通用户 </td>
                            <td>${users.phone}</td>
                            <td>${users.name}</td>
                            <td>${users.sex}</td>
                            <td>${users.grade}</td>
                            <td><img src="<%=Const.ROOT%>images/${users.pic}" style="max-width:100px"/></td>
                            <td>${users.school}</td>
                            <td>${users.major}</td>
                            
                            <td>
                                <a class="link-update" href="<%=Const.ROOT%>users?action=showupdate&id=${users.id}&uid=${users.id}">修改</a>
                            </td>
                        </tr>
                  </c:if>
                    </table>
                    <div class="list-page"> 
                    <c:if test="${users.role==1}">
                    <input class="btn btn6" onclick="goPage(1)" value="首页" type="button"> 
                    <input class="btn btn6" onclick="goPage(${pageBean.prevPage})" value="上一页" type="button">
                     【${pageBean.pageNo}/${pageBean.totalPage}】
                    <input class="btn btn6" onclick="goPage(${pageBean.nextPage})"  value="下一页" type="button">
                  <input class="btn btn6" onclick="goPage(${pageBean.totalPage})" value="末页" type="button">  
                  </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>
    	function del(id){
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>users?action=del&id="+id;
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