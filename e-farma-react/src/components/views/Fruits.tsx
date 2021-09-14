import React, {useState} from 'react';
import axios from 'axios';
import addToCart from '../elements/AddToCart'
import {Common} from "../Common";


interface InterfaceFruits {
    id: number;
    name: string;
    quantity: number;
    weight: string;
    price: number;
}

const defaultFruits: InterfaceFruits[] = [];

const Fruits = () => {

    const [FruitItems, setFruitsItems]: [InterfaceFruits[], (posts: InterfaceFruits[]) => void] = useState(
        defaultFruits
    );

    React.useEffect(() => {
        axios
            .get<InterfaceFruits[]>('http://localhost:9000/api/fruits', {
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
                setFruitsItems(response.data);
                console.log(response)
            })
            .catch((ex) => {
                console.log(ex)
            });
    }, []);

    return (<div>
            <div className="container">
                <div className="row">
                    <table>
                        <thead>
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
                        {FruitItems.map((fruit) => (
                            <tr key={fruit.id}>
                                <td>{fruit.id}</td>
                                <td>{fruit.name}</td>
                                <td>{fruit.quantity}</td>
                                <td>{fruit.weight}  kg</td>
                                <td>{fruit.price}</td>
                                <td>{addToCart(fruit.id, fruit.name, "Fruit")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Fruits;