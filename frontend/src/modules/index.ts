import { combineReducers } from 'redux';
import testStatusChanger from './testStatusChanger';

const rootReducer = combineReducers({
  testStatusChanger
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
