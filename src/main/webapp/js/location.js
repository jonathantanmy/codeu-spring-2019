function fetchImageUploadUrlAndShowForm() {
  fetch('/location-image-upload-url')
      .then((response) => {
        return response.text();
      })
      .then((locationImageUploadUrl) => {
        const messageForm = document.getElementById('location-form');
        messageForm.action = locationImageUploadUrl;
        messageForm.classList.remove('hidden');
      });
}