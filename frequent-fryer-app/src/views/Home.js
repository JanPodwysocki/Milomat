import React, { Component } from 'react';
import { FlatButton, RaisedButton, TextField, Snackbar } from "material-ui";
import { Link } from "react-router-dom";
import { grey800 } from "material-ui/styles/colors";
import '../index.css';
import axios from "axios/index";
class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            value: '',
            mail: '',
            password: '',
            drawerOpened: false,
            render: '',
            message: 'Wrong credentials',
            open: false
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

    handleRequestClose = () => {
        this.setState({
            open: false,
        });
    };

    postDataHandler = () => {
        const data = {
            password: this.state.password,
            email: this.state.mail,
        };
        axios.post('http://localhost:8088/login', data)
            .then(response => {
                console.log(response.data);
                if (response.data === true) {
                    window.location.href = '/travelers/' + this.state.mail + '/flights';
                } else {
                    this.setState({
                        open: true,
                    });
                    this.state.message = "Wrong credentials";
                }
            });
    };

    render() {
        return (
            <div>
                <div>
                    <div className="App-dx">
                        <header>Milomat</header>
                    </div>
                    <br />
                    <div className="buttonStyle">
                        <TextField id={"mail"} hintText="Traveler Mail"
                            onChange={e => this.setState({ mail: e.target.value })} /> <br /><br />
                        <TextField id="password" hintText="Password" onChange={e => this.setState({ password: e.target.value })} type="password" />
                        <br /><br />
                        <RaisedButton id="login-user-button" label="LOGIN" onClick={this.postDataHandler} />
                        <br /> <br />
                        <Link to={'/registration'}>
                            <FlatButton label="REGISTER" primary={true} />
                        </Link>
                        <br /><br /><br /><br />
                        <Link to={'/'}>
                            Remind me the rules
                        </Link>
                        <Snackbar id="resultInfo"
                            open={this.state.open}
                            message={this.state.message}
                            autoHideDuration={2500}
                            onRequestClose={this.handleRequestClose}
                        />
                    </div>
                    <br />
                </div>

            </div >
        );
    }
}

export default Home;
