<%--
  Created by IntelliJ IDEA.
  User: Airlen
  Date: 2018/06/16
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<style type="text/css">
    #zhm_tbar,#zhm_tnav{
        min-width: 1200px;
    }
    #zhm_tbar{
        height: 30px;
        background-color: #333;
    }
    .zhm_tbar_r{
        float: right;
    }
    .zhm_tbar_r li{
        float: left;
        color: white;
        position: relative;
        margin: 5px;
        line-height: 20px;
    }
    .person_group,.logout{
        border-left: 1px solid #5d5d5d;
    }
</style>
<div id="zhm_header" >
    <div id="zhm_tbar">
        <ul class="zhm_tbar_r">
            <li><a class="msg_group">消息</a></li>
            <li><a class="person_group">个人中心</a></li>
            <li><a class="logout" onclick="header.logout()">退出</a></li>
        </ul>
    </div>
    <div id="zhm_tnav">

    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/header.js"></script>

