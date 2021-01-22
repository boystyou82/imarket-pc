import React from 'react';
import styled from 'styled-components/macro';
import { ASSETS_ICON } from '../../assets';

const eventLandingData: { [key: string]: any } = {
  coupon: {
    paddingTop: '2rem',
    linkUrl: 'http://www.imarket.co.kr/display/malls.do?_method=coupon&type=couponZone',
    iconUrl: ASSETS_ICON.COUPON,
    iconText1: '사업자회원',
    iconText2: '전용 쿠폰증정'
  },
  recommendPoint: {
    paddingTop: '1rem',
    linkUrl: 'http://www.imarket.co.kr/display/malls.do?_method=recommendIntro',
    iconUrl: ASSETS_ICON.RECOMMEND_POINT,
    iconText1: '지인 추천시',
    iconText2: '최대 5,000P증정'
  },
  cokdeal: {
    paddingTop: '0',
    linkUrl: 'http://www.imarket.co.kr/plant/PlantMaster.do?_method=Initial&plantId=1523',
    iconUrl: ASSETS_ICON.COKDEAL,
    iconText1: '사업자전용',
    iconText2: '콕딜'
  },
  simplePostpay: {
    paddingTop: '1.7rem',
    linkUrl: 'http://www.imarket.co.kr/display/malls.do?_method=viewEvent&type=cmsNotice',
    iconUrl: ASSETS_ICON.SIMPLE_POSTPAY,
    iconText1: '월1회 자동결제',
    iconText2: '간편후불서비스'
  },
  publishTaxbill: {
    paddingTop: '2rem',
    linkUrl: 'http://www.imarket.co.kr/mypage/order/OrderSearchList.do?_method=billRequstlInfo&md=chtype',
    iconUrl: ASSETS_ICON.PUBLISH_TAXBILL,
    iconText1: '세금계산서',
    iconText2: '발행서비스'
  },
  fastDelivery: {
    paddingTop: '2.2rem',
    linkUrl: 'http://www.imarket.co.kr/mypage/order/OrderSearchList.do?_method=initial&logintgt=mypage',
    iconUrl: ASSETS_ICON.FAST_DELIVERY,
    iconText1: '빠른배송',
    iconText2: '무료교환반품'
  }
};

interface IIconAndTextProps {
  type: string;
}

function ExhibitionMainlyIconPack({ type }: IIconAndTextProps) {
  const selectedIcon = eventLandingData[type];

  const renderIcon = (
    <StyledIconAndText paddingTop={selectedIcon.paddingTop}>
      <a href={selectedIcon.linkUrl} target="_blank" rel="noopener noreferrer">
        <div>
          <img src={selectedIcon.iconUrl} alt={`${selectedIcon.iconText1} ${selectedIcon.iconText2}`} />
        </div>
        <p>
          {selectedIcon.iconText1}
          <br />
          {selectedIcon.iconText2}
        </p>
      </a>
    </StyledIconAndText>
  );

  return <>{renderIcon}</>;
}

export default ExhibitionMainlyIconPack;

const StyledIconAndText = styled.div<{ paddingTop?: string }>`
  display: inline-block;
  margin-left: 1.5rem;
  width: 9.5rem;
  text-align: center;
  font-size: 1.3rem;
  &:first-child {
    margin-left: 0;
  }
  & > a > div {
    padding-top: ${props => props.paddingTop};
    height: 6.5rem;
  }
  & > a > p {
    margin-top: 0.5rem;
    line-height: 1.2;
  }
`;
