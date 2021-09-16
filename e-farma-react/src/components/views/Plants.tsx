import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";
import {Common} from "../Common";


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

    const [PlantsItems, setPlantsItems]: [InterfacePlants[], (posts: InterfacePlants[]) => void] = useState(
        defaultPlants
    );

    React.useEffect(() => {
        axios
            .get<InterfacePlants[]>(Common.URL+'/api/plants', {
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
                setPlantsItems(response.data);
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
                        {PlantsItems.map((plant) => (
                            <tr key={plant.id}>
                                <td>{plant.id}</td>
                                <td>{plant.name}</td>
                                <td>{plant.quantity}</td>
                                <td>{plant.species} szt</td>
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