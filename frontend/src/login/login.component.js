import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import MuiAlert from '@material-ui/lab/Alert';
import {connect} from 'react-redux';
import {userActions} from '../_actions';
import {history} from '../_helpers';
import Typography from '@material-ui/core/Typography';
import {withRouter} from 'react-router-dom';
import './login.component.css'
import {Snackbar} from "@material-ui/core";

const styles = theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    margin: {
        margin: theme.spacing(1),
    },
    withoutLabel: {
        marginTop: theme.spacing(3),
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    },

    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },

    button: {
        margin: theme.spacing(1),
    },

    input: {
        display: 'none',
    },
});


class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            showPassword: false,
            submitted: false,
            errorMessage: ''
        }
    }

    componentDidMount() {
        console.log(this.props);
        if (localStorage.getItem('token')) {
            history.push('/home');
        }
    }

    handleChange = prop => event => {
        this.setState({[prop]: event.target.value});
    };

    login = event => {
        this.setState({submitted: true});
        const {username, password} = this.state;
        const {dispatch} = this.props;
        if (username && password) {
            dispatch(userActions.login(username, password, this));
        }
    };

    render() {
        const {classes} = this.props;
        return (
            <div className="login-margin">
                <Grid container spacing={10}>
                    <Grid item xs={3}>
                    </Grid>
                    <Grid item xs={6}>
                        <Paper className={classes.paper}>
                            <Typography><b>Вход в систему</b></Typography>
                        </Paper>
                        <Paper className={classes.paper}>
                            <div>
                                <TextField
                                    label="Имя пользователя"
                                    value={this.state.username}
                                    className={classes.textField}
                                    onChange={this.handleChange('username')}
                                />
                                <br/>
                                <br/>
                                <TextField
                                    label="Пароль"
                                    autoComplete="current-password"
                                    type={this.state.showPassword ? 'text' : 'password'}
                                    className={classes.textField}
                                    value={this.state.password}
                                    onChange={this.handleChange('password')}
                                />
                                <br/>
                                <br/>
                                <Button variant="contained" color="primary" className={classes.button}
                                        onClick={(event) => {
                                            this.login()
                                        }}>
                                    Войти
                                </Button>
                                <Button variant="contained"  className={classes.button} href={"/signup"}>
                                    Регистрация
                                </Button>
                                <br/>
                                <br/>
                                {
                                    this.state.errorMessage && <Snackbar open={true}>
                                        <Alert severity="error">{this.state.errorMessage}</Alert>
                                    </Snackbar>
                                }
                            </div>
                        </Paper>
                    </Grid>
                    <Grid item xs={3}>
                    </Grid>
                </Grid>
            </div>
        );
    }
}

Login.propTypes = {
    classes: PropTypes.object.isRequired,
};

const Alert = (props) => {
  return <MuiAlert elevation={6} variant={"filled"} {...props} />
};

const mapStateToProps = (state) => {
    const {loggingIn} = state.authentication;
    return {
        loggingIn
    };
};

const connectedLoginPage = withRouter(connect(mapStateToProps, null, null, {
    pure: false
})(withStyles(styles)(Login)));

export {connectedLoginPage as Login};