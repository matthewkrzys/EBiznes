import React from 'react';
import {Header} from "./Header";
import axios from "axios";
import {useForm} from "react-hook-form";
import {common} from "./Common";

const Login = () => {
    const { register, handleSubmit } = useForm();


    function onSubmit(data: any) {
        console.log(data)
        axios({
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
            },
            url: common.URL + '/signIn',
            data: {
                email: data.email,
                password: data.password,
            },
            withCredentials: true,
        }).then(result => {
            console.log(result)
            window.location.reload();
        });
    }

    function authenticateGoogle() {
        window.open(common.URL + "/authenticate/google");
        setTimeout (window.close, 5000);
    }

    return(
        <div className="login">
            <Header/>
            <h1>Please Log In</h1>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>
                    <p>Username</p>
                    <input {...register("email")} />
                </label>
                <label>
                    <p>Password</p>
                    <input {...register("password")} />
                </label>
                <div>
                    <a href="/">
                        <button type="submit" >Submit </button>
                    </a>
                </div>
            <div className="modal-footer small-margin-bottom">
                <div className="col s3">
                    <div className="image">
                        <button className="waves-effect waves-light btn blue"
                                onClick={() => authenticateGoogle()}>
                            <img src="assets/images/google.png"  alt="google"/>
                        </button>
                    </div>
                </div>
            </div>
            </form>
        </div>
    )
}

export default Login;