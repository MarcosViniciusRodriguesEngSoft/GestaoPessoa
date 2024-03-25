
interface Props {
    children?: React.ReactNode;
    className?: string;
}

const Nav: React.FunctionComponent<Props> = ({ children, className }: Props) => {
    return (
        <ul className={className}>{children}</ul>
    );
}

export default Nav;