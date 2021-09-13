import React from 'react'; // let's also import Component
import {useHistory} from 'react-router-dom';

const SignOut = () => {

    const history = useHistory();

    React.useEffect(() => {
        localStorage.removeItem("token");
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