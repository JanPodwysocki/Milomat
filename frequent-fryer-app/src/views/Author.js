    import React, { Component } from 'react';
    import {Drawer, MenuItem, AppBar, TableHeaderColumn} from "material-ui";
    import { Link } from "react-router-dom";
    import Collapsible from 'react-collapsible';
    import '../index.css';
    import { blueGrey500, grey800 } from "material-ui/styles/colors";
    class Author extends Component {

        constructor(props) {
            super(props);
            this.state = {
                mail: window.location.href.slice(32, -7),
                items: 2,
                loadingState: false,
                isFull: false
            };
            this.handleChange = this.handleChange.bind(this);
        }

        componentDidMount() {
            this.refs.iScroll.addEventListener("scroll", () => {
                if (this.refs.iScroll.scrollTop + this.refs.iScroll.clientHeight >=this.refs.iScroll.scrollHeight){
                    this.loadMoreItems();
                }
            });

        }

        displayItems() {
            var items = [];
            for (let i = 1; i < this.state.items; i++) {
                const image = require("../resources/Photos/" + i + ".jpg");
                items.push(<img src={image} key={i} height="800"></img>);
            }
            return items;
        }

        displayInfo() {
            if(!this.state.isFull){
                return "LOADING MORE IMAGES ... ";
            }
            else
                return "ALL IMAGES LOADED ... ";

        }

        loadMoreItems() {
            let number = 0;
            if(this.state.items > 58){
                number = 60
                this.state.isFull = true;
            }
            else{
                number = this.state.items + 2;
            }
            this.setState({ loadingState: true });
            setTimeout(() => {
                this.setState({ items: number, loadingState: false });
            }, 1000);
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
            return (
                <div>
                    <AppBar className="Bar"
                            title={<div className="app-bar-title">Milomat</div>} style={{backgroundColor: grey800}}
                            titleStyle={{height: 100}}
                            onLeftIconButtonTouchTap={() => this.toggleDrawer()}
                    />
                    <Drawer open={this.state.drawerOpened} docked={false} onRequestChange={() => this.toggleDrawer()}>
                        <div className="centeredText">
                            <Link to={'/travelers/' + this.state.mail + '/profile'}>
                                <MenuItem>Profile</MenuItem>
                            </Link>
                            <Link to={'/travelers/' + this.state.mail + '/flights'}>
                                <MenuItem>Flights</MenuItem>
                            </Link>
                            <Link to={window.location}>
                                <MenuItem>About Author</MenuItem>
                            </Link>
                            <Link to={'/login'}>
                                <MenuItem>Logout</MenuItem>
                            </Link>
                        </div>
                    </Drawer>
                    <div className="accordion">
                        <Collapsible trigger="Overall" triggerOpenedClassName="trigger">
                            <p><br/>
                                Contact:<br/>
                                juan.wojtek@gmail.com<br/>
                                0048 795 475 160<br/>
                                <br/>
                                <br/>
                                I am an outgoing, curious in- dividual, 23y.o., <br/>
                                with a very methodical and a quite perceptive way of seeing the world. <br/>
                                Passionate about sailing and travels.<br/>
                                I admire the logic of math and like to have feet on the ground.<br/>
                                Recently i become interested in Machine Learning.<br/>
                                Privately interested in sailing (offshore skipper).</p>
                        </Collapsible>
                    </div>
                    <div className="accordion">
                        <Collapsible trigger=" IT" triggerOpenedClassName="trigger">
                            <p>EDUCATION & PROJECTS & EXPERIENCE
                                <br/>
                                <br/>
                                Software Developer | Working Student at Nokia<br/>
                                December 2017 - Present<br/>
                                Preparing and developing automated test environment for Software Developers<br/>
                                Technologies: Python, Bash, Jenkins, Robot framework<br/>
                                <br/>
                                Computer Science<br/>
                                Jagiellonian University - October 2015 - July 2018<br/>
                                Specialization: Software Development<br/>
                                <br/>
                                <br/>
                                Projects:<br/>
                                I. Frequent Flyer web application designed in cooperation of Jagiellonian University and Sabre Corporation. <br/>
                                The goal was to create a web appli- cation to count and grant a discount for air travellers basing on their previous flight<br/>
                                (Technologies: Java 8, Spring Boot, JUnit 5, Tomcat, React.js, Jenkins).<br/>
                                <br/>
                                II. Web application designed for creating questionnaires and managing responses<br/>
                                including visual representation of their results (Technolo- gies: Python, Django, jQuery, JavaScript, CSS)<br/>
                                <br/>
                                III. Student project designed in Microsoft SQL Server.<br/>
                                The goal was to create a database of a marina for various types of vessels describing its logistics and operations.<br/>
                                <br/>
                                Additional Courses:<br/>
                                1. "Learn to Program in Java", "Object Orientated Programming in Java" (both cer- tified by Microsoft)<br/>
                                2. "Introduction to Python" (certified by Microsoft)<br/>
                                3. MACHINE LEARNING (certified by Stanford University) - ongoing<br/>
                                4. Android App Development (certified by Galileo University) - ongoing</p>
                        </Collapsible>
                    </div>
                    <div className="accordion">
                        <Collapsible trigger="Sailing" triggerOpenedClassName="trigger">
                            <div
                                className="vc"
                                ref="iScroll"
                                style={{ height: "800px", overflow: "auto" }}
                            >
                                <h2>My adventures: </h2>
                                <div>
                                    {this.displayItems()}
                                </div>
                                {this.state.loadingState
                                    ? <p className="loading">
                                        {this.displayInfo()}
                                    </p>
                                    : ""}
                            </div>
                        </Collapsible>
                    </div>
                </div>
            );
        }

    }
    export default Author;
