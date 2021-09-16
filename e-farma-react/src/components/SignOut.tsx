import React from 'react'; // let's also import Component
import {useHistory} from 'react-router-dom';
import axios from "axios";
import {Common} from "./Common";

const SignOut = () => {

    const history = useHistory();

    React.useEffect(() => {
        axios({
            method: 'post',
            headers: {
                'Csrf-Token': Common.csrf
            },
            url: Common.URL + '/signOut',
            withCredentials: true,
        }).then(result => {
            console.log(result)
            history.push('/')
        });
        localStorage.removeItem("token");
        localStorage.removeItem("email");
    }, [history])


    return <div>
        signOut
    </div>

}

export default SignOut;