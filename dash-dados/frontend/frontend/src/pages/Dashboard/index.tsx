import React, { useEffect, useMemo, useState } from "react";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import ContentHeaderDash from "../../components/ContentHeaderDash";
import ItemHeader from "../../components/ItemHeader";
import LineChartBox from "../../components/LineChartBox";

import leads from "../../repositories_tests/leads";

import {
    Container,
    ListItemContainer,
    SelectDateContainer,
    TextSelectDate,
    DatePickerContainer,
    ButtonContainer
} from './styles';

const Dashboard: React.FC = () => {

    const [dataInicio, setDataInicio] = useState<Date>(new Date(new Date().setDate(new Date().getDate() - 365)));
    const [dataTermino, setDataTermino] = useState<Date>(new Date());

    const [cardStatus, setCardStatus] = useState<boolean>(true);
    const handleSetCardStatus = () => {
        setCardStatus(!cardStatus);
    }

    const lineChartData = useMemo(() => {
        const chartData = [];
        const filteredData = leads.filter(item => new Date(item.dateIn) >= dataInicio && new Date(item.dateIn) <= dataTermino);
        for (let d = new Date(dataInicio); d <= dataTermino; d.setMonth((d.getMonth()) + 1)) {
            const filteredDataPerMonth = filteredData.filter(item => (new Date(item.dateIn).getMonth()) == (d.getMonth()) && (new Date(item.dateIn).getFullYear()) == (d.getFullYear()));
            let data = {
                name: String(d.getMonth() + 1) + '/' + String(d.getFullYear()),
                total: filteredDataPerMonth.length,
                totalSite: filteredDataPerMonth.filter(item => item.fonte === 'SITE').length,
                totalTelefone: filteredDataPerMonth.filter(item => item.fonte === 'TELEFONE').length,
                totalPortal: filteredDataPerMonth.filter(item => item.fonte === 'PORTAL').length,
                totalPresencial: filteredDataPerMonth.filter(item => item.fonte === 'PRESENCIAL').length
            }
            chartData.push(data);
        }
        return chartData;
    }, [dataInicio, dataTermino]);

    return (
        <Container>
            <ContentHeaderDash title="Dashboard">
                <SelectDateContainer>
                    <TextSelectDate>
                        Data de início:
                    </TextSelectDate>
                    <DatePickerContainer>
                        <DatePicker
                            selected={dataInicio}
                            onChange={(date: Date) => setDataInicio(date)}
                            dateFormat="dd/MM/yyyy"
                            maxDate={dataTermino}
                        />
                    </DatePickerContainer>
                </SelectDateContainer>

                <SelectDateContainer>
                    <TextSelectDate>
                        Data de término:
                    </TextSelectDate>
                    <DatePickerContainer>
                        <DatePicker
                            selected={dataTermino}
                            onChange={(date: Date) => setDataTermino(date)}
                            dateFormat="dd/MM/yyyy"
                        />
                    </DatePickerContainer>
                </SelectDateContainer>
            </ContentHeaderDash>

            <ListItemContainer>
                <ItemHeader
                    title="Pré-Vendas"
                    handleSetCardStatus={handleSetCardStatus}
                    status={cardStatus}
                />
                { cardStatus ?  <> </> : <LineChartBox data={lineChartData} title="Relação entre Leads e Veículos de Captação!" />}
                

                <ItemHeader
                    title="Vendas"
                    handleSetCardStatus={() => { }}
                    status={true}
                />

                <ItemHeader
                    title="Locação"
                    handleSetCardStatus={() => { }}
                    status={true}
                />

                <ItemHeader
                    title="Chamados"
                    handleSetCardStatus={() => { }}
                    status={true}
                />
            </ListItemContainer>

        </Container>
    )
}

export default Dashboard;