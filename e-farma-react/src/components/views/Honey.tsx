import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";


interface InterfaceHoney {
    id: number;
    name: string;
    quantity: number;
    weight: string;
    price: number;

}

const defaultHoney: InterfaceHoney[] = [];

const Honey = () => {

    const [Honey, setHoney]: [InterfaceHoney[], (posts: InterfaceHoney[]) => void] = useState(
        defaultHoney
    );


    // eslint-disable-next-line react-hooks/rules-of-hooks
    let list: number[] = new Array(Honey.length+1);
    for (let i = 0; i < list.length; i++) {
        list[i] = 1;
    }

    React.useEffect(() => {
        axios
            .get<InterfaceHoney[]>('http://localhost:9000/api/honeys', {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setHoney(response.data);
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
                        {Honey.map((honey) => (
                            <tr key={honey.id}>
                                <td>{honey.id}</td>
                                <td>{honey.name}</td>
                                <td>{honey.quantity}</td>
                                <td>{honey.weight}</td>
                                <td>{honey.price}</td>
                                <td>{addToCart(honey.id, "Honey")}</td>
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