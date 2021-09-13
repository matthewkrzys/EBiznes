import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";


interface InterfaceFlowers {
    id: number;
    name: string;
    quantity: number;
    species: string;
    price: number;
    description: string;

}

const defaultFlower: InterfaceFlowers[] = [];

const Flowers = () => {

    const [flowersItems, setFlowersItems]: [InterfaceFlowers[], (posts: InterfaceFlowers[]) => void] = useState(
        defaultFlower
    );

    React.useEffect(() => {
        axios
            .get<InterfaceFlowers[]>('http://localhost:9000/api/flowers', {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setFlowersItems(response.data);
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
                            <th scope="col">species</th>
                            <th scope="col">price</th>
                            <th scope="col">description</th>
                            <th scope="col">action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {flowersItems.map((flower) => (
                            <tr key={flower.id}>
                                <td>{flower.id}</td>
                                <td>{flower.name}</td>
                                <td>{flower.quantity}</td>
                                <td>{flower.species}</td>
                                <td>{flower.price}</td>
                                <td>{flower.description}</td>
                                <td>{addToCart(flower.id, flower.name, "Flowers")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        );
};

export default Flowers;