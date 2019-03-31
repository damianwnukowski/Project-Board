import React, { Component } from "react";
import Navbar from "./components/Navbar";
import ProjectBoard from "./components/ProjectBoard";

class App extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <ProjectBoard />
      </div>
    );
  }
}

export default App;