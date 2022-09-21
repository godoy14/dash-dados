
import React from 'react';
import Aside from '../Aside';
import Content from '../Content';
import MainHeader from '../MainHeader';

import { Grid } from './styles';

const Layout: React.FC<{ children: React.ReactNode }> = ({
    children
}) => (
    <Grid>
        <Aside />
        <MainHeader name='Godoy' dataAtt='16/09/2022 22:10' />
        <Content>
            {children}
        </Content>
    </Grid>
);

export default Layout;
