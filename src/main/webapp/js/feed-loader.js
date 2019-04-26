 // Fetch messages and add them to the page.
  function fetchMessages(){
    const url = '/feed';
    fetch(url).then((response) => {
      return response.json();
    }).then((messages) => {
      const messageContainer = document.getElementById('message-container');
      if(messages.length == 0){
       messageContainer.innerHTML = '<p>There are no posts yet.</p>';
      }
      else{
       messageContainer.innerHTML = '';
      }
      messages.forEach((message) => {
       const messageDiv = buildMessageDiv(message);
       messageContainer.appendChild(messageDiv);
      });
    });
  }

  function buildMessageDiv(message){

   const usernameDiv = document.createElement('div');
   usernameDiv.classList.add("left-align");
   usernameDiv.appendChild(document.createTextNode(message.user));

   const timeDiv = document.createElement('div');
   timeDiv.classList.add('right-align');
   timeDiv.appendChild(document.createTextNode(new Date(message.timestamp)));

   const headerDiv = document.createElement('div');
   headerDiv.classList.add('message-header');
   headerDiv.appendChild(usernameDiv);
   headerDiv.appendChild(timeDiv);

   const bodyDiv = document.createElement('div');
   bodyDiv.classList.add('message-body');
   bodyDiv.appendChild(document.createTextNode(message.text));

   const messageDiv = document.createElement('div');
   messageDiv.classList.add("message-div");
   messageDiv.appendChild(headerDiv);
   messageDiv.appendChild(bodyDiv);

   return messageDiv;
  }

  function buildUILocation(){
    fetchLocations();
  }

  // Fetch messages and add them to the page.
  function fetchLocations(){
    const url = '/location';
    fetch(url).then((response) => {
      console.log(response);
      return response.json();
    }).then((locations) => {
      const messageContainer = document.getElementById('location-container');
      if(locations.length == 0){
       messageContainer.innerHTML = '<p>There are no locations yet.</p>';
      }
      else{
       messageContainer.innerHTML = '';
      }
      locations.forEach((location) => {
       const messageDiv = buildLocationDiv(location);
       messageContainer.appendChild(messageDiv);
      });
    });
  }

  function buildLocationDiv(location){
   const mainDiv = document.createElement('div');
   mainDiv.classList.add("card");
   mainDiv.classList.add("card-cascade");
   mainDiv.classList.add("wider");
   mainDiv.classList.add("reverse");
   mainDiv.style.width="500px";
   mainDiv.style.maxHeight="800px";
   mainDiv.style.display="inline-block";
   mainDiv.style.marginRight="15px";
   mainDiv.style.marginBottom="20px";

   const imageDiv = document.createElement('div');
   imageDiv.classList.add("view");
   imageDiv.classList.add("view-cascade");
   imageDiv.classList.add("overlay");

   if (location.imageUrl) {
        imageDiv.innerHTML += '<img class= "card-img-top" src="' + location.imageUrl + '" />';
   }  

   const contentDiv = document.createElement('div');
   contentDiv.classList.add("card-body");
   contentDiv.classList.add("card-body-cascade");
   contentDiv.classList.add("text-center");

   const nameDiv = document.createElement('h4');
   nameDiv.classList.add("left-align");
   nameDiv.innerHTML += '<strong>'+ location.name + '</strong>' + '</br>';

   const descDiv = document.createElement('p');
   descDiv.classList.add("right-align");
   descDiv.innerHTML += '<strong>'+ location.description + '</strong>';

   var jquery_script = document.createElement('script');
   jquery_script.setAttribute('src','https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"');
   document.head.appendChild(jquery_script);

   var cloudflare = document.createElement('script');
   cloudflare.setAttribute('src','https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"');
   document.head.appendChild(cloudflare);

   var stackpath = document.createElement('script');
   stackpath.setAttribute('src','https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"');
   document.head.appendChild(stackpath);
   
   contentDiv.appendChild(nameDiv);
   contentDiv.appendChild(descDiv);
   mainDiv.appendChild(imageDiv);
   mainDiv.appendChild(contentDiv);
   mainDiv.appendChild(jquery_script);
   mainDiv.appendChild(cloudflare);
   mainDiv.appendChild(stackpath);
   return mainDiv;
  }
   // <div class="card card-cascade wider reverse" id="message-container">

  //     <!-- Card image -->
  //     <div class="view view-cascade overlay">
  //     <img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Slides/img%20(70).jpg" alt="No image given">
  //     <a href="#!">
  //         <div class="mask rgba-white-slight"></div>
  //     </a>
  //     </div>
  
  //     <!-- Card content -->
  //     <div class="card-body card-body-cascade text-center">
  
  //     <!-- Title -->
  //     <h4 class="left-align"><strong>My adventure</strong></h4>
  //     <!-- Subtitle
  //     <h6 class="font-weight-bold indigo-text py-2">Photography</h6> -->
  //     <!-- Text -->
  //     <p class="right-align"> test</p>


  // Fetch data and populate the UI of the page.
  function buildUI(){
   fetchMessages();
  }
