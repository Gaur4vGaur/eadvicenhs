<?php
// Require the bundled autoload file - the path may need to change
// based on where you downloaded and unzipped the SDK
require __DIR__ . '/twilio-php-master/Twilio/autoload.php';

// Use the REST API Client to make requests to the Twilio REST API
use Twilio\Rest\Client;

// Use the client to do fun stuff like send text messages!
function sendMessage($msg) {
    // Your Account SID and Auth Token from twilio.com/console
    $sid = '<YOUR OWN TWILIO ACCOUNTSID>';
    $token = '<YOUR OWN TWILIO TOKEN>>';
    $client = new Client($sid, $token);
    try {
        $client->messages->create(
            // the number you'd like to send the message to
            '<TWILIIO RECEIPIENT>',
            array(
                // A Twilio phone number you purchased at twilio.com/console
                'from' => '<TWILIO NUMBER THAT YOU HAVE BEEN GIVEN>',
                // the body of the text message you'd like to send
                'body' => $msg
            )
        );
        echo "Sms sent with msg :\n".$msg;
    } catch (TwilioException $e){
        echo "Could not send. Twilio replied with: " . $e;
    }  
}

function greet($msg) {
    echo $msg;
}
?>