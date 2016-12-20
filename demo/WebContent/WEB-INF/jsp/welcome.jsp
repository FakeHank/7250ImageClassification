<%@ page contentType="text/html; charset=gb2312"%>

<html>
<head>
<title>Spring MVC Tutorial by Crunchify - Hello World Spring MVC
	Example</title>
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
	<video id="bgvid" playsinline autoplay muted loop>
		<!-- WCAG general accessibility recommendation is that media such as background video play through only once. Loop turned on for the purposes of illustration; if removed, the end of the video will fade in the same way created by pressing the "Pause" button  -->
		<source src="video/Looping_Clouds.mp4" type="video/mp4">
	</video>
	
	${message}

	<br>
	<br>
	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;">

		Spring MCV Tutorial by <a href="http://crunchify.com">Crunchify</a>.
		Click <a
			href="http://crunchify.com/category/java-web-development-tutorial/"
			target="_blank">here</a> for all Java and <a
			href='http://crunchify.com/category/spring-mvc/' target='_blank'>here</a>
		for all Spring MVC, Web Development examples.<br>
	</div>
</body>
</html>