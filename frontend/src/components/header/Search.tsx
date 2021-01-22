import React from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import { ASSETS_BUTTON } from '../../assets';
import useInput from '../../hooks/useInput';
import { SearchWordList, SearchWordsGnbResopnse } from '../../models/header';
import { RootState } from '../../modules';
import { StyledBox } from '../Styles';
import Text from '../text/Text';

function Search({ searchWordList }: SearchWordsGnbResopnse) {
  const { channel } = useSelector((state: RootState) => state.testStatusChanger);
  const { value, onChange } = useInput('');
  const searchImage = channel === 'medical' ? ASSETS_BUTTON.MAGNIFYING_MEDICAL : ASSETS_BUTTON.MAGNIFYING;

  const handleSubmit = (e: any) => {
    alert('검색 키워드는 : ' + value);
    e.preventDefault();
  };

  const renderHotKeyword = (
    <StyledHotKeyword>
      {searchWordList.map((data: SearchWordList, index: number) => (
        <li key={index}>
          <a href={data.linkUrl ? data.linkUrl : 'a'}>
            <Text size="1.1rem" color="#777" weight="light">
              {data.dispNm}
            </Text>
          </a>
        </li>
      ))}
    </StyledHotKeyword>
  );

  const renderForm = (
    <form onSubmit={handleSubmit}>
      <StyledInput type="text" value={value} onChange={e => onChange(e)} />
      <StyledButton type="submit">{<img src={searchImage} alt="검색" />}</StyledButton>
    </form>
  );

  const renderSDC = (
    <>
      <StyledSearchDefault>{renderForm}</StyledSearchDefault>
      {renderHotKeyword}
    </>
  );

  const renderMedical = (
    <>
      <StyledSearchMedical>{renderForm}</StyledSearchMedical>
      {renderHotKeyword}
    </>
  );

  const renderGrainger = <StyledSearchGrainger>{renderForm}</StyledSearchGrainger>;
  const renderDigital = <StyledSearchDefault>{renderForm}</StyledSearchDefault>;

  const renderDefault = (
    <>
      <StyledSearchDefault>{renderForm}</StyledSearchDefault>
      {renderHotKeyword}
    </>
  );

  const renderSearch: { [key: string]: JSX.Element } = {
    sdc: renderSDC,
    medical: renderMedical,
    digital: renderDigital,
    grainger: renderGrainger
  };

  return channel ? renderSearch[channel] : renderDefault;
}

export default Search;

const StyledSearchDefault = styled(StyledBox)`
  height: 4rem;
  background-color: #fff;
  border: 0.3rem solid #d4382e;

  & form {
    width: 100%;
    height: 100%;
  }
`;

const StyledSearchMedical = styled(StyledSearchDefault)`
  border-color: #1968b3;
`;

const StyledSearchGrainger = styled(StyledSearchDefault)`
  border-color: #231f20;
`;

const StyledInput = styled.input`
  width: calc(100% - 2.9rem);
  height: 100%;
  padding: 0.5rem 1.1rem;
  color: #333;
  font-size: 1.5rem;
  font-family: 'Noto Sans KR';
  font-weight: 500;
  box-sizing: border-box;
`;

const StyledButton = styled.button`
  float: right;
  margin-right: 0.6rem;
  margin-top: -0.1rem;
  height: 100%;
  & img {
    vertical-align: middle;
  }
`;

const StyledHotKeyword = styled.ul`
  height: 2.5rem;
  line-height: 2.2rem;
  overflow: hidden;

  & li {
    display: inline-block;
    margin-left: 1.2rem;

    & a:hover span {
      text-decoration: underline;
    }
  }
`;
