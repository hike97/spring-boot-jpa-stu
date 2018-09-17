<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>学生成绩管理系统</title>
		<!-- Bootstrap core CSS -->
		<link href="<%=request.getContextPath()%>/static/asserts/css/bootstrap.min.css" rel="stylesheet">
		<!-- Custom styles for this template -->
		<link href="<%=request.getContextPath()%>/static/asserts/css/signin.css" rel="stylesheet">
	</head>

	<body class="text-center">
		<form class="form-signin" action="<%=request.getContextPath() %>/admin/login" method="post">
			<img class="mb-4" src="<%=request.getContextPath()%>/static/asserts/img/bootstrap-solid.svg" alt="" width="72" height="72">
			<h1 class="h3 mb-3 font-weight-normal">学生成绩管理系统</h1>
			<!--判断-->
			<c:if test="${msg !=null}">
				<p style="color: red" >${msg}</p>
			</c:if>
			<label class="sr-only">用户名</label>
			<input type="text" name="username" class="form-control" placeholder="用户名" required="" autofocus="">
			<label class="sr-only">密码</label>
			<input type="password" name="password" class="form-control" placeholder="密码" required="">
			<div class="checkbox mb-3">
				<label>
          <%--<input type="checkbox" value="remember-me"> Remember me--%>
        </label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			<p class="mt-5 mb-3 text-muted">© 2017-2018</p>
			<a class="btn btn-sm">中文</a>
			<a class="btn btn-sm">English</a>
		</form>

	</body>

</html>