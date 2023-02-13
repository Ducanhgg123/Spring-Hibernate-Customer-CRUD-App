<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.ducanh.springdemo.utility.SortUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List customer</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	<!-- construct a sort link for first name -->
	<c:url var="sortLinkFirstName" value="/customer/list">
		<c:param name="sort"
			value="<%=Integer.toString(SortUtils.FIRST_NAME)%>" />
	</c:url>
	<!-- construct a sort link for last name -->
	<c:url var="sortLinkLastName" value="/customer/list">
		<c:param name="sort"
			value="<%=Integer.toString(SortUtils.LAST_NAME)%>" />
	</c:url>

	<!-- construct a sort link for email -->
	<c:url var="sortLinkEmail" value="/customer/list">
		<c:param name="sort" value="<%=Integer.toString(SortUtils.EMAIL)%>" />
	</c:url>
	
	<div id="container">
		<div id="content">
			<!-- Add Customer button -->
			<input type="button" value="Add customer"
				onclick="window.location.href='addCustomer'; return false"
				class="add-button">
			<form:form method="GET" action="searchCustomer">
				<input type="text" name="searchName"
					placeholder="Input customer name">
				<input type="submit" value="Search">
			</form:form>
			<table>
				<tr>
					<td><a href="${sortLinkFirstName }">First name</a></td>
					<td><a href="${sortLinkLastName }">Last name</a></td>
					<td><a href="${sortLinkEmail }">Email</a></td>
					<td>Action</td>
				</tr>
				<c:forEach var="customer" items="${customers }">
					<!-- Construct update link -->
					<c:url var="updateLink" value="/customer/updateCustomer">
						<c:param name="customerId" value="${customer.id }"></c:param>
					</c:url>
					<c:url var="deleteLink" value="/customer/deleteCustomer">
						<c:param name="customerId" value="${customer.id }"></c:param>
					</c:url>
					<tr>
						<td>${customer.firstName }</td>
						<td>${customer.lastName }</td>
						<td>${customer.email }</td>
						<td><a href="${updateLink }">Update </a> | <a
							href="${deleteLink }"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false;">Delete
						</a></td>
					</tr>

				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>