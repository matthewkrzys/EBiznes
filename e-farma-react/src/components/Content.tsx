import React from 'react';
import {
    Route,
    BrowserRouter,
} from "react-router-dom";
import {Products} from "./Products";
import Cart from "./Cart"
import Login from "./Login";
import SignUp from "./SignUp";
import Home from './Home';
import SignOut from "./SignOut";

function Content() {

    return <div>
        {localStorage.getItem("token") ? contentElement() : <Login/>}
    </div>

    function contentElement() {
        return <BrowserRouter>
            <Route exact path="/" component={Home}/>
            <Route exact path="/signIn" component={Login}/>
            <Route exact path="/signUp" component={SignUp}/>
            <Route exact path="/signOut" component={SignOut}/>
            <Route exact path="/products" component={Products}/>
            <Route exact path="/cart" component={Cart}/>
        </BrowserRouter>
    }
}

export default Content;