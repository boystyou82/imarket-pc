import React, { ReactNode } from 'react';
import Header from './header';
import Footer from './footer/Footer';
import TestStatusChanger from './TestStatusChanger';

type Props = {
  children: ReactNode;
};

function Layout({ children }: Props) {
  return (
    <>
      <TestStatusChanger />
      <Header />
      <main>{children}</main>
      <Footer />
    </>
  );
}

export default Layout;
