import React from 'react';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import styled from 'styled-components/macro';
import { CONSTANTS_DISPLAY } from '../utils/constant';

const PlantVisual = styled.div<{ bgColor: string }>`
  background: ${props => props.bgColor};

  & > div {
    margin: 0 auto;
    width: ${CONSTANTS_DISPLAY.PC_BASE_WIDTH};
  }
`;

const PlantHeader = styled.div`
  height: 3rem;
  line-height: 3rem;
  background: #eee;
  & div {
    margin: 0 auto;
    padding-left: 3rem;
    padding-right: 3rem;
    width: 120rem;
    & span:nth-child(2) {
      float: right;
    }
  }
`;

const PlantCategory = styled.ul`
  margin: 0 auto;
  width: 120rem;
  overflow: hidden;
  border-top: 0.3rem solid #d4382e;
  border-bottom: 0.1rem solid #333333;
  & li {
    float: left;
    width: 24rem;
    height: 4.3rem;
    border-right: 0.1rem solid #ccc;
    border-top: 0.1rem solid #ccc;
  }
  & li:nth-child(-n + 5) {
    border-top: none;
  }
  & li:nth-child(5n + 1) {
    border-left: 0.1rem solid #ccc;
  }
  & li a {
    display: inline-block;
    padding-left: 2rem;
    width: 24rem;
    height: 4.3rem;
    line-height: 4.3rem;
    background: url('http://www.imarket.co.kr/img/plant/cate_arrow_off.gif') no-repeat 21.5rem 1.6rem;
  }
  & li a:hover {
    background: url('http://www.imarket.co.kr/img/plant/cate_arrow_on.gif') no-repeat 21.5rem 1.6rem;
    background-color: #ff0000;
    color: #fff;
  }
`;

const PlantProduct = styled.div`
  margin: 0 auto;
  margin-bottom: 5rem;
  width: 120rem;

  & > div {
    & > p {
      margin-top: 5rem;
      font-size: 1.8rem;
      height: 5rem;
      line-height: 5rem;
      border-bottom: 0.1rem solid #000;
    }

    & > ul {
      padding-top: 3rem;
      overflow: hidden;
      & > li {
        float: left;
        margin-left: 2.5rem;
        margin-bottom: 2rem;
        width: 22rem;
        vertical-align: top;

        & > a {
          display: inline-block;
          width: 22rem;
          height: 22rem;
          background: #ccc;

          & img {
            width: 100%;
            height: 100%;
          }
        }

        & > p {
          height: 3rem;
          margin-top: 1rem;
          word-break: break-all;

          & > a {
            font-size: 1.4rem;
            color: #777;
            line-height: 1.3;
          }
        }

        & > div {
          & span:nth-child(1) {
            font-size: 2.5rem;
            color: #d4382e;
          }
          & span:nth-child(2) {
            text-decoration: line-through;
            color: #aaa;
            margin-left: 0.5rem;
          }
        }
      }

      & li:nth-child(5n + 1) {
        margin-left: 0;
      }
    }
  }
`;

type plantMasterList = {
  activeBgColor: string;
  activeTextColor: string;
  basicBgColor: string;
  basicTextColor: string;
  bgColor: string;
  cateColor: string;
  cateContent: string;
  cateId: string;
  cateLink: string;
  cateName: string;
  cateType: string;
  cateViewOption: string;
  cdDtlNm: string;
  cdDtlNo: string;
  displayEndDate: string;
  displayStrDate: string;
  displayType: string;
  displayYn: string;
  distCost: string;
  footContent: string;
  modDts: string;
  modNm: string;
  modNo: string;
  plantAdmNo: string;
  plantGuideSentence: string;
  plantHeader: string;
  plantId: string;
  plantKind: string;
  plantListBanner: string;
  plantMobileHeader: string;
  plantThumbnailBanner: string;
  plantTitle: string;
  plantUseYn: string;
  prdIcon: string;
  prdImg: string;
  prdNm: string;
  prdNo: string;
  prdState: string;
  regDts: string;
  regNm: string;
  regNo: string;
  rn: string;
  rowProductCount: string;
  saleStatTp: string;
  saleUnitcost: string;
  snsShareCnt: string;
  sort: string;
  templetId: string;
  templetName: string;
  templetWidth: string;
};

type plantMasterCategoryList = {
  cateContent: string;
  cateId: string;
  cateLink: string;
  cateName: string;
  cateSort: number;
  cateType: string;
  cateViewOption: string;
  modDts: string;
  modNo: number;
  plantId: string;
  regDts: string;
  regNo: number;
  rowProductCount: string;
};

type plantCategoryProductList = {
  authChnlNo: string;
  authCode: string;
  cateId: string;
  delvAmtPayTp: string;
  delvCost: string;
  dispYn: string;
  distCost: string;
  frgnPrdYn: string;
  hdelvMafcEntrNm: string;
  icnImg: string;
  icnNm: string;
  icnTp: string;
  minOrdQty: string;
  mktPr: string;
  perAmt: string;
  plantId: string;
  pointRate: string;
  prdDisplayRestriction: string;
  prdImg: string;
  prdModelNo: string;
  prdNm: string;
  prdNo: string;
  prdSort: string;
  priceDisplayTp: string;
  proddelvCostUseYn: string;
  rn: string;
  saleStatTp: string;
  saleStatTpNm: string;
  saleUnitcost: string;
  supplyEntrNo: string;
  taxTp: string;
};

function Promotion() {
  const [masterData, setMasterData] = useState<plantMasterList[]>([]);
  const [categoryData, setCategoryData] = useState<plantMasterCategoryList[]>([]);
  const [productData, setProudctData] = useState<plantCategoryProductList[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const { promotionId }: any = useParams();

  const MENU_COUNT_MAX = 5;

  useEffect(() => {
    const fetchData = async () => {
      try {
        setError(null);
        setMasterData([]);
        setCategoryData([]);
        setProudctData([]);
        setLoading(true);

        const response = await axios.get(`/api/plants/${promotionId}`);

        setMasterData(response.data.plantMasterList);
        setCategoryData(response.data.plantMasterCategoryList);
        setProudctData(response.data.plantCategoryProductList);
      } catch (e) {
        setError(e);
      }
      setLoading(false);
    };

    fetchData();
  }, [promotionId]);

  if (loading) return <div>loading....</div>;
  if (error) return <div>error!!!!</div>;
  if (!masterData && !categoryData && !productData) return null;

  function listTableSet(arr: any, n: number) {
    let len = arr.length;
    let count = Math.ceil(len / n) * n;
    let tmp = [];

    for (let i = 0; i < count; i++) {
      if (arr[i])
        tmp.push(
          <li key={i}>
            <a href="a">{arr[i].cateName}</a>
          </li>
        );
      else tmp.push(<li key={i}></li>);
    }

    return tmp;
  }

  function commaNumber(num: number): string {
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  }

  const productGroupBy = Object.values(
    productData.reduce((result: any, currentValue: any) => {
      (result[currentValue.cateId] = result[currentValue.cateId] || []).push(currentValue);
      return result;
    }, {})
  );

  const plantMenu = listTableSet(categoryData, MENU_COUNT_MAX);

  const productList = productGroupBy.map((data: any, index: number) => {
    const productItem = data.map((data: plantCategoryProductList, index: number) => (
      <li key={index}>
        <a href={`http://www.imarket.co.kr/product/MallDisplay.do?_method=Detail&sc.shopNo=0000100000&sc.prdNo=${data.prdNo}`}>
          <img src={data.prdImg} alt={data.prdNm} />
        </a>
        <p>
          <a href={`http://www.imarket.co.kr/product/MallDisplay.do?_method=Detail&sc.shopNo=0000100000&sc.prdNo=${data.prdNo}`}>{data.prdNm}</a>
        </p>
        <p>{data.hdelvMafcEntrNm}</p>
        <div>
          <p>
            <span>{commaNumber(Number(data.saleUnitcost))}</span>
            {data.mktPr !== null && <span>{commaNumber(Number(data.mktPr))}</span>}
          </p>
        </div>
      </li>
    ));
    return (
      <div key={index}>
        <p>{categoryData[index].cateName}</p>
        <ul>{productItem}</ul>
      </div>
    );
  });

  return (
    <div>
      {masterData.map((data, index) => (
        <div key={index}>
          <PlantHeader>
            <div>
              <span>기획전명 : {data.plantTitle} </span>
              <span>
                기간 : {data.displayStrDate} ~ {data.displayEndDate}
              </span>
            </div>
          </PlantHeader>
          <PlantVisual bgColor={data.bgColor}>
            <div dangerouslySetInnerHTML={{ __html: data.plantHeader }}></div>
          </PlantVisual>
          <PlantCategory>{plantMenu}</PlantCategory>
          <PlantProduct>{productList}</PlantProduct>
        </div>
      ))}
    </div>
  );
}

export default Promotion;
