import React from "react";

import { IconContext } from 'react-icons';
import { BiHomeAlt, BiSupport } from 'react-icons/bi';
import { VscDashboard, VscGraph } from 'react-icons/vsc';

import {
    Container,
    Header,
    MenuContainer,
    MenuItem,
    MenuItemText
} from './styles';

const Aside: React.FC = () => {

    return (
        <Container>
            <Header>
                <IconContext.Provider value={{ color: 'white', size: '80px' }}>
                    <VscGraph />
                </IconContext.Provider>
                <p>Dashboard Dados</p>
            </Header>
            <MenuContainer>

                <MenuItem>
                    <IconContext.Provider value={{ color: 'white', size: '25px' }}>
                        <BiHomeAlt />
                    </IconContext.Provider>
                    <MenuItemText href="/">
                        Início
                    </MenuItemText>
                </MenuItem>

                <MenuItem>
                    <IconContext.Provider value={{ color: 'white', size: '25px' }}>
                        <VscDashboard />
                    </IconContext.Provider>
                    <MenuItemText href="/">
                        Dashboard
                    </MenuItemText>
                </MenuItem>

                <MenuItem>
                    <IconContext.Provider value={{ color: 'white', size: '25px' }}>
                        <BiSupport />
                    </IconContext.Provider>
                    <MenuItemText href="/">
                        Suporte
                    </MenuItemText>
                </MenuItem>

            </MenuContainer>
        </Container>
    )

}

export default Aside;