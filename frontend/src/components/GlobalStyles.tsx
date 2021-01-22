import { createGlobalStyle } from 'styled-components/macro';
import reset from 'styled-reset';

const GlobalStyles = createGlobalStyle`
    ${reset}
    html{overflow-y:scroll;font-size:62.5%}
    body{font-size:1.4rem;font-family: "Noto Sans KR";font-weight:400}

    div, ul, li{box-sizing:border-box;}

    p,span{line-height:1}

    input, button {margin:0;padding:0;outline:0;border:0;border-radius:0;background-color: transparent}
    button{font-size:1.4rem;font-family: "Noto Sans KR";font-weight:400;cursor: pointer}
    a{text-decoration:none;color:inherit;cursor: pointer}
`;

export default GlobalStyles;
