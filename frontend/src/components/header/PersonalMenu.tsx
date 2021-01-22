import React from 'react';
import { ASSETS_ICON } from '../../assets';
import { StyledBox } from '../Styles';
import Text from '../text/Text';
import TooltipSubMenuLayer from '../layer/TooltipSubMenuLayer';
import styled from 'styled-components';
import { IChannel } from '../../models/header';
import TooltipLayer from '../layer/TooltipLayer';
import Indicator from '../indicator';
import { useSelector } from 'react-redux';
import { RootState } from '../../modules';

const userName = '유성태';

interface IRenderSwitch {
  active?: boolean;
}

function PersonalMenu({ channel }: IChannel) {
  const loginStatus = useSelector((state: RootState) => state.testStatusChanger.loginStatus);

  const style: { [key: string]: { textColor: string; iconOrderList: string; iconCart: string; iconProfile: string } } = {
    grainger: {
      textColor: '#fff',
      iconOrderList: ASSETS_ICON.ORDER_LIST_WHITE,
      iconCart: ASSETS_ICON.CART_WHITE,
      iconProfile: ASSETS_ICON.PROFILE_WHITE
    },
    default: {
      textColor: '#333',
      iconOrderList: ASSETS_ICON.ORDER_LIST,
      iconCart: ASSETS_ICON.CART,
      iconProfile: ASSETS_ICON.PROFILE
    }
  };

  const changeStyle = style[channel || 'default'] !== undefined ? style[channel || 'default'] : style['default'];

  function RenderSwitch({ active }: IRenderSwitch) {
    return (
      <StyledLayerSwitch active={active}>
        <StyledLayerAnchor>
          <img src={changeStyle.iconProfile} alt="마이페이지" />
          <Text size="1rem" weight="light" color={changeStyle.textColor} mt="0.6rem" ls="-0.05rem" as="p">
            {userName} 님 <StyledColoredText>ON</StyledColoredText>
          </Text>
          <StyledIndicator>{loginStatus && (channel === 'grainger' ? <Indicator.SmallArrowWhite active={active} /> : <Indicator.SmallArrowDarkgray active={active} />)}</StyledIndicator>
        </StyledLayerAnchor>
      </StyledLayerSwitch>
    );
  }

  function OrderList() {
    const orderImage = channel === 'grainger' ? ASSETS_ICON.ORDER_LIST_WHITE : ASSETS_ICON.ORDER_LIST;
    const textColor = channel === 'grainger' ? '#fff' : '#333';
    return (
      <StyledBox float="left" ta="center" relative>
        <StyledLayerAnchor href="#">
          <img src={orderImage} alt="주문내역" />
          <Text size="1rem" weight="light" color={textColor} mt="0.7rem" ls="-0.05rem" as="p">
            주문내역
          </Text>
        </StyledLayerAnchor>
        <StyledCount t="-0.9rem" r="-0.2rem">
          0
        </StyledCount>
      </StyledBox>
    );
  }

  function CartList() {
    const cartImage = channel === 'grainger' ? ASSETS_ICON.CART_WHITE : ASSETS_ICON.CART;
    const textColor = channel === 'grainger' ? '#fff' : '#333';
    return (
      <StyledBox float="left" ml="3.9rem" ta="center" relative>
        <StyledLayerAnchor href="#">
          <img src={cartImage} alt="장바구니" />
          <Text size="1rem" weight="light" color={textColor} mt="0.7rem" ls="-0.05rem" as="p">
            장바구니
          </Text>
        </StyledLayerAnchor>
        <StyledCount t="-0.9rem" r="-0.7rem">
          0
        </StyledCount>
      </StyledBox>
    );
  }

  function Mypage() {
    return (
      <StyledBox float="left" ml="2.7rem" ta="center">
        {loginStatus ? (
          <TooltipLayer>
            <RenderSwitch />
            <TooltipSubMenuLayer layerData={dataPersonalMenu} t="5.5rem" heel pr="3rem" />
          </TooltipLayer>
        ) : (
          <StyledLayerAnchor href="#">
            <img src={changeStyle.iconProfile} alt="마이페이지" />
            <Text size="1rem" weight="light" color={changeStyle.textColor} mt="0.6rem" ls="-0.05rem" as="p">
              마이페이지
            </Text>
          </StyledLayerAnchor>
        )}
      </StyledBox>
    );
  }

  return (
    <>
      <OrderList />
      <CartList />
      <Mypage />
    </>
  );
}

export default PersonalMenu;

const StyledLayerSwitch = styled.span<{ active?: boolean }>`
  display: block;
  &:hover p {
    text-decoration: underline;
  }
  ${({ active }) => active && `padding-bottom:1.2rem;`}
`;

const StyledColoredText = styled.span`
  color: #d4382e;
`;

const StyledCount = styled.div<{ t?: string; r?: string; b?: string; l?: string }>`
  position: absolute;
  top: ${props => props.t};
  right: ${props => props.r};
  width: 1.9rem;
  height: 1.9rem;
  line-height: 1.9rem;
  background: #d4382e;
  border-radius: 7rem;
  color: #fff;
  font-size: 1.1rem;
  font-weight: 300;
`;

const StyledIndicator = styled.span`
  position: absolute;
  top: 1.2rem;
  right: 0;
  width: 0.8rem;
  height: 0.4rem;

  img {
    display: block;
  }
`;

const StyledLayerAnchor = styled.a`
  display: block;
  &:hover p {
    text-decoration: underline;
  }
`;

const dataPersonalMenu = [
  {
    name: '나의 쇼핑정보',
    link: null,
    title: true
  },
  {
    name: '주문/배송조회',
    link: '#',
    title: false
  },
  {
    name: '취소/반품/교환',
    link: '#',
    title: false
  },
  {
    name: '증빙발행',
    link: '#',
    title: false
  },
  {
    name: '할인쿠폰',
    link: '#',
    title: false
  },
  {
    name: '나의 쇼핑상품',
    link: null,
    title: true
  },
  {
    name: '자주구매상품',
    link: '#',
    title: false
  },
  {
    name: '관심상품',
    link: '#',
    title: false
  }
];
