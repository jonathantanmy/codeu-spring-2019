<% boolean isUserLoggedIn = (boolean) request.getAttribute("isUserLoggedIn"); %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>PhotoOp HotSpots</title>
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/navigation-loader.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  </head>
  <body>
    <%-- <body onload="addLoginOrLogoutLinkToNavigation();"> --%>
      <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <header class="masthead mb-auto">
          <div class="inner">
            <h3 class="masthead-brand">PhotoOp HotSpots</h3>
            <nav class="nav nav-masthead justify-content-center">
              <a class="nav-link active" href="#">Home</a>
              <a class="nav-link" href="location-form.html">Location Form</a>
              <a class="nav-link" href="locationfeed.html">Location Feed</a>

              <%
              if (isUserLoggedIn) {
                String username = (String) request.getAttribute("username");
            %>  
              
              <a class="nav-link" href="/user-page.html?user=<%= username %>">Your Page</a>
              
             
              <a class="nav-link" href="/logout">Logout</a>
              
                
            <% } else {   %>
              <li class="nav-item">
                <li><a class="nav-link" href="/login">Login</a></li>
              </li>
            <% } %>
            </nav>
          </div>
        </header>
    <main role="main" class="inner cover">
    <h1 class="cover-heading">PhotoOp HotSpots</h1>
    <div class="row">
      <div class="column">
        <img src="images/dubai.jpeg" alt= "Temple" style="width: 100%">
      </div>
      <div class="column">
        <img src="images/cafe.jpg" alt="Cafe" style="width:100%">
      </div>
      <div class="column">
        <img src="images/cancun.jpeg" alt="Cancun" style="width:100%">
      </div>
    </div>
    
    <p class="lead">Welcome to PhotoOp HotSpots! We built this project hoping to create a community that
      can help travelers around the world find the best photo opportunities. Whether the photo spot you are looking 
      for is an iconic landmark or a hip coffee shop, we can help you find the best locations. Add your favorite spots and discover 
      where other user's favorite spots are. 
    </p>
    <p class="lead">
      <a href="about.html" class="btn btn-outline-warning">About Us</a>
    </p>
  </main>
  </body>
</html>
