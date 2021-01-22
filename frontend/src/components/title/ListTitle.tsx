import React from 'react';
import styled from 'styled-components/macro';
import Text from '../text/Text';
import { ITitleProps } from '../../models/title';

function ListTitle({ children, ...rest }: ITitleProps) {
  return (
    <StyledListTitle>
      <Text {...rest}>{children}</Text>
    </StyledListTitle>
  );
}

export default ListTitle;

const StyledListTitle = styled.div`
  height: 4.5rem;
  line-height: 4.5rem;
  border-bottom: 0.2rem solid #333;
`;
