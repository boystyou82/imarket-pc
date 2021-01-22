import React, { useState, useEffect, useRef } from 'react';
import LazyLoad from 'react-lazyload';
import styled from 'styled-components';
import { getExhibitionProductData } from '../../apis/apiCall';
import BannerItem from '../../components/banner/BannerItem';
import ProductItem from '../../components/product/ProductItem';
import { StyledBox } from '../../components/Styles';
import { IProductItem } from '../../models/product';

interface ITempProductListProps {
  dispNo: string;
}

interface IProductList {
  plantBoardBanner: string;
  plantId: string;
  //plantSort: string;
  plantTitle: string;
  eventPlantProductList: IProductItem[];
}

const ROW_PER_PAGE = 20;

function TempProductList({ dispNo }: ITempProductListProps) {
  const [list, setList] = useState<IProductList[]>([]);
  const loader = useRef<HTMLDivElement>(null);
  const paging = useRef(0);

  useEffect(() => {
    const option = {
      root: null,
      rootMargin: '20px',
      threshold: 0
    };

    const apiCallData = async (paging: number) => {
      const data = await getExhibitionProductData(dispNo, paging, ROW_PER_PAGE);
      setList(prevState => (prevState ? [...prevState, ...data.eventPlantList] : prevState));
      if (data.eventPlantList.length === 0 || data.eventPlantList.length < ROW_PER_PAGE) observer.disconnect();
    };

    const handleObserver = (targetList: IntersectionObserverEntry[]) => {
      const target = targetList[0];
      if (target.isIntersecting) {
        paging.current += 1;
        apiCallData(paging.current);
      }
    };

    const observer = new IntersectionObserver(handleObserver, option);

    if (loader.current) {
      observer.observe(loader.current);
    }
  }, [dispNo]);

  return (
    <>
      <StyledProductList>
        {list &&
          list.map((data: IProductList, index: number) => (
            <StyledList key={index}>
              <StyledBox w="59rem" h="20rem">
                <LazyLoad height={200} offset={100}>
                  <BannerItem
                    imageUrl={data.plantBoardBanner ? `http://www.imarket.co.kr/goods_attach${data.plantBoardBanner}` : 'https://dummyimage.com/590x200/ccc/000'}
                    linkUrl={`http://www.imarket.co.kr/plant/PlantMaster.do?_method=Initial&plantId=${data.plantId}&cgrp=cmmn&cmod=gnb_mn_ev&pgid=pcfront`}
                    text={data.plantTitle}
                    target="01" //01:_blank
                  ></BannerItem>
                </LazyLoad>
              </StyledBox>
              <StyledInnerList>
                {data.eventPlantProductList.map((data: IProductItem, index: number) => (
                  <StyledBox key={index} w="13rem" relative>
                    <LazyLoad height={200} offset={100}>
                      <ProductItem {...data} />
                    </LazyLoad>
                  </StyledBox>
                ))}
              </StyledInnerList>
            </StyledList>
          ))}
      </StyledProductList>
      <div ref={loader}></div>
    </>
  );
}

export default TempProductList;

const StyledProductList = styled.ul`
  overflow: hidden;
`;

const StyledList = styled.li`
  float: left;
  border: 0.1rem solid #e5e5e5;
  width: 59rem;
  height: 38rem;
  margin-left: 10rem;
  margin-bottom: 6rem;
  overflow: hidden;
  &:nth-child(odd) {
    margin-left: 0;
  }
`;

const StyledInnerList = styled.div`
  position: relative;
  margin: 0 auto;
  margin-top: -4rem;
  padding-top: 2rem;
  width: 51rem;
  background: #fff;
  overflow: hidden;
  & > div {
    float: left;
    margin-left: 5rem;
  }

  & > div:first-child {
    margin-left: 0;
  }
`;
