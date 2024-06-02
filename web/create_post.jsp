<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>发布帖子 - 找搭子系统</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<script>
    function validateForm() {
        var title = document.getElementById("title").value;
        if (title == null || title.trim() === "") {
            alert("标题不能为空");
            return false; // 阻止表单提交
        }
        return true; // 允许表单提交
    }
</script>

<body>
<header>
    <nav>
        <ul>
            <li><a href="home.jsp" id="home-link">首页</a></li>
            <li><a href="profile.jsp" id="profile-link">个人中心</a></li>
            <li><a href="messages.jsp" id="message-link">私信</a></li>
            <li><a href="create_post.jsp" id="post-link">发布帖子</a></li>
            <li><a href="login.jsp" id="logout-link">登出</a></li>
        </ul>
    </nav>
</header>

<h1>发布帖子</h1>
<form action="PostServlet" method="post" onsubmit="return validateForm()">
    <div>
        <label for="title">帖子标题：</label>
        <input type="text" id="title" name="title" required>
    </div>
    <div>
        <label for="content">帖子内容：</label>
        <textarea id="content" name="content" rows="4" required></textarea>
    </div>
    <div>
        <label for="img">选择图片：</label>
        <input type="file" id="img" name="img" accept="image*" required>
    </div>
    <div>
        <button type="submit">发布</button>
    </div>
</form>

</body>
</html>


