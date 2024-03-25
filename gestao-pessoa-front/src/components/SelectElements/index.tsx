import "./styles.css";


interface Props {
    totalItem: number;
    setItem: (value: number) => void;
}

const SelectItem: React.FunctionComponent<Props> = ({ totalItem, setItem }: Props) => {

    return (
        <div className="items-per-page">
            <label htmlFor="itemsPerPage">Itens por página:</label>
            <select id="itemsPerPage" value={totalItem} onChange={(e) => setItem(parseInt(e.target.value, 10))}>
                <option value={10}>10</option>
                <option value={20}>20</option>
                <option value={40}>40</option>
                <option value={50}>50</option>
                <option value={100}>100</option>
            </select>
        </div>
    );
}

export default SelectItem;
