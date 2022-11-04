import styled from 'styled-components';

export const Container = styled.div`

    width: 100%;

    background-color: #ffffe0;

    display: flex;
    flex-direction: column;

    height: 400px;

    margin: 0 120px 0 120px;

    border-bottom-left-radius: 15px;
    border-bottom-right-radius: 15px;
`;

export const Header = styled.div`
    width: 100%;

    text-align: center;

    > h1 {
        padding-left: 16px;
    }
    
`;

export const Content = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
`;

export const LegendContainer = styled.div`
    list-style: none;
    padding-left: 15px;
    padding-bottom: 10px;

    background-color: #DCDCDC;

    border-radius: 10px;

    margin-left: 10px;

    width: 250px;
    height: fit-content;

    > h2 {
        margin-top: 10px;
        text-align: center;
    }
`;

export const Legend = styled.li`
    display: flex;
    align-items: center;

    margin-bottom: 7px;
    margin-left: 16px;

    padding-right: 16px;

    > div {
        background-color: ${props => props.color};

        width: 40px;
        height: 40px;

        border-radius: 5px;

        font-size: 14px;
        line-height: 40px;
        text-align: center;
    };

    > span {
        margin-left: 5px;
    }
`;

export const ChartContainer = styled.div`
    width: 100%;
    flex: 1;
    padding-right: 15px;
`;
export const ButtonContainer = styled.a`
    width: 100%;
    height: 60px;
    display: flex;
    margin-bottom: 5px;
    justify-content: center;

    > button {
        background-color: #0a3754;
        border-radius: 25px;

        width: 100px;

        font-weight: bold;

        color: white;
    }
`;
