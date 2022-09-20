import React, { useState } from "react";
import { IconContext } from "react-icons";
import { BiChevronsRight, BiChevronsDown } from "react-icons/bi";

import {
    Container,
    Title,
    IconButton
} from './styles';

interface IItemHeaderProps {
    title: string;
    status: boolean;
    handleSetCardStatus(): void;
}

const ItemHeader: React.FC<IItemHeaderProps> = ({
    title, status, handleSetCardStatus
}) => {

    return (
        <Container>
            <Title>
                {title}
            </Title>
            <IconButton onClick={handleSetCardStatus}>
                <IconContext.Provider value={{ color: 'black', size: '50px' }}>
                    {status ? <BiChevronsRight /> : <BiChevronsDown />}
                </IconContext.Provider>
            </IconButton>
        </Container>
    )
}

export default ItemHeader;