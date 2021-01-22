import React, { useState } from 'react';
import styled from 'styled-components';
import { CONSTANTS_EXCLUDE_CATEGORY, CONSTANTS_URL } from '../../utils/constant';
import BannerItem from '../banner/BannerItem';
import shuffle from 'shuffle-array';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import { NextviewArrow, PreviewArrow } from '../Slider';
import { ASSETS_BUTTON } from '../../assets';

function SpecialMallList({ mallsSpecialtyResopnse }: any) {
  const setupSpecialtyMallCategory = mallsSpecialtyResopnse.map((keyData: any) =>
    keyData.specialtyMallCategoryList.map((data: any, index: number) =>
      index === 0
        ? Object.assign(data, {
            dispNm:
              CONSTANTS_EXCLUDE_CATEGORY.find(data => data.DISP_NO === keyData.specialtyMallCategoryList[0].dispNo)?.DISP_NAME === null
                ? keyData.specialtyMallCategoryList[0].dispNm
                : CONSTANTS_EXCLUDE_CATEGORY.find(data => data.DISP_NO === keyData.specialtyMallCategoryList[0].dispNo)?.DISP_NAME
          })
        : data
    )
  );

  const initSpecialtyMallBanner = mallsSpecialtyResopnse.map((keyData: any) => keyData.specialityMallList).flat();
  const [bannerList, setBannerList] = useState(shuffle(initSpecialtyMallBanner, { copy: true }));

  const settings = {
    dots: false,
    infinite: false,
    speed: 200,
    autoplay: true,
    autoplaySpeed: 2000,
    slidesToShow: 1,
    slidesToScroll: 1,
    nextArrow: <NextviewArrow />,
    prevArrow: <PreviewArrow />
  };

  const renderRollingBanner = (
    <StyledRollingBanner>
      <Slider {...settings}>
        {bannerList.map((data: any, index: number) => (
          <BannerItem key={index} text={data.dispNm} imageUrl={`${CONSTANTS_URL.IMAGE_DEVELOP_URL}${data.dispImg1}`} linkUrl={data.linkUrl} target={data.winActTp} />
        ))}
      </Slider>
    </StyledRollingBanner>
  );

  const handleBannerShuffle = () => {
    setBannerList(shuffle(bannerList, { copy: true }));
  };

  return (
    <StyledLayerBoxSpecialMallList>
      {setupSpecialtyMallCategory.map((data: any, index: number) => (
        <StyledSpecialMallBox key={index}>
          <StyledTitle>
            <a href="a">{data[0].dispNm}</a>
          </StyledTitle>
          <StyledList>
            {data.map(
              (data: any, index: number) =>
                index !== 0 &&
                index < 10 && (
                  <StyledListItem key={index}>
                    <a href="a">{data.dispNm}</a>
                  </StyledListItem>
                )
            )}
          </StyledList>
        </StyledSpecialMallBox>
      ))}
      <StyledRollingBox>
        {renderRollingBanner}
        <StyledButton onClick={() => handleBannerShuffle()}>
          <img src={ASSETS_BUTTON.ROTATE} alt="배너 무작위 섞기 버튼" />
        </StyledButton>
      </StyledRollingBox>
    </StyledLayerBoxSpecialMallList>
  );
}

export default SpecialMallList;

const StyledRollingBanner = styled.div`
  margin: 0 auto;
  margin-top: 1.5rem;
  width: 20rem;

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

const StyledButton = styled.button`
  cursor: pointer;
  position: absolute;
  top: 1.5rem;
  right: 2.6rem;
  width: 1.9rem;
  height: 1.9rem;
  border: 1px solid #e1e1e1;
  font-size: 0;

  img {
    display: inline-block;
  }

  &:hover img {
    animation: spin 0.5s 1 linear;
  }

  @keyframes spin {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(360deg);
    }
  }
`;

const StyledRollingBox = styled.div`
  float: right;
  width: 30rem;
  height: 28rem;
  border-left: 0.1rem solid #ddd;
`;

const StyledLayerBoxSpecialMallList = styled.div`
  position: absolute;
  left: 0;
  padding: 2rem 0 1.5rem 2.5rem;
  width: 100%;
  border: 0.1rem solid #434656;
  background: #fff;
  overflow: hidden;
  z-index: 1;

  &:after {
    content: '';
    display: table;
    clear: both;
  }
`;

const StyledSpecialMallBox = styled.div`
  float: left;
  margin: 0 2.5rem;
  width: 184px;
`;

const StyledTitle = styled.p`
  height: 3.9rem;
  line-height: 3.9rem;
  border-bottom: 1px solid #ddd;
  font-size: 20px;
  color: #666;

  & > a:hover {
    color: #333;
    text-decoration: underline;
  }
`;

const StyledList = styled.ul`
  padding-top: 0.4rem;
`;

const StyledListItem = styled.li`
  line-height: 2.4rem;

  & > a {
    font-size: 13px;
    color: #333;

    &:hover {
      text-decoration: underline;
    }
  }
`;
