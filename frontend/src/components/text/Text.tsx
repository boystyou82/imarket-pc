import React, { ReactNode } from 'react';
import styled from 'styled-components/macro';

interface ITextProps {
  children: ReactNode;
  size?: string;
  weight?: string;
  color?: string;
  ls?: string;
  mt?: string;
  ml?: string;
  as?: React.ElementType;
}

interface ITextPriceProps {
  price: number;
}

interface ITextClampProps {
  children: ReactNode;
  clamp: number;
}

interface ITextWithIconProps extends ITextProps {
  iconPosition: 'left' | 'right';
  icon: string;
  paddingValue: string;
}

export function TextPercent({ children, ...rest }: ITextProps) {
  return (
    <StyledPercent>
      <SText size="1.8rem">{children}</SText>
      <SText size="1.4rem">%</SText>
    </StyledPercent>
  );
}

export function TextPrice({ price }: ITextPriceProps) {
  return (
    <StyledPrice>
      <SText size="1.7rem" weight="medium" color="#333">
        {price.toLocaleString().split('.')[0]}
      </SText>
      <SText size="1.5rem">원</SText>
    </StyledPrice>
  );
}

export function TextWithIcon({ children, icon, iconPosition, paddingValue, ...rest }: ITextWithIconProps) {
  return (
    <StyledTextWithIcon icon={icon} iconPosition={iconPosition} paddingValue={paddingValue} {...rest}>
      {children}
    </StyledTextWithIcon>
  );
}

function Text({ children, ...rest }: ITextProps) {
  return <SText {...rest}>{children}</SText>;
}

export default Text;

const SText = styled.span<ITextProps>`
  font-size: ${props => props.size};
  font-weight: ${props => {
    switch (props.weight) {
      case 'light':
        return `300;`;
      case 'regular':
        return `400;`;
      case 'medium':
        return `500;`;
      case 'bold':
        return `700;`;
      default:
        break;
    }
  }};
  ${({ color }) => color && `color: ${color};`}
  ${({ mt }) => mt && `margin-top:${mt}`};
  ${({ ml }) => ml && `margin-left:${ml}`};
  ${({ ls }) => ls && `letter-spacing:${ls}`};
`;

const StyledPercent = styled.div`
  text-align: center;
  line-height: 2.8rem;
  font-family: Arial;
  letter-spacing: -0.05rem;
  color: #fff;
`;

const StyledPrice = styled.span`
  letter-spacing: -0.05rem;
  line-height: 1.2;
`;

const StyledTextWithIcon = styled(SText)<{ icon: string; iconPosition: 'left' | 'right'; paddingValue: string }>`
  background: url(${props => props.icon}) no-repeat ${props => props.iconPosition} center;
  ${props => (props.iconPosition === 'left' ? `padding-left:${props.paddingValue}` : `padding-right:${props.paddingValue}`)};
`;

export const StyledTextClamp = styled.span<ITextClampProps>`
  display: -webkit-box;
  line-height: 1.2;
  word-wrap: break-word;
  -webkit-line-clamp: ${props => props.clamp};
  -webkit-box-orient: vertical;
  overflow: hidden;
`;
