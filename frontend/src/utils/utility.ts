export function commaNumber(value: number): string {
  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

export function vatPrice(value: number) {
  return Math.round(value / 1.1);
}
