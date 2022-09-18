import styled from 'styled-components';

export const Container = styled.div`
    grid-area: MH;

    background-color: #0a3754;

    display: flex;

    justify-content: space-between;
    align-items: center;

    color: #ffffff;

`;

export const Welcome = styled.div`
    width: 100%;
    
    padding-left: 15px;
    text-align: center;
`;

export const DataAtt = styled.div`

    padding-right: 20px;
    width: 270px;

    > p {
        text-align: center;
    }
`;
