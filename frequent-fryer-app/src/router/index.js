import React from 'react';
import { Route, Switch } from "react-router-dom";
import Home from '../views/Home';
import FlightAdded from '../views/FlightAdded';
import Flights from "../views/Flights";
import Registration from '../views/Registration';
import Profile from '../views/Profile';
import PreHome from '../views/PreHome';
const Main = () => (
    <main>
        <Switch>
            <Route exact path='/' component={PreHome} />
            <Route path='/login' component={Home} />
            <Route path='/travelers/:travelerId/flights' component={Flights} />
            <Route path='/registration' component={Registration} />
            <Route path='/travelers/:travelerId/profile' component={Profile} />
        </Switch>
    </main>
);

export default Main
