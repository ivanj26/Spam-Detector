<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
    <meta name="viewport"content="width=device-height, initial-scale=1">
   		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spam Detector</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    	<script type="text/javascript">
    		$(document).ready(function (){
    			$(document).on('search', '#searchtweets', function(e){
    				e.preventDefault();
    				$('#loading-image').show();
    				$('#tweets').hide();
	        		$('#keywordform').hide();
    				var q = $('#searchtweets').val();
    				$('div.header').hide(1000);
    				$('.container').animate({
    					top: '0vh'
    				});
   				 	$.ajax
   			        ({
   			            type: "GET",           
   			            data: {search: q},
   			            url: 'SearchTweets',
	   			        success:function(content)
	   			        {
 			        		$('#tweets').html(content);    
 			        		$('#loading-image').hide();
 			        		$('#tweets').show();
 			        		$('#keywordform').show();
	   			        }           
   			        });
    			});
    			$(document).on('search', '#keywordspam', function(e){
    				e.preventDefault();
    				var result;
    				var keyword = $('#keywordspam').val();
    				if (document.getElementById('kmp').checked){
    					result = 1;
    				} else if (document.getElementById('bm').checked){
    					result = 2;
    				} else {
    					result = 3;
    				}
    				$.ajax({
    					type: "GET",
    					data: {algo: result, keyword: keyword},
    					url: 'DoAlgorithm',
    					success:function(content){
    						$('#tweets').html(content);
    					}
    				});
    			});
    		});
    	</script>
    </head>
	<body>
        <div class="header">
            <h1>Spam Detector</h1>
            <pre style="font-family: Ralewayregular;"><p style="text-align: center"> A web based application detect spam using BM, KMP, and Regex Algorithm</p><p style="font-size: 7;">Author: Ivan J (059), Jose H (027), Alghi (038)</p></pre>
        </div>
       <div class="container">
            <form onSubmit="return false;">
                <input type="search" onblur="this.placeholder= 'Search tweets..'" tabindex="" onfocus="this.placeholder = ''" name="q" id="searchtweets">
            </form>
        </div>
        <div class="row" style="text-align: center">
             <img id="loading-image" src="https://loading.io/spinners/ellipsis/lg.discuss-ellipsis-preloader.gif" align="middle" hidden style="margin-top: 10%;">
        </div>
        <div class="container" id="keywordform" hidden style="margin-top: 10%;">
          <form onSubmit="return false;">
                <input type="search" onblur="this.placeholder= 'Type your keyword..'" tabindex="" onfocus="this.placeholder = ''" name="q" id="keywordspam">
                <div class="radio-toolbar" id="algorithms" style="margin: 8px;">
                    <input type="radio" name="algorithm" id="kmp" value="KMP" checked><label>KMP Algorithm</label>
                    <input type="radio" name="algorithm" id="bm" value="BM"><label>BM Algorithm</label>
                    <input type="radio" name="algorithm" id="regex" value="Regex"><label>Regex Algorithm</label>
                </div>
          </form>
        </div>
        
        <!-- Buat template setiap tweet post -->
        <div class="row" style="position: relative; text-align: center; margin-top: 50px; max-width: 50%; margin-left: auto; margin-right: auto;" id="tweets" hidden>
            <!-- <div class="tweetpost">
                <p id="username" style="font-size: 14; font-family: Ralewayregular; text-align: left">@Alghifari says: </p>
                <hr>
                <pre id = "post" style="white-space: inherit;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</pre>
            </div>
              <div class="tweetpost">
                <p id="username" style="font-size: 14; font-family: Ralewayregular; text-align: left">@Alghifari says: </p>
                <hr>
                <pre id = "post" style="white-space: inherit;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</pre>
            </div>
            -->
        </div>
    </body>
</html>