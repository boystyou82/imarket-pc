import React from 'react';
import styled from 'styled-components';
import { StyledBox } from '../Styles';
import { ASSETS_BUTTON } from '../../assets';
import { CONSTANTS_DISPLAY } from '../../utils/constant';
import ProductItem from '../product/ProductItem';
import Button from '../Button';
import { NextviewArrow, PreviewArrow } from '../Slider';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';

function LatestProductList({ recentlyViewedProductList }: any) {
  const settings = {
    dots: false,
    infinite: false,
    speed: 500,
    slidesToShow: 7,
    slidesToScroll: 7,
    nextArrow: <NextviewArrow />,
    prevArrow: <PreviewArrow />
  };

  return (
    <>
      {recentlyViewedProductList.length === 0 ? (
        <StyledLayerBoxEmpty>
          <p>최근 본 상품이 없습니다.</p>
        </StyledLayerBoxEmpty>
      ) : (
        <StyledLayerBoxLatestProductList>
          <StyledLayerBoxTitle>최근 본 상품</StyledLayerBoxTitle>
          <StyledBoxSlider mt="2.5rem" center>
            <Slider {...settings}>
              {recentlyViewedProductList.map((data: any, index: number) => (
                <StyledBox key={index} w="15rem" float="left" relative>
                  <ProductItem {...data} prdImg={data.mainImg} vatPriceShow />
                  <StyledButtonGroup>
                    <Button size="s" theme="red" onClick={() => alert('장바구니')}>
                      장바구니
                    </Button>
                    <Button size="s" theme="white" onClick={() => alert('삭제')}>
                      삭제
                    </Button>
                  </StyledButtonGroup>
                </StyledBox>
              ))}
            </Slider>
          </StyledBoxSlider>
        </StyledLayerBoxLatestProductList>
      )}
    </>
  );
}

export default LatestProductList;

const StyledButtonGroup = styled(StyledBox)`
  padding-bottom: 5px;

  & button {
    vertical-align: middle;
  }

  & button:nth-child(2) {
    margin-left: 0.8rem;
  }
`;

const StyledBoxSlider = styled(StyledBox)`
  width: calc(${CONSTANTS_DISPLAY.PC_BASE_WIDTH} - 6rem);

  .slick-slide > div {
    margin: 0 1.25rem;
  }
  .slick-list {
    margin: 0 -1.25rem;
  }

  .slick-arrow {
    top: 20%;
  }

  &:hover {
    .slick-arrow {
      &:not(.slick-disabled) {
        background-color: rgba(0, 0, 0, 0.2);
      }

      &:after {
        background-image: url(${ASSETS_BUTTON.ARROW_ACTIVE});
      }
    }
  }
`;

const StyledLayerBoxEmpty = styled.div`
  position: absolute;
  left: 0;
  padding-bottom: 2.6rem;
  border: 0.1rem solid #434656;
  background: #fff;
  width: 53rem;
  height: 18.2rem;
  z-index: 1;

  & p {
    margin-top: 85px;
    text-align: center;
    font-size: 1.6rem;
    color: #777;
  }
`;

const StyledLayerBoxLatestProductList = styled.div`
  position: absolute;
  left: 0;
  width: 100%;
  height: 31.5rem;
  border: 0.1rem solid #434656;
  background: #fff;
  overflow: hidden;
  z-index: 1;
`;

const StyledLayerBoxTitle = styled.p`
  margin: 2.3rem 0 0 4.3rem;
  font-size: 2rem;
  color: #333743;
`;
