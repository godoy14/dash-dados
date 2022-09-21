import styled from 'styled-components';

export const Container = styled.div`
    background-color: #DCDCDC;
    height: 100%;
`;

export const ListItemContainer = styled.div`
    display: flex;

    justify-content: space-between;
    flex-wrap: wrap;

`;

export const ItemHeader = styled.div`
    display: flex;
    justify-content: space-between;
    
    width: 100px;
    height: 80px;

    border-radius: 10px;

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
