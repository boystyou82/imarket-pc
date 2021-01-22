import React, { useCallback, useState } from 'react';
import styled from 'styled-components';
import { IBannerItem } from '../../models/banner';
import { StyledBox } from '../Styles';
import { ASSETS_BUTTON } from '../../assets';
import BannerItem from '../banner/BannerItem';
import { CONSTANTS_DISPLAY } from '../../utils/constant';

interface ICurtainBannerProps extends IBannerItem {
  backgroundColor: string;
}

function CurtainBanner({ ...rest }: ICurtainBannerProps) {
  const [mounted, setMounted] = useState(true);
  const handleClose = useCallback(function () {
    setMounted(false);
    sessionStorage.setItem('curtainBanner_disabled', 'true');
  }, []);

  const renderCurtainBanner = (
    <StyledCurtainBanner {...rest}>
      <StyledBox w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} h="10rem" relative center>
        <BannerItem {...rest} />
        <StyledCloseButton onClick={() => handleClose()}>
          <img src={ASSETS_BUTTON.CLOSE_TWOTONE} alt="커튼 배너 닫기 버튼"></img>
        </StyledCloseButton>
      </StyledBox>
    </StyledCurtainBanner>
  );

  if (sessionStorage.getItem('curtainBanner_disabled') === 'true') return null;

  return <>{mounted && renderCurtainBanner}</>;
}

export default CurtainBanner;

const StyledCurtainBanner = styled.div<{ backgroundColor: string }>`
  ${({ backgroundColor }) => backgroundColor && `background: ${backgroundColor};`}
`;

const StyledCloseButton = styled.button`
  position: absolute;
  right: 0.5rem;
  top: 50%;
  margin-top: -1.7rem;
`;
