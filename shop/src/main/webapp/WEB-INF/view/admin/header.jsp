<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="logo">
	<a href="<c:url value='/admin/index.action'/>">
		<img src="<c:url value='/images/admin/logo.png'/>" width="303" height="43" /></a>
</div>
<script type="text/javascript">
	function logout() {
		if (confirm("确认退出吗")) {
			location.href = "<c:url value='/endsession'/>";
			//window.close();
		}
	}
</script>
<p>
	<a href="javascript:logout()">退出管理</a> <a href="<c:url value='/admin/index.action'/>">后台首页</a> 
	<a href="<c:url value='/index.action'/>" target='_blank'>商城首页</a>
	<span>您好 <label class='bold'>${sessionScope.user.name }</label>，当前身份 <label
		class='bold'>超级管理员</label></span>
</p>