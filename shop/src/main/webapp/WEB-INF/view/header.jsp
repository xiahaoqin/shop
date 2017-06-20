<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function logout() {
		if (confirm("确认退出吗")) {
			location.href = "<c:url value='/endsession'/>";
			//window.close();
		}
	}
</script>
<div class="header">
	<h1 class="logo">
		<a title="" style="background:url(<c:url value='/images/logo.png'/>);" href="">电子商务平台</a>
	</h1>
	<c:if test="${not empty sessionScope.user}">
		<ul class="shortcut">
			<c:if test="${sessionScope.user.states eq 2 }">
				<li><a href="<c:url value='/admin/index.action'/>">后台管理</a></li>
			</c:if>
			<li class="first"><a href="<c:url value='/usercenter/index.action'/>">我的账户</a></li>
			<li><a href="<c:url value='/usercenter/order_list.action'/>">我的订单</a></li>
			<li class='last'><a href="">使用帮助</a></li>
		</ul>
	</c:if>
	
	<p class="loginfo">
		<c:if test="${not empty sessionScope.user}">
		${user.name}您好，欢迎您来到${site}购物！[<a href="javascript:logout()" class="reg">安全退出</a>]
	  </c:if>
		<c:if test="${empty sessionScope.user}">
	  	[<a href="<c:url value='/login.action'/>">登录</a>
			<a class="reg" href="<c:url value='/register.action'/>">免费注册</a>]
	  </c:if>
	</p>
</div>