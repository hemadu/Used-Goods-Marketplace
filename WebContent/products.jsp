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
						<!--<c:if test="${users.role==0}">-->
						<li><i class="glyphicon glyphicon-log-out" aria-hidden="true"></i><a href="<%=Const.ROOT%>users?action=tologin"  target="_blank">个人中心</a></li>
						<!--</c:if>-->
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
							  <option value="">全部</option>
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
			<div class="col-md-4 products-left">
				
				<div class="categories animated wow slideInUp animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInUp;">
					<h3>商品类型</h3>
					<ul class="cate">
						<li><a href="javascript:searchByType(${goodstypeList[0].id})">${goodstypeList[0].name}</a> <span>(4)</span></li>
						<ul>
								<li><a href="javascript:searchByName('笔记本')">笔记本</a></li>
								<li><a href="javascript:searchByName('手机')">手机</a> </li>
								<li><a href="javascript:searchByName('平板电脑')">平板电脑</a> </li>
								<li><a href="javascript:searchByName('手表')">手表</a> </li>
							</ul>
						<li><a href="javascript:searchByType(${goodstypeList[1].id})">${goodstypeList[1].name}</a> <span>(3)</span></li>
						<ul>
								<li><a href="javascript:searchByName('守望先锋')">守望先锋</a> </li>
								<li><a href="javascript:searchByName('英雄联盟')">英雄联盟</a> </li>
								<li><a href="javascript:searchByName('CS-GO')">CS-GO</a> </li>
							</ul>
						<li><a href="javascript:searchByType(${goodstypeList[2].id})">${goodstypeList[2].name}</a> <span>(5)</span></li>
						<ul>
								<li><a href="javascript:searchByName('帽子')">帽子</a> </li>
								<li><a href="javascript:searchByName('西装')">西装</a> </li>
								<li><a href="javascript:searchByName('衬衫')">衬衫</a> </li>
								<li><a href="javascript:searchByName('裤子')">裤子</a> </li>
								<li><a href="javascript:searchByName('鞋')">鞋</a> </li>
							</ul>
						<li><a href="javascript:searchByType(${goodstypeList[3].id})">${goodstypeList[3].name}</a> <span>(3)</span></li>
						<ul>
								<li><a href="javascript:searchByName('花盆')">花盆</a></li>
								<li><a href="javascript:searchByName('水壶')">水壶</a></li>
								<li><a href="javascript:searchByName('窗帘')">窗帘</a></li>
							</ul>
					<li><a href="javascript:searchByType(${goodstypeList[4].id})">${goodstypeList[4].name}</a> <span>(#)</span></li>
						<ul>
								
							</ul>
					</ul>
				</div>
				<div class="new-products animated wow slideInUp animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInUp;">
					<h3>
					<c:if test="${need==0}">新产品</c:if>
					<c:if test="${need==1}">新求购</c:if>
					</h3>
					<div class="new-products-grids">
						<div class="new-products-grid">
							<div class="new-products-grid-left">
								<a href="<%=Const.ROOT%>goods?action=detail&id=${goodsList1[maxId].id}&need=${need}">
								<img src="<%=Const.ROOT%>images/${goodsList1[maxId].pic}" alt=" " class="img-responsive"></a>
							</div>
							<div class="new-products-grid-right">
								<h4>${goodsList1[maxId].name}</h4>
								<!-- <p> <span class="item_price">类型：</span>${goodsList[maxId].goodstype.name} </p> -->
								<div class="simpleCart_shelfItem new-products-grid-right-add-cart">
								
								<c:if test="${goodsList1[maxId].type==0}">
									<p> <span class="item_price">￥${goodsList1[maxId].price}元</span><a class="item_add"  href="javascript:addcarts(${goodsList1[maxId].id})" >加入购物车 </a></p>
								</c:if>
								<c:if test="${goodsList1[maxId].type==1}">
									<p> <span class="item_price">￥${goodsList1[maxId].price}元</span><a class="item_add" href="<%=Const.ROOT%>goods?action=detail&id=${goodsList1[maxId].id}&need=${need}">联系买家</a></p>
								</c:if>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
						<div class="new-products-grid">
							<div class="new-products-grid-left">
								<a href="<%=Const.ROOT%>goods?action=detail&id=${goodsList1[maxId-1].id}&need=${need}">
								<img src="<%=Const.ROOT%>images/${goodsList1[maxId-1].pic}" alt=" " class="img-responsive"></a>
							</div>
							<div class="new-products-grid-right">
								<h4>${goodsList1[maxId-1].name}</h4>
								<!-- <p> <span class="item_price">类型：</span>${goodsList[maxId].goodstype.name} </p> -->
								<div class="simpleCart_shelfItem new-products-grid-right-add-cart">
								
								<c:if test="${goodsList1[maxId-1].type==0}">
									<p> <span class="item_price">￥${goodsList1[maxId-1].price}元</span><a class="item_add"  href="javascript:addcarts(${goodsList1[maxId-1].id})" >加入购物车 </a></p>
								</c:if>
								<c:if test="${goodsList1[maxId-1].type==1}">
									<p> <span class="item_price">￥${goodsList1[maxId-1].price}元</span><a class="item_add" href="<%=Const.ROOT%>goods?action=detail&id=${goodsList1[maxId-1].id}&need=${need}">联系买家 </a></p>
								</c:if>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
						<div class="new-products-grid">
							<div class="new-products-grid-left">
							<a href="<%=Const.ROOT%>goods?action=detail&id=${goodsList1[maxId-2].id}&need=${need}">
								<img src="<%=Const.ROOT%>images/${goodsList1[maxId-2].pic}" alt=" " class="img-responsive"></a>
							</div>
							<div class="new-products-grid-right">
								<h4>${goodsList1[maxId-2].name}</h4>
								<!-- <p> <span class="item_price">类型：</span>${goodsList[maxId].goodstype.name} </p> -->
								<div class="simpleCart_shelfItem new-products-grid-right-add-cart">
								
								<c:if test="${goodsList1[maxId-2].type==0}">
									<p> <span class="item_price">￥${goodsList1[maxId-2].price}元</span><a class="item_add"  href="javascript:addcarts(${goodsList1[maxId-2].id})" >加入购物车 </a></p>
								</c:if>
								<c:if test="${goodsList1[maxId-2].type==1}">
									<p> <span class="item_price">￥${goodsList1[maxId-2].price}元</span><a class="item_add" href="<%=Const.ROOT%>goods?action=detail&id=${goodsList1[maxId-2].id}&need=${need}">联系买家 </a></p>
								</c:if>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
						
						
					</div>
				</div>
			</div>
			<div class="col-md-8 products-right">
				<div class="products-right-grid">
					<div class="products-right-grids animated wow slideInRight" data-wow-delay=".5s">
						
						
						<div class="clearfix"> </div>
					</div>
					<div class=" animated wow slideInRight" data-wow-delay=".5s">
						<img src="images/18.jpg" alt=" " class="img-responsive" />
						<div class="products-right-grids-position1">
							
						</div>
					</div>
				</div>
						<div class="products-right-grids-bottom">
						  <c:forEach items="${goodsList}" var="gl" >
						 <div class="new-collections-grid1 products-right-grid1 animated wow slideInUp aaa" data-wow-delay=".5s" style="width:222px;float:left;margin-bottom:16px;margin-right:16px;">
							<div class="new-collections-grid1-image">
								<a href="single.html" class="product-image">
								<img src="<%=Const.ROOT%>images/${gl.pic}" alt=" " class="img-responsive"></a>
								<div class="new-collections-grid1-image-pos products-right-grids-pos">
									<a href="<%=Const.ROOT%>goods?action=detail&id=${gl.id}&need=${need=null?0:need}">      详    情</a>
								</div>
							</div>
							<h4><a>${gl.name}</a></h4>
							<div class="simpleCart_shelfItem products-right-grid1-add-cart">
								<c:if test="${gl.type==0}">
								<p><i>${gl.goodstype.name}</i> <span class="item_price">￥${gl.price}元</span><a class="item_add" href="javascript:addcarts(${gl.id})">加入购物车</a></p>
								
								</c:if>
								<c:if test="${gl.type==1}">
								<p><i>${gl.goodstype.name}</i> <span class="item_price">￥${gl.price}元</span><a class="item_add"   href="<%=Const.ROOT%>goods?action=detail&id=${gl.id}&need=${need}">联系买家</a></p>
								
								</c:if>
								
							</div>
						</div>	 
						</c:forEach>
					<div class="clearfix"> </div>
				</div>
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
			<div class="clearfix"> </div>
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
    	function addcarts(pid){
    		<c:if test="${users==null}">
    		alert("请先登录！");
    		return;
    		</c:if>
    		<c:if test="${users.role==1}">
    		alert("您没有此权限！");
    		return;
    		</c:if>
    		
    		//location.href="<%=Const.ROOT%>carts?action=add&id="+pid;
    		$.getJSON("<%=Const.ROOT%>carts",{"action":"add","id":pid},function(data){
    			if(data==1){
    				alert('已成功添加购物车! ');
    			}else if(data==2){
    				alert('超出需求上线! ');
    			}else if(data==3){
    				alert('不能购买自己的商品! ');
    			}else if(data==4){
    				alert('库存不足! ');
    			}else{
    				alert('操作失败! ');
    			}
    		});
    	}
</script>
<!-- //zooming-effect -->
</body>

</html>