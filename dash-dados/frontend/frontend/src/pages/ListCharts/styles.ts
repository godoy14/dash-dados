import styled from 'styled-components';


export const Container = styled.div`
    display: flex;
    flex-direction: column;

    background-color: #d3d3d3;
`;

export const ContainerHeader = styled.div`

    height: 80px;

    background-color: #C0C0C0;

    width: 100%;

    display: flex;
    justify-content: space-between;


`;

export const TitleContainer = styled.div`
    display: flex;
    text-align: center;
    align-items: center;

    padding-left: 15px;
    padding-top: 5px;

    > h1 {
        padding-left:10px;
    }
`;

export const ControllerContainer = styled.div`
    display: flex;
`;

export const TextContainer = styled.div``;

export const TextDetailContainer = styled.p`
    padding-left: 15px;
`;

export const Content = styled.div`
    width: 100%;
    display: flex;
    flex-wrap: wrap;

    > chartBox {
        background-color: red;
    }

`;

export const SelectDateContainer = styled.div`

    width: 175px;

    padding-top: 2px;
    padding-right: 15px;

    display: flex;
    flex-direction: column;
`;

export const TextSelectDate = styled.div`
    padding-left: 3px;
    
    font-size: 18px;
`;

export const DatePickerContainer = styled.div`

    .react-datepicker__input-container input{
        width: 100%;
        height: 46px;
        background-color: #1b2531;
        border: 1px solid #384459;
        border-radius: 5px;
        color: #9aaabe;
        padding: 0 20px;
        font-size: 18px;

        text-align: center;
    }
`;

export const ChartBox = styled.div`
    width: 100%;
    height: 450px;

    border-radius: 25px;

    margin-top: 40px;
    margin-left: 60px;
    margin-right: 60px;

    padding-top: 20px;
    padding-bottom: 20px;

    display: flex;

    background-color: #ffffe0;
`;

export const Box = styled.div`
    width: 100%;
    height: 40px;
    background-color: #d3d3d3;
`;
