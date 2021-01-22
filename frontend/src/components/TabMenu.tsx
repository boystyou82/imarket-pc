import React from 'react';
import styled from 'styled-components/macro';
import { ASSETS_INDICATOR } from '../assets';
import { CONSTANTS_DISPLAY } from '../utils/constant';
import { NavLink } from 'react-router-dom';
import Sticky from 'react-sticky-el';

interface ITabMenuProps {
  columnMaxCount: number;
  menuData: [];
  defaultBgColor?: string;
  activeBgColor?: string;
  baseUrl: string;
}

function TabMenu({ columnMaxCount, menuData, defaultBgColor, activeBgColor, baseUrl }: ITabMenuProps) {
  /* 탭메뉴 데이터 가공 : 순차적으로 데이터 저장 후 남은 칼럼수 만큼 빈 li태그 추가 */
  const menuDataSetup = (arr: any, n: number) => {
    let len = arr.length;
    let count = Math.ceil(len / n) * n;
    let tmp = [];

    for (let i = 0; i < count; i++) {
      if (arr[i])
        tmp.push(
          <li key={i}>
            <NavLink exact={arr[i].dispNo === null} to={arr[i].dispNo === null ? `${baseUrl}/all` : `${baseUrl}/${arr[i].dispNo}`}>
              {arr[i].dispNm} <span>{arr[i].categoryCnt}</span>
            </NavLink>
          </li>
        );
      else tmp.push(<li key={i}></li>);
    }

    return tmp;
  };

  const renderMenu = menuDataSetup(menuData, columnMaxCount);

  return (
    <Sticky stickyStyle={{ zIndex: 1, boxSizing: 'content-box' }}>
      <StyledTabMenu columnMaxCount={columnMaxCount}>{renderMenu}</StyledTabMenu>
    </Sticky>
  );
}

export default TabMenu;

const StyledTabMenu = styled.ul<{ columnMaxCount: number }>`
  width: ${CONSTANTS_DISPLAY.PC_BASE_WIDTH};
  overflow: hidden;
  border-top: 0.3rem solid #d4382e;
  border-bottom: 0.1rem solid #333;
  border-left: 0.1rem solid #ccc;
  border-right: 0.1rem solid #ccc;
  background-color: #fff;

  & li {
    float: left;
    width: calc(100% / ${props => props.columnMaxCount});
    height: 4.3rem;
    border-left: 0.1rem solid #ccc;
    border-top: 0.1rem solid #ccc;
  }

  & li:nth-child(-n + ${props => props.columnMaxCount}) {
    border-top: none;
  }

  & li:nth-child(${props => props.columnMaxCount}n + 1) {
    border-left: none;
  }

  & li > a {
    display: block;
    padding-left: 2rem;
    height: 4.3rem;
    line-height: 4.3rem;
    background: url(${ASSETS_INDICATOR.ARROW_S_RIGHT_GRAY}) no-repeat calc(100% - 2rem) center;
    color: #333;
    font-weight: 500;

    & > span {
      margin-left: 0.5rem;
      font-weight: 400;
      color: #999;
    }
  }

  & li > a:hover {
    background: url(${ASSETS_INDICATOR.ARROW_S_RIGHT_WHITE}) no-repeat calc(100% - 2rem) center;
    background-color: #d4382e;
    color: #fff;

    & > span {
      color: #fff;
    }
  }

  & li > a.active {
    background: url(${ASSETS_INDICATOR.ARROW_S_RIGHT_WHITE}) no-repeat calc(100% - 2rem) center;
    background-color: #d4382e;
    color: #fff;

    & > span {
      color: #fff;
    }
  }
`;
