<%--
  Created by IntelliJ IDEA.
  User: hike97
  Date: 2018/8/9
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">您好！${sessionScope.loginUser.realName}</a>
    <%--<input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">--%>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="<%=request.getContextPath()%>/admin/logout">退出</a>
        </li>
    </ul>
</nav>
