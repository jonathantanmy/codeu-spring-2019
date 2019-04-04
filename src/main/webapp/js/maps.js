let map;
function createMap(){
      fetch('/MapsServlet').then(function(response) {
        return response.json();
      }).then((BubbleTeaLocations) => {
        var manhattan = {lat: 40.7831, lng: -73.9712}
        const map = new google.maps.Map(document.getElementById('map'), {
          center: manhattan,
          zoom:10
        });

        BubbleTeaLocations.forEach((BubbleTeaLocation) => {
          const marker = new google.maps.Marker({
            position: {lat: BubbleTeaLocation.lat, lng: BubbleTeaLocation.lng},
            map: map
          });
          var name = BubbleTeaLocation.name
          addInfoWindow(marker, name)
        });
      });
}
function addInfoWindow(map, marker, message) {
  var infoWindow = new google.maps.InfoWindow({
    content: name
  });
  google.maps.event.addListener('click', () => {
    infoWindow.open(map, marker);
  });
  
}