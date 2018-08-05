<%@ page import="com.zenghm.config.center.ConfigManager" %><%--
  Created by IntelliJ IDEA.
  User: Airlen
  Date: 2018/08/05
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%
    String className = ConfigManager.class.getName();
    //ConfigManager.main(null);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>配置管理中心</title>
  </head>
  <body>
  <h3>配置管理中心</h3>
   <p>欢迎使用配置管理中心！</p>
   <p><%=className%></p>
  </body>
</html>
