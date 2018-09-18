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
					<h1>健康档案结果</h1>
					<h1>${healthRecordEntity.thisScore}</h1>
					<p>上期评估</p>
					<h1>${healthRecordEntity.historyScoce}</h1>
					<p>上期评估时间</p>
					<p></p>
					<a href="<%=request.getContextPath()%>/healthRecord/toAdd?id=${healthRecordEntity.id}">评估本周健康状况</a>
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
			$(function () {
				//alert("${healthRecordEntity.id}");
            })
		</script>

	</body>

</html>