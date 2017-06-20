<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<base href="${base}/" />
<title>后台管理</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="<c:url value='/css/admin.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery-2.0.3.js'/>"></script>
<script type="text/javascript">
	if("${msg}"!=""){
		alert("${msg}");
	}
	
	$(document).ready(function(){
		var name = null;
		$("#name").blur(function(){
			if($("#name").val()!=""){
				$.ajax({
    				type:"POST",
    				url:"<c:url value='/category.check'/>",
					data:"name="+$("#name").val(),
					success:function(result){
						if(result=="ok"){
							$("#nametip").css("color","blue");
							$("#nametip").html("可以添加");
							name = true;
						}else{
							$("#nametip").css("color","red");
							$("#nametip").html(result);
							name = false;
						}
					}
    			});}
    			});
    			
    		$("#cateb").click(function(){
    		if(name!=null){
    			if(name==true){
    				$("#sub").submit();
    			}
    		}else{
    			$("#nametip").css("color","red");
    			$("#nametip").html("不能为空");
    		}
    	});	
	});
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
			<div class="content_box">
				
				<div class="content form_content">
					<form action="<c:url value='/category.add'/>" method="post" id="sub">
						<table class="form_table" cellpadding="0" cellspacing="0">
							<col width="150px" />
							<col />
							<tr>
								<th>分类名称：</th>
								<td><input class="normal" name="name" type="text" id="name"
									value=""><label id="nametip">* 必选项</label></td>
							</tr>
							<tr>
								<td></td>
								<td><button class="submit" type="button" id="cateb">
										<span>确 定</span>
									</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div id="separator"></div>
	</div>
</body>
</html>
