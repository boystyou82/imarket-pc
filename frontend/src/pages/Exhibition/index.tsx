import React from 'react';
import HeaderContainer from './HeaderContainer';
import BannerContainer from './BannerContainer';
import { StyledBox } from '../../components/Styles';
import { CONSTANTS_DISPLAY } from '../../utils/constant';
import TabAndListContainer from './TabAndListContainer';

function Exhibition() {
  return (
    <>
      <HeaderContainer />
      <StyledBox w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} pt="4rem" center>
        <BannerContainer />
        <TabAndListContainer />
      </StyledBox>
    </>
  );
}

export default Exhibition;
