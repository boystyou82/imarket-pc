export interface Welcome {
  displaysMainCategoriesResopnse: DisplaysMainCategoriesResopnse;
  recentlyViewedProductsResopnse: RecentlyViewedProductsResopnse;
  mallsSpecialtyResopnse: MallsSpecialtyResopnse[];
  searchWordsGnbResopnse: SearchWordsGnbResopnse;
  membersOrdersCountResopnse: MembersOrdersCountResopnse;
  cartsGnbResopnse: CartsGnbResopnse;
  displaysShopsBannersResopnse: DisplaysShopsBannersResopnse;
}

export interface CartsGnbResopnse {
  productsCountInCart: number;
  totalPrice: number;
}

export interface DisplaysMainCategoriesResopnse {
  mainTotalCategoryList: MainTotalCategoryList[];
}

export interface MainTotalCategoryList {
  levelNo: number;
  shopNo: string;
  dispNo: string;
  dispNm: string;
  dispPrir: string;
  dispTp: string;
  dispKind: null | string;
  dispDep: string;
  dispPostfix: null;
  addLink: null;
  prdImgSize: string;
  prdSortTp: null | string;
  linkHtml8: null;
  highDispNo: string;
  mainSpmallYn: MainSpmallYn;
}

export enum MainSpmallYn {
  N = 'N'
}

export interface DisplaysShopsBannersResopnse {
  displayBannerInfoList: { [key: string]: null | string }[];
}

export interface MallsSpecialtyResopnse {
  specialtyMallCategoryList: { [key: string]: null | string }[];
  specialityMallList: any[];
}

export interface MembersOrdersCountResopnse {
  orderCount: number;
}

export interface RecentlyViewedProductsResopnse {
  recentlyViewedProductList: any[];
}

export interface SearchWordsGnbResopnse {
  searchWordList: SearchWordList[];
}

export interface SearchWordList {
  dispImg1: null;
  dispImg2: null;
  dispNm: string;
  dispNo: null;
  linkParam: null;
  linkUrl: null | string;
  winActTp: null;
}

export interface IChannel {
  channel?: string;
}
