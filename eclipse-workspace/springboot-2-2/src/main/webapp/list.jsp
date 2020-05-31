<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="60%" align="center" border="1">
		<Tr>
			<Th>编号</Th><Th>姓名</Th><Th>年龄</Th><Th>操作</Th>
		</Tr>
		<c:forEach items="${requestScope.list }" var="k" >
			<Tr align="center">
				<Td>${k.id }</Td>
				<Td>${k.name }</Td>
				<Td>${k.age }</Td>
				<Td>
					<a href="">操作</a>
				</Td>
			</Tr>
		</c:forEach>
	</table>
</body>
</html>