function displayImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            var imageElement = document.getElementById('selectedImage');
            imageElement.src = e.target.result;
            imageElement.style.display = 'block';  // 画像を表示
        }

        reader.readAsDataURL(input.files[0]);
    }
}