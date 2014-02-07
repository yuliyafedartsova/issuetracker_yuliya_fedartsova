var cycleInterrupted = false;  
$(document).ready(function(){
  $(".panes li").hide();
  $(".panes li").first().addClass("current").show();
  $(".tabs td").first().addClass("activeTab");
  $(".tabs td").eq(0).find(".text").css('background-color','#FF0090');
  var delay = $('.options').attr("speed");
  var slideCycleFunc = setInterval(slideCycle, delay);


  $(".inactiveTab").mouseenter(function(){
     cycleInterrupted = true;
     $(".tabs td").removeClass("activeTab").addClass("inactiveTab");
     $(this).removeClass("inactiveTab").addClass("activeTab");
     clearInterval(slideCycleFunc);
     var index = $(".tabs td").index(this);
     $(".panes li").removeClass("current").hide();
     $(".panes li").eq(index).addClass("current").show();
     $(this).find(".text").css('background-color', '#FF0090');
     $(".bookmarkText").html($(this).attr("info"));
	 $(".reference").attr("href", $(this).attr("link"));
  });

    $(".inactiveTab").mouseleave(function(){
      $(this).find(".text").css('background-color','rgb(112,112,112)');
      cycleInterrupted = false;

      var delay = $(".options").attr("speed");
      slideCycleFunc = setInterval(slideCycle, delay);
  });
});

function slideCycle(){
    var current = $(".current");
    var next = current.next();
    var index = $(".panes li").index(current);
    $(".tabs td").eq(index).find(".text").css('background-color','rgb(112,112,112)');
    if($(".current").next().prop("tagName") == "LI"){
        var next = current.next();
        $(".tabs td").eq(index + 1).find(".text").css('background-color','#FF0090');
    }else{
        next = $(".panes li").first();
        $(".tabs td").eq(0).find(".text").css('background-color','#FF0090');
    }

    var fadeIn = $('.options').attr("fadeIn");
    var fadeOut = $('.options').attr("fadeOut");

    current.fadeOut(fadeOut, function(){
	if(cycleInterrupted != true){
            current.removeClass("current");
      next.addClass("current");
      var indexNext = $(".panes li").index(next);
      $(".tabs td").removeClass("activeTab").addClass("inactiveTab");
      $(".tabs td").eq(indexNext).removeClass("inactiveTab").addClass("activeTab");
      next.fadeIn(fadeIn);
        }
    });
    $(".bookmarkText").html(next.attr("info"));
    $(".reference").attr("href", next.attr("link"));

}