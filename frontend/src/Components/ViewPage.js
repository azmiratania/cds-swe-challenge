import React, { Component } from "react";


import ApiService from "../Services/ApiService";

const search = window.location.search;
const query = new URLSearchParams(search);



export default class ViewPage extends Component {

    constructor(props) {
        super(props);

        // Declare all methods
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangeResturantName = this.onChangeResturantName.bind(this);
        this.onClickSubmit = this.onClickSubmit.bind(this);
        this.viewResturants = this.viewResturants.bind(this);
        this.updateViewKey = this.updateViewKey.bind(this);


        // State variables
        this.state = {
            username: "",
            resturantName: "",
            resturantList: [],
            viewKey: "",
            interval: null,
            selectedResturant: "",
            isSessionActive: true
        }

    }


    componentDidMount() {
        var tempInterval = setInterval(() => {
            this.viewResturants();
        }, 2000);
        this.setState({ interval: tempInterval });
    }

    viewResturants() {
        var key = this.updateViewKey();
        if (key !== null && key !== "") {
            ApiService.view(key)
                .then(reply => {
                    this.setState({
                        resturantList: reply.data.resturantList,
                        selectedResturant: reply.data.selectedResturant,
                        isSessionActive: reply.data.isSessionActive
                    });
                    console.log(reply.data);
                });
        }
    }

    updateViewKey() {
        var key = query.get("key");
        this.setState({ viewKey: key });
        console.log(key);
        return key;
    }

    onClickSubmit() {

        var key = this.updateViewKey();
        var cookieKey = this.getCookie("viewKey");

        if (cookieKey === key) {
            alert("Already submitted.");
            return;
        }

        if (this.state.resturantName !== "") {
            var postData = "name=" + this.state.resturantName + "&username=" + this.state.username;

            ApiService.submitResturant(key, postData)
                .then(reply => {
                    console.log(reply.data);
                    this.setCookie("viewKey", key, 10);
                    this.setCookie("username", this.state.username, 10);
                });

        };

        this.viewResturants();
    }


    onChangeUsername(e) {
        this.setState({ username: e.target.value });
    }

    onChangeResturantName(e) {
        this.setState({ resturantName: e.target.value });
    }

    // Save to cookie
    setCookie(cname, cvalue, exdays) {
        const d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        let expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }


    // Read from cookie
    getCookie(cname) {
        let name = cname + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }


    render() {

        const { username, resturantName, resturantList, selectedResturant, isSessionActive } = this.state;


        return (
            <div className="container-fluid">
                {isSessionActive ? (
                    <div>
                        <div className="form-box">
                            <div className="row mt-5 mb-5">
                                <div className="col-md-4 pl-1 pr-1 mb-2">
                                    <input className="form-control" type="text" id="username" placeholder="Your Name" onChange={this.onChangeUsername} value={username} />
                                </div>
                                <div className="col-md-4 pl-1 pr-1 mb-2">
                                    <input className="form-control" type="text" id="resturantName" placeholder="Resturant Name" onChange={this.onChangeResturantName} value={resturantName} />
                                </div>
                                <div className="col-md-4 pl-1 pr-1 mb-2">
                                    <button className="form-control btn btn-outline-primary" onClick={this.onClickSubmit}>Submit</button>
                                </div>
                            </div>
                        </div>
                        <div className="form-box input-group">
                            <span className="input-group-text">Resturants</span>
                            <div className="form-control" >{resturantList.join(", ")}</div>
                        </div>
                    </div>
                ) : (
                    <div className="form-box input-group">
                        <span className="input-group-text">Selected Resturant : </span>
                        <div className="form-control" >{selectedResturant}</div>
                    </div>
                )}
            </div>
        )
    }
}