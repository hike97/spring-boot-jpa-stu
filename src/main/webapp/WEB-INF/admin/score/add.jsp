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
		<!--引入抽取的topbar-->
		<!--模板名：会使用thymeleaf的前后缀配置规则进行解析-->
		<%@include file="../../main/head.jsp"%>

		<div class="container-fluid">
			<div class="row">
				<!--引入侧边栏-->
				<%@include file="../../main/left.jsp" %>
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<form action="<%=request.getContextPath()%>/score/info/score" method="post">
						<input type="hidden" name="id" value="${score.id}">
						<c:choose>
							<c:when test="${score !=null}">
								<div class="form-group">
									<label>姓名</label>
									<input class="form-control"  value="${score.student.realName}" type="text" readonly>
								</div>
							</c:when>
							<c:otherwise>
								<div class="form-group">
									<label>姓名</label>
									<select class="form-control" name="stuId">
										<c:forEach var="stu" items="${stuList}">
											<option value="${stu.id}"<c:if test="${score.student.id == stu.id}">selected</c:if>>${stu.realName}</option>
										</c:forEach>
									</select>
								</div>
							</c:otherwise>
						</c:choose>

						<div class="form-group">
							<label>分数</label>
							<input type="text" class="form-control" name="score" value="${score.score}">
						</div>
						<div class="form-group">
							<label>年份</label>
							<select class="form-control" name="year">
								<option value="2017" <c:if test="${score.year == 2017}">selected</c:if>>2017</option>
								<option value="2018" <c:if test="${score.year == 2018}">selected</c:if>>2018</option>
								<option value="2019" <c:if test="${score.year == 2019}">selected</c:if>>2019</option>
							</select>
						</div>
						<c:if test="${score !=null}">
							<input type="hidden" name="_method" value="put"/>
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