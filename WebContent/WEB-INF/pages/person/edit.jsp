<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Person-Edit</title>
		<%@ include file="../taglibs.jsp" %>
        <script type="text/javascript">
	       
        </script>	        
	</head>
	<body>
		<form:form action="save.do" method="post" modelAttribute="person">
			<table class="formtable" >
				<tr>
					<td>名称：</td>
					<td>
						<form:input path="name"/>
					</td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td>
						<form:input path="age" />
					</td>
				</tr>
				<tr>
					<td>地址：</td>
					<td>
						<form:input path="address" />
					</td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td>
						<form:input path="email" />
					</td>
				</tr>
			</table>
			<form:hidden path="id"/>
			<c:forEach var="selection" items="${selections}">
				<input type="hidden" id="selections" name="selections" value="${selection}"/>
			</c:forEach>
		</form:form>
	</body>
</html>