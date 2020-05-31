<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="add.do" method="post">
	 姓名<input type="text" name="name"><br/>
	 年龄<input type="text" name="age"><br/>
	 <input type="submit" value="添加">
	</form>
	
	<form action="show.do" method="post">
	<input type="submit" value="查询所有信息">
	</form>
</body>
</html>