import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";


interface InterfaceVegetables {
    id: number;
    name: string;
    quantity: number;
    weight: string;
    price: number;
}

const defaultVegetables: InterfaceVegetables[] = [];

const Vegetables = () => {

    const [Vegetables, setVegetables]: [InterfaceVegetables[], (posts: InterfaceVegetables[]) => void] = useState(
        defaultVegetables
    );

    // eslint-disable-next-line react-hooks/rules-of-hooks
    const list: number[] = new Array(Vegetables.length+1);
    for (let i = 0; i < list.length; i++) {
        list[i] = 1;
    }

    React.useEffect(() => {
        axios
            .get<InterfaceVegetables[]>('http://localhost:9000/api/vegetables', {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setVegetables(response.data);
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
                        {Vegetables.map((vegetable) => (
                            <tr key={vegetable.id}>
                                <td>{vegetable.id}</td>
                                <td>{vegetable.name}</td>
                                <td>{vegetable.quantity}</td>
                                <td>{vegetable.weight}</td>
                                <td>{vegetable.price}</td>
                                <td>{addToCart(vegetable.id, "Vegetables")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Vegetables;