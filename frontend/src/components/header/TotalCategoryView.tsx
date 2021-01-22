import React, { useState } from 'react';
import styled, { css } from 'styled-components/macro';
import { ASSETS_BUTTON, ASSETS_ICON, ASSETS_INDICATOR } from '../../assets';
import { CONSTANTS_DISPLAY, CONSTANTS_EXCLUDE_CATEGORY } from '../../utils/constant';

function TotalCategoryView({ mainTotalCategoryList }: any) {
  const [show, setShow] = useState(false);

  const handleCategoryView = () => {
    setShow(!show);
  };

  const setupTotalCategory = mainTotalCategoryList
    .filter((keyData: any) => keyData.levelNo === 1 && !CONSTANTS_EXCLUDE_CATEGORY.some(data => data.DISP_NO === keyData.dispNo))
    .map((keyData: any) => mainTotalCategoryList.filter((data: any) => (data.levelNo === 1 && data.dispNo === keyData.dispNo) || (data.levelNo === 2 && data.highDispNo === keyData.dispNo)));

  const setupSpmall = mainTotalCategoryList
    .filter((keyData: any) => keyData.levelNo === 1 && CONSTANTS_EXCLUDE_CATEGORY.some(data => data.DISP_NO === keyData.dispNo))
    .map((keyData: any) =>
      CONSTANTS_EXCLUDE_CATEGORY.find(data => data.DISP_NO === keyData.dispNo)?.DISP_NAME !== null
        ? Object.assign(keyData, { dispNm: CONSTANTS_EXCLUDE_CATEGORY.find(data => data.DISP_NO === keyData.dispNo)?.DISP_NAME })
        : keyData
    );

  const renderCategoryList = (
    <StyledCategoryBox>
      {setupTotalCategory.map(
        (data: any, index: number) =>
          data.length !== 1 && (
            <StyledCategoryBlock key={index}>
              {data.map(
                (data: any, index: number) =>
                  data.levelNo === 1 && (
                    <StyledCategoryTitle key={index}>
                      <a href="a">{data.dispNm}</a>
                    </StyledCategoryTitle>
                  )
              )}
              <StyledCategoryList>
                {data.map(
                  (data: any, index: number) =>
                    data.levelNo === 2 &&
                    index < 10 && (
                      <StyledCategoryListItem key={index}>
                        <a href="a">{data.dispNm}</a>
                      </StyledCategoryListItem>
                    )
                )}
              </StyledCategoryList>
            </StyledCategoryBlock>
          )
      )}
    </StyledCategoryBox>
  );

  const renderSpmallList = (
    <StyledSpmallBox>
      <StyledSpmallTitle>전문몰</StyledSpmallTitle>
      <StyledSpmallList>
        {setupSpmall.map((data: any, index: number) => (
          <StyledSpmallListItem key={index}>
            <a href="a">{data.dispNm}</a>
          </StyledSpmallListItem>
        ))}
      </StyledSpmallList>
    </StyledSpmallBox>
  );

  return (
    <StyledTotalCategoryView onMouseLeave={() => setShow(false)}>
      <StyledButton onClick={() => handleCategoryView()} active={show}>
        <StyledHambuger />
        <span>전체 카테고리</span>
        <StyledIndicator active={show} />
      </StyledButton>
      {show && (
        <StyledLayer>
          {renderCategoryList}
          {renderSpmallList}
          <StyledCloseButton onClick={() => handleCategoryView()}>
            <img src={ASSETS_BUTTON.CLOSE_BLACK_LINE} alt="전체 카테고리 닫기 버튼"></img>
          </StyledCloseButton>
        </StyledLayer>
      )}
    </StyledTotalCategoryView>
  );
}

export default TotalCategoryView;

const StyledHambuger = styled.span`
  display: inline-block;
  width: 1.5rem;
  height: 1.2rem;
  background: url(${ASSETS_ICON.HAMBUGER});
`;

const StyledIndicator = styled.span<{ active: boolean }>`
  display: inline-block;
  width: 1.4rem;
  height: 0.7rem;
  background: url(${props => (props.active ? ASSETS_INDICATOR.ARROW_M_UP_BLACK : ASSETS_INDICATOR.ARROW_M_DOWN_BLACK)});
`;

const StyledButton = styled.button<{ active: boolean }>`
  border-left: 0.1rem solid #ccc;
  border-right: 0.1rem solid #ccc;
  width: 18rem;
  height: 4.5rem;
  ${({ active }) =>
    active &&
    css`
      margin-top: -0.2rem;
      border: 0.2rem solid #000;
      border-bottom: 0;
      height: 4.7rem;
    `}
  ${StyledHambuger} {
    margin-right: 1.7rem;
    vertical-align: middle;
  }

  ${StyledIndicator} {
    margin-left: 1.7rem;
    vertical-align: middle;
  }
`;

const StyledLayer = styled.div`
  &:before {
    content: '';
    position: absolute;
    z-index: 1;
    top: -0.2rem;
    left: 0;
    width: 17.6rem;
    height: 0.2rem;
    background: #fff;
  }

  position: absolute;
  top: 4.5rem;
  padding: 0 1.8rem 2rem 1.8rem;
  width: ${CONSTANTS_DISPLAY.PC_BASE_WIDTH};
  border: 0.2rem solid #333;
  background: #fff;
  z-index: 1;
`;

const StyledTotalCategoryView = styled.div`
  float: left;
  position: relative;
`;

const StyledCategoryBlock = styled.div`
  float: left;
  margin-top: 1.2rem;
  width: 15.5rem;
`;

const StyledCategoryTitle = styled.p`
  height: 2.7rem;
  line-height: 2.5rem;
  border-bottom: 0.1rem solid #ddd;
  margin-bottom: 0.4rem;
  box-sizing: border-box;

  & > a {
    font-size: 1.3rem;
    font-weight: 500;
    color: #333;

    &:hover {
      text-decoration: underline;
    }
  }
`;

const StyledCategoryList = styled.ul`
  height: 18.9rem;
  overflow: hidden;
`;

const StyledCategoryListItem = styled.li`
  height: 2rem;
  line-height: 2rem;
  overflow: hidden;

  & a {
    font-size: 1.2rem;
    color: #666;

    &:hover {
      text-decoration: underline;
    }
  }
`;

const StyledCategoryBox = styled.div`
  &:after {
    content: '';
    display: table;
    clear: both;
  }
`;

const StyledSpmallBox = styled.div`
  &:after {
    content: '';
    display: table;
    clear: both;
  }
`;

const StyledSpmallTitle = styled.div`
  position: relative;
  float: left;
  width: 14.5rem;
  height: 4rem;
  background: #cfd7ec;
  text-align: center;
  line-height: 4rem;
  font-size: 1.4rem;
  font-weight: 500;
  color: #333645;

  &:after {
    content: '';
    position: absolute;
    top: 50%;
    left: 100%;
    margin-top: -0.5rem;
    border-width: 0.5rem;
    border-style: solid;
    border-color: transparent transparent transparent #cfd7ec;
  }
`;

const StyledSpmallList = styled.ul`
  display: table;
  table-layout: fixed;
  float: left;
  width: 104rem;
  text-align: center;
  margin-left: 1.3rem;
`;

const StyledSpmallListItem = styled.li`
  display: table-cell;
  height: 4rem;
  border: 0.1rem solid #ddd;
  border-left: none;
  line-height: 4rem;
  background: #f7f7f7;
  cursor: pointer;

  &:first-child {
    border-left: 0.1rem solid #ddd;
  }

  &:hover {
    background: #fff;
    border: 0.1rem solid #333;
  }

  &:not(:first-child):hover a {
    margin-left: -1px;
  }

  & a {
    display: inline-block;
    width: 100%;
    color: #333743;
  }
`;

const StyledCloseButton = styled.button`
  position: absolute;
  bottom: 2.4rem;
  right: 1.8rem;
`;
