import React from 'react';
import styled from 'styled-components';
import { ASSETS_BUTTON } from '../assets';

export const NextviewArrow = (props: any) => {
  const { className, onClick } = props;
  return <StyledSliderNextviewArrow className={className} onClick={onClick} />;
};

export const PreviewArrow = (props: any) => {
  const { className, onClick } = props;
  return <StyledSliderPreviewArrow className={className} onClick={onClick} />;
};

const StyledSliderArrow = styled.div`
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 3rem;
  height: 3rem;
  z-index: 1;

  &:after {
    content: '';
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 1rem;
    height: 2rem;
    background-image: url(${ASSETS_BUTTON.ARROW_DEFAULT});
  }

  &&&:not(.slick-disabled):hover {
    cursor: pointer;
    background-color: rgba(0, 0, 0, 0.5);
  }

  &&&.slick-disabled:after {
    background-image: url(${ASSETS_BUTTON.ARROW_DISABLED});
  }

  &&&.slick-disabled:hover {
    cursor: default;
  }
`;

const StyledSliderNextviewArrow = styled(StyledSliderArrow)`
  right: -3rem;

  &:after {
    transform: translate(-50%, -50%) rotate(180deg);
  }
`;

const StyledSliderPreviewArrow = styled(StyledSliderArrow)`
  left: -3rem;
`;
