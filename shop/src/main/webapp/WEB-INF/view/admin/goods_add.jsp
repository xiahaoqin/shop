<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html >
<html>
<head>
<base href="${base}/" />
<title>后台管理</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="<c:url value='/css/admin.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery-2.0.3.js'/>"></script>
<script type="text/javascript">
       $(function(){
            var ue = UE.getEditor('description',{
                toolbars:[
                    ['fullscreen', 'source', '|', 'undo', 'redo', '|',
                        'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                        'rowspacingtop', 'rowspacingbottom']
                ]
                ,initialFrameWidth:600  //初始化编辑器宽度,默认1000
                ,initialFrameHeight:300  //初始化编辑器高度,默认320

            });

        });
        
        
     $(document).ready(function(){
		var name = null;
		$("#goodsname").blur(function(){alert(1);
			if($("#name").val()!=""){
				$.ajax({
    				type:"POST",
    				url:"<c:url value='/goods.check'/>",
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
    			
    		$("#goodsb").click(function(){
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
			<span>${msg }</span>
				<div class="content form_content">
					<form action="<c:url value='/${goodsSub }'/>" method="post" id="sub" enctype="multipart/form-data">
						<div id="table_box_1">
							<table class="form_table">
								<colgroup>
									<col width="150px">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th>商品名称：</th>
										<td><input class="normal" name="name" type="text" id="goodsname"
											value="${goods.name }"><label id="nametip">*</label></td>
									</tr>
									<tr>
										<th>所属分类：</th>
										<td><select name="categoryName">
										<c:forEach items="${categories}" var="category">
										<option value="${category.name}">${category.name}</option>
										</c:forEach>
												
										</select></td>
									</tr>
									<tr>
										<th>基本数据：</th>
										<td>
											<div class="con">
												<table class="border_table">
													<thead id="goodsBaseHead">
														<tr>
															<th>商品货号</th>
															<th>库存</th>
															<th>市场价格</th>
															<th>销售价格</th>
														</tr>
													</thead>
													<tbody id="goodsBaseBody">
														<tr class="td_c">
															<td><input class="small" name="number"
																type="text" value="${goods.number}" /></td>
															<td><input class="tiny" name="stock"
																type="text" value="${goods.stock}"></td>
															<td><input class="tiny" name="marketPrice"
																type="text" value="${goods.marketPrice}"></td>
															<td><input class="tiny" name="salePrice"
																type="text" value="${goods.salePrice}"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<th>缩略图：</th>
										<td>
										<img alt="${goods.name }" src="${goods.image }" style="width:100px;height:100px">
										<input type="file" name="thumbnail" id="thumbnail"/></td>
									</tr>
									<tr>
										<th>产品描述：</th>
										<td><textarea rows="5" cols="5" name="description" id="description">${goods.description }</textarea></td>
									</tr>
								</tbody>
							</table>
						</div>
						<table class="form_table">
							<colgroup>
								<col width="150px">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td></td>
									<td><button class="submit" type="submit" id="goodsb">
											<span>发布商品</span>
										</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div id="separator"></div>
	</div>
</body>
</html>