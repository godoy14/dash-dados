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
    DatePickerContainer
} from './styles';

const Dashboard: React.FC = () => {

    const todayLastYear = new Date(new Date().setDate(new Date().getDate() - 365));
    const today = new Date();

    const [dataInicio, setDataInicio] = useState<Date>(todayLastYear);
    const [dataTermino, setDataTermino] = useState<Date>(today);
    console.log(dataInicio);

    const lineChartData = useMemo(() => {
        const chartData = [];
        for (let d = dataInicio; d <= dataTermino; d.setMonth((d.getMonth() + 1) + 1) ) {
            const filteredData = leads.filter(item => (new Date(item.dateIn).getMonth() + 1) == (d.getMonth() + 1));
            let data = {
                name: String(d.getMonth() + 1) + '/' + String(d.getFullYear()),
                total: filteredData.length,
                totalSite: filteredData.filter(item => item.fonte === 'SITE').length,
                totalTelefone: filteredData.filter(item => item.fonte === 'TELEFONE').length,
                totalPortal: filteredData.filter(item => item.fonte === 'PORTAL').length,
                totalPresencial: filteredData.filter(item => item.fonte === 'PRESENCIAL').length
            }
            chartData.push(data);
            console.log(data);
            console.log(dataInicio);
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
                <ItemHeader title="Pré-Vendas" />
                <LineChartBox data={lineChartData} title="Total de Leads por mês" />

                <ItemHeader title="Vendas" />

                <ItemHeader title="Locação" />

                <ItemHeader title="Chamados" />
            </ListItemContainer>

        </Container>
    )
}

export default Dashboard;