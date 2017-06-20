<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidebar f_l">
	<img src="<c:url value='/images/front/ucenter/ucenter.gif'/>" width="180" height="40" />
	<div class="box">
		<div class="title">
			<h2>交易记录</h2>
		</div>
		<div class="cont">
			<ul class="list">
				<li><a href="<c:url value='/usercenter/order_list.action'/>">我的订单</a></li>
			</ul>
		</div>
	</div>
	<div class="box">
		<div class="title">
			<h2 class='bg4'>账户资金</h2>
		</div>
		<div class="cont">
			<ul class="list">
				<li><a href="<c:url value='/usercenter/account_log.action'/>">帐户余额</a></li>
				<li><a href="<c:url value='/usercenter/online_recharge.action'/>">在线充值</a></li>
			</ul>
		</div>
	</div>
	<div class="box">
		<div class="title">
			<h2 class='bg5'>个人设置</h2>
		</div>
		<div class="cont">
			<ul class="list">
				<li><a href="<c:url value='/address.findAll'/>">地址管理</a></li>
			</ul>
		</div>
	</div>
</div>
