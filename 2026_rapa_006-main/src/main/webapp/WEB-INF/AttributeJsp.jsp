<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello World</h1>
    <p>
        보내주신 값에서 5를 곱하면?

        <%= (Integer.parseInt(request.getAttribute("result").toString()) * 5) %>

    </p>
</body>
</html>
