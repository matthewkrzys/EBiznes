import React from 'react'; // let's also import Component
import {useHistory} from 'react-router-dom';

const SignOut = () => {
    const history = useHistory();
    const handleClick = () => history.push('/');

    function cleanTokenCookie() {
        localStorage.removeItem("token");
        document.cookie.split(";").forEach(function (c) {
            document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
        });
        handleClick()
    }

    return <div>
        signOut
        {cleanTokenCookie}
    </div>

}

export default SignOut;