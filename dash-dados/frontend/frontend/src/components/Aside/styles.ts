import styled from 'styled-components';

export const Container = styled.div`
    grid-area: AS;

    background-color: #0a3754;
`;

export const Header = styled.header`

    padding-left: 10px;
    
    height: 120px;
    
    display: flex;
    align-items: center;
    // justify-content: space-between;

    background-color: #000033;

    > p {
        //padding-left: 8px;
        color: #ffffff;
        font-size: 30px;
        font-weight: 900;
        text-align: center;
    }
    
`;

export const MenuContainer = styled.div`

    display: flex;
    flex-direction: column;

    align-items: left;

    margin-top: 30px;

;
`;

export const MenuItem = styled.div`

    height: 60px;

    display: flex;
    flex-direction: row;

    padding-top: 15px;
    padding-left: 20px;

    border-radius: 10px;

    transition: opacity .3s;

    &:hover {
        opacity: .7;
        background-color: #000033;
    }

    
`;

export const MenuItemText = styled.a`

    text-decoration: none;

    padding-left: 15px;

    height: 100%;

    font-size: 22px;
    font-weight: 500;

    color: #FFFFFF;
    

`;