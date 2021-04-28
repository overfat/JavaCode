<%--
  Created by IntelliJ IDEA.
  User: hanfeixiang
  Date: 3/13/21
  Time: 4:12 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <!--直接使用中文提交会出现乱码问题-->
  <form action="/e/t" method="post">
    <input type="text" name="name">
    <input type="submit">
  </form>
  </body>
</html>
