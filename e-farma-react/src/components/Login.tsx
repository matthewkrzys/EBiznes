import React, {useState} from 'react';
import {Header} from "./Header";

// interface Props {
//     updateToken: (event: string) => void
// }

// const Login: React.FC<Props> = ({updateToken}) => {
const Login = () => {
    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");

    function onSubmit() {
        // updateToken("jet");
        console.log(username + "  " + password)
        localStorage.setItem("token", "Bearer");
    }

    return(
        <div className="login">
            <Header/>
            <h1>Please Log In</h1>
            <form>
                <label>
                    <p>Username</p>
                    <input type="text" onChange={(e) => setUserName(e.target.value)}/>
                </label>
                <label>
                    <p>Password</p>
                    <input type="password" onChange={(e) => setPassword(e.target.value)}/>
                </label>
                <div>
                    <button type="submit" onClick={onSubmit} >Submit </button>
                </div>
            </form>
        </div>
    )
}

export default Login;