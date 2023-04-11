<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 7/4/2023
  Time: 下午5:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
  <base href="<%=basePath%>" />
  <meta charset="UTF-8" />
  <title>登录</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
  <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
  <meta name="author" content="Codrops" />
  <link rel="shortcut icon" href="../favicon.ico">
  <link rel="stylesheet" type="text/css" href="css/demo.css" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
  <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div class="container">
  <!-- Codrops top bar -->
  <div class="codrops-top">
    <a href="">
      <strong>&laquo; Previous Demo: </strong>Responsive Content Navigator
    </a>
    <span class="right">
                    <a href=" http://tympanus.net/codrops/2012/03/27/login-and-registration-form-with-html5-and-css3/">
                        <strong>Back to the Codrops Article</strong>
                    </a>
                </span>
    <div class="clr"></div>
  </div><!--/ Codrops top bar -->
  <header>
    <h1>完美祥慧专卖店管理系统 <span>登录页面</span></h1>
  </header>
  <section>
    <div id="container_demo" >
      <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
      <a class="hiddenanchor" id="toregister"></a>
      <a class="hiddenanchor" id="tologin"></a>
      <div id="wrapper">
        <div id="login" class="animate form">
          <form  action="/managerlogin" method="post">
            <h1>登录</h1>
            <p>
              <label for="username" class="uname" data-icon="u" > 姓名 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red">${msg}</span></label>
              <input id="username" name="username" required="required" type="text" />
            </p>
            <p>
              <label for="password" class="youpasswd" data-icon="p"> 密码 </label>
              <input id="password" name="password" required="required" type="password"  />
            </p>
            <p class="keeplogin">
              <input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" />
              保持登录状态
              <input type="checkbox" name="user" id="loginkeeping" value="manager" />
              管理员
              <input type="checkbox" name="user" id="loginkeeping" value="employee" />
              员工

            </p>
            <p class="login button">
              <input type="submit" value="Login"/>
              <!-- 	<input type="button" class="login-btn" id="submit" value="Login"/> -->
              <!-- 	<a class="login-btn" id="submit" style="text-decoration: none;">登录</a>  -->
            </p>
            <p class="change_link">
              没有注册?
              <a href="register.html" class="to_register">注册</a>
            </p>
          </form>
        </div>
      </div>
    </div>
  </section>
</div>
<div class="loading">
  <div class="mask">
    <div class="loading-img">
      <img src="images/loading.gif" width="31" height="31">
    </div>
  </div>
</div>
</body>
</html>
