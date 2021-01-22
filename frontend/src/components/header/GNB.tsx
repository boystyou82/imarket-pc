import React from 'react';
import styled from 'styled-components';
import { ASSETS_LOGO } from '../../assets';
import { CONSTANTS_DISPLAY } from '../../utils/constant';
import { StyledBox } from '../Styles';
import Text from '../text/Text';
import HotMenu from './HotMenu';
import TotalCategoryView from './TotalCategoryView';

function GNB({ mainTotalCategoryList, recentlyViewedProductList, mallsSpecialtyResopnse }: any) {
  return (
    <StyledGNB>
      <StyledBox w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} center relative clearfix>
        <TotalCategoryView mainTotalCategoryList={mainTotalCategoryList} />
        <HotMenu recentlyViewedProductList={recentlyViewedProductList} mallsSpecialtyResopnse={mallsSpecialtyResopnse} />
        <StyledIdb float="right">
          <a href="http://idb.imarket.co.kr/main.do?cgrp=cmmn&amp;cmod=gnb_mn_idb&amp;pgid=pcfront" target="_blank" rel="noopener noreferrer">
            <img src={ASSETS_LOGO.IDB_SMALL} alt="iDB" />
            <Text size="1.5rem" ml="0.5rem" weight="medium">
              산업재 이야기
            </Text>
          </a>
        </StyledIdb>
      </StyledBox>
    </StyledGNB>
  );
}

export default GNB;

const StyledGNB = styled.div`
  border-bottom: 0.1rem solid #ccc;

  &:after {
    content: '';
    display: table;
    clear: both;
  }
`;

const StyledIdb = styled(StyledBox)`
  line-height: 4.5rem;

  & a {
    display: inline-block;
    color: #333743;

    & img,
    span {
      vertical-align: middle;
    }

    &:hover {
      text-decoration: underline;
    }
  }
`;
