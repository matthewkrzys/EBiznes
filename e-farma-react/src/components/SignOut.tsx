import React from 'react'; // let's also import Component
import { useHistory } from 'react-router-dom';

const SignOut = () => {
    const history = useHistory();
    const handleClick = () => history.push('/');
    function singOut() {
        localStorage.removeItem("token");
        handleClick()
    }
        return <div>
            signOut
            {singOut()}
        </div>

}

export default SignOut;