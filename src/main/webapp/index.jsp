<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="img/favicon.png" type="image/png">
        <title>Student Information System</title>
       
    </head>
    <body>
        
 <%@ include file="jsp/Header.jsp"%>
        
        <!--================Home Banner Area =================-->
        <section class="home_banner_area">
            <div class="banner_inner d-flex align-items-center">
            	<div class="overlay bg-parallax" data-stellar-ratio="0.9" data-stellar-vertical-offset="0" data-background=""></div>
				<div class="container">
					<div class="banner_content text-center">
						<h3>We Ensure better education <br />for a better world</h3>
						<!--   <p>White text</p>-->
						<a class="main_btn" href="<%=SIMView.LOGIN_CTL%>">Get Started</a>
					</div>
				</div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->
       <%@ include file="jsp/Footer.jsp" %>
    </body>
</html>