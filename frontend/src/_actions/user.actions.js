import {userService} from '../_services/';
import {history} from '../_helpers';

export const userActions = {
    login,
    logout
};

function login(username, password, thisComponent) {
    return dispatch => {
        let apiEndpoint = 'auth/signin';
        let payload = {
            username: username,
            password: password
        };
        userService.post(apiEndpoint, payload)
            .then((response) => {
                if (response !== undefined && response.data.token) {
                    localStorage.setItem('token', response.data.token);
                    localStorage.setItem('userId', response.data.userId);
                    dispatch(setUserDetails(response.data));
                    history.push('/home');
                }else {
                    thisComponent.setState({errorMessage: "Неверный логин или пароль"});
                }
            })
    };
}

function logout() {
    return dispatch => {
        localStorage.removeItem('token');
        dispatch(logoutUser());
        history.push('/');
    }
}

export function setUserDetails(user) {
    return {
        type: "LOGIN_SUCCESS",
        token: user.token
    }
}

export function logoutUser() {
    return {
        type: "LOGOUT_SUCCESS",
        token: ''
    }
}