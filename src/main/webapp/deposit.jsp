<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposit Money</title>
      <link rel="stylesheet" href="styles.css" />
    <style >
            body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            background-image: url("https://i.postimg.cc/4Nd0DzNR/Picsart-24-03-14-20-18-16-468.jpg");
        background-size: cover;
      
        
        }
     form,button{
     margin-left:530px;
     margin-top:300px;
     color:white;
     }
     input{
     width:150px;
     display:block;
     margin-top:15px;
     }
     #in{
     width:70px;
     margin-top:-26px;
     margin-left:160px;
     }
     button {
       height:33px; 
        padding: 10px 20px; /* Add padding to the button */
        background-color: #4caf50; /* Green background color */
        border: none; /* Remove border */
        color: white; /* Text color */
        text-align: center; /* Align text to center */
        text-decoration: none; /* Remove default underline style */
        display: inline-block; /* Make the button inline block */
        font-size: 16px; /* Set font size */
        cursor: pointer; /* Add cursor pointer on hover */
        margin-top: 20px; /* Add margin at the top */
        margin-right:575px;
      }
      </style>
</head>
<body>
    
    <form id="container" action="DepositServlet" method="post">
        <h2>Deposit Money</h2>
        <label for="depositAmount">Enter the amount:</label>
        <input type="text" id="depositAmount" name="depositAmount" required>
        <input id="in" type="submit" value="Deposit">
    </form>
    <button class="btn btn-logout" onclick="location.href='home.html'">Back</button>
</body>
</html>
