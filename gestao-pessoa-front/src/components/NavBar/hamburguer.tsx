interface Props {
    setMenuOpen: React.Dispatch<React.SetStateAction<boolean>>;
    menuOpen: boolean;
    className?: string;
}

const Hamburguer: React.FunctionComponent<Props> = ({ setMenuOpen, menuOpen, className }: Props) => {

    return (
        <div className={className} onClick={() => setMenuOpen(!menuOpen)}>
            <span></span>
            <span></span>
            <span></span>
        </div>
    );
}

export default Hamburguer;