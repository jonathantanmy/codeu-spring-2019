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