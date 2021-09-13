import React, {useState} from 'react';
import axios from "axios";
import {Header} from "./Header";
import {Footer} from "./Footer";
import {common} from "./Common";


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
        console.log(common.ID)
        axios
            .get<InterfaceCart[]>(common.URL + '/api/cart/status/'+common.ID, {
                headers: {
                    'Content-Type': 'application/json',
                },
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
                    'Content-Type': 'application/json',
                },
                url: common.URL + '/api/cart/delete',
                data: {
                    userId: common.ID,
                    productId: cart.productId,
                    tableName: cart.tableName,
                    quantity: cart.quantity
                }
            });
            window.location.reload()
        }

        function addQantity(cart: InterfaceCart) {
            axios({
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
                },
                url: common.URL + '/api/cart/add',
                data: {
                    userId: common.ID,
                    productId: cart.productId,
                    tableName: cart.tableName,
                    quantity: cart.quantity + 1
                }
            });
            window.location.reload()
        }

        function removeQuaintity(cart: InterfaceCart) {
            axios({
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
                },
                url: common.URL + '/api/cart/add',
                data: {
                    userId: common.ID,
                    productId: cart.productId,
                    tableName: cart.tableName,
                    quantity: cart.quantity - 1
                }
            });
            window.location.reload()
        }

        return <div>
            <button type="button" onClick={() => removeQuaintity(cartElement)}>
                -
            </button>
            <button type="button" onClick={() => addQantity(cartElement)}>
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
                                <tr>
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