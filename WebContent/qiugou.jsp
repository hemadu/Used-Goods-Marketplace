<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="cn.util.Const"%>
<!DOCTYPE html>
<html>
<head>
<title>二手交易市场</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Best Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script src="js/jquery.min.js"></script>
<!-- //js -->
<!-- cart -->
	<script src="js/simpleCart.min.js"> </script>
<!-- cart -->
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<!-- for bootstrap working -->
	<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- animation-effect -->
<link href="css/animate.min.css" rel="stylesheet"> 
<script src="js/wow.min.js"></script>
<script>
 new WOW().init();
</script>
<!-- //animation-effect -->
</head>
	
<body>
<!-- header -->
	<div class="header">
		<div class="container">
			<div class="header-grid">
				<div class="header-grid-left animated wow slideInLeft" data-wow-delay=".5s">
					<ul>
						<c:if test="${users!=null}">
						<li><i class="glyphicon glyphicon-book" aria-hidden="true"></i>欢迎你：${users.name}</li>
						<li><i class="glyphicon glyphicon-log-out" aria-hidden="true"></i><a href="<%=Const.ROOT%>users?action=tologin"  target="_blank">个人中心</a></li>
						<li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a href="<%=Const.ROOT%>users?action=beforelogout">注销</a></li>
						</c:if>
						<c:if test="${users==null}">
						<li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a href="<%=Const.ROOT%>login.jsp">登录</a></li>
						<li><i class="glyphicon glyphicon-book" aria-hidden="true"></i><a href="<%=Const.ROOT%>register.jsp">注册</a></li>
						</c:if>
						<li><a href="<%=Const.ROOT%>goods?action=products&need=1">查看求购信息</a></li>
						<li><a href="<%=Const.ROOT%>goods?action=products&need=0">查看购物信息</a></li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="logo-nav">
				<div class="logo-nav-left animated wow zoomIn" data-wow-delay=".5s">
					<h1><a href="<%=Const.ROOT%>goods?action=products&need=0">二手市场<span>C2C</span></a></h1>
				</div>
				<div class="logo-nav-left1">
					<nav class="navbar navbar-default">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header nav_2">
						<button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					</nav>
				</div>
				
				<div class="logo-nav-right">
					<div class="search-box">
						<div id="sb-search1" class="sb-search sb-search-open ">
							 <form action="<%=Const.ROOT%>goods" method="post"   id="searchForm">
                			<input type="hidden" name="pageNo" id="pageNo"/>
                			<input type="hidden" name="action" value="products"/>
							<input class="sb-search-input" style="width:520px" name="name"placeholder="输入您要查找的内容..."  value="${params.name==null?'':params.name}" type="search" id="search">
							<input class="sb-search-submit" type="submit"   value="查询">
							<select id="country" name="type" onchange="change_country(this.value)" class="frm-field required sect">
							  <option value="">类型</option>
							  <c:forEach items="${goodstypeList}" var="gtl" varStatus="st">
								 <option value="${gtl.id}"<c:if test="${gtl.id==params.type}">selected</c:if>>${gtl.name}</option>
								</c:forEach>
							</select>
							
								<span class="sb-icon-search"> </span>
							</form>
						</div>
					</div>
						<!-- search-scripts -->
						<script src="js/classie.js"></script>
						<script src="js/uisearch.js"></script>
							<script>
								new UISearch( document.getElementById( 'sb-search' ) );
							</script>
						<!-- //search-scripts -->
				</div>
				<div class="header-right">
					<div class="cart box_1">
						<a href="<%=Const.ROOT%>carts?action=price&type=0" target="_blank">
							<h3> <div class="total">购物车</div>
								<img src="images/bag.png" alt="" />
							</h3>
						</a>
						
						<div class="clearfix"> </div>
					</div>	
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //header -->
<!-- breadcrumbs -->
	<div class="products">
		
		
		<div class="container">
		<div role="tabpanel" class="tab-pane fade bootstrap-tab-text active in" id="profile" aria-labelledby="profile-tab" style="width:730px;margin-left:150px;">
						
								<div class="bootstrap-tab-text-grids">
								<c:if test="${replyList!=null}">
								<div class="bootstrap-tab-text-grid">
										<div class="bootstrap-tab-text-grid-left">
											<img src="<%=Const.ROOT%>images/${message.users.pic}" alt=" " class="img-responsive" />
										</div>
										<div class="bootstrap-tab-text-grid-right">
											<ul>
												<li>${message.users.name}</li>
											</ul>
											<p>${message.content}</p>
										</div>
										<div class="clearfix"> </div>
									</div>
								<c:forEach items="${goodsList}" var="gl">
									<div class="bootstrap-tab-text-grid">
										<div class="bootstrap-tab-text-grid-left">
											<img src="<%=Const.ROOT%>images/${gl.users.pic}" alt=" " class="img-responsive" />
										</div>
										<div class="bootstrap-tab-text-grid-right">
											<ul>
												<li>${gl.users.name}</li>
											</ul>
											<p>${gl.content}</p>
										</div>
										<div class="clearfix"> </div>
									</div>
									</c:forEach>
										
								</c:if>
								<c:if test="${replyList==null}">
								<c:forEach items="${goodsList}" var="gl">
									<div class="bootstrap-tab-text-grid">
										<div class="bootstrap-tab-text-grid-left">
											<img src="<%=Const.ROOT%>images/${gl.users.pic}" alt=" " class="img-responsive" />
										</div>
										<div class="bootstrap-tab-text-grid-right">
											<ul>
												<li>求购商品:${gl.name}</li>
												<li><a href="<%=Const.ROOT%>goods?action=detail&id=${gl.id}&need=${need}"><span class="glyphicon glyphicon-share" aria-hidden="true"></span>联系买家</a></li>
												<li>单价 :${gl.price}</li>
												<li>求购数量:${gl.quantity}</li>
											</ul>
											<p>${gl.content}</p>
										</div>
										<div class="clearfix"> </div>
									</div>
									</c:forEach>
									</c:if>
									<nav class="numbering animated wow slideInRight" data-wow-delay=".5s">
				  <ul class="pagination paging">
				  <li>
				    <input aria-hidden="true" class="btn btn6"  onclick="goPage(1)" value="首页" type="button">
					</li>
					<li>
					  <input aria-hidden="true" class="btn btn6"  onclick="goPage(${pageBean.prevPage})" value="&laquo;" type="button">
					</li>
					<li>
					【${pageBean.pageNo}/${pageBean.btotalPage}】
					</li>
					<li>
					  <input aria-hidden="true" class="btn btn6"  onclick="goPage(${pageBean.nextPage1})" value="&raquo;" type="button">
					</li>
					 <li>
						 <input aria-hidden="true" class="btn btn6" onclick="goPage(${pageBean.btotalPage})" value="末页" type="button"> 
					</li>
				  </ul>
				</nav>
							
								</div>
							</div>
		
		</div>
		
		
	</div>
<div class="footer">
	<div class="footer-logo animated wow slideInUp animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInUp;">
				<h2><a href="<%=Const.ROOT%>goods?action=products&need=0">二手市场<span>C2C</span></a></h2>
				<span><a href="<%=Const.ROOTMANAGE%>login.jsp"  target="_blank" style="color: white">后台管理</a></span>
			</div>
	</div>
<!-- //single-related-products -->
<!-- footer -->
	<!--  <div class="footer">
		<div class="container">
			<div class="footer-grids">
				<div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".5s">
					<h3>About Us</h3>
					<p>Duis aute irure dolor in reprehenderit in voluptate velit esse.<span>Excepteur sint occaecat cupidatat 
						non proident, sunt in culpa qui officia deserunt mollit.</span></p>
				</div>
				<div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".6s">
					<h3>Contact Info</h3>
					<ul>
						<li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>1234k Avenue, 4th block, <span>New York City.</span></li>
						<li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a href="mailto:info@example.com">info@example.com</a></li>
						<li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 567</li>
					</ul>
				</div>
				<div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".7s">
					<h3>Flickr Posts</h3>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/13.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/14.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/15.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/16.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/13.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/14.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/15.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/16.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/13.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/14.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/15.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="footer-grid-left">
						<a href="single.html"><img src="images/16.jpg" alt=" " class="img-responsive" /></a>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="col-md-3 footer-grid animated wow slideInLeft" data-wow-delay=".8s">
					<h3>Blog Posts</h3>
					<div class="footer-grid-sub-grids">
						<div class="footer-grid-sub-grid-left">
							<a href="single.html"><img src="images/9.jpg" alt=" " class="img-responsive" /></a>
						</div>
						<div class="footer-grid-sub-grid-right">
							<h4><a href="single.html">culpa qui officia deserunt</a></h4>
							<p>Posted On 25/3/2016</p>
						</div>
						<div class="clearfix"> </div>
					</div>
					<div class="footer-grid-sub-grids">
						<div class="footer-grid-sub-grid-left">
							<a href="single.html"><img src="images/10.jpg" alt=" " class="img-responsive" /></a>
						</div>
						<div class="footer-grid-sub-grid-right">
							<h4><a href="single.html">Quis autem vel eum iure</a></h4>
							<p>Posted On 25/3/2016</p>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="footer-logo animated wow slideInUp" data-wow-delay=".5s">
				<h2><a href="<%=Const.ROOT%>goods?action=products&need=0">二手市场<span>买你所需</span></a></h2>
			</div>
			<div class="copy-right animated wow slideInUp" data-wow-delay=".5s">
				<p>Copyright &copy; 2016.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
			</div>
		</div>
	</div>-->
<!-- //footer -->
<!-- zooming-effect -->
	<script src="js/imagezoom.js"></script>
	    <script>
	    function goPage(pageNo){
    		document.getElementById("pageNo").value=pageNo;
    		document.getElementById("searchForm").submit();
    		//$("#searchForm").submit();
    		//location.href="<%=Const.ROOT%>goods?action=list&pageNo="+pageNo;
    	}
    
    	function searchByType(type){
    		document.getElementById("country").value=type;
    		document.getElementById("searchForm").submit();
    	}
    	function searchByName(name){
    		document.getElementById("search").value=name;
    		document.getElementById("searchForm").submit();
    	}
</script>
<!-- //zooming-effect -->
</body>

</html>