import React, {useState} from 'react';
import axios from 'axios';
import {Header} from "./Header";
import {Footer} from "./Footer";

interface InterfaceStatusOrder {
    userId: number;
    productId: number;
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
            .get<InterfaceStatusOrder[]>('http://localhost:9000/api/cart/statusOrder/1', {
                headers: {
                    'Content-Type': 'application/json',
                },
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
                                <th>userId</th>
                                <th>productId</th>
                                <th>tableName</th>
                                <th>quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            {statusOrder.map((element) => (
                                <tr>
                                    <td>{element.userId}</td>
                                    <td>{element.productId}</td>
                                    <td>{element.tableName}</td>
                                    <td>{element.quantity}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th>userId</th>
                                <th>productId</th>
                                <th>tableName</th>
                                <th>quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            {statusOrder.map((element) => (
                                <tr>
                                    <td>{element.userId}</td>
                                    <td>{element.productId}</td>
                                    <td>{element.tableName}</td>
                                    <td>{element.quantity}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
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