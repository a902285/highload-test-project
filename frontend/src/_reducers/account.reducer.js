const initialState = {
    anchor: 'left',
    account: [],
    open: false,
    id: '',
    firstName: '',
    lastName: '',
    age: '',
    gender: ''
};

export function account(state = initialState, action) {
    switch (action.type) {
        case 'FETECHED_ALL_ACCOUNT':
            return {
                ...state,
                account: action.account
            };
        case 'ACCOUNT_DETAIL':
            return {
                ...state,
                id: action.id,
                firstName: action.firstName,
                lastName: action.lastName,
                age: action.age,
                gender: action.gender
            };
        case "USER_UPDATED":
            return state;
        case "HANDLE_ON_CHANGE":
            return {
                ...state,
                [action.props]: action.value
            };
        default:
            return state
    }
}