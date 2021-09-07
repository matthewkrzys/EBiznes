import React, {Component} from 'react';

export class Footer extends Component {

    render() {
        return <footer className="section-footer border-top bg">
            <div className="container">
                <section className="footer-bottom row">
                    <div className="col-md-2">
                        <p className="text-muted"> 2021 Company name </p>
                    </div>
                    <div className="col-md-8 text-md-center">
                        <span className="px-2">info@com</span>
                        <span className="px-2">+000-000-0000</span>
                        <span className="px-2">Street name 123, ABC</span>
                    </div>
                    <div className="col-md-2 text-md-right text-muted">
                        <i className="fab fa-lg fa-cc-visa"/>
                        <i className="fab fa-lg fa-cc-paypal"/>
                        <i className="fab fa-lg fa-cc-mastercard"/>
                    </div>
                </section>
            </div>
        </footer>
    }
}