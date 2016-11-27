var React = require('react');

var About = React.createClass({
  render: function() {
    var content = {
      flex: 0.8
    };

    return (
      <div style={content}>
        <h1 className="text-center">About</h1>
        <p>This is a weather application built on React. Wanted to learn React so this was something
        helped me familiarized with the framework</p>
        <p>
          Here are some tools I used:
        </p>
        <ul>
          <li>
            <a href="https://facebook.github.io/react">React</a>JavaScript framework React
          </li>
          <li>
            <a href="https://openweathermap.org">Open Weather Map</a>Thanks to the API provided
              by Open Weather Map, I could retrieve weather data for different city in real time.
          </li>
        </ul>
      </div>
    );
  }
});

module.exports = About;
