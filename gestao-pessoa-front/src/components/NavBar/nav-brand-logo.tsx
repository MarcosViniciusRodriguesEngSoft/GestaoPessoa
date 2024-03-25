import React from "react";

interface Props {
    className?: string;
    logo?: string | React.ReactNode;
    logoType: "Icon" | "Img";
}

const NavBrandLogo: React.FunctionComponent<Props> = ({ className, logo, logoType }: Props) => {
    return (
        <React.Fragment>
            {logoType === "Icon" ?
                <div className={className}>
                    {logo}
                </div>
                : null}
            {logoType === "Img" && typeof logo === 'string' ?
                < div className={className}>
                    {logo && <img src={logo} alt="logo" />}
                </div >
                : null}
        </React.Fragment>
    );
}

export default NavBrandLogo;
