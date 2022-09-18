import styled from 'styled-components';

export const Container = styled.div`
    width: 100%;
    height: 80px;

    background-color: #FDFD96;

    margin: 10px 60px 10px 60px;
    
    display: flex;
    justify-content: space-between;

    padding: 0 20px 0 20px;

    align-items: center;
    text-align: center;

    border-radius: 10px;

`;

export const Title = styled.h1``;

export const IconButton = styled.button`
    background-color: #FDFD96;

    border-radius: 5px;

    transition: opacity .3s;

    &:hover {
        opacity: .7;
        background-color: #F0E68C;
    }
`;