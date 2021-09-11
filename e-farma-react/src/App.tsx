import React, {Component} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import Content from "./components/Content";

export class App extends Component {

  render() {
    return <div className="content">
        <Content/>
      </div>
  }
}

export default App;

