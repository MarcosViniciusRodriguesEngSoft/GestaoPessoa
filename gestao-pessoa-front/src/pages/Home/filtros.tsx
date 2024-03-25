import { Button } from 'antd';
import Search from 'antd/es/input/Search';
import { memo, useEffect } from 'react';
import { Link } from 'react-router-dom';

interface Props {
    find: () => void;
    setSearch?: React.Dispatch<React.SetStateAction<string>>;
    search?: string;
}

const Filtros = memo(({ find, setSearch, search }: Props) => {

    useEffect(() => {
        const typingTimeout = setTimeout(() => {
            find();
        }, 800);
        return () => clearTimeout(typingTimeout);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [search]);

    return (
        <div className="header-filter">
            <div className="wp-100 flex">
                <div className="search">
                    <div>
                        <Search
                            placeholder="Digite o nome da pessoa"
                            onChange={(e) => setSearch(e.target.value)}
                        />
                    </div>
                </div>
                <div className="header-cadastro-pessoa-button">
                    <div>
                        <Link to={`/cadastro-pessoa`} className="link">
                            <Button>Cadastrar Pessoa</Button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    )
})

export default Filtros;

