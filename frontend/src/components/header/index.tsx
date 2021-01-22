import React from 'react';
import styled from 'styled-components/macro';
import { CONSTANTS_DISPLAY } from '../../utils/constant';
import { StyledBox } from '../Styles';
import CurtainBanner from './CurtainBanner';
import Gateway from './Gateway';
import GNB from './GNB';
import Logo from './Logo';
import Search from './Search';
import { getHeadersData } from '../../apis/apiCall';
import { useAsync } from 'react-async';
import { Welcome } from '../../models/header';
import TooltipImageLayer from '../layer/TooltipImageLayer';
import PersonalMenu from './PersonalMenu';
import { useSelector } from 'react-redux';
import { RootState } from '../../modules';

const dummyData = {
  backgroundColor: '#ff0000',
  text: 'dummy1',
  imageUrl: 'https://dummyimage.com/1280x100/ccc/fff',
  linkUrl: 'www.naver.com',
  target: '01'
};

function Header() {
  const { channel } = useSelector((state: RootState) => state.testStatusChanger);
  const { isLoading, data, error } = useAsync<Welcome>(getHeadersData);

  /* const channelControl = () => {
    switch (channel) {
      case 'medical':
        return { searchBarSize: '52rem' };
      case 'digital':
        return { searchBarSize: '45.4rem' };
      case 'grainger':
        return { headerBackgroundColor: '#231f20', searchBarSize: '45.4rem' };
      default:
        return { headerBackgroundColor: '#fff', searchBarSize: '58rem' };
    }
  }; */

  const searchSize: { [key: string]: string } = {
    medical: '52rem',
    grainger: '45.4rem',
    digital: '45.4rem'
  };

  const headerSize: { [key: string]: string } = {
    digital: '7.3rem',
    grainger: '7.3rem'
  };

  const personalMenuTop: { [key: string]: string } = {
    digital: '1.8rem',
    grainger: '1.8rem'
  };

  if (isLoading || !data || error) return null;

  const renderInner = (
    <StyledBox w={CONSTANTS_DISPLAY.PC_BASE_WIDTH} h={channel ? (headerSize[channel] ? headerSize[channel] : '8rem') : '8rem'} center relative>
      <Logo channel={channel} />
      <StyledBox t="1.5rem" r="52rem" w={channel ? searchSize[channel] : '58rem'} absolute>
        <Search searchWordList={data.searchWordsGnbResopnse.searchWordList} />
      </StyledBox>
      {channel === undefined && (
        <StyledBox t="1rem" l="80rem" absolute>
          <TooltipImageLayer {...data.displaysShopsBannersResopnse.displayBannerInfoList} />
        </StyledBox>
      )}
      <StyledBox r="0" t={channel ? (personalMenuTop[channel] ? personalMenuTop[channel] : '2.5rem') : '2.5rem'} absolute>
        <PersonalMenu channel={channel} />
      </StyledBox>
    </StyledBox>
  );

  const renderSDC = <StyledHeader>{renderInner}</StyledHeader>;
  const renderMedical = <StyledHeader>{renderInner}</StyledHeader>;
  const renderGrainger = <StyledHeaderGrainger>{renderInner}</StyledHeaderGrainger>;
  const renderDigital = <StyledHeader>{renderInner}</StyledHeader>;
  const renderDefault = <StyledHeader>{renderInner}</StyledHeader>;

  const renderHeader: { [key: string]: JSX.Element } = {
    sdc: renderSDC,
    medical: renderMedical,
    digital: renderDigital,
    grainger: renderGrainger
  };

  return (
    <header>
      <CurtainBanner {...dummyData} />
      <Gateway />
      {channel ? renderHeader[channel] : renderDefault}
      <GNB
        mainTotalCategoryList={data.displaysMainCategoriesResopnse.mainTotalCategoryList}
        recentlyViewedProductList={data.recentlyViewedProductsResopnse.recentlyViewedProductList}
        mallsSpecialtyResopnse={data.mallsSpecialtyResopnse}
      />
    </header>
  );
}

export default Header;

const StyledHeader = styled.div`
  border-bottom: 0.1rem solid #ccc;
`;

const StyledHeaderGrainger = styled(StyledHeader)`
  background-color: #231f20;
`;
