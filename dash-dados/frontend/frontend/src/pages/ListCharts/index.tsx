import React, { useMemo, useState } from "react";

import LineChartBox from "../../components/LineChartBox";
import PieChartBox from "../../components/PieChartBox";

import { IconContext } from "react-icons";
import { TbZoomMoney } from "react-icons/tb";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import leads from "../../repositories_tests/leads";

import {
    Container,
    ContainerHeader,
    TitleContainer,
    TextContainer,
    Content,
    SelectDateContainer,
    TextSelectDate,
    DatePickerContainer,
    TextDetailContainer,
    ControllerContainer,
    ChartBox,
    Box
} from './styles';

const ListCharts: React.FC = () => {

    const [dataInicio, setDataInicio] = useState<Date>(new Date(new Date().setDate(new Date().getDate() - 365)));
    const [dataTermino, setDataTermino] = useState<Date>(new Date());

    const dataFonte = useMemo(() => {
        const chartData = [];
        const filteredData = leads.filter(item => new Date(item.dateIn) >= dataInicio && new Date(item.dateIn) <= dataTermino);
        for (let d = new Date(dataInicio); d <= dataTermino; d.setMonth((d.getMonth()) + 1)) {
            const filteredDataPerMonth = filteredData.filter(item => (new Date(item.dateIn).getMonth()) == (d.getMonth()) && (new Date(item.dateIn).getFullYear()) == (d.getFullYear()));
            let data = {
                name: String(d.getMonth() + 1).padStart(2, "0") + '/' + String(d.getFullYear()),
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

    const dataTipo = useMemo(() => {
        const filteredData = leads.filter(item => new Date(item.dateIn) >= dataInicio && new Date(item.dateIn) <= dataTermino);

        const total = filteredData.length;
        const totalLoc = filteredData.filter(item => item.tipo === 'LOCACAO').length;
        const totalVenda = filteredData.filter(item => item.tipo === 'VENDA').length;

        const percentVenda = Number(((totalVenda / total) * 100).toFixed(1));
        const percentLoc = Number(((totalLoc / total) * 100).toFixed(1));

        const chartData = [
            {
                name: 'VENDA',
                value: totalVenda,
                percent: percentVenda,
                color: '#ff0000'
            },
            {
                name: 'LOCACAO',
                value: totalLoc,
                percent: percentLoc,
                color: '#FFA500'
            }
        ]
        return chartData;
    }, [dataInicio, dataTermino]);

    return (
        <Container>
            <ContainerHeader>
                <TextContainer>
                    <TitleContainer>
                        <IconContext.Provider value={{ color: 'black', size: '25px' }}>
                            <TbZoomMoney />
                        </IconContext.Provider>
                        <h1>Pré Vendas</h1>
                    </TitleContainer>
                    <TextDetailContainer>
                        Segue a listagem de gráficos relacionados a Pré Vendas de Locação e Vendas!
                    </TextDetailContainer>
                </TextContainer>
                <ControllerContainer>
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
                </ControllerContainer>
            </ContainerHeader>
            <Content>
                <ChartBox>
                    <LineChartBox
                        data={dataFonte}
                        title="Relação entre Leads e Veículos de Captação!"
                        seeButton={false} />
                </ChartBox>
                <ChartBox>
                    <PieChartBox data={dataTipo} title="Relação entre Leads de Locação e Vendas" />
                </ChartBox>
            </Content>
            <Box />
        </Container>
    )
};

export default ListCharts;