import React from 'react';
import Skeleton from 'react-loading-skeleton';
import styled from 'styled-components/macro';
import { useAsync } from 'react-async';

import { StyledBox } from '../../components/Styles';
import BannerItem from '../../components/banner/BannerItem';

import { CONSTANTS_DISPLAY, CONSTANTS_URL } from '../../utils/constant';
import { getExhibitionBannerData } from '../../apis/apiCall';

interface IBanner {
  dispImg1: string;
  dispImg2: string;
  dispNm: string;
  dispNo: string;
  linkParam: string;
  linkUrl: string;
  winActTp: string;
  //cookieNm: string,
  //dispEndDts: 2020-10 - 27T05: 46: 27.158Z,
  //dispStrtDts: 2020 - 10 - 27T05: 46: 27.158Z,
}

function BannerContainer() {
  const { isLoading, data, error } = useAsync(getExhibitionBannerData);

  if (isLoading || !data || error) return null;

  const cardBannerData = data.eventPcBannerList;
  const wideBannerData = data.eventBandBannerList;

  const renderCardBanner = cardBannerData ? (
    <StyledCardBanner>
      {cardBannerData?.map((data: IBanner, index: number) => (
        <BannerItem key={index} text={data.dispNm} imageUrl={`${CONSTANTS_URL.PC_BASE_URL}${data.dispImg1}`} linkUrl={data.linkUrl} target={data.winActTp} />
      ))}
    </StyledCardBanner>
  ) : (
    <StyledCardBanner>
      <div>
        <Skeleton width={285} height={280} />
      </div>
      <div>
        <Skeleton width={285} height={280} />
      </div>
      <div>
        <Skeleton width={285} height={280} />
      </div>
      <div>
        <Skeleton width={285} height={280} />
      </div>
    </StyledCardBanner>
  );

  const renderWideBanner = (
    <StyledWideBanner>
      {wideBannerData?.map((data: IBanner, index: number) => (
        <BannerItem key={index} text={data.dispNm} imageUrl={`http://www.imarket.co.kr/${data.dispImg1}`} linkUrl={data.linkUrl} target={data.winActTp} />
      ))}
    </StyledWideBanner>
  );

  return (
    <StyledBox w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} center>
      {cardBannerData.length !== 0 && renderCardBanner}
      {wideBannerData.length !== 0 && renderWideBanner}
    </StyledBox>
  );
}

export default BannerContainer;

const StyledCardBanner = styled.div`
  margin-bottom: 6rem;
  overflow: hidden;
  & > div {
    float: left;
    margin-left: calc((100% - 114rem) / 3);

    &:first-child {
      margin-left: 0;
    }
  }
`;

const StyledWideBanner = styled.div`
  margin-bottom: 5rem;
  text-align: center;
`;
