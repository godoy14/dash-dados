import React from 'react';
import { Routes,Route } from 'react-router-dom';

import Layout from '../components/Layout';
import Dashboard from '../pages/Dashboard';
import Inicio from '../pages/Inicio';
import Suporte from '../pages/Suporte';
import ListCharts from '../pages/ListCharts';

const AppRoutes: React.FC = () => (
    <Layout>
        <Routes>
            <Route path="/" element={<Inicio />} />
            <Route path="/dash" element={<Dashboard />} />
            <Route path="/suporte" element={<Suporte />} />
            <Route path="/dash/:type" element={<ListCharts />} />
        </Routes>
    </Layout>
)

export default AppRoutes;