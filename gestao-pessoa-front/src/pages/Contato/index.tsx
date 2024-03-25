import { notification } from "antd";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { IPessoaFullDTO } from '../../_config/model/elotech-gestao-pessoa-api.model';
import { usePessoaService } from "../../_config/services/pessoa.service";
import { formatPhone } from "../../_config/utils/format";
import Filtros from "./filtros";

function Contato() {
    const pessoaService = usePessoaService();
    const [pessoaContatos, setPessoaContatos] = useState<IPessoaFullDTO>();
    const [search, setSearch] = useState<string>('');
    const { idPessoa } = useParams<any>();

    const findPessoaContato = () => {
        pessoaService.findFullById(parseInt(idPessoa))
            .then(({ data }) => {
                setPessoaContatos(data);
            })
            .catch((error) => {
                console.error('Erro ao carregar a lista de contatos:', error);
                notification.error({
                    description: 'Desculpe, não foi possível renderizar a lista.',
                    type: 'error',
                    message: 'Erro!',
                    placement: 'bottomRight'
                });
            });
    }

    useEffect(() => {
        findPessoaContato();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [search]);

    return (
        <div className="container">

            <Filtros
                find={findPessoaContato}
                setSearch={setSearch}
                search={search}
            />
            {pessoaContatos && pessoaContatos?.contatos?.filter(contato => contato?.nome?.toLowerCase()?.includes(search?.toLowerCase()))
                .map(contato =>
                    <div className="list-item" key={contato.id}>
                        <div>
                            <h2>{contato.nome}</h2>
                        </div>
                        <div>
                            <p>{contato.id}</p>
                        </div>
                        <div>
                            <p>{contato.email}</p>
                        </div>
                        <div>
                            <p>{formatPhone(contato.telefone)}</p>
                        </div>
                    </div>
                )}
        </div>
    );
}

export default Contato;
