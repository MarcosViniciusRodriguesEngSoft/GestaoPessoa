import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import logo from "../../_assets/img/png/logo.png";
import Hamburguer from "./hamburguer";
import Nav from "./nav";
import NavBrand from "./nav-brand";
import NavBrandLogo from "./nav-brand-logo";
import NavLinkItem from "./nav-link";
import Navebar from "./navbar";
import "./styles.css";

const NavbarTwo = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const unlisten = () => {
            setMenuOpen(false)
        };
        return unlisten;
    }, [navigate]);

    return (
        <Navebar className="navbar" >
            <NavBrand to="/" className="logo-title" logo={<NavBrandLogo logoType="Img" logo={logo} className="navbrand-logo" />}>Gestão de Pessoas</NavBrand>
            <Hamburguer
                className="menu"
                setMenuOpen={setMenuOpen}
                menuOpen={menuOpen}
            />
            <Nav className={menuOpen ? "open" : ""}>
                <NavLinkItem to="/">Home</NavLinkItem>
                {/* <DropDown title="Drop">
                    <NavLinkItem to="/contact">Contact</NavLinkItem>
                    <NavLinkItem to="/faq">FAQ</NavLinkItem>
                </DropDown>
                <Space width={50} /> */}
            </Nav>
        </Navebar>
    )
};

export default NavbarTwo;