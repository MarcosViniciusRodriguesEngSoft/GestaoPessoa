import { AxiosRequestConfig } from "axios";

export interface PageableResponse<T> {
    content: Array<T>;
    empty: boolean;
    first: boolean;
    last: boolean;
    number: number
    numberOfElements: number;
    pageable: Pageable;
    size: number;
    sort: Sort;
    totalElements: number;
    totalPages: number;
}

export interface Pageable {
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    sort: Sort;
    unpaged: boolean;
}

export interface Sort {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
}

export interface IAxiosResponseCustom<T = any> {
    data: T;
    status: number;
    statusText: string;
    headers: any;
    config: AxiosRequestConfig;
    request?: any;
    ok?: boolean;
}

export interface IAxiosResponseCustomPageable<T> {
    data: PageableResponse<T>;
    status: number;
    statusText: string;
    headers: any;
    config: AxiosRequestConfig;
    request?: any;
    ok: boolean;
}