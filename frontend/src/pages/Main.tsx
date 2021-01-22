import React from 'react';
import styled from 'styled-components/macro';
import { StyledBox } from '../components/Styles';
import { CONSTANTS_DISPLAY } from '../utils/constant';

const DummyMain = styled.div`
  text-align: center;
  height: 72.5rem;
`;

function Main() {
  return (
    <>
      <StyledBox w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} center>
        <DummyMain>
          <img src="/static/dummyMain.png" alt="더미 메인" />
        </DummyMain>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <div>Dummy 메인페이지 입니다.</div>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
      </StyledBox>
    </>
  );
}

export default Main;
