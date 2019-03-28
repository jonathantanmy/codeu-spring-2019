let map;
function createMap(){
      fetch('/MapsServlet').then(function(response) {
        return response.json();
      }).then((BubbleTeaLocations) => {

        const map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 40.7831, lng: -73.9712},
          zoom:7
        });

        BubbleTeaLocations.forEach((BubbleTeaLocation) => {
          new google.maps.Marker({
            position: {lat: BubbleTeaLocation.lat, lng: BubbleTeaLocation.lng},
            map: map
          });
        });
      });

      map.addListener('click', (event) => {
        new google.maps.Marker({
          position: event.latLng,
          map: map
        });
      });
    }