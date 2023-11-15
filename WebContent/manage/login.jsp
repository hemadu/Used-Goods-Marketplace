<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>『二手交易市场』后台管理</title>
	<link rel="stylesheet" type="text/css" href="<%=Const.ROOTMANAGE%>css/admin_login.css"/>
</head>
<body>
<div class="admin_login_wrap">
    <h1>个人中心</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="<%=Const.ROOT%>users" method="post">
            <input type="hidden" name="action" value="login"/>
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="phone" value="admin" id="phone" size="40" class="admin_input_style" required="required" />
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="password" value="admin" id="pwd" size="40" class="admin_input_style" required="required"/>
                    </li>
                    <li>
                        <label for="code">验证码：</label>
                        <input type="text" name="code" id="code" size="20" class="admin_input_style" required="required"/> <img src="vcode.jsp"  id="vcode"onclick="changeCode()" style="vertical-align:middle"/>
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="提交" class="btn btn-primary" />
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <p class="admin_copyright"><a tabindex="5" href="<%=Const.ROOT%>goods?action=products" >返回首页</a> &copy; </p>
</div>
</body>
<script>
function changeCode(){
	document.getElementById("vcode").src="vcode.jsp?id="+new Date();
}

</script>
</html>