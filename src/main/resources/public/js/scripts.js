$(function() {
    $('#messages li').click(function() {
        $(this).fadeOut();
    });
    setTimeout(function() {
        $('#messages li.info').fadeOut();
    }, 3000);
      $("#username-box").focus();

    $("#password2").keyup(validate);

    $('html, body').animate({
            scrollTop: $('.reg-form').offset().top
        }, 'slow');

   });


function validate() {
  var password1 = $("#password1").val();
  var password2 = $("#password2").val();



    if(password1 == password2) {
       $("#validate-status").text("");
    }
    else {
        $("#validate-status").text("Passwords didn't match!");
    }

}