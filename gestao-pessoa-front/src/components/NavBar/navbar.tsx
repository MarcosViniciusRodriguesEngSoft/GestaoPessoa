
interface Props {
    children?: React.ReactNode;
    className?: string;
}

const Navbar: React.FunctionComponent<Props> = ({ children, className }: Props) => {
    return (
        <div className={className}>
            <nav>
                {children}
            </nav>
        </div>
    );
}

export default Navbar;