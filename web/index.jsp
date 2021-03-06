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
        <title> Dr Fatal | Management Information System</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/simple-sidebar.css" rel="stylesheet">

        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <li class="sidebar-brand">
                        Fatal Medicine
                    </li>
                    <%
                        String sidebar;
                        Models.DatabaseModel.User user = (Models.DatabaseModel.User) request.getSession().getAttribute("user");
                        if (user != null) {
                            sidebar = "/WEB-INF/jspf/navbar.jspf";
                        } else {
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
                    <%
                        String view = (String) request.getAttribute("view");
                        if ((view == null) | (user == null)) {
                            view = "homepage.jsp";
                        }
                        view = "/WEB-INF/jspf/" + view;
                    %>  
                    <jsp:include page="<%=view%>"></jsp:include>

                </div>
            </div>
        </div>

    </body>
</html>
