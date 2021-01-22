import React, { ButtonHTMLAttributes, ReactNode } from 'react';
import styled, { css } from 'styled-components';

interface IButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  children: ReactNode;
  size?: 'xs' | 's' | 'm' | 'l' | 'xl';
  theme?: 'red' | 'white';
}

function Button({ children, ...props }: IButtonProps) {
  return (
    <StyledButton type="button" {...props}>
      {children}
    </StyledButton>
  );
}

export default Button;

interface Ibutton {
  [key: string]: any;
}

const buttonSize: Ibutton = {
  s: css`
    width: 6.5rem;
    height: 2.6rem;
    line-height: 2.6rem;
    font-size: 13px;
  `
};

const buttonTheme: Ibutton = {
  red: css`
    background-color: #f14949;
    color: #fff;

    &:hover {
      background-color: #d71e1e;
    }
  `,

  white: css`
    border: 1px solid #ccc;
    color: #666;

    &:hover {
      border: 1px solid #626161;
      background-color: #777;
      color: #fff;
    }
  `
};

const StyledButton = styled.button<IButtonProps>`
  ${({ size }) => size && buttonSize[size]}
  ${({ theme }) => theme && buttonTheme[theme]}
`;
