<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>闪电霹雳识猫侠</title>
    <script src="http://d3js.org/d3.v3.min.js" language="JavaScript"></script>
   <!--  <script src="frontend/liquidFillGauge.js" language="JavaScript"></script> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <style>
        .liquidFillGaugeText { font-family: Helvetica; font-weight: bold; }
    </style>
<head>
<style>
body {
	margin: 0;
	background: #000;
}

video {
	position: fixed;
	min-width: 100%;
	min-height: 100%;
	width: auto;
	height: auto;
	z-index: -100;
	background-size: cover;
	transition: 1s opacity;
}

#polina {
	font-family: Agenda-Light, Agenda Light, Agenda, Arial Narrow,
		sans-serif;
	font-weight: 100;
	background: rgba(0, 0, 0, 0.3);
	color: white;
	padding: 2rem;
	margin: 2rem;
	font-size: 2rem;
	position: absolute;
	top: 5%;
	left: 0%;
}

h1 {
	font-size: 2.5rem;
	text-transform: uppercase;
	margin-top: 0;
	letter-spacing: .3rem;
}
</style>
</head>
<body>
	<!-- <div id="pro">
		<svg id="fillgauge1" width="97%" height="250" onclick="gauge1.update(NewValue());"></svg>
	</div> -->

	<div id="res">
		${result}
		<c:if test="${result==0}">
			<video id="bgvid" playsinline autoplay muted loop>
				<!-- WCAG general accessibility recommendation is that media such as background video play through only once. Loop turned on for the purposes of illustration; if removed, the end of the video will fade in the same way created by pressing the "Pause" button  -->
				<source src="video/cat.mp4" type="video/mp4">
			</video>
		</c:if>
		<c:if test="${result==1}">
			<video id="bgvid" playsinline autoplay muted loop>
				<!-- WCAG general accessibility recommendation is that media such as background video play through only once. Loop turned on for the purposes of illustration; if removed, the end of the video will fade in the same way created by pressing the "Pause" button  -->
				<source src="video/Coverr-Lulu.mp4" type="video/mp4">
			</video>
			<h2 style="text-align:center">Why am I not a cat?</h2>
		</c:if>
		<br>
		<br>
		<div align="center">
	
			<%-- <h1></h1>
			<ol>
				<c:forEach items="${files}" var="file">
	           		- ${file} <br>
				</c:forEach>
			</ol> --%>
			<!-- <a href="http://localhost:8080/demo/upload.html"><input
				type="button" value="Go Back" /></a> <br /> <br /> <br /> -->
			<!-- <div
				style="font-family: verdana; line-height: 25px; padding: 5px 10px; border-radius: 10px; border: 1px dotted #A4A4A4; width: 50%; font-size: 12px;">
	
				Spring MVC Upload Multiple Files Example by <a
					href='http://crunchify.com'>Crunchify</a>. Click <a
					href='http://crunchify.com/category/java-web-development-tutorial/'>here</a>
				for all Java, Spring MVC, Web Development examples.<br>
			</div> -->
		</div>
	</div>
</body>
<!-- <script language="JavaScript">
$(document).ready(function(){
	$("p").animate({fontSize: '3em'}, "slow");
});
	var gauge1 = loadLiquidFillGauge("fillgauge1", 100);
    var config1 = liquidFillGaugeDefaultSettings();
    config1.circleColor = "#FF7777";
    config1.textColor = "#FF4444";
    config1.waveTextColor = "#FFAAAA";
    config1.waveColor = "#FFDDDD";
    config1.circleThickness = 0.2;
    config1.textVertPosition = 0.2;
    config1.waveAnimateTime = 1000;

    
	var i = -1;
	
    function NewValue(){
    	var te1 = document.getElementById("pro");
        te1.setAttribute("hidden", true);
        document.getElementById("res").removeAttribute("hidden");
    }
    
    
    
</script> -->
</html>