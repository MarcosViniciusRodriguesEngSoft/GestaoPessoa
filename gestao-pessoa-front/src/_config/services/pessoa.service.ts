import { AxiosResponse } from 'axios';

import http from '../helpers/http-common';
import { IPessoaCleanDTO, IPessoaDTO, IPessoaFullDTO } from '../model/elotech-gestao-pessoa-api.model';
import { PageableResponse } from '../model/response.model';

export const usePessoaService = () => {
    const resourceURL = '/api/pessoas';

    const findById = (id): Promise<AxiosResponse<IPessoaDTO>> => {
        return http.get(`${resourceURL}/${id}`)
    }

    const findFullById = (id): Promise<AxiosResponse<IPessoaFullDTO>> => {
        return http.get(`${resourceURL}/full/${id}`)
    }

    const findPageable = (
        nome: string,
        currentPage: number,
        itemsPerPage: number
    ): Promise<AxiosResponse<PageableResponse<IPessoaCleanDTO>>> => {
        return http.get(`${resourceURL}/pageable`, {
            params: { nome: nome, currentPage: currentPage, itemsPerPage: itemsPerPage },
        });
    };

    const findCleanList = (): Promise<AxiosResponse<IPessoaCleanDTO[]>> => {
        return http.get(`${resourceURL}/clean/list`)
    };

    const createFull = (body): Promise<AxiosResponse<IPessoaFullDTO>> => {
        return http.post(`${resourceURL}`, body)
    };

    const updateFull = (id: number, body): Promise<AxiosResponse<IPessoaFullDTO>> => {
        return http.put(`${resourceURL}/${id}`, body)
    };

    const deletePessoa = (id: number) => {
        return http.delete(`${resourceURL}/${id}`)
    }
    return {
        findById,
        findFullById,
        findCleanList,
        createFull,
        updateFull,
        deletePessoa,
        findPageable
    };
};
