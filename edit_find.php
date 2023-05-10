<?php
    $con = mysqli_connect("localhost", "blacksutte12", "a950315626367!", "blacksutte12");
    mysqli_query($con,'SET NAMES utf8');

    $findId = $_POST["findId"];    

    $query  = "select * from member where id='".$findId."'";
    $response = array();
    $response["success"] = false;  

    $result = $con->query($query);
    while($row = $result->fetch_assoc())
    {
        $response["id"] = $row['id'];        
        $response["name"] = $row['name'];
        $response["hp"] = $row['hp'];
        $response["success"] = true;  

    }

    echo json_encode($response);
?>