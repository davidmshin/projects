var React = require('react');
var {Link} = require('react-router');

var Examples = (props) => {
  return (
    <div>
      <h1 className="text-center page-title">Examples</h1>
      <div className="weather-search">
        <p>Here are a few example locations. </p>
        <ol>
          <li>
            <Link to='/?location=St.Louis'>St.Louis, MO</Link>
          </li>
          <li>
            <Link to='/?location=San Francisco'>San Francisco, CA</Link>
          </li>
        </ol>
      </div>

    </div>
  )
};

module.exports = Examples;
