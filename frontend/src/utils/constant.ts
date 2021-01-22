export const CONSTANTS_DISPLAY = {
  PC_BASE_WIDTH: '128rem'
};

export const CONSTANTS_URL = {
  PC_BASE_URL: 'http://www.imarket.co.kr',
  IMAGE_PRODUCTION_URL: 'http://image.imarket.co.kr',
  IMAGE_DEVELOP_URL: 'http://www.imarket.co.kr'
};

// 미전시/특수목적 카테고리 제외
// 의료몰(213), 호텔mro(215), 그레인저(216), 디지털관(217)
export const CONSTANTS_EXCLUDE_CATEGORY = [
  { DISP_NO: '213', DISP_NAME: '의료몰' },
  { DISP_NO: '215', DISP_NAME: '호텔MRO' },
  { DISP_NO: '216', DISP_NAME: null },
  { DISP_NO: '217', DISP_NAME: null }
];
