import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Layout from '../components/Layout';
import Dashboard from '../pages/Dashboard';
import Inicio from '../pages/Inicio';
import Suporte from '../pages/Suporte';

const AppRoutes: React.FC = () => (
    <Layout>
        <Routes>
            <Route path="/" element={<Inicio />} />
            <Route path="/dash" element={<Dashboard />} />
            <Route path="/suporte" element={<Suporte />} />
        </Routes>
    </Layout>
)

export default AppRoutes;