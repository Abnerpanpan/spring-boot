
$(".comment-box").focus(function(){
	$(".comment-container").animate({height:"200px"})
	$(".comment-box").animate({height:"100px"})
	$(".comment-bottom").fadeIn()
})
$(".comment-box").blur(function(){
	$(".comment-bottom").fadeOut()
	$(".comment-box").animate({height:"44px"})
	$(".comment-container").animate({height:"80px"})
})

$(".fix-1").mouseover(function(){
	$(".fix-1 i").removeClass("uk-icon-heart-o");
	$(".fix-1 i").addClass("uk-icon-heart");
})
$(".fix-1").mouseout(function(){
	$(".fix-1 i").removeClass("uk-icon-heart");
	$(".fix-1 i").addClass("uk-icon-heart-o");
})
$(".fix-2").mouseover(function(){
	$(".fix-2 i").removeClass("uk-icon-bookmark-o");
	$(".fix-2 i").addClass("uk-icon-bookmark");
})
$(".fix-2").mouseout(function(){
	$(".fix-2 i").removeClass("uk-icon-bookmark");
	$(".fix-2 i").addClass("uk-icon-bookmark-o");
})
$(".fix-3").mouseover(function(){
	$(".fix-3 i").removeClass("uk-icon-commenting-o");
	$(".fix-3 i").addClass("uk-icon-commenting");
})
$(".fix-3").mouseout(function(){
	$(".fix-3 i").removeClass("uk-icon-commenting");
	$(".fix-3 i").addClass("uk-icon-commenting-o");
})
$(".fix-3").click(function(){
	$("html,body").animate({scrollTop: $(".comment-container").offset().top},500);
    $("#content").focus();
})
$(window).scroll(function(){
	$(".fix-left").stop().fadeIn();
	setTimeout(function(){$(".fix-left").stop().fadeOut(500)},10000);
})

function showReplay(obj) {
	if($(obj).parent().parent().next().is(":visible")){
		$(obj).parent().parent().next().hide("slow");
		$(".com-reply i").removeClass("uk-icon-angle-up");
		$(".com-reply i").addClass("uk-icon-angle-down");
	}else{
		$(obj).parent().parent().next().show("slow");
		$(".com-reply i").removeClass("uk-icon-angle-down");
		$(".com-reply i").addClass("uk-icon-angle-up");

	}
}

$(function(){
    // 滑过显示阴影框
    $(document).on('mouseenter', '.post li', function(){

        $(this).find('.shade')
            .stop()
            .animate({ opacity : '1'}, 300)
    });
    $(document).on('mouseleave', '.post li', function(){

        $(this).find('.shade')
            .stop()
            .animate({ opacity : '0'}, 300)
    });

});

$(window).scroll(function(){
    if ($(this).scrollTop() > 100) {
        $('.toTop').fadeIn();
    } else {
        $('.toTop').fadeOut();
    }
});

// Click event to scroll to top
$('.toTop').click(function(){
    $('html, body').animate({scrollTop : 0}, 1500, 'easeInOutExpo');
    return false;
});

function secretToggle(obj) {
    var $secretComment= $(obj).parent().next();
    $secretComment.slideToggle();
}
function showSecretReplay(obj) {
    var $secretBox=$(obj).parent().prev();
    if($secretBox.is(":hidden")){
        $secretBox.slideToggle();
    }else{
        $secretBox.slideToggle();
    }
}

$(".secret-comment-main").focus(function(){
	$(".secret-btn").slideToggle();
	$(".secret-comment-main").animate({height:"62px"})
})
$(".secret-comment-main").blur(function(){
    $(".secret-comment-main").animate({height:"40px"})
    $(".secret-btn").slideToggle();
})

function showInputBox(obj) {
    	if($(obj).parent().parent().children("#replayText").length){
		}else {
            var html="<textarea id=\"replayText\" class=\"secret-comment pmargin-small\"></textarea>\n" +
                "\t\t\t\t<input id=\"secret-replay-btn\" onclick=\"replaySb(this)\" type=\"button\" class=\"secret-replay-btn\" value=\"回复\">\n" +
                "\t\t\t\t<div style=\"clear: both\">";
            $(obj).parent().after(html);
        }
}
