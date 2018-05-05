import React, { Component } from 'react';
import { FlatButton, RaisedButton, TextField } from "material-ui";
import { Link } from "react-router-dom";
import { grey800 } from "material-ui/styles/colors";
import '../index.css';

class PreHome extends Component {

    constructor(props) {
        super(props);
        this.state = {
            value: '',
            mail: '',
            password: '',
            drawerOpened: false,
            render: ''
        };
        this.handleChange = this.handleChange.bind(this);
    }

    toggleDrawer() {
        this.setState({
            drawerOpened: !this.state.drawerOpened
        });
    }

    handleChange(event) {
        this.setState({ value: event.target.value });
    }

    render() {
        return (
            <div>
                <div>
                    <div className="App-dx">
                        <header>Milomat</header>
                    </div>
                    <div className="Beta">
                        <br/>
                        <header>Welcome to Milomat FAQ!</header>
                        <br/>
                    </div>
                    <div className="buttonStyle">
                        <h3>
                            Would you like to buy tickets at special prices?<br /> <br />
                            Collect your miles and and use them to pay less for future travels!<br /><br /><br /><br />
                            How to add a flight correctly?<br /><br />
                            Go to Flights view and fulfill proper gaps<br /><br /><br /><br />
                            Remember:<br /><br />
                            Origin: three-uppercase-letter code of departure airport<br /><br />
                            Destination: three-uppercase-letter code of arrival airport<br /><br />
                            Carrier: two-uppercase-letter id of carrier of your flight<br /><br />
                            Flight Number: four-digit id of your flight<br /><br />
                            Remember to choose proper Trip Type, Trip Class and Departure Dates<br />
                        </h3>
                        <h2>
                            Let’s do it!<br />
                        </h2>
                        <br />
                        <br />
                        <Link to={'/login'}>
                            <RaisedButton id="login-user-button" label="LOGIN" />
                        </Link>
                        <br />
                        <br />
                        <Link to={'/registration'}>
                            <FlatButton  id="register" label="REGISTER" primary={true} />
                        </Link>
                    </div>
                    <br />
                </div>
            </div >
        );
    }
}

export default PreHome;
