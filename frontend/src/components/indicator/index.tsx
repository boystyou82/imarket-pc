import React from 'react';
import { ASSETS_INDICATOR } from '../../assets';

interface IIndicatorProps {
  active?: boolean;
}

const Indicator = {
  DefaultArrow: function ({ active }: IIndicatorProps) {
    return <img src={active ? ASSETS_INDICATOR.ARROW_M_UP_BLACK : ASSETS_INDICATOR.ARROW_M_DOWN_BLACK} alt="a" />;
  },
  SmallArrowDarkgray: function ({ active }: IIndicatorProps) {
    return <img src={active ? ASSETS_INDICATOR.ARROW_S_UP_DARKGRAY : ASSETS_INDICATOR.ARROW_S_DOWN_DARKGRAY} alt="a" />;
  },
  SmallArrowWhite: function ({ active }: IIndicatorProps) {
    return <img src={active ? ASSETS_INDICATOR.ARROW_S_UP_WHITE : ASSETS_INDICATOR.ARROW_S_DOWN_WHITE} alt="a" />;
  },
  SmallArrowRight: function ({ active }: IIndicatorProps) {
    return <img src={active ? ASSETS_INDICATOR.ARROW_S_RIGHT_WHITE : ASSETS_INDICATOR.ARROW_S_RIGHT_GRAY} alt="a" />;
  }
};

export default Indicator;
