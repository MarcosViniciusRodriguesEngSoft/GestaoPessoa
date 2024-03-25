import { Button, Input } from 'antd';
import React from 'react';
import { AiOutlineCloseCircle } from 'react-icons/ai';
import { IContatoCleanDTO } from '../../_config/model/elotech-gestao-pessoa-api.model';
import Label from '../../components/Label';
import Validation from '../../components/Validation';

interface Props {
    contatos: IContatoCleanDTO[];
    contato: IContatoCleanDTO;
    setContatos: React.Dispatch<React.SetStateAction<IContatoCleanDTO[]>>;
    deleteContato: (index: number) => void;
    ValidationFields: boolean;
    index: number;
}

const ListContatos: React.FunctionComponent<Props> = ({ contatos, contato, setContatos, deleteContato, index, ValidationFields }) => {
    return (
        <div className="card" key={index}>
            <div className="flex justify-flex-end">
                <Button
                    danger
                    onClick={() => deleteContato(index)}
                    style={{ marginBottom: 10 }}
                > Excluir Contato <AiOutlineCloseCircle className="ml-5" />
                </Button>
            </div>
            <div>
                <Label htmlFor={`nome${index}`} elementRequired="*">Nome</Label>
                <Input
                    id={`nome${index + 1}`}
                    className="wp-100"
                    placeholder="Ex: Marcos Vinicius Rodrigues"
                    defaultValue={contato?.nome}
                    value={contato?.nome}
                    onChange={(e) => {
                        const updateContatos = [...contatos];
                        updateContatos[index].nome = e.target.value;
                        setContatos(updateContatos);
                    }} />
                <Validation validationFilds={ValidationFields} validationFild={contato?.nome} msgError="Informe o nome completo" />
            </div>
            <div className="mt-20">
                <Label htmlFor={`email${index}`} required>E-mail</Label>
                <Input
                    id={`email${index + 1}`}
                    className="wp-100"
                    placeholder="Ex: nome.sobrenome@gmail.com.br"
                    defaultValue={contato?.email}
                    value={contato?.email}
                    onChange={(e) => {
                        const updateContatos = [...contatos];
                        updateContatos[index].email = e.target.value;
                        setContatos(updateContatos);
                    }} />
                <Validation validationFilds={ValidationFields} validationFild={contato?.email} msgError="Informe o e-mail" />
            </div>
            <div className="mt-20">
                <Label htmlFor={`telefone${index}`} required>Telefone</Label>
                <Input
                    id={`telefone${index + 1}`}
                    className="wp-100"
                    placeholder="Ex: (00) 0000-0000"
                    defaultValue={contato?.telefone}
                    value={contato?.telefone}
                    onChange={(e) => {
                        const updateContatos = [...contatos];
                        updateContatos[index].telefone = e.target.value;
                        setContatos(updateContatos);
                    }} />
                <Validation validationFilds={ValidationFields} validationFild={contato?.telefone} msgError="Informe o telefone" />
            </div>
        </div>
    )
}

export default ListContatos