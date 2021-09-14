import React, {useState} from 'react';
import axios from 'axios';
import {Header} from "./Header";
import {Footer} from "./Footer";
import {Common} from "./Common";
import Address from "./elements/Address";

interface InterfaceStatusOrder {
    userId: number;
    productId: number;
    productName: string;
    tableName: string;
    quantity: number;
}

const defaultStatusOrder: InterfaceStatusOrder[] = [];

const StatusOrder = () => {

    const [statusOrder, setStatusOrder]: [InterfaceStatusOrder[], (posts: InterfaceStatusOrder[]) => void] = useState(
        defaultStatusOrder
    );

    React.useEffect(() => {
        axios
            .get<InterfaceStatusOrder[]>(Common.URL + '/api/cart/statusOrder/'+Common.ID, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Csrf-Token': Common.csrf,
                    'Authorization': Common.authorization
                },
                withCredentials: true,
                timeout: 10000,
            })
            .then((response) => {
                setStatusOrder(response.data);
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
                        <table>
                            <thead>
                            <tr>
                                <th>category</th>
                                <th>productName</th>
                                <th>quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            {statusOrder.map((element) => (
                                <tr key={element.productId+element.tableName}>
                                    <td>{element.tableName}</td>
                                    <td>{element.productName}</td>
                                    <td>{element.quantity}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <Address/>
                    <br/>
                    <a href="/buy">
                        <button>Buy</button>
                    </a>
                </div>
            </div>
            <Footer/>
        </div>
    );
};

export default StatusOrder;