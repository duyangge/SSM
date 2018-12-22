<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<style>
	td,input,select{
		font-size:18px;
	}
	span{
	font-size:23px;
	font-weight:bold;
	}
</style>
<script type="text/javascript">
	function queryItems(){
		document.formItems.action="${pageContext.request.contextPath }/items/queryItems.action"
		document.formItems.submit();
	}
	
	function deleteItems(){
		document.formItems.action="${pageContext.request.contextPath }/items/deleteItems.action"
		document.formItems.submit();
	}
	
	function editItemsAll(){
		document.formItems.action="${pageContext.request.contextPath }/items/editItemsAll.action"
		document.formItems.submit();
	}
	function addItems(){
		document.formItems.action="${pageContext.request.contextPath }/items/addItems.action"
		document.formItems.submit();
	}

</script>
</head>
<body> 
<c:if test="${username != null }">
	当前用户：<font>${username }</font>
	<a href="${pageContext.request.contextPath }/loginOut.action">退出</a>
</c:if>

<c:if test="${username == null }">
	<a href="${pageContext.request.contextPath }/loginIn.action">登陆</a>

</c:if>
	<form name="formItems" action="${pageContext.request.contextPath }/items/queryItems.action" method="post">
	
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>查询条件：<input type="text" name="itemsCustom.name" style="width:250px;height:20px;"/></td>
				<td>商品类型：<select name="itemsTypes" title="类型" >
								<c:forEach items="${itemsTypes}" var="itemsType">
									<option value="${itemsType.key}">${itemsType.value}</option>
								</c:forEach>
							</select>
				</td>
				<td><input type="button" value="查询" onclick="queryItems()"/></td>
				<td><input type="button" value="批量删除" onclick="deleteItems()"/></td>
				<td><input type="button" value="批量修改" onclick="editItemsAll()"/></td>
				<td><input type="button" value="添加商品" onclick="addItems()"/></td>
			</tr>
		</table>
		<span>商品列表：</span>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="border-buttom:0">
			<tr>
				<td><span>选择</span></td>
				<td><span>商品名称</span></td>
				<td><span>商品价格</span></td>
				<td><span>生产日期</span></td>
				<td><span>商品描述</span></td>
				<td><span>操作</span></td>
			</tr>
			<c:forEach items="${itemList}" var="items">
			<tr style="height:120px;">
				<td><input type="checkbox" name="items_id" value="${items.id}"/></td>
				<td>
					<font>${items.name}</font>
					<br/>
					<c:if test="${items.pic != null}">
						<img src="/pic/${items.pic}" width="140" height="100"/>
					</c:if>
				</td>
				<td>${items.price}</td>
				<td><fmt:formatDate value="${items.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${items.detail}</td>
				<td><a href="${pageContext.request.contextPath}/items/editItems.action?id=${items.id}">修改</a></td>
			
			</tr>
			</c:forEach>
		</table>
	</form>
</body>

</html>