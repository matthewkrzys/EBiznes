import React from 'react'; // let's also import Component
import {useHistory} from 'react-router-dom';
import axios from "axios";
import {Common} from "./Common";

const SignOut = () => {

    const history = useHistory();

    React.useEffect(() => {
        localStorage.removeItem("token");
        localStorage.removeItem("email");
        document.cookie.split(";").forEach(function (c) {
            document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
        });
        history.push('/')

        axios({
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
            },
            url: Common.URL + '/signOut',
            withCredentials: true,
        }).then(result => {
            console.log(result)
        });
    }, [history])


    return <div>
        signOut
    </div>

}

export default SignOut;