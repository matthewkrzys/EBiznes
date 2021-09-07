import React  from 'react';
import { useForm } from "react-hook-form";

function SignUp() {
    const { register, handleSubmit } = useForm();

    function onSubmit(data: any) {
        alert(JSON.stringify(data));
    }

    return (
        <div className="App">
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
                    <label htmlFor="password">Password</label>
                    <input {...register("password")} />
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

export default SignUp;