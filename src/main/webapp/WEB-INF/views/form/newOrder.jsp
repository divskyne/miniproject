<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" media="screen" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<!-- source: http://www.textfixer.com/tutorials/css-table-alternating-rows.php -->
<style type="text/css">
	.TFtable{
		width:100%; 
		border-collapse:collapse; 
	}
	.TFtable td{ 
		padding:7px; border:#4e95f4 1px solid;
	}
	/* provide some minimal visual accomodation for IE8 and below */
	.TFtable tr{
		background: #b8d1f3;
	}
	/*  Define the background color for all the ODD background rows  */
	.TFtable tr:nth-child(odd){ 
		background: #b8d1f3;
	}
	/*  Define the background color for all the EVEN background rows  */
	.TFtable tr:nth-child(even){
		background: #dae5f4;
	}
</style>
<head>
    <title>New/Edit Order Item Information</title>
</head>
<body>
<h2>New/Edit Order Item Information</h2>
<section>
id: <c:out value="${id}"/><br>
Date: <c:out value="${date}"/><br>
<a href="/order/orderDetail" class="btn btn-default">Add New Item</a>
<a href="/order/" class="btn btn-default">Show All Orders</a>
<p/>
</section>
<section>

<table class="TFtable">
<tr>
  <td><h3>Id</h3></td>
  <td><h3>Name</h3></td>
  <td><h3>Description</h3></td>
  <td><h3>Price</h3></td>
  <td><h3>Amount</h3></td>
  <td><h3>Cost</h3></td>
  <td><h3></h3></td>
  <td><h3></h3></td>
</tr>
<c:forEach items="${orderList}" var="orderitem">
<tr>
	<td><c:out value="${orderitem.getId()}"/></td>
	<td><c:out value="${orderitem.getName()}"/></td>
	<td><c:out value="${orderitem.getDescription()}"/></td>
	<td><c:out value="${orderitem.getPrice()}"/></td>
	<td><c:out value="${orderitem.getAmount()}"/></td>
	<td><c:out value="${orderitem.getCost()}"/></td>
	<td><a href="/order/orderDetail?orderId=${orderitem.getId()}">Edit</a></td>
	<td><a href="/order/deleteitem?orderId=${orderitem.getId()}">Delete</a></td>
</tr>
</c:forEach>
</table>
</section>
</body>
</html>
