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
    		$(document).ready(function(){
    			$('#search').on('search', function(e){
    				e.preventDefault();
    				$('#loading-image').show();
    				var q = $('#search').val();
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
 			        		$('#result').html(content);    
 			        		$('#loading-image').hide();
	   			        }           
   			        });
    			});
    		});
    	</script>
    </head>
	<body>
        <div class="header">
            <h1>Spam Detector</h1>
            <p style="text-align: center"> A web based application detect spam using BM, KMP, and Regex Algorithm</p>
        </div>
        <div class="container">
            <form onSubmit="return false;">
                <input type="search" onblur="this.placeholder= 'Search tweets..'" tabindex="" onfocus="this.placeholder = ''" name="q" id="search">
            </form>
        </div>
        <div class="row" style="text-align: center">
             <img id="loading-image" src="https://loading.io/spinners/ellipsis/lg.discuss-ellipsis-preloader.gif" align="middle" hidden>
        </div>
        
        <!--Buat query twitter-->
        <div class="row" style="text-align: center; margin: 16px">
            <p>
                Test
            </p>
        </div>
    </body>
</html>