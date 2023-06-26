<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>emplist</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
				<div id="header">
					<div id="rightheader">
						<p>
							2009/11/20
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">Main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>

				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Welcome!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								Username
							</td>
							<td>
								Gendar
							</td>
							<td>
								Age
							</td>
							<td>

							</td>
						</tr>
						<c:forEach items="${empList}" var="emp">
							<tr class="row1">
								<td>${emp.getId()}</td>
								<td>${emp.getUserName()}</td>
								<td><c:if test="${emp.getGender() == 'm'}">男</c:if>
									<c:if test="${emp.getGender() == 'f'}">女</c:if></td>
								<td>${emp.getAge()}</td>
								<td><a href="UserDetail?id=${emp.getId()}">详细</a></td>
							</tr>
						</c:forEach>
					</table>
					<p>
						<input type="button" class="button" value="退出系统"
							onclick="location='login.html'" />
					</p>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
