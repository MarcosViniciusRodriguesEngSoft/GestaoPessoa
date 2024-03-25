import { Button, DatePicker, Input, Modal, notification } from "antd";
import { useEffect, useState } from "react";
import { AiFillPlusCircle } from "react-icons/ai";
import { Link, useNavigate, useParams } from "react-router-dom";
import { IContatoCleanDTO, IPessoaFullDTO } from "../../_config/model/elotech-gestao-pessoa-api.model";
import { useContatoService } from "../../_config/services/contato.service";
import { usePessoaService } from "../../_config/services/pessoa.service";
import { formatCpf, toDayjs } from "../../_config/utils/format";
import Label from "../../components/Label";
import Validation from "../../components/Validation";
import ListContatos from "./list-contatos";
import "./styles.css";
import { error } from "console";

function CadastroPessoa() {
    const pessoaService = usePessoaService();
    const contatoService = useContatoService();
    const [pessoa, setPessoa] = useState<IPessoaFullDTO>();
    const [ValidationFields, setValidationFields] = useState<boolean>(false);
    const [contatos, setContatos] = useState<IContatoCleanDTO[]>([]);
    const navigate = useNavigate();
    const { idPessoa } = useParams<any>();

    const findPessoa = () => {
        pessoaService.findFullById(parseInt(idPessoa))
            .then(response => {
                setPessoa(response.data);
                setContatos(response.data.contatos);
            });
    }

    const createPessoa = () => {
        pessoa.contatos = contatos.length > 0 ? contatos : null;
        pessoaService.createFull(pessoa)
            .then(() => {
                notification.success({
                    description: `Pessoa cadastrada com sucesso.`,
                    type: 'success',
                    message: 'Sucesso!',
                });
                navigate("/");
            }).catch((error) => {
                console.log(error.response.data);
                notification.success({
                    description: error.response.data,
                    type: 'success',
                    message: 'Sucesso!',
                });
            })
    }

    const updatePessoa = () => {
        pessoa.contatos = contatos.length > 0 ? contatos : null;
        pessoaService.updateFull(parseInt(idPessoa), pessoa)
            .then(() => {
                notification.success({
                    description: `Pessoa editada com sucesso.`,
                    type: 'success',
                    message: 'Sucesso!',
                });
                navigate("/");
            });
    }

    const sendRequest = () => {
        if (!idPessoa) {
            createPessoa();
        } else {
            updatePessoa();
        }
    }

    const ValidationForms = () => {
        setValidationFields(true);

        if (!pessoa?.nome) {
            document.getElementById('nome')?.focus();
            return notification.warning({
                description: `Preencha o campo Nome.`,
                type: 'warning',
                message: 'Atenção!',
            });
        }
        if (!pessoa?.cpf) {
            document.getElementById('cpf')?.focus();
            return notification.warning({
                description: `Preencha o p campo CPF.`,
                type: 'warning',
                message: 'Atenção!',
            });
        }
        if (!pessoa?.dataNascimento) {
            document.getElementById('dataNascimento')?.focus();
            return notification.warning({
                description: `Preencha o campo Data de Nascimento.`,
                type: 'warning',
                message: 'Atenção!',
            });
        }

        for (let i = 0; i < contatos.length; i++) {
            const contato = contatos[i];
            if (!contato.nome) {
                return notification.warning({
                    description: `Preencha o campo Nome do Contato.`,
                    type: 'warning',
                    message: 'Atenção!',
                });
            }
            if (!contato.email) {
                return notification.warning({
                    description: `Preencha o campo E-mail do Contato.`,
                    type: 'warning',
                    message: 'Atenção!',
                });
            }
            if (!contato.telefone) {
                return notification.warning({
                    description: `Preencha o campo Telefone do Contato.`,
                    type: 'warning',
                    message: 'Atenção!',
                });
            }
        }

        sendRequest();
    }

    useEffect(() => {
        if (idPessoa) {
            findPessoa();
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const addContato = () => {
        const newContato: IContatoCleanDTO = {
            id: null,
            nome: null,
            email: null,
            telefone: null
        };
        setContatos([...contatos, newContato]);
    };

    const deleteContato = (index) => {
        Modal.confirm({
            title: `Deseja realmente excluir o contato?`,
            content: "O contato" + contatos[index].nome ? contatos[index].nome : "",
            okText: 'Sim',
            cancelText: 'Não',
            onOk: () => {
                if (contatos[index].id) {
                    contatoService.deleteContato(contatos[index].id)
                        .then(() => {
                            notification.success({
                                description: `Contato excluido com sucesso.`,
                                type: "success",
                                message: "Sucesso!",
                                placement: 'bottomRight'
                            });
                        })
                }
                const updateContatos = [...contatos];
                updateContatos.splice(index, 1);
                setContatos(updateContatos);
            }
        });
    }

    return (
        <div className="container-cadastro">
            <div className="box">
                <div className="mt-20">
                    <Label htmlFor="nome" required>Nome</Label>
                    <Input
                        id="nome"
                        className="wp-100"
                        placeholder="Ex: Marcos Vinicius Rodrigues"
                        defaultValue={pessoa?.nome}
                        value={pessoa?.nome}
                        onChange={(e) => setPessoa({ ...pessoa, nome: e.target.value })} />
                    <Validation validationFilds={ValidationFields} validationFild={pessoa?.nome} msgError="Informe o nome completo" />
                </div>
                <div className="mt-20">
                    <Label htmlFor="cpf" required>CPF</Label>
                    <Input
                        id="cpf"
                        className="wp-100"
                        placeholder="Ex: 000.000.000-00"
                        defaultValue={formatCpf(pessoa?.cpf)}
                        value={formatCpf(pessoa?.cpf)}
                        onChange={(e) => setPessoa({ ...pessoa, cpf: e.target.value })} />
                    <Validation validationFilds={ValidationFields} validationFild={pessoa?.cpf} msgError="Informe o cpf completo" />
                </div>
                <div className="mt-20">
                    <Label htmlFor="dataNascimento" required>Data de Nascimento</Label>
                    <DatePicker
                        id="dataNascimento"
                        className="wp-40"
                        placeholder="DD/MM/AAAA"
                        format={'DD-MM-YYYY'}
                        defaultValue={toDayjs(pessoa?.dataNascimento)}
                        value={toDayjs(pessoa?.dataNascimento)}
                        onChange={(e) => setPessoa({ ...pessoa, dataNascimento: e.toDate() })} />
                    <Validation validationFilds={ValidationFields} validationFild={pessoa?.dataNascimento} msgError="Informe a data de nascimento" />
                </div>

                <div className="mt-20">
                    <Button
                        className="mb-10"
                        type="primary"
                        onClick={addContato}>
                        Adicionar Contato <AiFillPlusCircle className="ml-5" />
                    </Button>
                    <div className="card-contato">
                        {contatos && contatos.map((contato, index) => (
                            <ListContatos ValidationFields={ValidationFields} contatos={contatos} contato={contato} setContatos={setContatos} deleteContato={deleteContato} index={index} />
                        ))}
                    </div>
                </div>

                <div className="flex justify-flex-end mt-20">
                    <Link to={"/"}>
                        <Button className="button-padrao mr-20">Voltar</Button>
                    </Link>
                    <Button className="button-padrao button-salvar" onClick={ValidationForms}>Salvar</Button>
                </div>
            </div>
        </div>
    );
}

export default CadastroPessoa;
