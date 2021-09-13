import React  from 'react';
import { useForm } from "react-hook-form";
import axios from "axios";
import {common} from "../Common";

function ChangeAddress() {
    const { register, handleSubmit } = useForm();

    function onSubmit(data: any) {
        axios({
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            },
            url: common.URL + '/api/user/update ',
            data: {
                id: common.ID,
                name: data.name,
                surname: data.surname,
                email: data.email,
                telephone: data.telephone,
                city: data.city,
                street: data.street,
                buildingNumber: data.buildingNumber,
                apartmentNumber: data.apartmentNumber
            },
            withCredentials: true
        });
        window.location.href = "/statusOrder"

    }

    return (
        <div className="changeAddress">
            <a href="/">Home</a>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <label htmlFor="Name">Name</label>
                    <input {...register("name")} />
                </div>

                <div>
                    <label htmlFor="Surname">Surname</label>
                    <input {...register("surname")} />
                </div>
                <div>
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        {...register("email")}
                    />
                </div>

                <div>
                    <label htmlFor="telephone">Telephone</label>
                    <input {...register("telephone")} />
                </div>

                <div>
                    <label htmlFor="city">City</label>
                    <input {...register("city")} />
                </div>

                <div>
                    <label htmlFor="street">Street</label>
                    <input {...register("street")} />
                </div>

                <div>
                    <label htmlFor="buildingNr">Building number</label>
                    <input {...register("buildingNumber")} />
                </div>

                <div>
                    <label htmlFor="apartmentNr">Apartment number</label>
                    <input {...register("apartmentNumber")} />
                </div>

                <input type="submit" />
            </form>
        </div>
    );

}

export default ChangeAddress;