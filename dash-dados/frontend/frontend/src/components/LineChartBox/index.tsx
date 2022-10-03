import React from "react";

import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    ResponsiveContainer
} from 'recharts';


import {
    Container,
    Header,
    LegendContainer,
    ButtonContainer,
    ChartContainer,
    Legend,
    Content
} from "./styles";

interface ILineChartBox {
    title: string,
    data: {
        name: string;
        total: number;
        totalSite: number;
        totalTelefone: number;
        totalPortal: number;
        totalPresencial: number;
    }[],
}

const LineChartBox: React.FC<ILineChartBox> = ({
    title,
    data
}) => {
    return (
        <Container>
            <Header>
                <h1>{title}</h1>
            </Header>
            <Content>
                <LegendContainer>
                    <h2>Legendas</h2>
                    <Legend color='#ff0000'>
                        <div></div>
                        <span>Total</span>
                    </Legend>
                    <Legend color='#0000ff'>
                        <div></div>
                        <span>Site</span>
                    </Legend>
                    <Legend color='#00ff00'>
                        <div></div>
                        <span>Telefone</span>
                    </Legend>
                    <Legend color='#FFA500'>
                        <div></div>
                        <span>Portal</span>
                    </Legend>
                    <Legend color='#000000'>
                        <div></div>
                        <span>Presecial</span>
                    </Legend>
                </LegendContainer>

                <ChartContainer>
                    <ResponsiveContainer>
                        <LineChart
                            data={data}
                            margin={{
                                top: 5,
                                right: 30,
                                left: 20,
                                bottom: 5,
                            }}>

                            <CartesianGrid />
                            <XAxis dataKey="name" />
                            <YAxis />
                            <Tooltip />
                            <Line
                                type="monotone"
                                dataKey="total"
                                name="Total"
                                stroke="red"
                                strokeWidth={5}
                                dot={{ r: 5 }}
                                activeDot={{ r: 8 }}
                            />
                            <Line
                                type="monotone"
                                dataKey="totalSite"
                                name="Site"
                                stroke="blue"
                                strokeWidth={5}
                                dot={{ r: 5 }}
                                activeDot={{ r: 8 }}
                            />
                            <Line
                                type="monotone"
                                dataKey="totalTelefone"
                                name="Telefone"
                                stroke="green"
                                strokeWidth={5}
                                dot={{ r: 5 }}
                                activeDot={{ r: 8 }}
                            />
                            <Line
                                type="monotone"
                                dataKey="totalPortal"
                                name="Portal"
                                stroke="orange"
                                strokeWidth={5}
                                dot={{ r: 5 }}
                                activeDot={{ r: 8 }}
                            />
                            <Line
                                type="monotone"
                                dataKey="totalPresencial"
                                name="Presencial"
                                stroke="black"
                                strokeWidth={5}
                                dot={{ r: 5 }}
                                activeDot={{ r: 8 }}
                            />
                        </LineChart>
                    </ResponsiveContainer>
                </ChartContainer>

            </Content>
            <ButtonContainer href="/dash/leads">
                <button>Ver mais!</button>
            </ButtonContainer>

        </Container>
    )
}

export default LineChartBox;