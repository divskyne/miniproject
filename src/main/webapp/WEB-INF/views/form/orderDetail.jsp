<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" media="screen" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<html>

<head>
    <title>Order Editing</title>
</head>
<body>
<h2>New/Edit Order Information</h2>
<section>
<form:form method="POST" commandName="order" action="/order/additem">
   <table>
    <tr>
        <td><form:label path="id">Id</form:label></td>
        <td><form:input path="id" readonly="true"/></td>
    
    <tr>
     <td><form:label path="name">Product</form:label></td>
     <td><form:select path="name">
					  <form:option value="" label="--- Select Product ---" />
					  <form:options items="${productName}" />
				      </form:select></td>
    </tr>
    <tr>
        <td><form:label path="amount">Amount</form:label></td>
        <td><form:input path="amount" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit" class="btn btn-default"/>
        </td>
    </tr>
</table>  
</form:form>
</section>

</body>
</html>
