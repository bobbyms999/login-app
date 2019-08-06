<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function formaction(a) {
		if (a == 1) {
			document.getElementById("asd").style.display = "block";
			//document.body.style.backgroundColor = "#f1f1f1";

			document.getElementById("login").style.backgroundColor = "#f1f1f1";
			document.getElementById("register").style.backgroundColor = "#4CAF50";

			document.getElementById("asdb").style.display = "none";

		} else if (a == 2) {

			document.getElementById("asdb").style.display = "block";
			document.getElementById("login").style.backgroundColor = "#4CAF50";
			document.getElementById("register").style.backgroundColor = "#f1f1f1";
			document.getElementById("asd").style.display = "none";

		} else {

			document.getElementById("asdb").style.display = "none";

		}
	}
</script>
<style>
a {
	text-decoration: none;
	display: inline-block;
	padding: 8px 16px;
}

.register {
	background-color: #f1f1f1;
	color: black;
	width: 12%;
	font-size: 18px;
	float: right;
}

.login {
	background-color: #4CAF50;
	color: black;
	width: 12%;
	font-size: 18px;
	float: right;
}

div.ex {
	text-align: right width:300px;
	padding: 50px;
	border: 5px solid grey;
	margin: 0px;
	background-color: seashell;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<input type="submit" class="register" value="Register" id="register"
		onclick="formaction(1);">
	<input type="submit" class="login" value="Login" id="login"
		onclick="formaction(2);">

	<h1 align="center" style="color: blue;">Sign Up</h1>
	<div class="ex" align="center"> 
	  	<form id="asdb" action="${pageContext.request.contextPath}/userLoginApp/userlogin" method="post" style="display: block;">
	
			<table style="with: 50%">
				<tr>
					<td>User Name</td>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password"/></td>
				</tr>
			</table>
			<input type="submit" style="text-align: center" value="Submit" />


		</form> 
 
	<form id="asd" action="${pageContext.request.contextPath}/userLoginApp/userregister" method="post" style="display: none;">
		
		<table style="with: 50%">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName" /></td>
				
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Phone No</td>
				<td><input type="text" name="mobileNo" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>Identity No</td>
				<td><input type="text" name="identityNo" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
		</table>
		<input type="submit" style="text-align: center" value="Submit" />
	</form>

	</div>

	

</body>
</html>