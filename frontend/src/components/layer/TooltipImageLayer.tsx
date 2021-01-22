import React, { useState } from 'react';
import { StyledBox } from '../Styles';
import BannerItem from '../banner/BannerItem';
import { CONSTANTS_URL } from '../../utils/constant';

function TooltipImageLayer(displayBannerInfoList: any) {
  const [show, setShow] = useState(false);

  const handleLayer = () => {
    setShow(!show);
  };
  return (
    <StyledBox onMouseEnter={() => handleLayer()} onMouseLeave={() => handleLayer()}>
      <BannerItem imageUrl={`${CONSTANTS_URL.PC_BASE_URL}${displayBannerInfoList[0].dispImg1}`} linkUrl={displayBannerInfoList[0].linkUrl} text={''} target={displayBannerInfoList[0].winActTp} />
      {show && (
        <StyledBox absolute ml="-4rem" zIndex={1}>
          <BannerItem imageUrl={`${CONSTANTS_URL.PC_BASE_URL}${displayBannerInfoList[0].dispImg3}`} linkUrl={displayBannerInfoList[0].linkUrl} text={''} target={displayBannerInfoList[0].winActTp} />
        </StyledBox>
      )}
    </StyledBox>
  );
}

export default TooltipImageLayer;
