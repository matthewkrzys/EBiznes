import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";
import {Common} from "../Common";


interface InterfacePreserves {
    id: number;
    name: string;
    quantity: number;
    weight: string;
    price: number;
    description: string;

}

const defaultPreserves: InterfacePreserves[] = [];

const Preserves = () => {

    const [PreservesItems, setPreservesItems]: [InterfacePreserves[], (posts: InterfacePreserves[]) => void] = useState(
        defaultPreserves
    );

    React.useEffect(() => {
        axios
            .get<InterfacePreserves[]>(Common.URL+'/api/preserves', {
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
                setPreservesItems(response.data);
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
                        {PreservesItems.map((preserve) => (
                            <tr key={preserve.id}>
                                <td>{preserve.id}</td>
                                <td>{preserve.name}</td>
                                <td>{preserve.quantity}</td>
                                <td>{preserve.weight} kg</td>
                                <td>{preserve.price}</td>
                                <td>{preserve.description}</td>
                                <td>{addToCart(preserve.id, preserve.name, "Preserve")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Preserves;