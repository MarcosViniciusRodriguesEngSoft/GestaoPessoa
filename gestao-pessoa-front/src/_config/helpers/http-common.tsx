import axios from "axios";

const http = axios.create({
    baseURL: process.env.REACT_APP_GESTAOPESSOA_PUBLIC_BASE_URL_API,
});

export default http;

