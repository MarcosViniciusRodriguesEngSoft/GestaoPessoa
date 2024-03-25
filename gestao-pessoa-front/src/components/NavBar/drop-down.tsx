import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

interface Props {
    title: string
    children?: React.ReactNode;
}

const DropDown: React.FunctionComponent<Props> = ({ title, children }: Props) => {
    const [isOpen, setIsOpen] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const unlisten = () => {
            setIsOpen(false)
        };
        return unlisten;
    }, [navigate]);

    return (
        <div className="dropdown" >
            <li className="dropdown-title" onClick={() => setIsOpen(!isOpen)}>{title}</li>
            {isOpen && (
                <ul className="dropdown-list">
                    {React.Children.map(children, child => (
                        <React.Fragment>{child}</React.Fragment>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default DropDown;