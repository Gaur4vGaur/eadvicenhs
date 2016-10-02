<?php
    require "send.php";

    function fail() {
        echo "Error: Expecting name of document in ?doc=<name>"; 
    }

    if($_SERVER['REQUEST_METHOD'] == 'GET') {
        header("Cache-Control: no-cache, must-revalidate");
        header('Content-Type: text/plain');

        $link = "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";
        $parsedLink = parse_url($link);

        // Log what we have received from the GET request
        $myfile = fopen('getlog.txt','a') or die('Unable to open file!');
        fWrite(
            $myfile,
            "----------------------\n".
            date('Y-m-d H:i:s').": ".$link."\n"
        );
        fclose($myfile);

        // Validate if url has a query section or not
        if(array_key_exists('query', $parsedLink)){
            parse_str($parsedLink['query'], $output);

            // Validate if query section has 'doc' key
            if(array_key_exists('doc', $output)){
                $doc = $output['doc'];
                $host = "http://eadvicenhs.wordpress.com/";
                $link = $host.$doc;
                echo "The link is ".$link."\n";

                // Making the sms body text
                $smsBody = "Jolly good day, here's your link: \n".$link;

                // Logging the sent message
                $myfile = fopen('log.txt','a') or die('Unable to open file!');
                fWrite(
                    $myfile,
                    "----------------------\n".
                    date('Y-m-d H:i:s').": ".$smsBody."\n"
                );
                fclose($myfile);

                sendMessage($smsBody);
            } else{
                fail();
            }
        } else {
            fail();
        }
    }
?>