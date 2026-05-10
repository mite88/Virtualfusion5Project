<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <%
    int number = Integer.parseInt(request.getAttribute("result").toString());

    for ( int i = 1; i < 10; i++ ) {
  %>
    <%=number%> x <%=i%> = <%=number * i%> </p>
  <%
    }
  %>

</body>
</html>
