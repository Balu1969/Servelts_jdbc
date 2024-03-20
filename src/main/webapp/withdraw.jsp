<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Money</title>
      <link rel="stylesheet" href="styles.css" />
    <style>
      body {
        background-image: url("https://i.postimg.cc/4Nd0DzNR/Picsart-24-03-14-20-18-16-468.jpg");
        background-size: cover;
      }
       #container {
        text-align: center;
        width: 180px;
        height: 20px;
        margin-left: 575px;
        margin-top: 340px;
        
        color: #6a6468;
      }
      #head,#lb {
        margin-bottom: 5px;
        color: skyblue;
        padding-bottom:20px;
      }
    
     button {
        /* Add padding to the button */
        background-color: #4caf50; /* Green background color */
        border: none; /* Remove border */
        color: white; /* Text color */
        text-align: center; /* Align text to center */
        text-decoration: none; /* Remove default underline style */
        display: inline-block; /* Make the button inline block */
        font-size: 16px; /* Set font size */
        cursor: pointer; /* Add cursor pointer on hover */
        margin-top: 12px; /* Add margin at the top */
        margin-left:50px;
      }
      
      .bck{
      margin-top: 78px;
      margin-left:603px;
      }
      </style>
</head>
<body>
<div id="container">
    <h3 id="head">Withdraw Money</h3>
    <form action="WithdrawServlet" method="post">
        <label id="lb" for="withdrawAmount">Enter the amount:</label>
        <input type="text" id="withdrawAmount" name="withdrawAmount" required>
        <button type="submit" value="Withdraw">Submit</button>
    </form>
    </div>
         <button class="bck" onclick="location.href='home.html'">Back</button>
</body>
</html>
