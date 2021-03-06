import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";
import {Common} from "../Common";


interface InterfaceSeeds {
    id: number;
    name: string;
    quantity: number;
    weight: string;
    price: number;
    description: string;

}

const defaultSeeds: InterfaceSeeds[] = [];

const Seeds = () => {

    const [SeedsItems, setSeedsItems]: [InterfaceSeeds[], (posts: InterfaceSeeds[]) => void] = useState(
        defaultSeeds
    );

    React.useEffect(() => {
        axios
            .get<InterfaceSeeds[]>(Common.URL+'/api/seeds', {
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
                setSeedsItems(response.data);
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
                            <th scope="col">description</th>
                            <th scope="col">action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {SeedsItems.map((seed) => (
                            <tr key={seed.id}>
                                <td>{seed.id}</td>
                                <td>{seed.name}</td>
                                <td>{seed.quantity}</td>
                                <td>{seed.weight} g</td>
                                <td>{seed.price}</td>
                                <td>{seed.description}</td>
                                <td>{addToCart(seed.id, seed.name, "Seeds")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Seeds;