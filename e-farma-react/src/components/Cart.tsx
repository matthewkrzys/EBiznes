import React, {useState} from 'react';
import axios from "axios";
import {Header} from "./Header";
import {Footer} from "./Footer";
import {Common} from "./Common";


interface InterfaceCart {
    userId: number;
    productId: number;
    productName: string;
    tableName: string;
    quantity: number;
}

const defaultCart: InterfaceCart[] = [];

const Cart = () => {

    const [CartItem, setCart]: [InterfaceCart[], (posts: InterfaceCart[]) => void] = useState(
        defaultCart
    );

    React.useEffect(() => {
        console.log(Common.ID)
        axios
            .get<InterfaceCart[]>(Common.URL + '/api/cart/status/'+Common.ID, {
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
                setCart(response.data);
                console.log(response.data)
            })
            .catch((ex) => {
                console.log(ex)
            });
    }, []);

    function deleteElement(cartElement: InterfaceCart) {

        function deleteRequest(cart: InterfaceCart) {
            axios({
                method: 'delete',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Csrf-Token': Common.csrf,
                    'Authorization': Common.authorization
                },
                withCredentials: true,
                url: Common.URL + '/api/cart/delete',
                data: {
                    userId: Common.ID,
                    productId: cart.productId,
                    productName: cart.productName,
                    tableName: cart.tableName,
                    quantity: cart.quantity
                }
            });
            window.location.reload()
        }

        function addQuantity(cart: InterfaceCart) {
            axios({
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Csrf-Token': Common.csrf,
                    'Authorization': Common.authorization
                },
                withCredentials: true,
                url: Common.URL + '/api/cart/add',
                data: {
                    userId: Common.ID,
                    productId: cart.productId,
                    productName: cart.productName,
                    tableName: cart.tableName,
                    quantity: cart.quantity + 1
                }
            });
            window.location.reload()
        }

        function removeQuantity(cart: InterfaceCart) {
            axios({
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Csrf-Token': Common.csrf,
                    'Authorization': Common.authorization
                },
                withCredentials: true,
                url: Common.URL + '/api/cart/add',
                data: {
                    userId: Common.ID,
                    productId: cart.productId,
                    productName: cart.productName,
                    tableName: cart.tableName,
                    quantity: cart.quantity - 1
                }
            });
            window.location.reload()
        }

        return <div>
            <button type="button" onClick={() => removeQuantity(cartElement)}>
                -
            </button>
            <button type="button" onClick={() => addQuantity(cartElement)}>
                +
            </button>
            <button type="button" onClick={() => deleteRequest(cartElement)}>
                Delete
            </button>
        </div>

    }

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
                                <th>action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {CartItem.map((cart) => (
                                <tr key={cart.productId+cart.tableName}>
                                    <td>{cart.tableName}</td>
                                    <td>{cart.productName}</td>
                                    <td>{cart.quantity}</td>
                                    <td>{deleteElement(cart)}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <br/>
                    <a href="/statusOrder">
                        <button>Status Order</button>
                    </a>
                </div>
            </div>
            <Footer/>
        </div>
    )
}
export default Cart;