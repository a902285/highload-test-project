import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import {connect} from 'react-redux';
import {signupAction} from '../_actions';
import {history} from '../_helpers';
import Typography from '@material-ui/core/Typography';
import {withRouter} from 'react-router-dom';
import {Snackbar} from "@material-ui/core";
import MuiAlert from "@material-ui/lab/Alert";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Radio from "@material-ui/core/Radio";

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

    formLabel: {
        textAlign: 'left',

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


class Signup extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            firstName: '',
            lastName: '',
            age: '',
            gender: '',
            city: '',
            description: '',
            showPassword: false,
            submitted: false,
            errorMessage: '',
        }
    }

    componentDidMount() {
        if (localStorage.getItem('token')) {
            history.push('/home');
        }
    }

    handleChange = prop => event => {
        this.setState({[prop]: event.target.value});
    };

    signup = event => {
        const {dispatch} = this.props;

        let payload = {
            username: this.state.username,
            password: this.state.password,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            age: this.state.age,
            gender: this.state.gender,
            city: this.state.city,
            description: this.state.description
        };

        dispatch(signupAction.createAccount(payload, this));
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
                            <Typography><b>Регистрация нового пользователя</b></Typography>
                        </Paper>
                        <Paper className={classes.paper}>
                            <div>
                                <TextField
                                    label="Логин"
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
                                <TextField
                                    label="Имя"
                                    value={this.state.firstName}
                                    className={classes.textField}
                                    onChange={this.handleChange('firstName')}
                                />
                                <br/>
                                <br/>
                                <TextField
                                    label="Фамилия"
                                    value={this.state.lastName}
                                    className={classes.textField}
                                    onChange={this.handleChange('lastName')}
                                />
                                <br/>
                                <br/>
                                <TextField
                                    label="Возраст"
                                    value={this.state.age}
                                    className={classes.textField}
                                    onChange={this.handleChange('age')}
                                />
                                <br/>
                                <br/>

                                <FormControl component={"fieldset"} className={classes.textField}>
                                    <FormLabel component={"legend"} className={classes.formLabel}>Пол</FormLabel>
                                    <RadioGroup value={this.state.gender} onChange={this.handleChange('gender')}>
                                        <FormControlLabel value="0" control={<Radio/>} label="Женский"/>
                                        <FormControlLabel value="1" control={<Radio/>} label="Мужской"/>
                                    </RadioGroup>
                                </FormControl>

                                <br/>
                                <TextField
                                    label="Город"
                                    value={this.state.city}
                                    className={classes.textField}
                                    onChange={this.handleChange('city')}
                                />
                                <br/>
                                <br/>
                                <TextField
                                    label="Интересы"
                                    value={this.state.description}
                                    className={classes.textField}
                                    onChange={this.handleChange('description')}
                                />
                                <br/>
                                <br/>
                                <Button variant="contained" className={classes.button} href={"/"}>
                                    Вход
                                </Button>
                                <Button variant="contained" color="primary" className={classes.button}
                                        onClick={(event) => {
                                            this.signup()
                                        }}>
                                    Сохранить
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

const Alert = (props) => {
    return <MuiAlert elevation={6} variant={"filled"} {...props} />
};

Signup.propTypes = {
    classes: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => {
    return state;
};

const connectedSignupPage = withRouter(connect(mapStateToProps, null, null, {
    pure: false
})(withStyles(styles)(Signup)));

export {connectedSignupPage as Signup};