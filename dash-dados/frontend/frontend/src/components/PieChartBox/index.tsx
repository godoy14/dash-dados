import React from "react";
import { Cell, Pie, PieChart, ResponsiveContainer } from "recharts";

import {
    Container,
    Header
} from './styles';

interface IPieChartProps {
    title: string,
    data: {
        name: string;
        value: number;
        percent: number;
        color: string
    }[]
}

const PieChartBox: React.FC<IPieChartProps> = ({ data, title }) => {
    return (
        <Container>
            <Header>
                <h1>{title}</h1>
            </Header>
            <ResponsiveContainer>
                <PieChart>
                    <Pie data={data} labelLine={false} dataKey="percent">
                        {
                            data.map((indicator) => (
                                <Cell key={indicator.name} fill={indicator.color} />
                            ))
                        }
                    </Pie>
                </PieChart>
            </ResponsiveContainer>

        </Container>
    )
};

export default PieChartBox;