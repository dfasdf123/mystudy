<?php
    $con = mysqli_connect("localhost", "blacksutte12", "a950315626367!", "blacksutte12");
    mysqli_query($con,'SET NAMES utf8');


    $query  = "select * from member";
    $response = array();
    $result=mysqli_query($con,$query);


    while($row=mysqli_fetch_array($result))
    {
        array_push( $response,
        array(
            'idx'=>$row[0],
            'id'=>$row[1],
            'pw'=>$row[2],
            'name'=>$row[3],
            'hp'=>$row[4]            
        ));      
    }
    header('Content-Type: application/json; charset=utf8');
    $json = json_encode(array("member"=>$response), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
    echo $json;


?>