import {userService} from '../_services/';
import {history} from '../_helpers';

export const signupAction = {
    createAccount
};

function createAccount(payload, thisComponent) {
    return dispatch => {
        let apiEndpoint = 'auth/signup';
        userService.post(apiEndpoint, payload)
            .then((response) => {
                if (response !== undefined) {
                    dispatch(createUserInfo());
                    history.push('/');
                } else {
                    thisComponent.setState({errorMessage: "Ошибка при сохранении нового пользователя. Проверьте корректность введенных данных / измените имя пользователя"});
                }
            })
    }
}

export function createUserInfo() {
    return {
        type: "SIGNUP_SUCCESS"
    }
}