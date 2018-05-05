import React, {Component} from 'react';
import {RaisedButton, TextField} from "material-ui";
import {Link} from "react-router-dom";

class FlightAdded extends Component {

    constructor(props) {
        super(props);
        this.state = {value: ''}
        this.handleChange = this.handleChange.bind(this);
        console.log('xD');
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    render() {
        return (
            <div>
                <div>
                    BRAWO
                </div>
            </div>
        );
    }
}

export default FlightAdded;
