<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="menu">
	<li><a href="<c:url value='/goods.findAll'/>">商品列表</a></li>
	<li><a href="<c:url value='/goods.findcate'/>">商品添加</a></li>
	<li><a href="<c:url value='/category.findAll'/>">分类列表</a></li>
	<li><a href="<c:url value='/admin/category_add.action'/>">添加分类</a></li>
	<li><a href="<c:url value='/admin/order_list.action'/>">订单列表</a></li>
	<li><a href="<c:url value='/admin/user_list.action'/>">用户列表</a></li>
</ul>