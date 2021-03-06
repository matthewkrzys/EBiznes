import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";
import {Common} from "../Common";


interface InterfaceHoney {
    id: number;
    name: string;
    quantity: number;
    weight: string;
    price: number;

}

const defaultHoney: InterfaceHoney[] = [];

const Honey = () => {

    const [HoneyItems, setHoneyItems]: [InterfaceHoney[], (posts: InterfaceHoney[]) => void] = useState(
        defaultHoney
    );

    React.useEffect(() => {
        axios
            .get<InterfaceHoney[]>(Common.URL+'/api/honeys', {
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
                setHoneyItems(response.data);
                console.log(response)
            })
            .catch((ex) => {
                console.log(ex)
            });
    }, []);

    return (<div>
            <div className="container">
                <div className="row">
                    <table >
                        <thead >
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">name</th>
                            <th scope="col">quantity</th>
                            <th scope="col">weight</th>
                            <th scope="col">price</th>
                            <th scope="col">action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {HoneyItems.map((honey) => (
                            <tr key={honey.id}>
                                <td>{honey.id}</td>
                                <td>{honey.name}</td>
                                <td>{honey.quantity}</td>
                                <td>{honey.weight} kg</td>
                                <td>{honey.price}</td>
                                <td>{addToCart(honey.id, honey.name,"Honey")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Honey;