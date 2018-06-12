<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%-- 方式一 --%>
    <%--<%@include file="/biz/constant.jsp"%>--%>
    <%--<%@include file="/biz/include-css.jsp"%>--%>
    <%-- 方式二 页面需要设置参数 isELIgnored="false" 否则表达式不生效 --%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/uikit-2.25.0/css/uikit.min.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/uikit-2.25.0/css/uikit.almost-flat.min.css" />--%>
    <%-- 方式三 --%>
    <jsp:include page="biz/include-css.jsp"/>
    <%-- 方式四 错误--%>
    <%--<jsp:forward page="biz/include-css.jsp"/>--%>
</head>
<h2>Hello World!</h2>
<body class="uk-height-1-1">
<div class="uk-vertical-align uk-text-center uk-height-1-1">
    <div class="uk-vertical-align-middle" style="width: 250px;">
        <img class="uk-margin-bottom" width="140" height="120"
             src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4NCjwhLS0gR2VuZXJhdG9yOiBBZG9iZSBJbGx1c3RyYXRvciAxNi4wLjQsIFNWRyBFeHBvcnQgUGx1Zy1JbiAuIFNWRyBWZXJzaW9uOiA2LjAwIEJ1aWxkIDApICAtLT4NCjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+DQo8c3ZnIHZlcnNpb249IjEuMSIgaWQ9IkViZW5lXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB3aWR0aD0iMTQwcHgiIGhlaWdodD0iMTIwcHgiIHZpZXdCb3g9Ii0yOS41IDI3NS41IDE0MCAxMjAiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgLTI5LjUgMjc1LjUgMTQwIDEyMCIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+DQo8ZyBvcGFjaXR5PSIwLjciPg0KCTxwYXRoIGZpbGw9IiNEOEQ4RDgiIGQ9Ik0tNi4zMzMsMjk4LjY1NHY3My42OTFoOTMuNjY3di03My42OTFILTYuMzMzeiBNNzkuNzg4LDM2NC4zNTVIMS42NTZ2LTU3LjcwOWg3OC4xMzJWMzY0LjM1NXoiLz4NCgk8cG9seWdvbiBmaWxsPSIjRDhEOEQ4IiBwb2ludHM9IjUuODYsMzU4LjE0MSAyMS45NjIsMzQxLjIxNiAyNy45OTUsMzQzLjgyNyA0Ny4wMzIsMzIzLjU2MSA1NC41MjQsMzMyLjUyMyA1Ny45MDUsMzMwLjQ4IA0KCQk3Ni4yMDMsMzU4LjE0MSAJIi8+DQoJPGNpcmNsZSBmaWxsPSIjRDhEOEQ4IiBjeD0iMjQuNDYyIiBjeT0iMzIxLjMyMSIgcj0iNy4wMzQiLz4NCjwvZz4NCjwvc3ZnPg0K"
             alt="">
        <form class="uk-panel uk-panel-box uk-form">
            <div class="uk-form-row">
                <input class="uk-width-1-1 uk-form-large" type="text" placeholder="Username">
            </div>
            <div class="uk-form-row">
                <input class="uk-width-1-1 uk-form-large" type="text" placeholder="Password">
            </div>
            <div class="uk-form-row">
                <a class="uk-width-1-1 uk-button uk-button-primary uk-button-large" onclick="index.login()">Login</a>
            </div>
            <div class="uk-form-row uk-text-small">
                <label class="uk-float-left"><input type="checkbox"> Remember Me</label>
                <a class="uk-float-right uk-link uk-link-muted" href="#">Forgot Password?</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<%--<%@include file="/biz/include-js.jsp"%>--%>
<jsp:include page="/biz/constant.jsp"/>
<jsp:include page="biz/include-js.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<%--<jsp:forward page="biz/include-js.jsp"/>--%>
