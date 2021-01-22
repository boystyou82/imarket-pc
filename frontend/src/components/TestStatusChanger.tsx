import React from 'react';
import { StyledBox } from './Styles';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../modules';
import { login, logout, channelChanger } from '../modules/testStatusChanger';

function TestStatusChanger() {
  const loginStatus = useSelector((state: RootState) => state.testStatusChanger.loginStatus);
  const channel = useSelector((state: RootState) => state.testStatusChanger.channel);
  const dispatch = useDispatch();

  const onLogin = () => {
    dispatch(login());
  };

  const onLogout = () => {
    dispatch(logout());
  };

  const onChannelChanger = (value: string | undefined) => {
    dispatch(channelChanger(value));
  };
  return (
    <StyledBox w="20rem" bg="rgba(255,255,150,0.8)" fixed t="0" l="0">
      <br />
      [!! 테스트용 컨트롤러:redux !!]
      <br />
      <br />
      <div>로그인 상태값 : {loginStatus ? 'true' : 'false'}</div>
      <br />
      <div>
        <input type="radio" id="loginStatus1" name="loginStatus" onChange={onLogin} checked={loginStatus === true} />
        <label htmlFor="loginStatus1"> 로그인</label>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" id="loginStatus2" name="loginStatus" onChange={onLogout} checked={loginStatus === false} />
        <label htmlFor="loginStatus2"> 로그아웃</label>
      </div>
      <br />
      <br />
      <div>채널 상태값 : {channel} </div>
      <br />
      <div>
        <input type="radio" id="channelStatus4" name="channelStatus" onChange={() => onChannelChanger('sdc')} checked={channel === 'sdc'} />
        <label htmlFor="channelStatus4"> SDC</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" id="channelStatus3" name="channelStatus" onChange={() => onChannelChanger('medical')} checked={channel === 'medical'} />
        <label htmlFor="channelStatus3"> 의료몰</label>
        <br />
        <input type="radio" id="channelStatus5" name="channelStatus" onChange={() => onChannelChanger('grainger')} checked={channel === 'grainger'} />
        <label htmlFor="channelStatus5"> 그레인저</label>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" id="channelStatus2" name="channelStatus" onChange={() => onChannelChanger('digital')} checked={channel === 'digital'} />
        <label htmlFor="channelStatus2"> 디지털관</label> <br />
        <input type="radio" id="channelStatus1" name="channelStatus" onChange={() => onChannelChanger(undefined)} checked={channel === undefined} />
        <label htmlFor="channelStatus1"> 기본</label>
      </div>
    </StyledBox>
  );
}

export default TestStatusChanger;
