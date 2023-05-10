<?php
    $con = mysqli_connect("localhost", "blacksutte12", "a950315626367!", "blacksutte12");
    mysqli_query($con,'SET NAMES utf8');

    $eidtId = $_POST["eidtId"];    
    $eidtName = $_POST["eidtName"];    
    $eidtHp = $_POST["eidtHp"];    

    $query  = "update member Set name ='".$eidtName."', hp ='".$eidtHp."' 
    where id ='".$eidtId."'";


    $result = $con->query($query);    

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>