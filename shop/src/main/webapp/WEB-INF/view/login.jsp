<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="${base}/" />
<meta charset="utf-8" />
<title>用户登录_嗨购商城</title>
<jsp:include page="base.jsp" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/index.css'/>" />
</head>
<script type="text/javascript">
	$(document).ready(function(){
    	var name = null;
    	var password = null;
    
    	$("#name").blur(function(){
    			$.ajax({
    				type:"POST",
    				url:"<c:url value='/checkname'/>",
					data:"name="+$("#name").val(),
					success:function(result){
						if(result!="ok"){
							$("#namemsg").css("color","red");
							$("#namemsg").html(result);
							name = false;
						}else{
							$("#namemsg").css("color","blue");
							$("#namemsg").html(result);
							name = true;
						}
					}
    			});
    	});
    	
    	$("#password").blur(function(){
    		if($("#password").val().length<6||$("#password").val().length>20){
    			$("#pwdmsg").css("color","red");
    			$("#pwdmsg").html("密码长度必须是6-20位");
    			password = false;
    		}else{
    			$("#pwdmsg").css("color","blue");
    			$("#pwdmsg").html("ok");
				password = true;
    		}
    	});
    	
    	$("#lgb").click(function(){
    		if(name!=null&&password!=null){
    			if(name==true&&password==true){
    				$("#subm").submit();
    			}
    		}
    	});
    });
</script>
<body class="second">
	<div class="brand_list container_2">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="wrapper clearfix">
			<div class="wrap_box">
				<h3 class="notice">已注册用户，请登录</h3>
				<p class="tips">欢迎来到我们的网站，如果您已是本站会员请登录</p>
				<span>${msg }</span>
				<div class="box login_box clearfix">
					<form action='<c:url value='/login'/>' method="post" id="subm">
						<input type="hidden" name="opr" value="login" />
						<table width="515" class="form_table f_l">
							<col width="120px" />
							<col />
							<tr>
								<th>用户名：</th>
								<td><input class="gray" type="text" name="name"
									id="name" placeholder="请输入用户名" /><span id="namemsg" style="color:red"></span></td>
							</tr>
							<tr>
								<th>密码：</th>
								<td><input class="gray" type="password" id="password"
									name="password" placeholder="请输入6-20位长度的密码" /><span
									id="pwdmsg" style="color:red"></span></td>
							</tr>
							<tr>
								<td></td>
								<td><input id="lgb" class="submit_login" type="button" value="登录" /></td>
							</tr>
						</table>
					</form>

					<!--正常登录时-->
					<table width="360px" class="form_table prompt_3 f_l">
						<col width="75px" />
						<col />
						<tr>
							<th></th>
							<td>
								<p class="mt_10">
									<strong class="f14">您还不是<span class="orange">嗨购商城</span>用户
									</strong>
								</p>
								<p>
									现在免费注册成为嗨购商城用户，便能立即享受便宜又放心的购物乐趣。<a class="blue" href="">网站首页>></a>
								</p>
								<p class="mt_10">
									<a class="reg_btn" href="<c:url value='/register.jsp'/>">注册新用户</a>
								</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
