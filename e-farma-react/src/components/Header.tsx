import React, {Component} from 'react';

export class Header extends Component {

    render() {
        return <header className="section-header">
            <section className="header-main border-bottom">
                <div className="container">
                    <div className="row align-items-center">
                        <div className="col-lg-2 col-4">
                            <h1>E-Farma</h1>
                        </div>
                        <div className="col-lg-6 col-sm-12">
                        </div>
                        <div className="col-lg-4 col-sm-6 col-12">
                            <div className="widgets-wrap float-md-right">
                                <div className="widget-header icontext">
                                    <div className="text">
                                        {localStorage.getItem("token") ? this.showSignOut() : this.showSingInAndRegister()}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            {localStorage.getItem("token") ? this.showNaviBar() : <div/>}
        </header>
    }

    showNaviBar() {
        return <nav className="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
            <div className="container">
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#main_nav"
                        aria-controls="main_nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"/>
                </button>
                <div className="collapse navbar-collapse" id="main_nav">
                    <ul className="navbar-nav">
                        <li className="nav-item dropdown">
                            <a href="/">
                                <button>Home</button>
                            </a>
                        </li>
                        <li className="nav-item dropdown">
                            <a href="/products">
                                <button>Products</button>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="/cart">
                                <button>Cart</button>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>
    }

    showSignOut() {
        return <div>
            <a href="/signOut">SignOut</a>
        </div>
    }

    showSingInAndRegister() {
        return <div>
            <span className="text-muted">Welcome!</span>
            <div>
                <a href="/signIn">Sign in</a> |
                <a href="/signUp"> Register</a>
            </div>
        </div>
    }

}