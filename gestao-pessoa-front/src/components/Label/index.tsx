import "./styles.css";
interface Props {
    children?: React.ReactNode,
    htmlFor?: string,
    styleLabel?: React.CSSProperties,
    elementRequired?: string,
    required?: boolean,
    styleSpan?: React.CSSProperties,
    className?: string,
}

const Label: React.FunctionComponent<Props> = ({ children, htmlFor, styleLabel, elementRequired, required, styleSpan, className }: Props) => {
    return (
        <div className={className ? className : "box-label"}>
            <label style={styleLabel} htmlFor={htmlFor}>{children}
                {required ?
                    <span style={styleSpan ? styleSpan : { color: "#FF0000" }}>{elementRequired ? elementRequired : "*"}</span>
                    : null}
            </label>
        </div >
    );
}

export default Label;