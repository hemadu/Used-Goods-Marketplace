
<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>后台管理</title>
     <link rel="stylesheet" type="text/css" href="<%=Const.ROOTMANAGE %>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=Const.ROOTMANAGE %>css/main.css"/>
    <link rel="stylesheet" type="text/css" href="<%=Const.ROOT %>webuploader/webuploader.css">
</head>
<body>
<%@include file="sidebar.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">
		
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="<%=Const.ROOTMANAGE %>index.jsp">首页</a><span class="crumb-step">&gt;</span>产品管理<span class="crumb-step">&gt;</span><span class="crumb-name">添加产品</span></div>
        </div>
        
        <div class="result-wrap">
            <div class="result-content">
                <form action="<%=Const.ROOT%>goods?action=add" method="post" id="myform" name="myform"  >
               	<input type="hidden" name="type" value="${need}">
                    <table class="insert-tab" width="100%">
                        <tbody>
                       
                       <tr>
                            <th width="120"><i class="require-red">*</i> 产品类型：</th>
                            <td>
                                <select name="cid" id="cid" class="common-text required" style="width:200px" required="required">
                                <!--  <option value="1">全部</option>-->
                         		 <c:forEach items="${goodstypeList}" var="gtl">
                    	    	<option value="${gtl.id }">${gtl.name}</option>
                             	</c:forEach>
                                </select>
                            </td>
                        </tr>
                        
                       
                       <tr>
                                <th><i class="require-red">*</i>物品名称：</th>
                                <td>
                                    <input class="common-text required" id="name" name="name" size="50" required="required" type="text">
                                </td>
                      </tr>
                       
                       <tr>
	                              <th>单价：</th>
	                                <td><input class="common-text required"  required="required" name="price"  size="50" type="text"></td>
	                   </tr>
                       
	                   <tr>
	                              <th>
	                              <c:if test="${need==0}">
	                              库存数量：
	                              </c:if>
	                               <c:if test="${need==1}">
	                              求购数量：
	                              </c:if>
	                              </th>
	                                <td><input class="common-text required"  required="required"  name="quantity"  size="50" type="text"></td>
	                   </tr>
	                    <c:if test="${need==0}">
	                   <tr style="line-height:20px">
	                              <th>
	                               图片地址：
	                              </th>
	                              <td>
	                              
									<div class="btns">
										<div id="picker">选择文件</div>
										<img src="" id="show" style="display:none;max-width:40px"/>
										<!--  <button id="ctlBtn" type="button" class="btn btn-default">开始上传</button>-->
									</div>
									<input type="hidden" class="common-text required" id="img"  name="pic" >
	                                </td>
	                   </tr>
	                  </c:if>
	                   <c:if test="${need==1}">
	                   <input type="hidden" class="common-text required" id="img"  name="pic"  value="${users.pic}">
	                   </c:if>
                         <tr>
	                                <th>内容：</th>
	                                <td>
	                                <div id="editor">
	                                
	                                </div>
	                                <input type="hidden" name="content"  id="content" />
	                          		</td>
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

</body>
<script type="text/javascript" src="<%=Const.ROOT %>js/jquery-3.2.1.js"></script>
<!--引入JS-->
<script type="text/javascript" src="<%=Const.ROOT %>webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=Const.ROOT %>wangEditor/wangEditor.js"></script>
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
	//富文本框
	var E = window.wangEditor;
    var editor = new E('#editor');
    editor.customConfig.uploadFileName = 'file';
	editor.customConfig.uploadImgServer = '<%=Const.ROOT %>upload';
	//当内容改变时修改content
	editor.customConfig.onchange = function (html) {
       $("#content").val(html);
    }
    editor.create();
</script>
</html>