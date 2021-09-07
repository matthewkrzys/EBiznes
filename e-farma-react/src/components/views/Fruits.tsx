import React, {useState} from 'react';
import axios from 'axios';
import addToCart from '../elements/AddToCart'


interface InterfaceFruits {
    id: number;
    name: string;
    quantity: number;
    weight: string;
    price: number;
}

const defaultFruits: InterfaceFruits[] = [];

const Fruits = () => {

    const [FruitElements, setFruits]: [InterfaceFruits[], (posts: InterfaceFruits[]) => void] = useState(
        defaultFruits
    );


    // eslint-disable-next-line react-hooks/rules-of-hooks
    let list: number[] = new Array(FruitElements.length+1);
    for (let i = 0; i < list.length; i++) {
        list[i] = 1;
    }

    React.useEffect(() => {
        axios
            .get<InterfaceFruits[]>('http://localhost:9000/api/fruits', {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setFruits(response.data);
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
                        {FruitElements.map((fruit) => (
                            <tr key={fruit.id}>
                                <td>{fruit.id}</td>
                                <td>{fruit.name}</td>
                                <td>{fruit.quantity}</td>
                                <td>{fruit.weight}</td>
                                <td>{fruit.price}</td>
                                <td>{addToCart(fruit.id, "Fruit")}</td>
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