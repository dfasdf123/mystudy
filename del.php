<?php
    $con = mysqli_connect("localhost", "blacksutte12", "a950315626367!", "blacksutte12");
    mysqli_query($con,'SET NAMES utf8');

    $delId = $_POST["delId"];      

    $query  = "DELETE FROM member WHERE id ='".$delId."'"; 
    

    $result = $con->query($query);    

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>