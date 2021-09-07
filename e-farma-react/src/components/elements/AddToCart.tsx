import axios from "axios";
import React from "react";

const addToCart = (id: number, tableName: string) => {

    function sendRequest(param: any, tableName: string) {
        console.log("send")
        axios({
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
            },
            url: 'http://localhost:9000/api/cart/add',
            data: {
                userId: 1,
                productId: param,
                tableName: tableName,
                quantity: 1
            }
        });
    }


    return <div>
        <button type="button" onClick={() => sendRequest(id, tableName)}> Add to cart
        </button>
    </div>

}

export default addToCart;