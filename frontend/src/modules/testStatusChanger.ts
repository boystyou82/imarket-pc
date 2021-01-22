import { IChannel } from '../models/header';

const LOGIN = 'testStatusChanger/LOGIN' as const;
const LOGOUT = 'testStatusChanger/LOGOUT' as const;
const CHANNEL_CHANGER = 'testStatusChanger/CHANNEL_CHANGER' as const;

export const login = () => ({ type: LOGIN });
export const logout = () => ({ type: LOGOUT });
export const channelChanger = (value: string | undefined) => ({ type: CHANNEL_CHANGER, value });

type StatusAction = ReturnType<typeof login> | ReturnType<typeof logout> | ReturnType<typeof channelChanger>;

interface IStatusState extends IChannel {
  loginStatus: boolean;
}

const initialState: IStatusState = {
  loginStatus: false,
  channel: undefined
};

export default function testStatusChanger(state = initialState, action: StatusAction) {
  switch (action.type) {
    case LOGIN:
      return {
        ...state,
        loginStatus: true
      };
    case LOGOUT:
      return {
        ...state,
        loginStatus: false
      };
    case CHANNEL_CHANGER:
      return {
        ...state,
        channel: action.value
      };
    default:
      return state;
  }
}
