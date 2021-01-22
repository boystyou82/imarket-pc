import React from 'react';
import styled from 'styled-components/macro';
import { IBannerItem } from '../../models/banner';

function BannerItem({ text, imageUrl, linkUrl, target }: IBannerItem) {
  switch (target) {
    case '01':
      target = '_blank';
      break;
    case '02':
      target = '_self';
      break;
  }
  return (
    <StyledBannerItem>
      <a href={linkUrl} target={target}>
        <img src={imageUrl} alt={text} />
      </a>
    </StyledBannerItem>
  );
}

export default BannerItem;

const StyledBannerItem = styled.div`
  font-size: 0;
  & > a {
    display: inline-block;
  }
`;
