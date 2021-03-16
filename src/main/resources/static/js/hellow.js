$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/customers/byid/1"
    }).then(function(data) {
       $('.customer-id').append(data.id);
       $('.customer-lastName').append(data.lastName);
    });
});