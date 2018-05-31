<?php

    $connect= mysqli_connect("localhost","root","admin") or die ("could not connect to mysql");
    mysqli_select_db($connect, "mayur");


    $data = file_get_contents ('C:/Users/vaibhav upadhyaya/Desktop/pics/minor 6sem/post_dates/instagram-profilecrawl-master/profiles/i.think.2.json');
    $json = json_decode($data, TRUE);

    echo ('<pre> print the json ');
    print_r($json);
    print_r ($json['posts'][0]['tags'][0]);
    echo ('</pre>');

    echo '<br>output:</br>';






    foreach ($json['posts'] as $key)
    {
        print_r($key['date']);
        echo "<br/>";
        print_r($key['comments']);
        echo "<br/>";
        print_r($key['likes']);
        echo "<br/>";
        //print_r($key['comments']);
        echo "<br/><br/><br/>";
        $query = "INSERT INTO post_info(post_date, post_comments, post_likes) VALUES('{$key['date']}', '{$key['comments']}', '{$key['likes']}')"; 
        if(!mysqli_query($connect,$query)) 
        { 
            die('Error : Query Not Executed. Please Fix the Issue! ' . mysqli_error()); 
        } 
        else
        {
             echo "Data Inserted Successully!!!";
        }
    }