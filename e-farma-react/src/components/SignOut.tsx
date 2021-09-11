import React from 'react'; // let's also import Component
import {useHistory} from 'react-router-dom';

const SignOut = () => {
    const history = useHistory();
    const handleClick = () => history.push('/');

    function singOut() {
        localStorage.removeItem("token");
        // document.cookie = ""
        document.cookie.split(";").forEach(function (c) {
            document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
        });

        handleClick()
    }

    return <div>
        signOut
        {singOut()}
    </div>

}

export default SignOut;