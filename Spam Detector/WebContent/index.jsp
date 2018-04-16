<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spam Detector</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    	<script type="text/javascript">
    		$(document).ready(function(){
    			$('#search').on('search', function(e){
    				e.preventDefault();
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
        <span id="result"></span>
    </body>
</html>