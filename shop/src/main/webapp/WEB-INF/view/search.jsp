<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		$('.allsort').hover(function() {
			$('#div_allsort').show();
			
			$.getJSON("<c:url value='/category.findAll2'/>",function(result){
				var catelist = eval("("+result+")");
				alert(catelist);
			});
	
	
		}, function() {
			$('#div_allsort').hide();
		});
	});
	
	
</script>
<div class="searchbar">
	<div class="allsort">
		<a href="javascript:void(0);">全部商品分类</a>

		<!--总的商品分类-开始-->
	 	<ul class="sortlist" id='div_allsort' style='display:none'>
		 <c:forEach items="${categories2 }" var="cg">
		  	<li>
				<h2>
					<a href="<c:url value='/goods.findByCid/${cg.id }/${cg.name }'/>">${cg.name }</a>
				</h2>
			</li>
		  </c:forEach> 
		</ul> 
	</div>

	<div class="searchbox">
		<form method='get' action=''>
			<input type='hidden' name='controller' value='site' /> <input
				type='hidden' name='action' value='search_list' /> <input
				class="text" type="text" name='word' autocomplete="off"
				value="输入关键字..." /> <input class="btn" type="submit" value="商品搜索"
				onclick="checkInput('word','输入关键字...');" />
		</form>

	</div>
	<div class="hotwords">热门搜索：</div>
</div>