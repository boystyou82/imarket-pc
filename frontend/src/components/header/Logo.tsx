import React from 'react';
import styled from 'styled-components';
import { ASSETS_LOGO } from '../../assets';
import { IChannel } from '../../models/header';

function Logo({ channel }: IChannel) {
  const renderSDC = (
    <h1>
      <a href="/">
        <img src={ASSETS_LOGO.SDC} alt="SDC" />
      </a>
    </h1>
  );

  const renderMedical = (
    <h1>
      <img src={ASSETS_LOGO.MEDICAL} alt="medical" useMap="#medicalLogoMap" />
      <map id="medicalLogoMap" name="medicalLogoMap">
        <area target="" alt="imarket" title="imarket" href="/" coords="0,0,102,37" shape="rect" />
        <area target="" alt="의료몰" title="의료몰" href="/medical" coords="108,1,198,36" shape="rect" />
      </map>
    </h1>
  );

  const renderGrainger = (
    <StyledGrainger>
      <h1>
        <a href="a">
          <img src={ASSETS_LOGO.IMARKET_WHITE_SMALL} alt="iMarket" />
        </a>
      </h1>
      <h1>
        <a href="a">
          <img src={ASSETS_LOGO.GRAINGER} alt="grainger" />
        </a>
      </h1>
    </StyledGrainger>
  );

  const renderDigital = (
    <StyledDigital>
      <h1>
        <a href="a">
          <img src={ASSETS_LOGO.IMARKET_DEFAULT_SMALL} alt="iMarket" />
        </a>
      </h1>
      <h1>
        <a href="a">
          <img src={ASSETS_LOGO.DIGITAL} alt="디지털존" />
        </a>
      </h1>
    </StyledDigital>
  );

  const renderDefault = (
    <h1>
      <a href="/">
        <img src={ASSETS_LOGO.IMARKET_DEFAULT} alt="iMarket" />
      </a>
    </h1>
  );

  const renderLogo: { [key: string]: JSX.Element } = {
    sdc: renderSDC,
    medical: renderMedical,
    digital: renderDigital,
    grainger: renderGrainger
  };

  return <StyledLogo>{channel ? renderLogo[channel] : renderDefault}</StyledLogo>;
}

export default Logo;

const StyledLogo = styled.div`
  display: inline-block;
  margin-top: 1.5rem;

  area:focus {
    border: none;
    outline: none;
    outline-style: none;
    -moz-outline-style: none;
  }

  img,
  img a {
    outline: none !important;
    border: none !important;
  }
`;

const StyledGrainger = styled.div`
  h1 {
    float: left;
  }

  h1:nth-child(2) {
    margin-top: 0.3rem;
    margin-left: 1.5rem;
  }
`;

const StyledDigital = styled.div`
  h1 {
    float: left;
  }

  h1:nth-child(2) {
    margin-top: 0.3rem;
    margin-left: 1.5rem;
  }
`;
