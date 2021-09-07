import React, {Component} from 'react';
import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";
import Fruits from "./views/Fruits";
import Honey from "./views/Honey";
import Plants from "./views/Plants";
import Preserves from "./views/Preserves";
import Seeds from "./views/Seeds";
import Vegetables from "./views/Vegetables";
import Tools from "./views/Tools";
import Flowers from "./views/Flowers";
import {Header} from "./Header";

export class Products extends Component {

    componentWillMount() {
    }

    componentDidMount() {
    }

    render() {
        return <HashRouter>
                    <Header/>
                <section className="section-main bg padding-y">
                    <div className="container-md">
                        <div className="row">
                            <aside className="col-md-3">
                                <nav className="card">
                                    <ul className="menu-category">
                                        <li><NavLink to="/Products/Flowers">Flowers</NavLink></li>
                                        <li><NavLink to="/Products/Fruits">Fruits</NavLink></li>
                                        <li><NavLink to="/Products/Honey">Honey</NavLink></li>
                                        <li><NavLink to="/Products/Plants">Plants</NavLink></li>
                                        <li><NavLink to="/Products/Preserves">Preserves</NavLink></li>
                                        <li><NavLink to="/Products/Seeds">Seeds</NavLink></li>
                                        <li><NavLink to="/Products/Vegetables">Vegetables</NavLink></li>
                                        <li><NavLink to="/Products/Tools">Tools</NavLink></li>
                                    </ul>
                                </nav>
                            </aside>
                            <div className="col-md-8">
                                <Route path="/Products/Flowers" component={Flowers}/>
                                <Route path="/Products/Fruits" component={Fruits}/>
                                <Route path="/Products/Honey" component={Honey}/>
                                <Route path="/Products/Plants" component={Plants}/>
                                <Route path="/Products/Preserves" component={Preserves}/>
                                <Route path="/Products/Seeds" component={Seeds}/>
                                <Route path="/Products/Vegetables" component={Vegetables}/>
                                <Route path="/Products/Tools" component={Tools}/>
                            </div>
                        </div>
                    </div>
                </section>
        </HashRouter>
    }
}