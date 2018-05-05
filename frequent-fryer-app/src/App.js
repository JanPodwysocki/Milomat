import React, { Component } from 'react';
import './App.css';
import Main from './router/index';
import { AppBar, Drawer, MenuItem, MuiThemeProvider } from 'material-ui';
import { BrowserRouter } from 'react-router-dom';
import { grey800 } from "material-ui/styles/colors";

class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="App-xd">
                <BrowserRouter>
                    <MuiThemeProvider>
                        <Main />
                    </MuiThemeProvider>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
