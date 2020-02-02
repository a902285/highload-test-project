import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';
import AppBar from '../_components/appbar';
import Nav from '../_components/nav';
import TextField from '@material-ui/core/TextField';
import {userService} from "../_services";

const drawerWidth = 240;
const styles = theme => ({
    root: {
        flexGrow: 1,
    },
    appFrame: {
        zIndex: 1,
        overflow: 'hidden',
        position: 'relative',
        display: 'flex',
        width: '100%',
    },
    appBar: {
        width: `calc(100% - ${drawerWidth}px)`,
    },
    'appBar-left': {
        marginLeft: drawerWidth,
    },
    'appBar-right': {
        marginRight: drawerWidth,
    },
    drawerPaper: {
        position: 'relative',
        width: drawerWidth,
    },
    toolbar: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.default,
        padding: theme.spacing(3),
    },
});

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            account: {
                username: '',
                password: '',
                firstName: '',
                lastName: '',
                age: '',
                gender: '',
                city: '',
                description: ''
            }
        }
    }

    componentDidMount() {
        const {match: {params}} = this.props;
        let userId = localStorage.getItem("userId")
        if (params.id) {
            userId = params.id
        }
        userService.get("account/" + userId)
            .then((response) => {
                if (response !== undefined) {
                    if (response.data.gender === 1) {
                        response.data.gender = 'Мужской'
                    } else if (response.data.gender === 0) {
                        response.data.gender = 'Женский'
                    }
                    this.setState({account: response.data})
                }
            })
    }

    render() {
        const {classes} = this.props;
        const {account} = this.state;
        return (
            <div className={classes.root}>
                <div className={classes.appFrame}>
                    <AppBar/>
                    <Nav/>
                    <main className={classes.content}>
                        <div className={classes.toolbar}/>
                        <TextField
                            label="Логин"
                            value={account.username}
                            disabled={true}
                        />
                        <br/>
                        <br/>
                        <TextField
                            label="Имя"
                            value={account.firstName}
                            className={classes.textField}
                            disabled={true}
                        />
                        <br/>
                        <br/>
                        <TextField
                            label="Фамилия"
                            value={account.lastName}
                            className={classes.textField}
                            disabled={true}
                        />
                        <br/>
                        <br/>
                        <TextField
                            label="Возраст"
                            value={account.age}
                            className={classes.textField}
                            disabled={true}
                        />
                        <br/>
                        <br/>
                        <TextField
                            label="Пол"
                            value={account.gender}
                            className={classes.textField}
                            disabled={true}
                        />
                        <br/>
                        <br/>
                        <TextField
                            label="Город"
                            value={account.city}
                            className={classes.textField}
                            disabled={true}
                        />
                        <br/>
                        <br/>
                        <TextField
                            label="Интересы"
                            value={account.description}
                            className={classes.textField}
                            disabled={true}
                        />
                        <br/>
                        <br/>
                    </main>
                </div>
            </div>
        );
    }
}

Home.propTypes = {
    classes: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
    return state;
}

const connectedHomePage = withRouter(connect(mapStateToProps, null, null, {
    pure: false
})(withStyles(styles)(Home)));
export {connectedHomePage as Home};