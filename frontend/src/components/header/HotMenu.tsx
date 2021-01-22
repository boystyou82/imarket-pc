import React from 'react';
import { Link } from 'react-router-dom';
import styled, { css } from 'styled-components';
import { ASSETS_INDICATOR } from '../../assets';
import TooltipLayer from '../layer/TooltipLayer';
import LatestProductList from './LatestProductList';
import SpecialMallList from './SpecialMallList';

function HotMenu({ recentlyViewedProductList, mallsSpecialtyResopnse }: any) {
  return (
    <StyledHotMenuList>
      <li>
        <TooltipLayer>
          <StyledLayerSwitch>
            최근 본 상품 <span />
          </StyledLayerSwitch>
          <LatestProductList recentlyViewedProductList={recentlyViewedProductList} />
        </TooltipLayer>
      </li>
      <li>
        <a href="a">자주 구매 상품</a>
      </li>
      <li>
        <a href="a">관심상품</a>
      </li>
      <li>
        <a href="a">주문배송조회</a>
      </li>
      <li>
        <Link to="/exhibition">이벤트/기획전</Link>
      </li>
      <li>
        <TooltipLayer>
          <StyledLayerSwitch>
            전문몰 <span />
          </StyledLayerSwitch>
          <SpecialMallList mallsSpecialtyResopnse={mallsSpecialtyResopnse} />
        </TooltipLayer>
      </li>
    </StyledHotMenuList>
  );
}

export default HotMenu;

const StyledHotMenuList = styled.ul`
  float: left;

  & > li {
    float: left;
    letter-spacing: -0.1rem;
  }

  & > li > a {
    display: inline-block;
  }

  & > li:nth-child(1) {
    margin-left: 5.5rem;
  }

  & > li:nth-child(2) {
    margin-left: 6.7rem;
  }

  & > li:nth-child(3) {
    margin-left: 5.5rem;
  }

  & > li:nth-child(4) {
    margin-left: 5.5rem;
  }

  & > li:nth-child(5) {
    margin-left: 5.5rem;
  }

  & > li:nth-child(6) {
    margin-left: 5.5rem;
  }

  & > li > a {
    height: 4.5rem;
    line-height: 4.5rem;
  }

  & > li > a:hover {
    color: #d71d1e;
  }
`;

const StyledLayerSwitch = styled.button<{ active?: boolean }>`
  position: relative;
  line-height: 4.5rem;

  & span {
    display: inline-block;
    margin-left: 0.4rem;
    width: 0.8rem;
    height: 0.4rem;
    background: url(${ASSETS_INDICATOR.ARROW_S_DOWN_DARKGRAY});
    vertical-align: middle;
  }

  ${({ active }) =>
    active &&
    css`
      & {
        color: #d71d1e;

        & span {
          background: url(${ASSETS_INDICATOR.ARROW_S_UP_RED});
        }
      }

      &:after {
        content: '';
        position: absolute;
        bottom: -0.1rem;
        left: 50%;
        margin-left: -0.5rem;
        right: 2rem;
        width: 1.1rem;
        height: 0.6rem;
        background: url(${ASSETS_INDICATOR.HEEL_S});
        z-index: 2;
      }
    `}
`;
