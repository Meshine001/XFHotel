$(document).ready(function(){

    $(".reserve").live('click',function(){
        window.location.href="order.html"
    });
    $(".payment").live('click',function(){
        window.location.href="payment.html";
    });
    $(".evaluate").live('click',function(){
        window.location.href="evaluate.html";
    })
    $(".cancel").live('click',function(){
        window,location.href="checkout.html";
    })
});
