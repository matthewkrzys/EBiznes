import React from 'react'; // let's also import Component
import {useHistory} from 'react-router-dom';
import axios from "axios";
import {Common} from "./Common";

const SignOut = () => {

    const history = useHistory();

    React.useEffect(() => {
        console.log(document.cookie.split(";").filter((value, index) => value.includes("csrfToken")).map(value => value.split("=")[1]).toString());
        axios({
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Csrf-Token': Common.csrf,
                'Authorization': Common.authorization
            },
            url: Common.URL + '/signOut',
            withCredentials: true,
        }).then(result => {
            console.log(result)
        });
        localStorage.removeItem("token");
        localStorage.removeItem("email");
        document.cookie.split(";").forEach(function (c) {
            document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
        });
        history.push('/')
    }, [history])


    return <div>
        signOut
    </div>

}

export default SignOut;