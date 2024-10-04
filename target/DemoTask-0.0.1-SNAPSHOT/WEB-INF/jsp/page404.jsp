<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>404 Not Found</title>
  <!-- Include Bootstrap CSS and FontAwesome -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <style>
    /* Body styling */
    body, html {
      height: 100%;
      margin: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #141518ff; /* Jet color */
      color: #fafafa; /* White */
      font-family: 'Arial', sans-serif;
    }

    /* Container styling */
    .container {
      text-align: center;
    }

    /* 404 Text animation */
    .four-o-four {
      font-size: 10rem;
      font-weight: bold;
      letter-spacing: 10px;
      animation: float 2.5s ease-in-out infinite;
    }

    @keyframes float {
      0%, 100% {
        transform: translateY(0);
      }
      50% {
        transform: translateY(-20px);
      }
    }

    /* Page Not Found text */
    .not-found-text {
      font-size: 1.5rem;
      margin-bottom: 2rem;
    }

    /* Bouncing button animation */
    .btn-bounce {
      display: inline-block;
      position: relative;
      transition: transform 0.3s;
    }

    .btn-bounce:hover {
      animation: bounce 0.5s ease-in-out;
    }

    @keyframes bounce {
      0%, 100% {
        transform: translateY(0);
      }
      50% {
        transform: translateY(-10px);
      }
    }

    /* Shadow effect for the 404 text */
    .four-o-four {
      text-shadow: 0 0 30px rgba(255, 255, 255, 0.2);
    }
  </style>
</head>
<body>
<div class="container">
  <div class="four-o-four">404</div>
  <p class="not-found-text">Oops! The page you're looking for doesn't exist.</p>
  <!-- Bouncing Button with Home icon -->
  <a href="/" class="btn btn-primary btn-bounce" style="background-color: #219ebcff;">
    <i class="fas fa-home"></i> Take me Home
  </a>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
