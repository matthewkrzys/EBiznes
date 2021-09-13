import axios from "axios";
import React from "react";
import {common} from "../Common";

const addToCart = (id: number, name: string, tableName: string) => {

    function sendRequest(param: any) {
        console.log("send")
        axios({
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
            },
            url: common.URL + '/api/cart/add',
            data: {
                userId: common.ID,
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