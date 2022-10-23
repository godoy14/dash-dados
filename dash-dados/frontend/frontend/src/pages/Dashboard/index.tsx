import axios from "axios";
import React, { useEffect, useMemo, useState } from "react";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import ContentHeaderDash from "../../components/ContentHeaderDash";
import ItemHeader from "../../components/ItemHeader";
import LineChartBox from "../../components/LineChartBox";
import { LeadModel } from "../../models/lead";

import leads from "../../repositories_tests/leads";

import {
    Container,
    ListItemContainer,
    SelectDateContainer,
    TextSelectDate,
    DatePickerContainer
} from './styles';

interface ILineChartData {
    name: string;
    total: number;
    totalSite: number;
    totalTelefone: number;
    totalPortal: number;
    totalPresencial: number;
}

const Dashboard: React.FC = () => {

    const [dataInicio, setDataInicio] = useState<Date>(new Date(new Date().setDate(new Date().getDate() - 365)));
    const [dataTermino, setDataTermino] = useState<Date>(new Date());

    const [dataLead, setDataLead] = useState<LeadModel[]>([]);
    const [leadLineChartData, setLeadLineChartData] = useState<ILineChartData[]>([]);

    const [cardStatus, setCardStatus] = useState<boolean>(true);
    const handleSetCardStatus = () => {
        setCardStatus(!cardStatus);
    }

    // const lineChartData = useMemo(() => {
    //     const chartData = [];
    //     const filteredData = leads.filter(item => new Date(item.dateIn) >= dataInicio && new Date(item.dateIn) <= dataTermino);
    //     for (let d = new Date(dataInicio); d <= dataTermino; d.setMonth((d.getMonth()) + 1)) {
    //         const filteredDataPerMonth = filteredData.filter(item => (new Date(item.dateIn).getMonth()) == (d.getMonth()) && (new Date(item.dateIn).getFullYear()) == (d.getFullYear()));
    //         let data = {
    //             name: String(d.getMonth() + 1) + '/' + String(d.getFullYear()),
    //             total: filteredDataPerMonth.length,
    //             totalSite: filteredDataPerMonth.filter(item => item.fonte === 'SITE').length,
    //             totalTelefone: filteredDataPerMonth.filter(item => item.fonte === 'TELEFONE').length,
    //             totalPortal: filteredDataPerMonth.filter(item => item.fonte === 'PORTAL').length,
    //             totalPresencial: filteredDataPerMonth.filter(item => item.fonte === 'PRESENCIAL').length
    //         }
    //         chartData.push(data);
    //     }
    //     return chartData;
    // }, [dataInicio, dataTermino]);

    useEffect(() => {
        var dataInicioStr = dataInicio.getFullYear().toString() + '-' + (dataInicio.getMonth() + 1).toString().padStart(2, "0") + '-' + dataInicio.getDate().toString().padStart(2, "0") + 'T00:00';
        var dataTerminoStr = dataTermino.getFullYear().toString() + '-' + (dataTermino.getMonth() + 1).toString().padStart(2, "0") + '-' + dataTermino.getDate().toString().padStart(2, "0") + 'T00:00';
        axios.get('http://localhost:8080/negocios/leads/imobiliarias/1/datesSearch?startDate=' + dataInicioStr + '&finishDate=' + dataTerminoStr)
            .then(response => {
                const r = response.data.map((item: LeadModel) => {
                    return {
                        id: item.id,
                        imobiliariaId: item.imobiliariaId,
                        pipelineId: item.pipelineId,
                        status: item.status,
                        fonte: item.fonte,
                        responsavelId: item.responsavelId,
                        idBitrix: item.idBitrix,
                        codImovel: item.codImovel,
                        dateIn: item.dateIn,
                        dateOut: item.dateOut,
                        tipo: item.tipo,
                    }
                })
                setDataLead(r);
            })
        const chartData = [];
        var monthDifference = dataTermino.getMonth() - dataInicio.getMonth() + (12 * (dataTermino.getFullYear() - dataInicio.getFullYear())) + 1;
        // for (let d = new Date(dataInicio); d <= dataTermino; d.setMonth((d.getMonth()) + 1)) {
        //     console.log("d");
        //     console.log(d);
        //     console.log(dataTermino);
        //     const filteredDataPerMonth = dataLead.filter(item => (new Date(item.dateIn).getMonth() + 1) == (d.getMonth() + 1) && (new Date(item.dateIn).getFullYear()) == (d.getFullYear()));
        //     let data = {
        //         name: String(d.getMonth() + 1) + '/' + String(d.getFullYear()),
        //         total: filteredDataPerMonth.length,
        //         totalSite: filteredDataPerMonth.filter(item => item.fonte === 'SITE').length,
        //         totalTelefone: filteredDataPerMonth.filter(item => item.fonte === 'TELEFONE').length,
        //         totalPortal: filteredDataPerMonth.filter(item => item.fonte === 'PORTAL').length,
        //         totalPresencial: filteredDataPerMonth.filter(item => item.fonte === 'PRESENCIAL').length
        //     }
        //     chartData.push(data);
        // }
        let d = new Date(dataInicio);
        while (monthDifference > 0){
            const filteredDataPerMonth = dataLead.filter(item => (new Date(item.dateIn).getMonth() + 1) == (d.getMonth() + 1) && (new Date(item.dateIn).getFullYear()) == (d.getFullYear()));
            let data = {
                name: String(d.getMonth() + 1) + '/' + String(d.getFullYear()),
                total: filteredDataPerMonth.length,
                totalSite: filteredDataPerMonth.filter(item => item.fonte === 'SITE').length,
                totalTelefone: filteredDataPerMonth.filter(item => item.fonte === 'TELEFONE').length,
                totalPortal: filteredDataPerMonth.filter(item => item.fonte === 'PORTAL').length,
                totalPresencial: filteredDataPerMonth.filter(item => item.fonte === 'PRESENCIAL').length
            }
            chartData.push(data);

            monthDifference -= 1;
            d.setMonth(d.getMonth() + 1);

        }
        setLeadLineChartData(chartData);
        console.log(chartData);
    }, [dataInicio, dataTermino, cardStatus]);

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
                {cardStatus ? <> </> : <LineChartBox
                                            data={leadLineChartData}
                                            title="Relação entre Leads e Veículos de Captação!"
                                            seeButton={true} />}


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