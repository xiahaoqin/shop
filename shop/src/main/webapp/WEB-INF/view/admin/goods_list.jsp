<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html >
<html>
<head>
<base href="${base}/" />
<title>后台管理</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="<c:url value='/css/admin.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery-2.0.3.js'/>"></script>
<script type="text/javascript">
	function goPage(p){
		if(p<1 || p>${pageBean.totalPage}){
			return;
		}
		$("#page").val(p);
		$("#form1").submit();
	}
	$(function(){
		$("#category").val("${categoryId}");
	});
	
		function del(id){alert(111);
		if(confirm("您确认删除该记录吗?")){
			location.href="<c:url value='/goods.delete/"+id+"'/>";
		}
	 }
		
	
	
	//function go(p){
	//	$("#p").val(p);
	//	$("#form1").submit();
		//location.href="goods?opr=list&p="+p+"&categoryId="+$("#category").val()+"&name="+$("#name").val();
	//}
</script>
</head>
<body>
	<div class="container">
		<div id="header">
			<jsp:include page="header.jsp"></jsp:include>
		</div>
		<div id="admin_left">
			<ul class="submenu">
				<jsp:include page="left.jsp"></jsp:include>
			</ul>
			<div id="copyright"></div>
		</div>

		<div id="admin_right">
			<div class="headbar">
				<div class="searchbar">
					<form action="goods" method="get" id="form1"'>
						<input type="hidden" name="opr" value="list" />
						<input type="hidden" name="p" id="page" value="1" />
						<select class="auto" name="categoryId" id="category">
							<option value="">选择分类</option>
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
							 商品名： <input class="small" name="name" id="name" type="text" value="${name}">
							销售价:<input class="small" name="goods.name" id="name" type="text" value="">
							至<input class="small" name="goods.name" id="name" type="text" value="">
							<button class="btn" type="submit">
								<span class="sel">筛 选</span>
							</button>
					</form>
				</div>

				<div class="field">
					<table class="list_table">
						<col width="40px" />
						<col width="400px" />
						<col width="120px" />
						<col width="70px" />
						<col width="70px" />
						<col width="70px" />
						<thead>
							<tr>
								<th>选择</th>
								<th>商品名称</th>
								<th>分类</th>
								<th>销售价</th>
								<th>库存</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<form action="" method="post" name="orderForm">
				<div class="content">
					<table class="list_table">
						<col width="40px" />
						<col width="400px" />
						<col width="120px" />
						<col width="70px" />
						<col width="70px" />
						<col width="70px" />
						<tbody>
							<c:forEach items="${goodslist}" var="goods">
								<tr>
									<td><input name="" type="checkbox" value="1" /></td>
									<td><a href="<c:url value='/goods.findOne/${goods.id }'/>" target="_blank" title="${goods.name}">${goods.name}</a></td>
									<td>${goods.categoryName}</td>
									<td>${goods.salePrice}</td>
									<td>${goods.stock}</td>
									<td><a href="<c:url value='/goods.findById/${goods.id}'/>"><img
											class="operator" src="<c:url value='/images/admin/icon_edit.gif'/>" alt="编辑" /></a>
										<a href="javascript:void(0);" onclick="del('${goods.id}')" ><img
											class="operator" src="<c:url value='/images/admin/icon_del.gif'/>" alt="删除" /></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
			<div class='pages_bar'><a href='javascript:goPage(1)' id="first">首页</a>
			<a href='javascript:goPage(${pageBean.currPage-1})' id="first">上一页</a>
			<c:forEach begin="1" end="${pageBean.totalPage}" var="p">
				<a href="javascript:goPage('${p}')">${p}</a>
			</c:forEach>
			<a href='javascript:goPage(${pageBean.currPage+1})' id="first">下一页</a>
			<a href='javascript:goPage(${pageBean.totalPage})' id="last" >尾页</a><span>当前第${pageBean.currPage}页/共${pageBean.totalPage}页</span></div>
		</div>
		<div id="separator"></div>
	</div>
</body>
</html>