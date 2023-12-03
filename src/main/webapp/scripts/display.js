$(document).ready(function() {
    $.ajax({
        url: 'displayUsers',
        method: 'GET',
        dataType: 'html',
        success: function(data) {
            $('#userlist').html(data);
        },
        error: function() {
            console.log('Error fetching list');
        }
    });
});