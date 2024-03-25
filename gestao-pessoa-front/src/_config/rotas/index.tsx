import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavbarTwo from "../../components/NavBar";
import CadastroPessoa from "../../pages/CadastroPessoa";
import Home from "../../pages/Home";

function Rotas() {
    return (
        <BrowserRouter>
            <NavbarTwo />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/cadastro-pessoa/:idPessoa?" element={<CadastroPessoa />} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;