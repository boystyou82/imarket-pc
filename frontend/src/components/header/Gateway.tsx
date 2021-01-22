import React from 'react';
import { useSelector } from 'react-redux';
import styled, { css } from 'styled-components/macro';
import { ASSETS_ICON, ASSETS_INDICATOR } from '../../assets';
import { RootState } from '../../modules';
import { CONSTANTS_DISPLAY } from '../../utils/constant';
import TooltipLayer from '../layer/TooltipLayer';
import TooltipSubMenuLayer from '../layer/TooltipSubMenuLayer';
import { StyledBox, StyledIncludeBackgroundIcon } from '../Styles';

function Gateway() {
  const loginStatus = useSelector((state: RootState) => state.testStatusChanger.loginStatus);

  const renderInterparkService = (
    <StyledBox float="left">
      <StyledInterparkServiceList>
        {dataInterparkService.data.map((data, index) => (
          <StyledInterparkServiceListItem key={index} current={data.linkName === dataInterparkService.seletedDataName ? true : false}>
            {loginStatus && data.type !== 'imarket' ? (
              <a href={`/gate/SSOGateway.do?_method=ssoExtern&type=${data.type}`} target={data.target}>
                {data.linkName}
              </a>
            ) : (
              <a href={data.linkUrl} target={data.target}>
                {data.linkName}
              </a>
            )}
          </StyledInterparkServiceListItem>
        ))}
      </StyledInterparkServiceList>
    </StyledBox>
  );

  const renderPersonalGatewayMenu = (
    <StyledBox float="right">
      <SPersonalGatewayMenuList>
        {loginStatus ? (
          <>
            <li>
              <a href="a">로그아웃</a>
            </li>
            <li>
              <a href="a">회원정보변경</a>
            </li>
          </>
        ) : (
          <>
            <li>
              <a href="A">로그인</a>
            </li>
            <li>
              <a href="a">회원가입</a>
            </li>
          </>
        )}
        <li>
          <a href="a">
            <StyledIncludeBackgroundIcon icon={ASSETS_ICON.FAVORITE} iconPosition="0 0.3rem" paddingValue="1.4rem" paddingPosition="left">
              즐겨찾기
            </StyledIncludeBackgroundIcon>
          </a>
        </li>
        <li>
          <a href="A">장바구니</a>
        </li>
        <li>
          <a href="a">마이페이지</a>
        </li>
        <li>
          <TooltipLayer>
            <StyledLayerAnchor>고객센터</StyledLayerAnchor>
            <TooltipSubMenuLayer layerData={dataPersonalGatewayMenu} t="3.3rem" />
          </TooltipLayer>
        </li>
      </SPersonalGatewayMenuList>
    </StyledBox>
  );

  return (
    <StyledGateway>
      <StyledBox w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} center>
        {renderInterparkService}
        {renderPersonalGatewayMenu}
      </StyledBox>
    </StyledGateway>
  );
}

export default Gateway;

const StyledGateway = styled.div`
  height: 3.4rem;
  background: #f4f6f9;
  border-bottom: 0.1rem solid #e9edf3;
  font-weight: 300;
  font-size: 1.2rem;
  color: #666;

  & > ${StyledBox} {
    &:after {
      content: '';
      display: table;
      clear: both;
    }
  }
`;

const StyledInterparkServiceList = styled.ul`
  border-right: 0.1rem solid #e9edf3;

  &:after {
    content: '';
    clear: both;
    display: table;
  }
`;

const StyledInterparkServiceListItem = styled.li<{ current: boolean }>`
  float: left;
  width: 6rem;
  border-left: 0.1rem solid #e9edf3;
  box-sizing: content-box;

  & a {
    display: block;
    padding-top: 0.9rem;
    height: 2.4rem;
    text-align: center;
    font-size: 1.2rem;
    line-height: 1.6rem;
    color: #777;
    transition: all ease 0.3s;

    &:hover {
      ${({ current }) => (current ? `background: #fff` : `background: #e4e9f0`)};
    }

    ${({ current }) =>
      current &&
      css`
        background: #fff;
        height: 2.5rem;
        color: #333;
      `}
  }
`;

const StyledAnchorStyle = css`
  display: block;
  padding-top: 0.9rem;
  height: 2.5rem;
  font-size: 1.2rem;
  line-height: 1.6rem;
  text-align: center;
  color: #777;

  &:hover {
    text-decoration: underline;
  }
`;

const StyledLayerAnchor = styled.a<{ active?: boolean }>`
  ${StyledAnchorStyle}

  padding-right: 1.7rem;

  &:hover {
    text-decoration: underline;
  }

  &:after {
    content: '';
    position: absolute;
    top: 1.1rem;
    right: 0;
    width: 1.3rem;
    height: 1.3rem;
    background: ${props => (props.active ? `url(${ASSETS_INDICATOR.ARROW_M_BORDER}) no-repeat 0 0;` : `url(${ASSETS_INDICATOR.ARROW_M_BORDER}) no-repeat 0 -1.2rem;`)};
  }
`;

const SPersonalGatewayMenuList = styled.ul`
  & > li {
    float: left;
    position: relative;
    padding: 0 0.8rem;
    height: 3.3rem;

    & > a {
      ${StyledAnchorStyle}
    }

    &:last-child {
      padding-right: 0;
    }
  }
`;

//인터파크 서비스 메뉴 링크정보
const dataInterparkService = {
  seletedDataName: '아이마켓',
  data: [
    {
      type: 'interpark',
      linkUrl: 'http://www.interpark.com/malls/index.html?cgrp=cmmn&amp;cmod=gnb_top_fmly&amp;pgid=pcfront',
      linkName: '인터파크',
      target: '_blank'
    },
    {
      type: 'book',
      linkUrl: 'http://book.interpark.com/bookPark/html/book.html?cgrp=cmmn&amp;cmod=gnb_top_fmly&amp;pgid=pcfront',
      linkName: '도서',
      target: '_blank'
    },
    {
      type: 'ticket',
      linkUrl: 'http://ticket.interpark.com?cgrp=cmmn&amp;cmod=gnb_top_fmly&amp;pgid=pcfront',
      linkName: '티켓',
      target: '_blank'
    },
    {
      type: 'tour',
      linkUrl: 'http://tour.interpark.com?cgrp=cmmn&amp;cmod=gnb_top_fmly&amp;pgid=pcfront',
      linkName: '투어',
      target: '_blank'
    },
    {
      type: 'imarket',
      linkUrl: 'http://www.imarket.co.kr?cgrp=cmmn&amp;cmod=gnb_top_fmly&amp;pgid=pcfront',
      linkName: '아이마켓',
      target: '_self'
    }
  ]
};

//개인메뉴 링크정보
const dataPersonalGatewayMenu = [
  {
    name: '1:1문의',
    link: '#',
    title: true
  },
  {
    name: 'FAQ',
    link: null,
    title: true
  },
  {
    name: '주문/결제 안내',
    link: '#',
    title: false
  },
  {
    name: '취소/반품/교환 안내',
    link: '#',
    title: false
  },
  {
    name: '서비스안내',
    link: null,
    title: true
  },
  {
    name: '쇼핑가이드',
    link: '#',
    title: false
  },
  {
    name: '간편후불',
    link: '#',
    title: false
  },
  {
    name: '추천프로그램',
    link: '#',
    title: false
  },
  {
    name: '오늘출고보장제',
    link: '#',
    title: false
  }
];
