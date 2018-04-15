<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spam Detector</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
	  <body>
        <div class="header">
            <h1>Spam Detector</h1>
            <p style="text-align: center"> A web based application detect spam using BM, KMP, and Regex Algorithm</p>
        </div>
        <div class="container">
            <form>
                <input type="search" onblur="this.placeholder= 'Search tweets..'" tabindex="" onfocus="this.placeholder = ''">
            </form>
        </div>
    </body>
</html>