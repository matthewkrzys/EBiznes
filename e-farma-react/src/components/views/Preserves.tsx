import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";


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

    const [Preserves, setPreserves]: [InterfacePreserves[], (posts: InterfacePreserves[]) => void] = useState(
        defaultPreserves
    );


    // eslint-disable-next-line react-hooks/rules-of-hooks
    let list: number[] = new Array(Preserves.length+1);
    for (let i = 0; i < list.length; i++) {
        list[i] = 1;
    }

    React.useEffect(() => {
        axios
            .get<InterfacePreserves[]>('http://localhost:9000/api/preserves', {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setPreserves(response.data);
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
                        {Preserves.map((preserve) => (
                            <tr key={preserve.id}>
                                <td>{preserve.id}</td>
                                <td>{preserve.name}</td>
                                <td>{preserve.quantity}</td>
                                <td>{preserve.weight}</td>
                                <td>{preserve.price}</td>
                                <td>{preserve.description}</td>
                                <td>{addToCart(preserve.id, "Preserve")}</td>
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