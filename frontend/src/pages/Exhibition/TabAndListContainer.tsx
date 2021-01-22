import React, { memo } from 'react';
import styled from 'styled-components/macro';

import ListTitle from '../../components/title/ListTitle';
import TabMenu from '../../components/TabMenu';
import { StyledBox } from '../../components/Styles';
import { useAsync } from 'react-async';

import { getExhibitionCategoryData } from '../../apis/apiCall';
import ProductList from './ProductList';
import { useRouteMatch } from 'react-router-dom';

interface IParams {
  exhibitionId: string;
}

function TabAndListContainer() {
  const { params } = useRouteMatch<IParams>();
  const { data } = useAsync(getExhibitionCategoryData);

  function setMenuData(data: any) {
    let tempArray = [];

    if (data) {
      tempArray = data.plantEventCategoryList;
      tempArray[0].dispNo = 'all';
    }
    return tempArray;
  }

  const tabMenuData = setMenuData(data);

  return (
    <StyledProductListContainer>
      <StyledBox mb="2rem">{<TabMenu columnMaxCount={6} baseUrl="/exhibition" menuData={tabMenuData} />}</StyledBox>

      <StyledBox mb="2rem">
        <ListTitle size="2.6rem" weight="medium">
          추천 기획전
        </ListTitle>
      </StyledBox>

      {tabMenuData.map((data: any, index: number) => (
        <StyleShowBox key={index} isShow={data.dispNo === params.exhibitionId}>
          <ProductList dispNo={data.dispNo} />
        </StyleShowBox>
      ))}
    </StyledProductListContainer>
  );
}

export default memo(TabAndListContainer);

const StyledProductListContainer = styled.div`
  margin-bottom: 10rem;
`;

const StyleShowBox = styled.div<{ isShow: boolean }>`
  display: ${props => (props.isShow ? 'block' : 'none')};
`;
