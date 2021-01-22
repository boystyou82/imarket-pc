import Axios from 'axios';
import { useAsync } from 'react-async';

async function serverTime() {
  const response = await Axios.get('/');
  const currentDate = new Date(response.headers.date);
  return currentDate;
}

function useCurrentDate() {
  const { isLoading, data, error } = useAsync(serverTime);

  if (isLoading || !data || error) return null;

  return data.getMonth() + 1;
}

export default useCurrentDate;
