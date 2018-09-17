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
					<h2>学生账号管理</h2>
					<h2><a class="btn btn-sm btn-success" href="<%=request.getContextPath()%>/stu/info/student" >添加</a></h2>
					<div class="table-responsive">
						<table class="table table-striped table-sm" id="dataTable">
							<thead>
								<tr>
									<th>序号</th>
									<th>姓名</th>
									<th>用户名</th>
									<th>性别</th>
									<th>年龄</th>
									<th>角色</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="s" items="${students}" varStatus="xh">
								<tr>
									<td>${xh.count}</td>
									<td>${s.realName}</td>
									<td>${s.userName}</td>
									<td>
										<c:if test="${s.gender==1}">男</c:if>
										<c:if test="${s.gender==0}">女</c:if>
									</td>
									<td>${s.age}</td>
									<td>${s.role}</td>
									<td>
										<a class="btn btn-sm btn-primary" href="/stu/info/student/${s.id}">编辑</a>
										<button delId="${s.id}" del_uri= "/stu/info/student/${s.id}" class="btn btn-sm btn-danger deleteBtn">删除</button>
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
			$(function () {
                $(".deleteBtn").on('click',function () {
                    var id = $(this).attr('delId');
                    var attr = $(this).attr("del_uri");
                    $.ajax({
                        type : "post",
                        url : '<%=request.getContextPath()%>/score/info/isAdd',
                        data : {'stuId':id},
                        async : false,
                        success : function(data){
							if(data){
                                alert("该学生有成绩录入，无法删除！");
							}else {
                                //删除当前员工的
                                //alert(attr);
                                $("#deleteForm").attr("action",attr).submit();
                                return false;
							}
                        }
                	});
                });
            })
		</script>

	</body>

</html>