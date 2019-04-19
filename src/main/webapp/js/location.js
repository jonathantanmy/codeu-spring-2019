let map;
let editMarker;
let autocomplete;
function buildUI() {
  fetchLocationImageUploadUrlAndShowForm();
}
function createMap() {

	map = new google.maps.Map(document.getElementById('map'), {
		center: {lat: 38.5949, lng: -94.8923},
		zoom: 4
	});

	map.addListener('click', (event) => {
		createMarkerForEdit(event.latLng.lat(), event.latLng.lng());
	});
}

function createMarkerForEdit(lat, lng){
	document.getElementById("lat").value = lat;
	document.getElementById("lng").value = lng;

	if(editMarker){
		editMarker.setMap(null);
	}

	editMarker = new google.maps.Marker({
		position: {lat: lat, lng: lng},
		map: map
	});
}
// zoom the map in on the city.
function onPlaceChanged() {
	var place = autocomplete.getPlace();
	if (place.geometry) {
		map.panTo(place.geometry.location);
		map.setZoom(15);
		createMarkerForEdit(place.geometry.location.lat(), place.geometry.location.lng());
	} else {
		console.log("Not a proper location");
	}
}
function initAutocomplete() {
  // Create the autocomplete object, restricting the search predictions to
  // geographical location types.
  autocomplete = new google.maps.places.Autocomplete(
      document.getElementById('autocomplete'), {types: ['geocode']});

  // When the user selects an address from the drop-down, populate the
  // address fields in the form.
  autocomplete.addListener('place_changed', onPlaceChanged);
}
function fetchLocationImageUploadUrlAndShowForm() {
  fetch('/location-image-upload-url')
      .then((response) => {
        return response.text();
      })
      .then((locationImageUploadUrl) => {
        const locationForm = document.getElementById('location-form');
        locationForm.action = locationImageUploadUrl;
        locationForm.classList.remove('hidden');
      });
}