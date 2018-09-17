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

		<title>学生信息列表</title>
		<%@include file="../../main/ref.jsp" %>
		<!-- Custom styles for this template -->
		<link href="<%=request.getContextPath()%>/static/asserts/css/dashboard.css" rel="stylesheet">
	</head>

	<body>
		<%@include file="../../main/head.jsp"%>
		<div class="container-fluid">
			<div class="row">
				<%@include file="../../main/left.jsp" %>
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<h2>成绩管理</h2>
					<h2><a class="btn btn-sm btn-success" href="<%=request.getContextPath()%>/score/info/score" >添加</a></h2>
					<div class="table-responsive">
						<table class="table table-striped table-sm" id="dataTable">
							<thead>
								<tr>
									<th>序号</th>
									<th>年份</th>
									<th>姓名</th>
									<th>性别</th>
									<th>年龄</th>
									<th>分数</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="s" items="${scores}" varStatus="xh">
								<tr>
									<td>${xh.count}</td>
									<td>${s.year}</td>
									<td>${s.student.realName}</td>
									<td>
										<c:if test="${s.student.gender==1}">男</c:if>
										<c:if test="${s.student.gender==0}">女</c:if>
									</td>
									<td>${s.student.age}</td>
									<td>${s.score}</td>
									<td>
										<a class="btn btn-sm btn-primary" href="/score/info/score/${s.id}">编辑</a>
										<button del_uri= "/score/info/score/${s.id}" class="btn btn-sm btn-danger deleteBtn">删除</button>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</main>
				<form id="deleteForm"  method="post">
					<input type="hidden" name="_method" value="delete"/>
				</form>
			</div>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/asserts/js/popper.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/asserts/js/bootstrap.min.js"></script>
		<script>
            $(".deleteBtn").click(function(){
                //删除当前员工的
                //alert($(this).attr("del_uri"));
                $("#deleteForm").attr("action",$(this).attr("del_uri")).submit();
                return false;
            });
		</script>

	</body>

</html>