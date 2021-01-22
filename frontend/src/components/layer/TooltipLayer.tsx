import React, { cloneElement, useState } from 'react';

interface ITooltipLayerProps {
  children: any;
}

function TooltipLayer({ children }: ITooltipLayerProps) {
  const [show, setShow] = useState(false);

  const handleLayer = () => {
    setShow(!show);
  };

  const renderLayerSwitch = children[0];
  const renderLayer = children[1];

  return (
    <div onMouseEnter={() => handleLayer()} onMouseLeave={() => setShow(false)}>
      {cloneElement(renderLayerSwitch, { active: show })}
      {show && renderLayer}
    </div>
  );
}

export default TooltipLayer;
