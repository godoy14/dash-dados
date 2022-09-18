import React from "react";

import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend
} from 'recharts';


import {
    Container
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
            <h1>{title}</h1>
                <LineChart
                    width={1000}
                    height={300}
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
                    <Legend />
                    <Line
                        type="monotone"
                        dataKey="total"
                        stroke="red"
                    />
                    <Line
                        type="monotone"
                        dataKey="totalSite"
                        stroke="blue"
                    />
                    <Line
                        type="monotone"
                        dataKey="totalTelefone"
                        stroke="green"
                    />
                    <Line
                        type="monotone"
                        dataKey="totalPortal"
                        stroke="orange"
                    />
                    <Line
                        type="monotone"
                        dataKey="totalPresencial"
                        stroke="black"
                    />

                </LineChart>
        </Container>
    )
}

export default LineChartBox;