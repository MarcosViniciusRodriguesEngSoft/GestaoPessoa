import React, { useEffect } from 'react';
import useMonitorScreen from '../../_config/helpers/monitor-screen';
import SelectItem from '../SelectElements';
import "./styles.css";

interface PaginationProps {
    data: number;
    itemsPerPage?: number;
    onPageChange: (page: number) => void;
    handleItemsPerPageChange?: (value: number) => void;
    currentPage: number;
    setCurrentPage: React.Dispatch<React.SetStateAction<number>>;
}

const Pagination: React.FC<PaginationProps> = ({ data, itemsPerPage, onPageChange, handleItemsPerPageChange, currentPage, setCurrentPage }) => {
    const monitorScreen = useMonitorScreen();

    const renderPaginationButtons = () => {
        const maxButtons = maxButtonPagination();
        const totalPages = Math.ceil(data / itemsPerPage);
        const halfMaxButtons = Math.floor(maxButtons / 2);

        let startPage = Math.max(currentPage - halfMaxButtons, 1);
        let endPage = Math.min(startPage + maxButtons - 1, totalPages);

        if (endPage - startPage < maxButtons - 1) {
            startPage = Math.max(endPage - maxButtons + 1, 1);
        }

        const buttons = [];
        for (let i = startPage; i <= endPage; i++) {
            buttons.push(
                <button
                    key={i}
                    onClick={() => handlePageChange(i)}
                    className={currentPage === i ? 'active' : ''}
                >
                    {i}
                </button>
            );
        }
        return buttons;
    };

    const maxButtonPagination = () => {
        let numberButton = 10;
        if (monitorScreen <= 1000) {
            numberButton = 8;
        }
        if (monitorScreen <= 680) {
            numberButton = 5;
        }
        if (monitorScreen <= 480) {
            numberButton = 3;
        }
        return numberButton;
    }

    useEffect(() => {
        const totalPages = Math.ceil(data / itemsPerPage);
        if (currentPage > totalPages) {
            setCurrentPage(totalPages);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [currentPage, data, itemsPerPage]);

    const handlePageChange = (page: number) => {
        setCurrentPage(page);
        onPageChange(page);
    };

    const handleGoToStart = () => {
        setCurrentPage(1);
        onPageChange(1);
    };

    const handleGoToEnd = () => {
        const totalPages = Math.ceil(data / itemsPerPage);
        setCurrentPage(totalPages);
        onPageChange(totalPages);
    };

    return (
        <div>
            <div className="pagination">
                {monitorScreen >= 480 ?
                    <button
                        onClick={handleGoToStart}
                        disabled={currentPage === 1}
                    >
                        {'<<'}
                    </button>
                    : null}
                <button
                    onClick={() => handlePageChange(currentPage - 1)}
                    disabled={currentPage === 1}
                >
                    {'<'}
                </button>
                {renderPaginationButtons()}
                <button
                    onClick={() => handlePageChange(currentPage + 1)}
                    disabled={currentPage === Math.ceil(data / itemsPerPage)}
                >
                    {'>'}
                </button>

                {monitorScreen >= 480 ?
                    <button
                        onClick={handleGoToEnd}
                        disabled={currentPage === Math.ceil(data / itemsPerPage)}
                    >
                        {'>>'}
                    </button>
                    : null}
            </div>

            <SelectItem
                setItem={handleItemsPerPageChange}
                totalItem={itemsPerPage}
            />
        </div>
    );
};

export default Pagination;
