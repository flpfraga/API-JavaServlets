<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Página inicial</h1>
	<form method="post" action="<%=request.getContextPath()%>/hello">
		Nome: <input type="text" name="nome" /> <br />
		<br /> Sobrenome: <input type="text" name="sobrenome" /> <br /> <input
			type="submit" name="enviar" />
	</form>
</body>
</html>