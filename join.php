<?php
    $con = mysqli_connect("localhost", "blacksutte12", "a950315626367!", "blacksutte12");
    mysqli_query($con,'SET NAMES utf8');

    $id = $_POST["id"];
    $pw = $_POST["pw"];
    $name = $_POST["name"];   
    $hp = $_POST["hp"];

    $statement = mysqli_prepare($con, "INSERT INTO member VALUES (null,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssss", $id, $pw, $name, $hp);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;             

    
    echo json_encode($response);

?>