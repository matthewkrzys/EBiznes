import React, {useState} from 'react';
import axios from 'axios';
import {Common} from "../Common";

interface InterfaceAddress {
    id: number;
    name: string;
    surname: string;
    email: string;
    telephone: string;
    city: string;
    street: string;
    buildingNumber: string;
    apartmentNumber: string;
}

const Address: React.FC = () => {

    const [addressElement, setAddressElement] = useState<InterfaceAddress>();

    React.useEffect(() => {
        console.log(localStorage.getItem("email"))
        console.log(Common.csrf)
        axios
            .get<InterfaceAddress>(Common.URL + '/api/user/email/'+ localStorage.getItem("email"), {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                console.log(response.data)
                setAddressElement(response.data)
            })
            .catch((ex) => {
                console.log(ex)
            });
    }, []);

    return (<div className="App">
            <div className="section-main bg padding-y">
                <div className="container">
                    <div>
                        <h5>Address</h5>
                        Name : {addressElement?.name}
                        <br/>
                        Surname : {addressElement?.surname}
                        <br/>
                        E-mail : {addressElement?.email}
                        <br/>
                        Telephone : {addressElement?.telephone}
                        <br/>
                        City : {addressElement?.city}
                        <br/>
                        Street : {addressElement?.street}
                        <br/>
                        Building Number : {addressElement?.buildingNumber}
                        <br/>
                        Apartament Number : {addressElement?.apartmentNumber}
                    </div>
                    <br/>
                    <a href="/changeAddress">
                        <button>Change Address</button>
                    </a>
                </div>
            </div>
        </div>
    );
};

export default Address;