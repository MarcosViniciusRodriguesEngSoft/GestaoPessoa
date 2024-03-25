import { AxiosRequestConfig } from 'axios';

export interface IAxiosResponseCustom<T = any> {
    data: T;
    status: number;
    statusText: string;
    headers: any;
    config: AxiosRequestConfig;
    request?: any;
    ok?: boolean;
}