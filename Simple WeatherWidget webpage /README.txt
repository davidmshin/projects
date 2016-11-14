Simple Weather-widget webpage in HTML, CSS, and Javascript

Using AJAX requests to a weather server.
fetchWeather() function specifically makes an AJAX request to the server.

Server outputs JSON
-AJAX requests cannot be performed cross-domain for security reasons. 
-Have set the Access-Control-Allow-Origin header on the server to allow requests from my EC2 instances, or from localhost. Needs to be run via EC2 or locally, in order for the AJAX requests to work.

