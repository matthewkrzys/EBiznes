import React, {useState} from 'react';
import {Header} from "./Header";
import {Footer} from "./Footer";
import axios from "axios";
import {Common} from "./Common";

const Buy = () => {

    const [message, setMessage] = useState("")

    React.useEffect(() => {
        axios
            .get(Common.URL + '/api/cart/buy/'+Common.ID, {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setMessage(response.data);
                console.log(response.data)
            })
            .catch((ex) => {
                console.log(ex)
            });
    }, []);
    return (<div className="App">
            <Header/>
            <div className="section-main bg padding-y">
                <div className="container">
                    <div>
                        {message}
                    </div>
                    <br/>
                </div>
            </div>
            <Footer/>
        </div>
    );
};

export default Buy;