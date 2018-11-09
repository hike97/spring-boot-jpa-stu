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
					<h1>健康档案</h1>
					<c:choose>
						<c:when test="${healthRecordEntity == null}">
							<p>暂无健康评估结果</p>
							<a href="<%=request.getContextPath()%>/healthRecord/toAdd">开启我的健康档案</a>
							<div>
								待评估
							</div>
						</c:when>
						<c:otherwise>
								<c:if test="${healthRecordEntity.hiddenScore == false}">
									<h1>${healthRecordEntity.thisScore}分</h1>
								</c:if>

								<c:if test="${hi_entity.thisScore != null}">
									<p>上期评估</p>
									<h1>${hi_entity.thisScore}分</h1>
									<p>上期评估时间</p>
									<c:choose>
										<c:when test="${hi_entity.lastUpdateTime == null}">
											<p><fmt:formatDate value="${hi_entity.createTime}" pattern="yyyy-MM-dd" /></p>
										</c:when>
										<c:otherwise>
											<p><fmt:formatDate value="${hi_entity.lastUpdateTime}" pattern="yyyy-MM-dd" /></p>
										</c:otherwise>
									</c:choose>

								</c:if>

							<div>
								<c:if test="${healthRecordEntity.thisScore<=50}">
									<h1>一般</h1>
								</c:if>
								<c:if test="${healthRecordEntity.thisScore>50 and healthRecordEntity.thisScore<=80}">
									<h1>较好</h1>
								</c:if>
								<c:if test="${healthRecordEntity.thisScore>80}">
									<h1>优秀</h1>
								</c:if>
							</div>
							<a href="<%=request.getContextPath()%>/healthRecord/toAdd?id=${healthRecordEntity.id}">评估本周健康状况</a>
						</c:otherwise>
					</c:choose>

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