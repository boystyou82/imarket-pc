import React from 'react';
import styled from 'styled-components/macro';

import useCurrentDate from '../../hooks/useCurrentDate';

import Text from '../../components/text/Text';
import ExhibitionMainlyIconPack from './ExhibitionMainlyIconPack';
import { StyledBox } from '../../components/Styles';
import { CONSTANTS_DISPLAY } from '../../utils/constant';

function HeaderContainer() {
  const currentMonth = useCurrentDate();

  return (
    <StyledHeader>
      <StyledBox relative w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} h="18rem" center>
        <StyledHeaderTitle size="5rem" weight="medium">
          {currentMonth}월 이벤트&혜택
        </StyledHeaderTitle>
        <StyledBox absolute t="4rem" r="3rem" w="65rem">
          <ExhibitionMainlyIconPack type="coupon" />
          <ExhibitionMainlyIconPack type="recommendPoint" />
          <ExhibitionMainlyIconPack type="cokdeal" />
          <ExhibitionMainlyIconPack type="simplePostpay" />
          <ExhibitionMainlyIconPack type="publishTaxbill" />
          <ExhibitionMainlyIconPack type="fastDelivery" />
        </StyledBox>
      </StyledBox>
    </StyledHeader>
  );
}

export default HeaderContainer;

const StyledHeader = styled.div`
  background: url('http://image.imarket.co.kr/contents/image/registered/20201026/1603678707831813000.jpg');
`;

const StyledHeaderTitle = styled(Text)`
  position: absolute;
  top: 50%;
  left: 5rem;
  margin-top: -2.5rem;
  color: #232c47;
`;
