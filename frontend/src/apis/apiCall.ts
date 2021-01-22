import Axios from 'axios';

export async function getExhibitionBannerData() {
  const response = await Axios.get('/api/plants/banners');
  return response.data;
}

export async function getExhibitionCategoryData() {
  const response = await Axios.get('/api/plants/events/categories');
  return response.data;
}

export async function getExhibitionProductData(dispNo: string, paging: number, rowPerPage: number) {
  const response = await Axios.get(`/api/plants/events/products?${dispNo !== 'all' ? `displayNo=${dispNo}&` : ''}${paging !== 0 ? `page=${paging}&` : ''}rowPerPage=${rowPerPage}`);
  return response.data;
}

export async function getHeadersData() {
  const response = await Axios.get('/api/displays/gnb');
  return response.data;
}
