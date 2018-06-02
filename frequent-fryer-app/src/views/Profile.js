import React, { Component } from 'react';
import { Drawer, MenuItem, AppBar } from "material-ui";
import { Link } from "react-router-dom";
import '../index.css';
import axios from "axios/index";
import { grey800 } from "material-ui/styles/colors";
import bronze from '../pngFiles/bronze1.png'
import silver from '../pngFiles/silver1.png'
import golden from '../pngFiles/gold1.png'


class Profile extends Component {

    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            mail: window.location.href.slice(32, -8),
            miles: '',
            tmp: {},
            updated: false,
            status: '',
            imagePath: '',
            drawerOpened: false
        };
        this.handleChange = this.handleChange.bind(this);
        axios.get('http://localhost:8088/' + window.location.href.slice(22))
            .then(response => {
                let tmp = JSON.parse(JSON.stringify(response.data));
                this.state.tmp = tmp;
                console.log('TUTAJ: ' + this.state.tmp.miles);
                this.state.firstName = tmp.firstName;
                this.state.lastName = tmp.lastName;
                this.state.miles = tmp.miles;
                this.state.status = tmp.status;
                if (this.state.status === 'Bronze') {
                    console.log('bronze');
                    this.state.imagePath = '../pngFiles/bronze1.png'
                } else if (this.state.status === 'Silver') {
                    console.log('silver');
                    this.state.imagePath = '../pngFiles/silver1.png'
                } else {
                    console.log('golden');
                    this.state.imagePath = '../pngFiles/gold1.png'
                }
                console.log('imagepath: ' + this.state.imagePath);
                this.setState({ updated: true });
                console.log('state mile: ' + this.state.miles);
            })
            .catch((e) => {
            });
    }

    handleChange(event) {
        this.setState({ value: event.target.value });
    }

    toggleDrawer() {
        this.setState({
            drawerOpened: !this.state.drawerOpened
        });
    }


    render() {
        let img;
        if (this.state.status === 'Bronze') {
            img = bronze;
        } else if (this.state.status === 'Silver') {
            img = silver;
        } else {
            img = golden;
        }
        return (
            <div>
                <AppBar className="Bar"
                    title={<div className="app-bar-title">Milomat</div>} style={{ backgroundColor: grey800 }}
                    titleStyle={{ height: 100 }}
                    onLeftIconButtonTouchTap={() => this.toggleDrawer()}
                />
                <div className="Beta">
                    <br />
                    <header>YOUR PROFILE</header>
                    <br />
                </div>
                <div className="centeredImage">
                    <img src={require("../pngFiles/happy-plane.png")} />
                </div>
                <Drawer open={this.state.drawerOpened} docked={false} onRequestChange={() => this.toggleDrawer()}>
                    <div className="centeredText">
                        <Link to={window.location}>
                            <MenuItem >Profile</MenuItem>
                        </Link>
                        <Link to={'/travelers/' + this.state.mail + '/flights'}>
                            <MenuItem>Flights</MenuItem>
                        </Link>
                        <Link to={'/travelers/' + this.state.mail + '/author'}>
                            <MenuItem>About Author</MenuItem>
                        </Link>
                        <Link to={'/login'}>
                            <MenuItem>Logout</MenuItem>
                        </Link>
                    </div>
                </Drawer>
                <div className="buttonStyle">
                    <h3>First Name: {this.state.firstName}</h3> <br />
                    <h3>Last Name: {this.state.lastName}</h3> <br />
                    <h3>You have {this.state.miles} miles</h3> <br />
                    <h2>You're a {this.state.status} flyer!</h2>
                    <div className="centeredImage">
                        <img src={img} />
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile;
