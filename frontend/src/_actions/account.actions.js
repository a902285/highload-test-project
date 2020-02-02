import {userService} from '../_services/';
import {history} from '../_helpers';

export const accountAction = {
    getAccount,
    getAccountById,
    onChangeProps,
    editAccountInfo,
    createAccount,
    deleteAccountById
};

function getAccount() {
    return dispatch => {
        let apiEndpoint = 'account';
        userService.get(apiEndpoint)
            .then((response) => {
                dispatch(changeAccountsList(response.data));
            }).catch((err) => {
            console.log(err);
        })
    };
}

function createAccount(payload) {
    return dispatch => {
        let apiEndpoint = 'account/';
        userService.post(apiEndpoint, payload)
            .then((response) => {
                dispatch(createUserInfo());
                history.push('/account');
            })
    }
}

function getAccountById(id) {
    return dispatch => {
        let apiEndpoint = 'account/' + id;
        userService.get(apiEndpoint)
            .then((response) => {
                dispatch(editAccountsDetails(response.data));
            })
    };
}

function onChangeProps(props, event) {
    return dispatch => {
        dispatch(handleOnChangeProps(props, event.target.value));
    }
}

function editAccountInfo(id, payload) {
    return dispatch => {
        let apiEndpoint = 'account/' + id;
        userService.put(apiEndpoint, payload)
            .then((response) => {
                dispatch(updatedUserInfo());
                history.push('/account');
            })
    }
}

function deleteAccountById(id) {
    return dispatch => {
        let apiEndpoint = 'account/' + id;
        userService.deleteDetail(apiEndpoint)
            .then((response) => {
                dispatch(deleteAccountsDetails());
                dispatch(accountAction.getAccount());
            })
    };
}

export function changeAccountsList(account) {
    return {
        type: "FETECHED_ALL_ACCOUNT",
        account: account
    }
}

export function handleOnChangeProps(props, value) {
    return {
        type: "HANDLE_ON_CHANGE",
        props: props,
        value: value
    }
}

export function editAccountsDetails(account) {
    return {
        type: "ACCOUNT_DETAIL",
        id: account.id,
        firstName: account.firstName,
        lastName: account.lastName,
        age: account.age,
        gender: account.gender
    }
}

export function updatedUserInfo() {
    return {
        type: "USER_UPDATED"
    }
}

export function createUserInfo() {
    return {
        type: "USER_CREATED_SUCCESSFULLY"
    }
}

export function deleteAccountsDetails() {
    return {
        type: "DELETED_ACCOUNT_DETAILS"
    }
}