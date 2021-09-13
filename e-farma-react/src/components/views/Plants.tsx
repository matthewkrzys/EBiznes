import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";


interface InterfacePlants {
    id: number;
    name: string;
    quantity: number;
    species: string;
    price: number;
    description: string;

}

const defaultPlants: InterfacePlants[] = [];

const Plants = () => {

    const [Plants, setPlants]: [InterfacePlants[], (posts: InterfacePlants[]) => void] = useState(
        defaultPlants
    );


    // eslint-disable-next-line react-hooks/rules-of-hooks
    let list: number[] = new Array(Plants.length+1);
    for (let i = 0; i < list.length; i++) {
        list[i] = 1;
    }

    React.useEffect(() => {
        axios
            .get<InterfacePlants[]>('http://localhost:9000/api/plants', {
                headers: {
                    'Content-Type': 'application/json',
                },
                timeout: 10000,
            })
            .then((response) => {
                setPlants(response.data);
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
                        {Plants.map((plant) => (
                            <tr key={plant.id}>
                                <td>{plant.id}</td>
                                <td>{plant.name}</td>
                                <td>{plant.quantity}</td>
                                <td>{plant.species}</td>
                                <td>{plant.price}</td>
                                <td>{plant.description}</td>
                                <td>{addToCart(plant.id, plant.name,"Plants")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Plants;