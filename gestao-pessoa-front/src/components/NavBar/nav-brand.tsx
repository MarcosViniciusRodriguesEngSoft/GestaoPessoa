import { ReactNode } from "react";
import { Link } from "react-router-dom";

interface Props {
    to: string;
    children?: React.ReactNode;
    className?: string;
    logo?: ReactNode;
}

const NavBrand: React.FunctionComponent<Props> = ({ to, children, className, logo }: Props) => {
    return (
        <Link to={to} className={className}>
            {logo}
            {children}
        </Link>
    );
}

export default NavBrand;