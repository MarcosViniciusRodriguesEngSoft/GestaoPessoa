import { Dropdown, Menu, Modal, notification } from "antd";
import { useEffect, useState } from "react";
import { AiOutlineEllipsis } from "react-icons/ai";
import { FaAddressBook, FaUserEdit } from "react-icons/fa";
import { Link } from "react-router-dom";
import { IPessoaCleanDTO } from '../../_config/model/elotech-gestao-pessoa-api.model';
import { PageableResponse } from "../../_config/model/response.model";
import { usePessoaService } from "../../_config/services/pessoa.service";
import { formatCpf, formatLocalDate } from "../../_config/utils/format";
import Pagination from "../../components/Pagination";
import Filtros from "./filtros";
import "./styles.css";

function Home() {
    const pessoaService = usePessoaService();
    const [pessoaList, setPessoaList] = useState<PageableResponse<IPessoaCleanDTO>>();
    const [search, setSearch] = useState<string>();
    const [itemsPerPage, setItemsPerPage] = useState(10);
    const [currentPage, setCurrentPage] = useState(1);

    const findPessoa = () => {
        pessoaService.findPageable(search, currentPage, itemsPerPage)
            .then(({ data }) => {
                setPessoaList(data);
            })
            .catch((error) => {
                console.error('Erro ao carregar a lista de pessoas:', error);
                notification.error({
                    description: 'Desculpe, não foi possível renderizar a lista.',
                    type: 'error',
                    message: 'Erro!',
                    placement: 'bottomRight'
                });
            });
    }

    const deletePessoa = (pessoa) => {
        Modal.confirm({
            title: `Deseja realmente excluir o cadastro ?`,
            content: `Nome: ${pessoa.nome}`,
            okText: 'Sim',
            cancelText: 'Não',
            onOk: () =>
                pessoaService.deletePessoa(pessoa.id)
                    .then(() => {
                        notification.success({
                            description: `Cadastro excluído com sucesso.`,
                            type: "success",
                            message: "Sucesso!",
                            placement: 'bottomRight'
                        });
                    }).finally(() => {
                        findPessoa();
                    })
        });
    }

    const menu = (pessoa: IPessoaCleanDTO): any => (
        <Menu>
            <Menu.Item onClick={() => deletePessoa(pessoa)}>
                Deletar cadastro
            </Menu.Item>
        </Menu>
    );

    const handlePageChange = (page: number) => {
        setCurrentPage(page);
    };

    const handleItemsPerPageChange = (value: number) => {
        setItemsPerPage(value);
        setCurrentPage(1);
    };

    useEffect(() => {
        if (pessoaList?.content.length < 0) {
            findPessoa();
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [search, currentPage, itemsPerPage]);

    return (
        <div className="container">

            <Filtros
                find={findPessoa}
                setSearch={setSearch}
                search={search}
            />


            {pessoaList && pessoaList.content.map(pessoa =>
                <div className="list-item">
                    <div>
                        <h2>{pessoa.nome}</h2>
                    </div>
                    <div>
                        <p>{pessoa.id}</p>
                    </div>
                    <div>
                        <p>{formatCpf(pessoa.cpf)}</p>
                    </div>
                    <div>
                        <p>{formatLocalDate(pessoa.dataNascimento)}</p>
                    </div>
                    <div className="list-item-button">
                        <Link to={`/cadastro-pessoa/${pessoa?.id}`}>
                            <FaUserEdit />
                        </Link>
                    </div>
                    <div className="list-item-button">
                        <Link to={`/lista-contato/${pessoa?.id}`}>
                            <FaAddressBook />
                        </Link>
                    </div>
                    <div className="list-item-button">
                        <Dropdown overlay={menu(pessoa)} placement="bottomRight" arrow>
                            <AiOutlineEllipsis
                                className="button-dropdown"
                                size="30"
                                color="#92A9CB"
                            />
                        </Dropdown>
                    </div>
                </div>
            )}
            {pessoaList && pessoaList.content.length > 0 ?
                <Pagination
                    data={pessoaList?.totalElements}
                    itemsPerPage={itemsPerPage}
                    onPageChange={handlePageChange}
                    handleItemsPerPageChange={handleItemsPerPageChange}
                    currentPage={currentPage}
                    setCurrentPage={setCurrentPage}
                />
                : null}
        </div>
    );
}

export default Home;
