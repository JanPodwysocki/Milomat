import React, { Component } from 'react';
import { FlatButton, RaisedButton, TextField, Snackbar } from "material-ui";
import { Link } from "react-router-dom";
import '../index.css';
import axios from "axios/index";

class Registration extends Component {

    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            mail: '',
            password: '',
            message: '',
            open: false,
            value: 1,
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({ value: event.target.value });
    }

    postDataHandler = () => {
        const data = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            password: this.state.password,
            email: this.state.mail
        };
        axios.post('http://localhost:8088/registration', data)
            .then(response => {
                this.state.message = response.data;
                console.log(response.data);
                this.setState({
                    open: true,
                });
                if (response.data === 'Success') {
                    window.location.href = '/';
                }
            })
            .catch((e) => {
                this.setState({
                    open: true,
                });
            });
    };

    handleRequestClose = () => {
        this.setState({
            open: false,
        });
    };

    render() {
        return (
            <div>
                <div className="App-dx">
                    <header>Milomat</header>
                </div>
                <div className="Beta">
                    <br />
                    <header>PLEASE REGISTER BELOW</header>
                    <br />
                </div>
                <div className="buttonStyle">
                    <TextField id={"name"} hintText="Name"
                        onChange={e => this.setState({ firstName: e.target.value })} /> <br /><br />
                    <TextField id={"surname"} hintText="Surname"
                        onChange={e => this.setState({ lastName: e.target.value })} /> <br /><br />
                    <TextField id={"mail"} hintText="Traveler Mail"
                        onChange={e => this.setState({ mail: e.target.value })} /> <br /><br />
                    <TextField id={"password"} hintText="Password"
                        onChange={e => this.setState({ password: e.target.value })}
                        type="password" /> <br /><br />
                    <RaisedButton id="register-user-button" label="REGISTER" onClick={this.postDataHandler} /><br /><br /><br /><br />
                    <Link to={'/'}>
                        Remind me the rules
                    </Link>
                </div>

                <Snackbar
                    open={this.state.open}
                    message={this.state.message}
                    autoHideDuration={2500}
                    onRequestClose={this.handleRequestClose}
                /> <br />
            </div>
        );
    }
}

export default Registration;
