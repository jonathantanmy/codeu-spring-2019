<% boolean isUserLoggedIn = (boolean) request.getAttribute("isUserLoggedIn"); %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>CodeU Starter Project</title>
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/navigation-loader.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  </head>
  <body>
    <%-- <body onload="addLoginOrLogoutLinkToNavigation();"> --%>
      <div id=nav>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
              <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="about.html">About</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="map.html">Map</a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="stats.html">Stats</a>
                </li>
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
          </div>
        </nav>
      </div>
  
    <h1>CodeU Starter Project</h1>
    <p>Hello everyone. We are CodeU Team 27.This is the CodeU starter project. Click the links above to login and visit your page.
    You can post messages on your page, and you can visit other user pages if you have
    their URL.</p>
    <p>Try to explore our website.</p>
  </body>
</html>
