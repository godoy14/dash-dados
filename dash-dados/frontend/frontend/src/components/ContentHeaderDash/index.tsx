import React from "react";
import { IconContext } from "react-icons";
import { VscDashboard } from "react-icons/vsc";

import {
    Container,
    TitleContainer,
    Controllers,
    TextContainer,
    TextDetailContainer
} from './styles';

interface IContentHeaderProps {
    title: string;
    children: React.ReactNode;
}

const ContentHeaderDash: React.FC<IContentHeaderProps> = ({
    title, children
}) => {
    return (
        <Container>
            <TextContainer>
                <TitleContainer>
                    <IconContext.Provider value={{ color: 'black', size: '25px' }}>
                        <VscDashboard />
                    </IconContext.Provider>
                    <h1>{title}</h1>
                </TitleContainer>
                <TextDetailContainer>
                    Aqui você encontra as categorias de relátorios disponiveis ao seu plano!
                </TextDetailContainer>
            </TextContainer>

            <Controllers>
                {children}
            </Controllers>

        </Container>
    )
}

export default ContentHeaderDash;