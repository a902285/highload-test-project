import React, {Component} from 'react';
import './App.css';
import {Route, Router, Switch} from 'react-router-dom';
import {Login, Signup} from './login/';
import {Home} from './home/';
import {Account} from './account/account.component';
import {AddAccount} from './account/addaccount.component';
import {history} from './_helpers';
import {PrivateRoute} from './_components';

class App extends Component {
    render() {
        return (
            <div className="App">
                <Router history={history}>
                    <div>
                        <Switch>
                            <PrivateRoute exact path='/home' component={Home}/>
                            <PrivateRoute exact path='/account' component={Account}/>
                            <PrivateRoute exact path='/add-account' component={AddAccount}/>
                            <PrivateRoute exact path='/edit-account/:id' component={AddAccount}/>
                            <PrivateRoute exact path='/view-account/:id' component={Home}/>
                            <Route exact path='/' component={Login}/>
                            <Route exact path='/signup' component={Signup}/>
                        </Switch>
                    </div>
                </Router>
            </div>
        );
    }
}

export default App;