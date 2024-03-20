<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposit Result</title>
      <link rel="stylesheet" href="styles.css" />
    <style>
    body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            background-image: url("https://i.postimg.cc/4Nd0DzNR/Picsart-24-03-14-20-18-16-468.jpg");
        background-size: cover;
        }
         .container {
        text-align: center;
        width: 250px;
        height: 20px;
        margin-left: 500px;
        margin-top: 350px;
        display: block;

        color: skyblue;
      }
        button {
       height:28px; 
         /* Add padding to the button */
        background-color: #4caf50; /* Green background color */
        border: none; /* Remove border */
        color: white; /* Text color */
        text-align: center; /* Align text to center */
        text-decoration: none; /* Remove default underline style */
        display: inline-block; /* Make the button inline block */
        font-size: 16px; /* Set font size */
        cursor: pointer; /* Add cursor pointer on hover */
        margin-top: 95px; /* Add margin at the top */
        margin-left:500px;
      }
    </style> <!-- Add your CSS file here -->
    <div class="container">
        <h2>Deposit Result</h2>
        <p>${message}</p> <!-- Display the message -->
        
    </div>
     <button class="btn btn-logout" onclick="location.href='home.html'">Home</button>
    
</body>
</html>
