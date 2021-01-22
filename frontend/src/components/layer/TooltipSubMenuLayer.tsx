import React from 'react';
import styled, { css } from 'styled-components';
import { ASSETS_INDICATOR } from '../../assets';
import { StyledBox } from '../Styles';

interface ITooltipLayerProps {
  layerData: any;
  t?: string;
  heel?: boolean;
  pr?: string;
}

function TooltipSubMenuLayer({ layerData, t, heel, pr }: ITooltipLayerProps) {
  const renderLayer = (
    <StyledLayer absolute r="0" t={t}>
      {heel && <StyledTooltipHeel />}
      <ul>
        {layerData.map((data: any, index: number) => (
          <StyledLayerTitle key={index} strong={data.title} noUnderline={data.link} pr={pr}>
            <a href="a">{data.name}</a>
          </StyledLayerTitle>
        ))}
      </ul>
    </StyledLayer>
  );

  return <div>{renderLayer}</div>;
}

export default TooltipSubMenuLayer;

const StyledLayer = styled(StyledBox)`
  z-index: 1;
  border: 0.1rem solid #333;
  background: #fff;
  box-shadow: 0 0.5rem 0.7rem 0 rgba(0, 0, 0, 0.2);

  & ul {
    padding: 1rem 1rem;

    & li {
      line-height: 2rem;
      letter-spacing: -0.05rem;
      white-space: nowrap;
      text-align: left;

      & a {
        font-size: 1.2rem;
        padding-left: 0.3rem;
        color: #666;

        &:hover {
          text-decoration: underline;
        }
      }
    }

    & li:first-child {
      border-top: none;
      margin-top: 0;
      padding-top: 0;
    }
  }
`;

const StyledTooltipHeel = styled.span`
  position: absolute;
  top: -0.6rem;
  right: 2rem;
  width: 1.1rem;
  height: 0.6rem;
  background: url(${ASSETS_INDICATOR.HEEL_S});
`;

const StyledLayerTitle = styled.li<{ strong: boolean; noUnderline: string | null; pr?: string }>`
  ${({ strong }) =>
    strong &&
    css`
      && {
        border-top: 0.1rem solid #e1e1e1;
        margin-top: 0.5rem;
        padding-top: 0.4rem;

        & a {
          padding-left: 0;
          font-size: 1.3rem;
          font-weight: 400;
          color: #333;
        }
      }
    `}

  ${({ noUnderline }) =>
    noUnderline === null &&
    css`
      &&& a {
        text-decoration: none;
        cursor: default;
      }
    `}

  ${({ pr }) =>
    pr &&
    css`
      &&& a {
        padding-right: ${pr};
      }
    `};
`;
