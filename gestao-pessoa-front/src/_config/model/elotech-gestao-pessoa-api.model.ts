/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.24.612 on 2024-03-23 22:30:04.

export interface IContatoCleanDTO {
    id: number;
    nome: string;
    telefone: string;
    email: string;
}

export interface IContatoDTO extends IContatoCleanDTO {
    pessoa: IPessoaDTO;
}

export interface IContatoFullDTO extends IContatoDTO {
}

export interface IPessoaCleanDTO {
    id: number;
    nome: string;
    cpf: string;
    dataNascimento: Date;
}

export interface IPessoaDTO extends IPessoaCleanDTO {
}

export interface IPessoaFullDTO extends IPessoaDTO {
    contatos: IContatoCleanDTO[];
}
