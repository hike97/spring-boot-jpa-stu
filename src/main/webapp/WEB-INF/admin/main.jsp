<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- saved from url=(0052)http://getbootstrap.com/docs/4.0/examples/dashboard/ -->
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
		<link href="<%=request.getContextPath()%>/static/asserts/css/dashboard.css" rel="stylesheet">
	</head>

	<body>
	    <%@include file="../main/head.jsp" %>
		<div class="container-fluid">
			<div class="row">
				<%@include file="../main/left.jsp" %>

				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<img style="width:100%; height:100%;" src="<%=request.getContextPath()%>/static/asserts/img/welcome.jpg">
				</main>
			</div>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/asserts/js/jquery-3.2.1.slim.min.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/asserts/js/popper.min.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/asserts/js/bootstrap.min.js" ></script>

		<!-- Icons -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/asserts/js/feather.min.js" ></script>
		<script>
			feather.replace()
			$(function () {
				<%--alert('${sessionScope.loginUser.realName}');--%>
            })
		</script>

	</body>

</html>