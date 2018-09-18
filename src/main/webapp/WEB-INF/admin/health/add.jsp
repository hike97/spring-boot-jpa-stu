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
					<form action="<%=request.getContextPath()%>/healthRecord/saveOrupdate" method="post" enctype="application/x-www-form-urlencoded">
						<input type="hidden" name="id" value="${healthRecord.id}">
						<div class="form-group">
							<label>性别</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"  value="1" <c:if test="${healthRecord.gender==1}">checked="true"</c:if>>
								<label class="form-check-label">男</label>
							</div>
							<div class="form-check form-check-inline">
								<input id="female" class="form-check-input" type="radio" name="gender"  value="0" <c:if test="${healthRecord.gender==0}">checked="true"</c:if>>
								<label class="form-check-label">女</label>
							</div>
						</div>
						<div class="form-group" id="fertility">
							<label>生育状况</label>
							<select class="form-control" name="fertility">
								<option value="0" <c:if test="${score.fertility == 0}">selected</c:if>>都不是</option>
								<option value="1" <c:if test="${score.fertility == 1}">selected</c:if>>备孕期</option>
								<option value="2" <c:if test="${score.fertility == 2}">selected</c:if>>怀孕期</option>
								<option value="3" <c:if test="${score.fertility == 3}">selected</c:if>>已怀孕</option>
							</select>
						</div>
						<div class="form-group">
							<label>年龄</label>
							<input type="text" class="form-control" name="age" value="${healthRecord.age}" >
						</div>
						<div class="form-group">
							<label>身高</label>
							<input type="number" name="height" class="form-control" value="${healthRecord.height}">
						</div>
						<div class="form-group">
							<label>体重</label>
							<input type="number" name="weight" class="form-control" value="${healthRecord.weight}">
						</div>
						<br>
						<h5>
							我的饮食习惯
						</h5>
						<br>
						<div class="form-group">
							<label>本周饮食概况</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="thisWeekdiet"  value="0" <c:if test="${healthRecord.thisWeekdiet==0}">checked="true"</c:if>>
								<label class="form-check-label">规律三餐</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="thisWeekdiet"  value="-1" <c:if test="${healthRecord.thisWeekdiet==-1}">checked="true"</c:if>>
								<label class="form-check-label">三餐不固定</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="thisWeekdiet"  value="-2" <c:if test="${healthRecord.thisWeekdiet==-2}">checked="true"</c:if>>
								<label class="form-check-label">不吃早餐</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="thisWeekdiet"  value="-3" <c:if test="${healthRecord.thisWeekdiet==-3}">checked="true"</c:if>>
								<label class="form-check-label">常吃外卖</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="thisWeekdiet"  value="-4" <c:if test="${healthRecord.thisWeekdiet==-4}">checked="true"</c:if>>
								<label class="form-check-label">常吃宵夜</label>
							</div>
						</div>
						<div class="form-group">
							<label>口味喜好</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="taste"  value="0" <c:if test="${healthRecord.taste==0}">checked="true"</c:if>>
								<label class="form-check-label">适中</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="taste"  value="1" <c:if test="${healthRecord.taste==1}">checked="true"</c:if>>
								<label class="form-check-label">清淡</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="taste"  value="2" <c:if test="${healthRecord.taste==2}">checked="true"</c:if>>
								<label class="form-check-label">辛辣</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="taste"  value="3" <c:if test="${healthRecord.taste==3}">checked="true"</c:if>>
								<label class="form-check-label">重口味</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="taste"  value="4" <c:if test="${healthRecord.taste==4}">checked="true"</c:if>>
								<label class="form-check-label">油腻</label>
							</div>
						</div>

						<div>
							<label>食物组成</label><br/>
							<label class="checkbox-inline">
								<input type="checkbox" name="dietComposition" value="10"> 荤素搭配
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="dietComposition" value="20"> 偏爱肉食
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="dietComposition" value="30"> 素食主义
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="dietComposition" value="40"> 减肥餐
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="dietComposition" value="50"> 五谷杂粮
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="dietComposition" value="60"> 水果爱好者
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="dietComposition" value="70"> 喜好甜食
							</label>
						</div>
						<br>
						<h5>
							我的生活习惯
						</h5>
						<br>
						<div class="form-group">
							<label>吸烟</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="smoke"  value="0" <c:if test="${healthRecord.smoke==0}">checked="true"</c:if>>
								<label class="form-check-label">从不</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="smoke"  value="-1" <c:if test="${healthRecord.smoke==-1}">checked="true"</c:if>>
								<label class="form-check-label">偶尔</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="smoke"  value="-2" <c:if test="${healthRecord.smoke==-2}">checked="true"</c:if>>
								<label class="form-check-label">经常</label>
							</div>
						</div>
						<div class="form-group">
							<label>喝酒</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="drink"  value="0" <c:if test="${healthRecord.drink==0}">checked="true"</c:if>>
								<label class="form-check-label">从不</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="drink"  value="-1" <c:if test="${healthRecord.drink==-1}">checked="true"</c:if>>
								<label class="form-check-label">偶尔</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="drink"  value="-2" <c:if test="${healthRecord.drink==-2}">checked="true"</c:if>>
								<label class="form-check-label">经常</label>
							</div>
						</div>
						<div class="form-group">
							<label>熬夜</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="stayUp"  value="0" <c:if test="${healthRecord.stayUp==0}">checked="true"</c:if>>
								<label class="form-check-label">从不</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="stayUp"  value="-1" <c:if test="${healthRecord.stayUp==-1}">checked="true"</c:if>>
								<label class="form-check-label">偶尔</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="stayUp"  value="-2" <c:if test="${healthRecord.stayUp==-2}">checked="true"</c:if>>
								<label class="form-check-label">经常</label>
							</div>
						</div>
						<div class="form-group">
							<label>入睡情况</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="sleepCondition"  value="0" <c:if test="${healthRecord.sleepCondition==0}">checked="true"</c:if>>
								<label class="form-check-label">易入睡</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="sleepCondition"  value="-1" <c:if test="${healthRecord.sleepCondition==-1}">checked="true"</c:if>>
								<label class="form-check-label">困难</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="sleepCondition"  value="-2" <c:if test="${healthRecord.sleepCondition==-2}">checked="true"</c:if>>
								<label class="form-check-label">早醒</label>
							</div>
						</div>
						<div class="form-group">
							<label>睡眠时长</label><br/>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="sleepHours"  value="-2" <c:if test="${healthRecord.sleepHours==-2}">checked="true"</c:if>>
								<label class="form-check-label">6小时以下</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="sleepHours"  value="-1" <c:if test="${healthRecord.sleepHours==-1}">checked="true"</c:if>>
								<label class="form-check-label">6-8小时</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="sleepHours"  value="0" <c:if test="${healthRecord.sleepHours==0}">checked="true"</c:if>>
								<label class="form-check-label">8小时以上</label>
							</div>
						</div>
						<br>
						<h5>
							其他个人习惯
						</h5>
						<%--dtz,jz,jzhan,q2lt,qrdxb,bxhs,fhwc,rcksb,sqssj,xly,bybs,csjwdnsj;--%>
						<div>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="10"> 低头族
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="20"> 久坐
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="30"> 久站
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="40"> 跷二郎腿
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="50"> 强忍大小便
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="60"> 不喜喝水
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="70"> 饭后卧床
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="80"> 如厕看书报
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="90"> 睡前刷手机
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="100"> 喜冷饮
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="110"> 暴饮暴食
							</label>
							<label class="checkbox-inline">
								<input type="checkbox" name="otherCondition"  value="120"> 长时间玩电脑/手机
							</label>
						</div>
						<h5>
							我的运动习惯
						</h5>
						同意泰生活访问你的运动与健身信息 <a href="#">同意></a>
						<c:if test="${healthRecord !=null}">
							<div class="form-group">
								<label>婚姻状况</label>
								<select class="form-control" name="maritalStatus">
									<option value="0" <c:if test="${healthRecord.maritalStatus == 0}">selected</c:if>>已婚</option>
									<option value="1" <c:if test="${healthRecord.maritalStatus == 1}">selected</c:if>>未婚</option>
									<option value="2" <c:if test="${healthRecord.maritalStatus == 2}">selected</c:if>>离异</option>
									<option value="3" <c:if test="${healthRecord.maritalStatus == 3}">selected</c:if>>丧偶</option>
								</select>
							</div>
							<div class="form-group">
								<label>过敏史</label>
								<textarea class="form-control" name="allergicHistory">${healthRecord.allergicHistory} </textarea>
							</div>
							<div class="form-group">
								<label>家族病史</label>
								<textarea class="form-control" name="familyMedicialHistory">${healthRecord.familyMedicialHistory}</textarea>
							</div>
						</c:if>
						<br>
						<button type="submit" class="btn btn-primary">立即查看评估结果</button>
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
			$(function () {
				$('#fertility').hide();
				$("input:radio[name='gender']").change(function () {
					var value = $(this).val();
					if (value==1){
                        $('#fertility').hide();
					}else {
                        $('#fertility').show();
					}
                })

            })
		</script>

	</body>

</html>