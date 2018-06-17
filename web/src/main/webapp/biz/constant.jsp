<%--
  Created by IntelliJ IDEA.
  User: Airlen
  Date: 2018/06/12
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" language="java" %>
<%
    String web_Root = request.getContextPath();
%>
<script type="text/javascript" language="JavaScript">
    (function (window) {
        window.getWeb_Root=function () {
            return '<%=web_Root%>';
        }
    })(window)
</script>
