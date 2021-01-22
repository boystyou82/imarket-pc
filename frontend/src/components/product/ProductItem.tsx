import React from 'react';
import styled from 'styled-components';
import { CONSTANTS_URL } from '../../utils/constant';
import Badge from '../Badge';
import { StyledBox, StyledImage } from '../Styles';
import Text, { TextPrice } from '../text/Text';
import { commaNumber, vatPrice } from '../../utils/utility';

interface IProductItemProps {
  prdNm: string;
  prdImg: string;
  saleUnitcost: number;
  perAmt: number;
  prdNo: string;
  vatPriceShow?: boolean;
}

function ProductItem({ prdNm, prdImg, saleUnitcost, perAmt, prdNo, vatPriceShow }: IProductItemProps) {
  const addDefaultSrc = (e: any) => {
    e.target.src = `${CONSTANTS_URL.IMAGE_DEVELOP_URL}/img/imfs/main/no_img_110.gif`;
  };
  return (
    <StyledProductItem href={`http://www.imarket.co.kr/product/MallDisplay.do?_method=Detail&sc.shopNo=0000100000&sc.prdNo=${prdNo}`} target="_blank" rel="noopener noreferrer" title={prdNm}>
      {perAmt !== 0 && perAmt !== undefined && <Badge position="tr" perAmt={perAmt} />}
      <StyledBox w="11rem" h="11rem" mb="1.2rem" center>
        <StyledImage src={`${CONSTANTS_URL.IMAGE_PRODUCTION_URL}/goods_image${prdImg}`} onError={e => addDefaultSrc(e)} />
      </StyledBox>
      <StyledBox mb="1rem" h="3rem" overflow="hidden">
        <StyledProductName size="1.3rem" color="#666">
          {prdNm}
        </StyledProductName>
      </StyledBox>
      <StyledBox mb="0.1rem">
        <TextPrice price={saleUnitcost} />
      </StyledBox>
      {vatPriceShow && <StyledVatPrice>(VAT별도 {commaNumber(vatPrice(saleUnitcost / 1.1))}원)</StyledVatPrice>}
    </StyledProductItem>
  );
}

export default ProductItem;

const StyledProductName = styled(Text)`
  word-break: break-all;
  &:hover {
    text-decoration: underline;
  }
`;

const StyledProductItem = styled.a`
  &:hover {
    & ${StyledProductName} {
      text-decoration: underline;
    }
  }
`;

const StyledVatPrice = styled.p`
  padding-bottom: 1rem;
  font-weight: 300;
  font-size: 1.1rem;
  color: #555;
  letter-spacing: -0.1rem;
`;
