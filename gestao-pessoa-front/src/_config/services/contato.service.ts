import { AxiosResponse } from 'axios';
import { IContatoCleanDTO, IContatoFullDTO } from '../model/elotech-gestao-pessoa-api.model';
import http from '../helpers/http-common';


export const useContatoService = () => {
    const resourceURL = '/api/contatos';

    const findById = (id: number) => {
        return http.get(`${resourceURL}/${id}`)
    }

    const findCleanList = (): Promise<AxiosResponse<IContatoCleanDTO[]>> => {
        return http.get(`${resourceURL}/clean/list`)
    };

    const createFull = (body): Promise<AxiosResponse<IContatoFullDTO>> => {
        return http.post(`${resourceURL}`, body)
    };

    const updateFull = (id: number, body): Promise<AxiosResponse<IContatoFullDTO>> => {
        return http.post(`${resourceURL}/${id}`, body)
    };

    const deleteContato = (id: number) => {
        return http.delete(`${resourceURL}/${id}`)
    }

    return {
        findById,
        findCleanList,
        createFull,
        updateFull,
        deleteContato
    };
};
