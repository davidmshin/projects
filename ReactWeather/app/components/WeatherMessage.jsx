import Background from '../img/weather.jpg';

var React = require('react');

var WeatherMessage = React.createClass ({
  // if(temperature>80) {
  //   //hot
  //   weather = 'hot';
  // } else if(temperature>69) {
  //   //warm
  //   weather = 'warm';
  // } else if(temperature>58) {
  //   //cool
  //   weather = "cool";
  // } else if(temperature>44) {
  //   //cold
  //   weahter = "cold";
  // } else {
  //   //Freekin freezing
  //   weather = "freezing";
  // }
  render:function() {
    let temp = this.props.temp;
    var location = this.props.location;

    return (
      <div id="weather-warm">
      <h3 className="text-center">It's {temp} in {location}.</h3>
      </div>
    );
  }
});

module.exports = WeatherMessage;
