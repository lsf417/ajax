<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.2.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.2.6/themes/icon.css">

<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/script.js"></script>
</head>

<body style="margin: 0px">
<div id="searchDiv" class="easyui-panel" title="查询" iconCls="icon-search" collapsed="true" collapsible="true">
  			姓名:<input type="text" name="name"/>
  			班级:<input type="text" name="clazz"/>
  			性别:<select name="gender">
  					<option value="-1">全部</option>
  					<option value="男">男</option>
  					<option value="女">女</option>
  				</select>
  			考核结果:<select name="result">
  					<option value="-1">全部</option>
  					<option value="1">优秀</option>
  					<option value="2">良好</option>
  					<option value="3">合格</option>
  					<option value="4">不合格</option>
  				</select>	
  			<a class="easyui-linkbutton" id="searchBtn"  iconCls="icon-search">查询</a>	
  		</div>
	<div id="mydiv" class="easyui-dialog" modal="true" closed="true" title="添加"
		iconCls="icon-add" closed="true" style="width:400px;height: 320px">
		<form id="frm">
			<table style="padding-left: 50px;padding-top: 50px;">
				<tr>
					<td>姓名</td>
					<td>
					<input type="hidden" name="id"/>
					<input type="text" name="name" class="easyui-validatebox"
						required="true" missingMessage="姓名不允许为空" /></td>
				</tr>
				<tr>
					<td>性别</td>
					<td><input type="radio" name="gender" value="男" checked />男 <input
						type="radio" name="gender" value="女" />女</td>
				</tr>
				<tr>
					<td>班级</td>
					<td><input type="text" name="clazz" class="easyui-validatebox"
						required="true" missingMessage="班级不允许为空" /></td>
				</tr>
				<tr>
					<td>分数</td>
					<td><input type="text" name="score" class="easyui-number"
						required="true" min="0" max="100" missingMessage="成绩不允许为空" /></td>
				</tr>
				<tr>
					<td>出生日期</td>
					<td><input type="text" class="easyui-datebox" name="bir"
						value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" /></td>
				</tr>
				<tr>
  					<td>&nbsp;</td>
  					<td>&nbsp;</td>
  				</tr>
  				<tr>
  					<td colspan="2" align="right">
  						<a id="saveBtn" iconCls="icon-save" class="easyui-linkbutton">保存</a>
  					</td>
  				</tr>
			</table>
		</form>
	</div>
	<table id="mytab"></table>
</body>
</html>
