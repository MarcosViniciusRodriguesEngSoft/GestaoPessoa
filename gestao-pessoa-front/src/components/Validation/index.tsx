import "./styles.css";

interface Props {
    validationFilds?: boolean,
    validationFild?: any,
    msgError?: string,
    msgSuccess?: string
    styleDescription?: React.CSSProperties,
    style?: React.CSSProperties,
}

const Validation: React.FunctionComponent<Props> = ({ validationFilds, validationFild, msgError, msgSuccess, styleDescription, style }: Props) => {
    const regra: string = validationFild;
    const hasError = validationFilds && (!regra || regra?.length === 0);

    return (
        <div className="box-validation" style={style}>
            {hasError || (regra?.length === 0 && msgError)  ?
                <p style={styleDescription ? styleDescription : { color: "#FF0000" }}>{msgError}</p>
                : null}

            {regra?.length > 0 && msgSuccess ?
                <p style={styleDescription ? styleDescription : { color: "#008000" }}>{msgSuccess}</p>
                : null}
        </div>
    );
}

export default Validation;