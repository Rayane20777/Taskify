<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  	<link rel="stylesheet" href="./css/style.css"/>
  <link rel="icon" type="image/svg+xml" href="./img/icon.png" />
    <title>Taskify</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
	<main id="landing">
		<header>
			<div class="h-right">
				<div class="brand">
					<div class="b-circle"></div>
					<h2>Taskify</h2>
				</div>
				
				<nav>
					<a href="#">How It Works</a>
					<a href="#">Features</a>
					<a href="#">FAQ</a>
					<a href="#">Pricing</a>
					<form action="project" method="GET">
						<input type="hidden" name="action" value="display">
						<button type="submit">Display Projects</button>
					</form>
				</nav>
			</div>
			
			<div class="contact">
				<h4>Contact Us</h4>
				<i class="bi bi-arrow-up-right-circle-fill"></i>
			</div>
			
		</header>
		
		
		<article>
			<div class="art-right">
				<div class="box-01">
					<h1>
						Achieve Your <span>Goals:</span><br/>
						Effective Task Management <br>
						for Better Productivity!
					</h1>
					<div class="login">
						<form method="POST">
							<input type="email" name="email" id="email" placeholder="Enter Email"/> 
							<button type="submit"><i class="bi bi-arrow-right"></i></button>
						</form>
						<span class="l-info">
						lorem ipsum dolores lorem ipsum dolores
						</span>
					</div>
				</div>
			</div>
		
			<div class="art-left">
				<div class="box-02">
					<img src="./img/box-03.png" alt="girl holding a pen and a notebook"/>
					<div class="mini-box-02"></div>
				</div>
			</div>
		</article>
	</main>
	<!--
<script>
   setInterval(function() {
       location.reload();
   }, 1000);
</script>
-->
</body>
</html>