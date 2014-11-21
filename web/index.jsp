<%-- 
    Document   : main
    Created on : 21-Nov-2014, 14:13:53
    Author     : aj23-brown
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Simple Sidebar - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Start Bootstrap
                    </a>
                </li>
                <% 
                    String sidebar;
                    DatabaseModel.User user = (DatabaseModel.User)request.getSession().getAttribute("user");
                    if(user != null){
                        sidebar = "/WEB-INF/jspf/navbar.jspf";
                    } else {
                        //navbar = "jspf/login.jspf";
                        sidebar = "/WEB-INF/jspf/loginbar.jspf";
                    }
                    
                        %> 
                        <jsp:include page="<%=sidebar%>"></jsp:include>
                        
                       
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
            <jsp:include page="/WEB-INF/jspf/error.jspf"></jsp:include>

            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>


</body>

</html>
