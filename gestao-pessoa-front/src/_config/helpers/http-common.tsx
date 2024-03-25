import { notification } from "antd";
import axios from "axios";

const http = axios.create({
    baseURL: process.env.REACT_APP_GESTAOPESSOA_PUBLIC_BASE_URL_API,
});

http.interceptors.response.use(
    response => response,
    error => {
        if (error && error.response) {
            const { status } = error.response;

            switch (status) {
                case 404:
                    notification.warning({ description: error.response.data?.message, type: 'warning', message: 'Aviso', placement: "bottomRight" })
                    break;
                case 400:
                    notification.error({
                        description: error.response.data.details[0]
                            || 'Ops... Ocorreu um erro!',
                        type: 'error',
                        message: 'Erro',
                        placement: "bottomRight"
                    })
                    break;
                default:
                    notification.error({ description: error.response.data?.message || 'Ops... Ocorreu um erro!', type: 'error', message: 'Erro', placement: "bottomRight" })
                    break;
            }
        }
        return Promise.reject(error);
    }
);


export default http;

