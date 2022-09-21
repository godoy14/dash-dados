import React from "react";

import Dashboard from "../../pages/Dashboard";

import {
    Container
} from './styles';

const Content: React.FC<{ children: React.ReactNode }> = ({
    children
}) => {
    
    return (
        <Container>
            {children}
        </Container>
    )

}

export default Content;