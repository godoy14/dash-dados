import React, { useMemo, useState } from "react";

import LineChartBox from "../../components/LineChartBox";
import PieChartBox from "../../components/PieChartBox";

import leads from "../../repositories_tests/leads";

import {
    Container
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

    const dataTipo = useMemo(() => {
        const filteredData = leads.filter(item => new Date(item.dateIn) >= dataInicio && new Date(item.dateIn) <= dataTermino);
        
        const total = filteredData.length;
        const totalLoc = filteredData.filter(item => item.tipo === 'LOCACAO').length;
        const totalVenda = filteredData.filter(item => item.fonte === 'VENDA').length;

        const percentVenda = Number(((totalVenda/total) * 100).toFixed(1));
        const percentLoc = Number(((totalLoc/total) * 100).toFixed(1));
        
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

    return(
        <Container>
            <h1>ListCharts - Página em desenvolvimento</h1>
            <LineChartBox data={dataFonte} title="Relação entre Leads e Veículos de Captação!" />

            <PieChartBox data={dataTipo} title="Relação entre Leads de Locação e Vendas" />

        </Container>
    )
};

export default ListCharts;