import React, {useState} from 'react';
import axios from 'axios';
import addToCart from "../elements/AddToCart";
import {Common} from "../Common";


interface InterfaceTools {
    id: number;
    name: string;
    quantity: number;
    price: number;
    description: string;

}

const defaultTools: InterfaceTools[] = [];

const Tools = () => {

    const [toolsItems, setToolsItems]: [InterfaceTools[], (posts: InterfaceTools[]) => void] = useState(
        defaultTools
    );

    React.useEffect(() => {
        axios
            .get<InterfaceTools[]>(Common.URL+'/api/tools', {
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
                setToolsItems(response.data);
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
                            <th scope="col">price</th>
                            <th scope="col">description</th>
                            <th scope="col">action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {toolsItems.map((tool) => (
                            <tr key={tool.id}>
                                <td>{tool.id}</td>
                                <td>{tool.name}</td>
                                <td>{tool.quantity}</td>
                                <td>{tool.price}</td>
                                <td>{tool.description}</td>
                                <td>{addToCart(tool.id, tool.name, "Tools")}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Tools;