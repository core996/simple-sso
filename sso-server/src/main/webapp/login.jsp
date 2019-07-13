<%--
  Created by IntelliJ IDEA.
  User: core
  Date: 2019/7/13
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    window.onload=function (ev) {
        var redirect_url = getUrlParam("redirect_url");
        document.getElementById("redirect_url").value = redirect_url ;
    }
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if(r != null){
            return decodeURIComponent(r[2]);
//这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
        }
        return null;//返回参数值
    }
</script>
<body>
${error}
<form action="login.servlet">
    <input name="username"><br>
    <input id="redirect_url" name="redirect_url" >
    <button type="submit"> 登录</button>
</form>
</body>
</html>
