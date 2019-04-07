<% boolean isUserLoggedIn = (boolean) request.getAttribute("isUserLoggedIn"); %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>CodeU Starter Project</title>
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/navigation-loader.js"></script>
  </head>
  <body>
    <%-- <body onload="addLoginOrLogoutLinkToNavigation();"> --%>
    <nav>
      <ul id="navigation">
        <li><a href="/">Home</a></li>
        <li><a href="/stats.html">Stats</a></li>
        <li><a href="/map.html">Map</a></li>
        <li><a href="/about.html">About Team 27</a></li>

        <%
      if (isUserLoggedIn) {
        String username = (String) request.getAttribute("username");
    %>
        <li><a href="/user-page.html?user=<%= username %>">Your Page</a></li>
        <li><a href="/logout">Logout</a></li>
    <% } else {   %>
       <li><a href="/login">Login</a></li>
    <% } %>

      </ul>
    </nav>
    <h1>CodeU Starter Project</h1>
    <p>Hello everyone. We are CodeU Team 27.This is the CodeU starter project. Click the links above to login and visit your page.
    You can post messages on your page, and you can visit other user pages if you have
    their URL.</p>
    <p>Try to explore our website.</p>
  </body>
</html>
