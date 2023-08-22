import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import ViewPage from "./ViewPage";

import ApiService from "../Services/ApiService";

const search = window.location.search;
const query = new URLSearchParams(search);



export default class HomePage extends Component {

    constructor(props) {
        super(props);

        // Declare all methods
        this.onClickStartSession = this.onClickStartSession.bind(this);
        this.onClickEndSession = this.onClickEndSession.bind(this);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeAdminKey = this.onChangeAdminKey.bind(this);
        this.onChangeViewKey = this.onChangeViewKey.bind(this);
        this.generateLink = this.generateLink.bind(this);
        this.copyTextToClipboard = this.copyTextToClipboard.bind(this);

        // State variables
        this.state = {
            name: "",
            adminKey: "",
            viewKey: "",
            isActive: false,
            isResturantSelected: false,
            selectedResturantName: ""
        }

    }

    generateLink(key, mode) {
        var link = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port;
        if (mode === 1) {
            link += "/AdminPage?key=" + key;
        } else {
            link += "/ViewPage?key=" + key;
        }
        return link;
    }

    // User clicked button manually
    onClickStartSession() {
        if (this.state.name !== "") {
            ApiService.initiateSession(this.state.name)
                .then(reply => {
                    if (reply.data.id > 0) {
                        this.setState({
                            adminKey: reply.data.adminKey,
                            viewKey: reply.data.viewKey,
                            name: reply.data.name,
                            isActive: true,
                            isResturantSelected: false,
                            selectedResturantName: ""
                        });
                        var viewUrl = this.generateLink(reply.data.viewKey);
                        this.copyTextToClipboard(viewUrl);
                        window.open(viewUrl,"_blank");

                    }
                    console.log(reply.data);
                });
        }
    }

    onClickEndSession() {
        var resturant;
        if (this.state.isActive) {
            ApiService.endSession(this.state.adminKey)
                .then(reply => {
                    if (reply.data === "") {
                        resturant = "McDonalds";
                    } else {
                        resturant = reply.data.selectedResturant;
                    }
                    this.setState({
                        selectedResturantName: resturant,
                        isActive: false,
                        isResturantSelected: true
                    });

                    console.log(reply.data);
                });
        }
    }


    onChangeName(e) {
        this.setState({ name: e.target.value });
    }

    onChangeAdminKey(e) {
        this.setState({ adminKey: e.target.value });
    }

    onChangeViewKey(e) {
        this.setState({ viewKey: e.target.value });
    }


    // Main function to copy text to clipboard
    copyTextToClipboard(text) {
        if (!navigator.clipboard) {
            // fallbackCopyTextToClipboard(text);
            return;
        }
        navigator.clipboard.writeText(text).then(
            function () {
                console.log('Async: Copying to clipboard was successful!');
            }, function (err) {
                console.error('Async: Could not copy text: ', err);
            }
        );
    }




    render() {

        const { name, isActive, isResturantSelected, selectedResturantName, adminKey, viewKey } = this.state;


        return (
            <div className="container-fluid">

                <div className="form-box">
                    <div className="row mt-5 mb-5">
                        <div className="col-md-6 pl-1 pr-1 mb-2">
                            <input className="form-control" type="text" id="name" placeholder="Your Name" onChange={this.onChangeName} value={name} />
                        </div>
                        <div className="col-md-6 pl-1 pr-1 mb-2">
                            <button className="form-control btn btn-outline-primary" onClick={this.onClickStartSession}>Start</button>
                        </div>
                    </div>

                </div>

                {isActive ? (
                    <div className="form-box">
                        <div className="input-group mb-3">
                            <span className="input-group-text" id="basic-addon1">User link</span>
                            <input className="form-control" id="viewKey" type="text" readOnly onChange={this.onChangeViewKey} value={this.generateLink(viewKey, 0)} />
                        </div>
                        <div className="input-group mb-3">
                            <button className="form-control btn btn-outline-primary" onClick={this.onClickEndSession}>End</button>
                        </div>
                    </div>

                ) : ("")
                }
                {isResturantSelected ? (
                    <div>
                        <h3>Selected Resturant : {selectedResturantName}</h3>
                    </div>

                ) : ("")}

            </div>

        )
    }
}