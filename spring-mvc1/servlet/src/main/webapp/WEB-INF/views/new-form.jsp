<%--
  Created by IntelliJ IDEA.
  User: jaehyeokseong
  Date: 7/4/2024
  Time: 12:35 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <!-- 상대경로 사용 -->
  <form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
  </form>
</body>
</html>
