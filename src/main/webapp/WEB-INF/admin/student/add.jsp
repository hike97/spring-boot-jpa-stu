<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- saved from url=(0052)http://getbootstrap.com/docs/4.0/examples/dashboard/ -->
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	   <%-- <meta http-equiv="Content-Type" content="application/json charset=utf-8">--%>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Dashboard Template for Bootstrap</title>
		<!-- Bootstrap core CSS -->
		<link href="<%=request.getContextPath()%>/static/asserts/css/bootstrap.min.css" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="<%=request.getContextPath()%>/static/asserts/css/dashboard.css" rel="stylesheet">
		<style type="text/css">
			/* Chart.js */
			
			@-webkit-keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			@keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			.chartjs-render-monitor {
				-webkit-animation: chartjs-render-animation 0.001s;
				animation: chartjs-render-animation 0.001s;
			}
		</style>
	</head>

	<body>
	<%--引入头--%>
		<%@include file="../../main/head.jsp"%>

		<div class="container-fluid">
			<div class="row">
				<!--引入侧边栏-->
				<%@include file="../../main/left.jsp" %>
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<form action="<%=request.getContextPath()%>/stu/info/student" method="post" enctype="application/x-www-form-urlencoded">
						<input type="hidden" name="id" value="${student.id}">
						<div class="form-group">
							<label>姓名</label>
							<input type="text" class="form-control" name="realName" value="${student.realName}">
						</div>
						<div class="form-group">
							<label>用户名</label>
							<input type="text" class="form-control" name="userName" value="${student.userName}" >
						</div>
						<div class="form-group">
							<label>性别</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"  value="1" <c:if test="${student.gender==1}">checked="true"</c:if>>
								<label class="form-check-label">男</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"  value="0" <c:if test="${student.gender==0}">checked="true"</c:if>>
								<label class="form-check-label">女</label>
							</div>
						</div>
						<div class="form-group">
							<label>年龄</label>
							<input type="number" name="age" class="form-control" value="${student.age}">
						</div>
						<div class="form-group">
							<label>角色</label>
							<select class="form-control" name="role">
								<option value="student"<c:if test="${student.role eq 'student'}">selected</c:if>>学生</option>
								<option value="admin"<c:if test="${student.role eq 'admin'}">selected</c:if>>管理员</option>
							</select>
						</div>
						<c:if test="${student !=null}">
						<input type="hidden" name="_method" value="put"/>
						<div class="form-group">
							<label>密码</label>
							<input type="text" class="form-control" name="passWord" value="${student.passWord}" >
						</div>
						</c:if>
						<button type="submit" class="btn btn-primary">保存</button>
					</form>
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
		</script>

	</body>

</html>