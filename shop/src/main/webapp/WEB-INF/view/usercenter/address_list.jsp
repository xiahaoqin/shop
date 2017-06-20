<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>地址管理_嗨购商城</title>
<jsp:include page="../base.jsp" />
<script type="text/javascript">
	 $(document).ready(function(){
	 	var name = null;
    	var street = null;
	 	
	 	$("#name").blur(function(){
    		if($("#name").val().length==0){
    			$("#nametip").css("color","red");
    			$("#nametip").html("收货人姓名不能为空");
    			name = false;
    		}else{
    			$("#nametip").css("color","blue");
    			$("#nametip").html("ok");
    			name = true;
    		}
    	});
    	
    	$("#s_county").change(function(){
			 var s1 = $("#s_province").val();
			 var s2 = $("#s_city").val();
			 var s3 = $("#s_county").val();
			 var address=""+s1+ s2 + s3;
    	
    			$("#area").val(address);
    	});
    	
    	$("#street").blur(function(){
    		if($("#street").val().length<6||$("#street").val().length>30){
    			$("#streettip").css("color","red");
    			$("#streettip").html("6-30位");
    			street = false;
    		}else{
    			$("#streettip").css("color","blue");
    			$("#streettip").html("ok");
				street = true;
    		}
    	});
    	
    	$("#adb").click(function(){
    		if(name!=null&&street!=null){
    			if(name==true&&street==true){
    				$("#adsub").submit();
    			}
    		}else{
    			$("#msg").html("检查一下必填信息");
    		}
    	});
	 });
	 
</script>
</head>
<body class="index">
	<div class="ucenter container">
		<jsp:include page="../header.jsp"></jsp:include>
		<jsp:include page="../navbar.jsp"></jsp:include>
		<jsp:include page="../search.jsp"></jsp:include>
		<div class="wrapper clearfix">
			<jsp:include page="left.jsp"></jsp:include>
			<div class="main f_r">
				<div class='tabs'>
					<div class="uc_title m_10 tabs_menu">
						<label class="current node"><span>地址管理</span></label>
					</div>
					<div class='tabs_content'>
						<div id="address_list" class="form_content m_10 node">
							<div class="uc_title2 m_10">
								<strong>已保存的有效地址</strong>
							</div>
							<table class="list_table" width="100%" cellpadding="0"
								cellspacing="0">
								<col width="120px" />
								<col width="120px" />
								<col width="120px" />
								<col width="120px" />
								<col width="120px" />
								<col />
								<thead>
									<tr>
										<th>收货人</th>
										<th>所在地区</th>
										<th>街道地址</th>
										<th>电话/手机</th>
										<th>邮编</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${listAdd }" var="add">
									<tr>
										<td>${add.uname }</td>
										<td>${add.area }</td>
										<td>${add.street }</td> 
										<td>${add.cellPhone }</td>
										<td>${add.code }</td>
										<td><a class="blue" href='<c:url value='/address.findOne/${add.id }'/>'>修改</a>|
											<a class="blue" href="<c:url value='/address.delete/${add.id }'/>">删除</a>|
											<a class="red2" href="<c:url value='/address.setsta/${add.id }'/>">取消默认</a>
									</tr>	
								</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!--表单修改-->
				<div class="orange_box" id='address_form'>
				<span id="msg"></span>
					<form action="<c:url value='/${subAdd}'/>" method='post' name='form' id="adsub">
						<table class="form_table" width="100%" cellpadding="0"
							cellspacing="0">
							<col width="120px" />
							<col />
							<caption>收货地址</caption>
							<tr>
								<th><span class="red">*</span> 收货人姓名：</th>
								<td><input name='uname' id="name" class="normal" type="text" value="${address.uname }"/><label id="nametip">收货人真实姓名，方便快递公司联系。</label></td>
							</tr>
							<tr>
								<th><span class="red">*</span> 所在地区：</th>
								<td>
									<select id="s_province" name="s_province"></select>  
									<select id="s_city" name="s_city" ></select>  
									<select id="s_county" name="s_county"></select>
									
									<script src="<c:url value='/js/area.js'/>" type="text/javascript"></script>
									<script type="text/javascript">_init_area();</script>
								    
								    <input id="area" name="area" type="hidden" value="" />
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span> 街道地区：</th>
								<td><input name='street' id="street" class="normal" type="text" value="${address.street }"/><label id="streettip">真实详细收货地址，方便快递公司联系。</label></td>
							</tr>
							<tr>
								<th>邮政编码：</th>
								<td><input name='code' class="normal" type="text" value="${address.code }"/><label>邮政编码,如250000。</label></td>
							</tr>
							<tr>
								<th>电话号码：</th>
								<td><input name='phone' class="normal" type="text" value="${address.phone }"/><label>电话号码,如010-12345688。</label></td>
							</tr>
							<tr>
								<th>手机号码：</th>
								<td><input name='cellPhone' class="normal" type="text" value="${address.cellPhone }"/><label>手机号码，如：13588888888</label></td>
							</tr>
							<tr>
								<th>设为默认：</th>
								<td><label><input name='state' type='checkbox'
										value=''></label></td>
							</tr>
							<tr>
								<th></th>
								<td><label class="btn"><input id="adb"type="button"
										value="保存" /></label> <label class="btn"><input type="reset"
										value="取消" /></label></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="../help.jsp"></jsp:include>
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</body>
</html>
