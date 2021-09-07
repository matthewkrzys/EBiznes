import React from 'react';
import {Header} from "./Header";
import {Footer} from "./Footer";
import Login from "./Login";

function Home() {

    return <div>
        {localStorage.getItem("token") ? homeDiv() : <Login/>}
    </div>


    function homeDiv() {
        return <div>
            <Header/>
            <section className="section-main bg padding-y">
            <div className="container-md">
                <article className="banner-wrap">
                    <img src="assets/images/3.jpg" className="w-90 rounded" alt="home"/>
                </article>
            </div>
            <Footer/>
            </section>
        </div>
    }
}

export default Home;