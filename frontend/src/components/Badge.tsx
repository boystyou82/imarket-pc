import React from 'react';
import { StyledRibon, StyledBox } from './Styles';
import { TextPercent } from './text/Text';

interface IBadgeProps {
  position?: string;
  perAmt: number | null;
}

interface IpositionValue {
  t: string;
  l: string;
  b: string;
  r: string;
}

function Badge({ position, perAmt }: IBadgeProps) {
  let positionProps: any = {};

  function getPosition(positionText: string) {
    switch (positionText) {
      case 'tl':
        return { t: '0', l: '0' };
      case 'tr':
        return { t: '0', r: '0' };
      case 'bl':
        return { b: '0', l: '0' };
      case 'br':
        return { b: '0', r: '0' };
    }
  }

  if (position) {
    positionProps = getPosition(position);
  }

  return (
    <StyledBox absolute {...positionProps}>
      <StyledBox>
        <StyledRibon />
      </StyledBox>
      <StyledBox absolute w="100%" t="0.3rem">
        <TextPercent>{perAmt}</TextPercent>
      </StyledBox>
    </StyledBox>
  );
}

export default Badge;
