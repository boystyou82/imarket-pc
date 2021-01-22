import styled, { css } from 'styled-components/macro';

interface IStyledBoxProps {
  absolute?: boolean;
  relative?: boolean;
  fixed?: boolean;
  overflow?: string;
  float?: string;
  t?: string;
  r?: string;
  b?: string;
  l?: string;
  mt?: string;
  mr?: string;
  mb?: string;
  ml?: string;
  pt?: string;
  pr?: string;
  pb?: string;
  pl?: string;
  w?: string;
  h?: string;
  bg?: string;
  center?: boolean;
  borderBox?: boolean;
  contentBox?: boolean;
  clearfix?: boolean;
  ta?: 'left' | 'center' | 'right';
  zIndex?: number;
}

export const StyledBox = styled.div<IStyledBoxProps>`
  ${({ absolute }) => absolute && `position: absolute;`}
  ${({ relative }) => relative && `position: relative;`}
  ${({ fixed }) => fixed && `position: fixed;`}
  ${({ overflow }) => overflow && `overflow: ${overflow};`}
  ${({ float }) => float && `float: ${float};`}
  ${({ borderBox }) => borderBox && `box-sizing: border-box;`}
  ${({ contentBox }) => contentBox && `box-sizing: content-box;`}
  ${({ center }) => center && `margin: 0 auto;`}
  ${({ t }) => t && `top: ${t};`}
  ${({ r }) => r && `right: ${r};`}
  ${({ b }) => b && `bottom: ${b};`}
  ${({ l }) => l && `left: ${l};`}
  ${({ mt }) => mt && `margin-top: ${mt};`}
  ${({ mr }) => mr && `margin-right: ${mr};`}
  ${({ mb }) => mb && `margin-bottom: ${mb};`}
  ${({ ml }) => ml && `margin-left: ${ml};`}
  ${({ pt }) => pt && `padding-top: ${pt};`}
  ${({ pr }) => pr && `padding-right: ${pr};`}
  ${({ pb }) => pb && `padding-bottom: ${pb};`}
  ${({ pl }) => pl && `padding-left: ${pl};`}
  ${({ w }) => w && `width: ${w};`}
  ${({ h }) => h && `height: ${h};`}
  ${({ bg }) => bg && `background: ${bg};`}
  ${({ ta }) => ta === 'left' && 'text-align:left'};
  ${({ ta }) => ta === 'center' && 'text-align:center'};
  ${({ ta }) => ta === 'right' && 'text-align:right'};
  ${({ clearfix }) =>
    clearfix &&
    css`
      &:after {
        content: '';
        display: table;
        clear: both;
      }
    `};
  ${({ zIndex }) => zIndex && `z-index:${zIndex}`};
`;

export const StyledRibon = styled.div`
  float: left;
  width: 4.2rem;
  height: 2.8rem;
  background: #d4382e;
  &:after {
    content: '';
    float: left;
    border-right: 2.1rem solid transparent;
    border-left: 2.1rem solid transparent;
    border-top: 1rem solid #d4382e;
    margin-top: 2.8rem;
  }
`;

export const StyledImage = styled.img`
  width: 100%;
  height: auto;
`;

export const StyledIncludeBackgroundIcon = styled.span<{ icon: string; iconPosition?: string; paddingPosition: string; paddingValue: string }>`
  background: url(${props => props.icon}) no-repeat ${props => props.iconPosition};
  ${props => (props.paddingPosition === 'left' ? `padding-left:${props.paddingValue}` : `padding-right:${props.paddingValue}`)};
`;
