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
    <link rel="stylesheet" type="text/css" href="<%=Const.ROOT %>webuploader/webuploader.css">
</head>
<%@ include file="sidebar.jsp"%>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE %>index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">修改用户</span></div>
        </div>
        
        	<div class="result-wrap">
            <div class="result-content">
                <form action="<%=Const.ROOT%>users" method="post" id="myform" name="myform">
                <input type="hidden" name="id"  value="${users.id}" />
                <input type="hidden" name="uid"  value="${users1.id}" />
                	<input type="hidden" name="action" value="update"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <c:if test="${users.role==1}">
                        <tr>
                            <th width="120"><i class="require-red">*</i>身份：</th>
                            <td>
                                <select name="role" id="role" class="required">
                                    <option value="1"<c:if test="${users.role==1}">selected</c:if>>管理员</option>
                                    <option value="0"<c:if test="${users.role==0}">selected</c:if>>普通用户</option>
                                </select>
                            </td>
                        </tr>
                        </c:if>
                      <c:if test="${users.role==0}">
                       <tr>
                            <th width="120"><i class="require-red">*</i>身份：</th>
                            <td>
                             <input class="common-text" size="50"  value=" 普通用户" type="text" readonly>
                            <input class="common-text" name="role" size="50"  type="hidden"value="0" type="text" readonly>
                            </td>
                        </tr>
                      </c:if>
                         <tr>
                                <th>用户名：</th>
                                <td><input class="common-text" name="phone" size="50" value="${users.phone}" type="text" readonly></td>
                            </tr>
                          
	                    <tr style="line-height:20px">
	                              <th>头像：</th>
	                              <td>
	                             <img src="<%=Const.ROOT %>images/${users.pic}"style="max-width:100px"id="show" />
									<div class="btns">
										<div id="picker">选择文件</div>
										<!--  <button id="ctlBtn" type="button" class="btn btn-default">开始上传</button>-->
									</div>
									<input type="hidden" class="common-text required" id="img"  name="pic" >
	                                </td>
	                   </tr>
	                   
	                   
	                   
                             <tr>
                                <th><i class="require-red">*</i>姓名：</th>
                                <td>
                                    <input class="common-text required" id="name" name="name" size="50" value="${users.name}" type="text">
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>性别：</th>
                                <td>
                                    <input class="common-text required" id="sex" name="sex" size="50" value="${users.sex}" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>年级：</th>
                                <td>
                                    <input class="common-text required" id="grade" name="grade" size="50" value="${users.grade}" type="text">
                                </td>
                            </tr>
                              <tr>
                                <th><i class="require-red">*</i>学校：</th>
                                <td>
                                    <input class="common-text required" id="school" name="school" size="50" value="${users.school}" type="text">
                                </td>
                            </tr>
                             <tr>
                                <th><i class="require-red">*</i>专业：</th>
                                <td>
                                    <input class="common-text required" id="major" name="major" size="50" value="${users.major}" type="text">
                                </td>
                            </tr>
                           
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>
        
    </div>
    <script>
    	function del(id){
    		if(confirm("是否删除 ")){
    			location.href="<%=Const.ROOT%>users?action=del&id="+id;
    		}
    	}
    </script>
    <!--/main-->
</div>
</body>
<script type="text/javascript" src="<%=Const.ROOT %>js/jquery-3.2.1.js"></script>
<!--引入JS-->
<script type="text/javascript" src="<%=Const.ROOT %>webuploader/webuploader.js"></script>
<script>
	var uploader = WebUploader.create({
		// swf文件路径
		swf : '<%=Const.ROOT %>webuploader/Uploader.swf',//高版本浏览器可以不要
		// 文件接收服务端。
		server : '<%=Const.ROOT %>/upload',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#picker',
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize : false
	});
	//当选择文件之后，就开始上传
	uploader.on('fileQueued', function(file) {
		// 选中文件时要做的事情，比如在页面中显示选中的文件并添加到文件列表，获取文件的大小，文件类型等	
		uploader.upload();//可以选择之后就上传
	});
	//上传成功后
	uploader.on("uploadSuccess", function(file, response) {
		alert("上传成功!");
		$("#show").attr("src","<%=Const.ROOT %>images/"+response.filename).show();
		$("#img").val(response.filename);//获取服务响应的文件名
	});
</script>
</html>