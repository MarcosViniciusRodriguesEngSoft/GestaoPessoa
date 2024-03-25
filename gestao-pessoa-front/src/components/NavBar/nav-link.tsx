import { NavLink } from "react-router-dom";

interface Props {
    to: string;
    children?: React.ReactNode;
}

const NavLinkItem: React.FunctionComponent<Props> = ({ to, children }: Props) => {
    return (
        <li>
            <NavLink to={to}>{children}</NavLink>
        </li>
    );
}

export default NavLinkItem;