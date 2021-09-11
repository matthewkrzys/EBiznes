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
import StatusOrder from "./StatusOrder";
import Buy from "./Buy";

function Content() {

    return <div className="content">
        {/*<Login/>*/}
        {contentElement()}
        {/*{localStorage.getItem("token") ? contentElement() : <Login/>}*/}
    </div>

    function contentElement() {
        return <BrowserRouter>
            <Route exact path="/" component={Home}/>
            <Route exact path="/signIn" component={Login}/>
            <Route exact path="/signUp" component={SignUp}/>
            <Route exact path="/signOut" component={SignOut}/>
            <Route exact path="/products" component={Products}/>
            <Route exact path="/cart" component={Cart}/>
            <Route exact path="/statusOrder" component={StatusOrder}/>
            <Route exact path="/buy" component={Buy}/>
        </BrowserRouter>
    }
}

export default Content;