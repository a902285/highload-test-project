const initialState = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    age: '',
    gender: '',
    city: '',
    description: ''
};

export function signup(state = initialState, action) {
    switch (action.type) {
        case 'SIGNUP_SUCCESS':
            return {
                ...state,
                username: action.username,
                password: action.password,
                firstName: action.firstName,
                lastName: action.lastName,
                age: action.age,
                gender: action.gender,
                city: action.city,
                description: action.description
            };
        default:
            return state
    }
}