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
}

const ItemHeader: React.FC<IItemHeaderProps> = ({
    title
}) => {

    const [cardStatus, setCardStatus] = useState<boolean>(true);

    const handleSetCardStatus = () => {
        setCardStatus(!cardStatus);
    }

    return (
        <Container>
            <Title>
                {title}
            </Title>
            <IconButton onClick={handleSetCardStatus}>
                <IconContext.Provider value={{ color: 'black', size: '50px' }}>
                    {cardStatus ? <BiChevronsRight /> : <BiChevronsDown />}
                </IconContext.Provider>
            </IconButton>
        </Container>
    )
}

export default ItemHeader;