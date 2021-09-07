import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";


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

    const [Seeds, setSeeds]: [InterfaceSeeds[], (posts: InterfaceSeeds[]) => void] = useState(
        defaultSeeds
    );


    // eslint-disable-next-line react-hooks/rules-of-hooks
    let list: number[] = new Array(Seeds.length+1);
    for (let i = 0; i < list.length; i++) {
        list[i] = 1;
    }

    React.useEffect(() => {
        axios
            .get<InterfaceSeeds[]>('http://localhost:9000/api/seeds', {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setSeeds(response.data);
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
                        {Seeds.map((seed) => (
                            <tr key={seed.id}>
                                <td>{seed.id}</td>
                                <td>{seed.name}</td>
                                <td>{seed.quantity}</td>
                                <td>{seed.weight}</td>
                                <td>{seed.price}</td>
                                <td>{seed.description}</td>
                                <td>{addToCart(seed.id, "Seeds")}</td>
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