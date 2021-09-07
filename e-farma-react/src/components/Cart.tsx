import React, {useState} from 'react';
import axios from "axios";
import {Header} from "./Header";
import {Footer} from "./Footer";


interface InterfaceCart {
    userId: number;
    productId: number;
    tableName: string;
    quantity: number;
}

const defaultCart: InterfaceCart[] = [];

const Cart = () => {

    const [Cart, setCart]: [InterfaceCart[], (posts: InterfaceCart[]) => void] = useState(
        defaultCart
    );

    React.useEffect(() => {
        axios
            .get<InterfaceCart[]>('http://localhost:9000/api/cart/status/1', {
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
                url: 'http://localhost:9000/api/cart/delete',
                data: {
                    userId: cart.userId,
                    productId: cart.productId,
                    tableName: cart.tableName,
                    quantity: cart.quantity
                }
            });
            window.location.reload()
        }

        function addQantity(cart: InterfaceCart){
            axios({
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
                },
                url: 'http://localhost:9000/api/cart/add',
                data: {
                    userId: 1,
                    productId: cart.productId,
                    tableName: cart.tableName,
                    quantity: cart.quantity+1
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
                url: 'http://localhost:9000/api/cart/add',
                data: {
                    userId: 1,
                    productId: cart.productId,
                    tableName: cart.tableName,
                    quantity: cart.quantity-1
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

    return (<div>
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
                                <th>action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {Cart.map((cart) => (
                                <tr>
                                    <td>{cart.userId}</td>
                                    <td>{cart.productId}</td>
                                    <td>{cart.tableName}</td>
                                    <td>{cart.quantity}</td>
                                    <td>{deleteElement(cart)}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <br/>
                <button> Move to status </button>
                </div>
            </div>
            <Footer/>
        </div>
    )
}
export default Cart;