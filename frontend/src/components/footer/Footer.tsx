import React from 'react';
import styled from 'styled-components/macro';

const DummyFooter = styled.div`
  border-top: 0.1rem solid #000;
  & > div {
    width: 120rem;
    margin: 0 auto;
  }
`;

function Footer() {
  return (
    <DummyFooter>
      <div>
        <img src="/static/dummyFooter.png" alt="더미 푸터" />
      </div>
    </DummyFooter>
  );
}

export default Footer;
