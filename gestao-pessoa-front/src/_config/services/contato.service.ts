import http from '../helpers/http-common';


export const useContatoService = () => {
    const resourceURL = '/api/contatos';

    const deleteContato = (id: number) => {
        return http.delete(`${resourceURL}/${id}`)
    }

    return {
        deleteContato
    };
};
