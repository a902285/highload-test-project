import {combineReducers} from 'redux';
import {authentication} from './auth.reducer';
import {account} from './account.reducer';
import {signup} from "./signup.reducer";

const rootReducer = combineReducers({
    authentication,
    account,
    signup
});
export default rootReducer;