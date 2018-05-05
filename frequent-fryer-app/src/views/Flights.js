import React, { Component } from 'react';
import axios from 'axios';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn } from 'material-ui/Table';
import { AppBar, Drawer, MenuItem, RaisedButton, SelectField, TextField, Snackbar } from "material-ui";
import { getUserFlights } from "../client/FlightsServiceClient";
import '../index.css';
import { grey800 } from "material-ui/styles/colors";
import DatePicker from 'material-ui/DatePicker';
import Link from 'react-router-dom/Link';

class Flights extends Component {
    constructor(props) {
        super(props);
        this.state = {
            flights: [],
            mail: window.location.href.slice(32, -8),
            origin: '',
            destination: '',
            classType: '',
            tripType: '',
            depDate: '',
            depDate2: '',
            carrier: '',
            number: '',
            value: 1,
            open: false,
            message: '',
            drawerOpened: false
        }
    }

    componentDidMount() {
        const { travelerId } = this.props.match.params;
        getUserFlights(travelerId)
            .then(data => {
                console.log(data);
                this.setState({ flights: data })
            })
            .catch(err => console.log("cannot fetch: " + err))
    }

    toggleDrawer() {
        this.setState({
            drawerOpened: !this.state.drawerOpened
        });
    }

    mapToTableRow = (flight, id) => {
        const { departureAirportCode, arrivalAirportCode, carrier, number, departureDate } = flight;
        return (
            <TableRow key={id}>
                <TableRowColumn>{carrier}</TableRowColumn>
                <TableRowColumn>{number}</TableRowColumn>
                <TableRowColumn>{departureDate}</TableRowColumn>
                <TableRowColumn>{departureAirportCode}</TableRowColumn>
                <TableRowColumn>{arrivalAirportCode}</TableRowColumn>
            </TableRow>);
    };
    parsedate = (date) => {
        let day = date.getDate();
        day = day < 10 ? '0' + day : day;
        let month = date.getMonth() + 1;
        month = month < 10 ? '0' + month : month;

        return '' + date.getFullYear() + '-' + month + '-' + day
    };

    postDataHandler = () => {
        const data = {
            mail: window.location.href.slice(32, -8),
            classType: this.state.classType,
            tripType: this.state.tripType,
            departureAirportCode: this.state.origin,
            arrivalAirportCode: this.state.destination,
            carrier: this.state.carrier,
            number: this.state.flightNo,
            departureDate: this.state.depDate,
            departureDate2: this.state.depDate2,
        };

        axios.post('http://localhost:8088/travelers/' + data.mail + '/flights/', data)
            .then((response) => {
                this.state.message = response.data;
                this.setState({
                    open: true,
                });
                console.log("open: " + this.state.open);
                window.setTimeout(function () { window.location.reload() }, 2100);
            })
            .catch((e) => {
                if (e.response.status === 400) {
                    this.state.message = 'Not all fields have been filled.';
                    console.log("response. status: " + e.response.status);
                } else if (e.response.status === 500) {
                    this.state.message = 'Second departure date is not selected.';
                    console.log("response. status: " + e.response.status);
                }
                this.setState({
                    open: true,
                });
            });
    };
    handleClassType = (event, index, value) => {
        this.setState({ value });
        this.state.classType = value;
    };

    handleTripType = (event, index, value) => {
        this.setState({ value });
        this.state.tripType = value;
    };

    handleChangeDate = (event, date) => {
        this.state.depDate = this.parsedate(date);
        console.log(this.state.depDate);
    };
    handleChangeDate2 = (event, date) => {
        this.state.depDate2 = this.parsedate(date);
        console.log(this.state.depDate2);
    };

    handleRequestClose = () => {
        this.setState({
            open: false,
        });
    };

    render() {
        const { travelerId } = this.props.match.params;
        return (
            <div>
                <div>
                    <AppBar className="Bar"
                        title={<div className="app-bar-title">Milomat</div>} style={{ backgroundColor: grey800 }}
                        titleStyle={{height: 100}}
                        onLeftIconButtonTouchTap={() => this.toggleDrawer()}
                    />
                    <Drawer open={this.state.drawerOpened} docked={false} onRequestChange={() => this.toggleDrawer()}>
                        <div className="centeredText">
                            <Link to={'/travelers/' + this.state.mail + '/profile'}>
                                <MenuItem >Profile</MenuItem>
                            </Link>
                            <Link to={window.location}>
                                <MenuItem>Flights</MenuItem>
                            </Link>
                            <Link to={'/login'}>
                                <MenuItem>Logout</MenuItem>
                            </Link>
                        </div>
                    </Drawer>
                </div >
                <div className="buttonStyle">
                    <TextField id={"origin"} hintText="Origin"
                        onBlur={e => this.setState({ origin: e.target.value })} />
                    <TextField id={"destination"} hintText="Destination"
                        onBlur={e => this.setState({ destination: e.target.value })} />
                    <br />
                    <TextField id={"carrier"} hintText="Carrier"
                        onBlur={e => this.setState({ carrier: e.target.value })} />
                    <TextField id={"flightNo"} hintText="Flight Number"
                        onBlur={e => this.setState({ flightNo: e.target.value })} />
                    <br />

                    <SelectField id={"classType"} hintText="Class Type"
                        value={this.state.classType}
                        onChange={this.handleClassType}>
                        <MenuItem value='Economy' primaryText="Economy" />
                        <MenuItem value='Premium Economy' primaryText="Premium Economy" />
                        <MenuItem value='Business' primaryText="Business" />
                        <MenuItem value='First' primaryText="First" />
                    </SelectField>

                    <SelectField id={"tripType"} hintText="Trip Type"
                        value={this.state.tripType}
                        onChange={this.handleTripType}>
                        <MenuItem value='oneWay' primaryText="One Way" />
                        <MenuItem value='roundTrip' primaryText="Round Trip" />
                    </SelectField>
                    <br />
                    <DatePicker id={"departureDate"} hintText="Departure Day"
                        onChange={this.handleChangeDate} />
                    <DatePicker id={"comeBackDate"} hintText="Come Back Departure Day"
                        onChange={this.handleChangeDate2} />
                    <br />
                    <RaisedButton onClick={this.postDataHandler} id="add-flight-button" label="Add a flight"
                        onChange={this.handleChange} value={this.state.value} />
                    <Snackbar
                        open={this.state.open}
                        message={this.state.message}
                        autoHideDuration={2500}
                        onRequestClose={this.handleRequestClose}
                    />
                </div>
                <div className="Beta">
                    <br/>
                    <header>YOUR FLIGHTS</header>
                    <br/>
                </div>

                <Table id={"flightsTable"}>
                    <TableHeader>
                        <TableRow>
                            <TableHeaderColumn>Linia lotnicza</TableHeaderColumn>
                            <TableHeaderColumn>Numer lotu</TableHeaderColumn>
                            <TableHeaderColumn>Data wylotu</TableHeaderColumn>
                            <TableHeaderColumn>Miejsce wylotu</TableHeaderColumn>
                            <TableHeaderColumn>Miejsce przylotu</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        {this.state.flights.map(this.mapToTableRow)}
                    </TableBody>
                </Table>
            </div >
        );
    }
}

export default Flights;