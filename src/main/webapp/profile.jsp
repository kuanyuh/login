<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/style_home.css" type="text/css" rel="stylesheet">

    <script src="js/profile.js"></script>
    <title>用户信息</title>
</head>
<body>

<div class="container  col-lg-4 col-lg-offset-4 text-center" style="padding-top: 50px; text-align: center;">
    <h2><b>Profile Edit</b></h1><hr><br></h2>
    <form action="unsafe_edit_backend.php" method="get">
        <div class="form-group row">
            <label for="name" class="col-sm-4 col-form-label">NickName</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="name" name="name" placeholder="${user.getName()}">
            </div>
        </div>
        <div class="form-group row">
            <label for="Email" class="col-sm-4 col-form-label">Email</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="email" name="email" placeholder="${user.getEmail()}">
            </div>
        </div>
        <div class="form-group row">
            <label for="Age" class="col-sm-4 col-form-label">Age</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="age" name="age" placeholder="${user.getAge()}">
            </div>
        </div>
        <div class="form-group row">
            <label for="PhoneNumber" class="col-sm-4 col-form-label">Phone Number</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="${user.getPhoneNumber()}">
            </div>
        </div>
        <div class="form-group row">
            <label for="Password" class="col-sm-4 col-form-label">Password</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="password" name="password" placeholder="">
            </div>
        </div>
        <br>
        <div class="form-group row">
            <div class="col-sm-12">
                <button type="button" class="btn btn-success btn-lg btn-block" onclick="update()">Save</button>
            </div>
        </div>

    </form>
    <br>
</div>
</body>
</html>
