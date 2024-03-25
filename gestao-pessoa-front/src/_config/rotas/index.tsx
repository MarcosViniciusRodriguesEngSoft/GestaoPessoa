import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavbarTwo from "../../components/NavBar";
import CadastroPessoa from "../../pages/CadastroPessoa";
import Home from "../../pages/Home";
import Contato from "../../pages/Contato";

function Rotas() {
    return (
        <BrowserRouter>
            <NavbarTwo />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/cadastro-pessoa/:idPessoa?" element={<CadastroPessoa />} />
                <Route path="/lista-contato/:idPessoa?" element={<Contato />} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;