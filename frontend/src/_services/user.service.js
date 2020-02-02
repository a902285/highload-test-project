import axios from 'axios';
import config from '../config/config';

export const userService = {
    get,
    post,
    put,
    deleteDetail
};

function get(apiEndpoint) {
    return axios.get(config.baseUrl + apiEndpoint, getOptions())
        .then((response) => {
            return response;
        }).catch((err) => {
            console.log(err);
            checkResponse(err.response);
        })
}

function post(apiEndpoint, payload) {
    return axios.post(config.baseUrl + apiEndpoint, payload, getOptions())
        .then((response) => {
            return response;
        }).catch((err) => {
            console.log(err);
            checkResponse(err.response);
        })
}

function put(apiEndpoint, payload) {
    return axios.put(config.baseUrl + apiEndpoint, payload, getOptions())
        .then((response) => {
            return response;
        }).catch((err) => {
            console.log(err);
            checkResponse(err.response);
        })
}

function deleteDetail(apiEndpoint) {
    return axios.delete(config.baseUrl + apiEndpoint, getOptions())
        .then((response) => {
            return response;
        }).catch((err) => {
            console.log(err);
            checkResponse(err.response);
        })
}

function getOptions() {
    let options = {};
    if (localStorage.getItem('token')) {
        options.headers = {'Authorization': 'Bearer ' + localStorage.getItem('token')};
    }
    return options;
}

function checkResponse(response) {
    if (response.status === 401) {
        localStorage.removeItem('token')
    }
}