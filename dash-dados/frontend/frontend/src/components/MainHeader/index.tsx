import React from "react";

import {
    Container,
    Welcome,
    DataAtt
} from './styles';

interface IMainHeaderProps {
    name: string;
    dataAtt: string;
}

const MainHeader: React.FC<IMainHeaderProps> = ({
    name, dataAtt
}) => {

    return (
        <Container>
            <Welcome><h1>Olá, {name}!</h1></Welcome>
            <DataAtt><p>Data de atualização dados: {dataAtt}</p></DataAtt>
        </Container>
    )

}

export default MainHeader;