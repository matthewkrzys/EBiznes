import axios from "axios";
import React from "react";
import {Common} from "../Common";

const addToCart = (id: number, name: string, tableName: string) => {

    function sendRequest(param: any) {
        console.log("send")
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
                productId: param,
                productName: name,
                tableName: tableName,
                quantity: 1
            }
        });
    }


    return <div>
        <button type="button" onClick={() => sendRequest(id)}> Add to cart
        </button>
    </div>

}

export default addToCart;