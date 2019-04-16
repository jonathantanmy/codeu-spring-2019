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
     const url = '/locations';
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
      const nameDiv = document.createElement('div');
      nameDiv.classList.add("left-align");
      nameDiv.appendChild(document.createTextNode("Name: " + location.name));

      const descDiv = document.createElement('div');
      descDiv.classList.add('right-align');
      descDiv.appendChild(document.createTextNode("Description: " + location.description));

      const headerDiv = document.createElement('div');
      headerDiv.classList.add('message-header');
      headerDiv.appendChild(nameDiv);
      headerDiv.appendChild(descDiv);

      const bodyDiv = document.createElement('div');
      bodyDiv.classList.add('message-body');
      bodyDiv.appendChild(document.createTextNode("Image Url: " +location.imageUrl));

      const messageDiv = document.createElement('div');
      messageDiv.classList.add("message-div");
      messageDiv.appendChild(headerDiv);
      messageDiv.appendChild(bodyDiv);

      return messageDiv;
     }


  // Fetch data and populate the UI of the page.
  function buildUI(){
   fetchMessages();
  }
