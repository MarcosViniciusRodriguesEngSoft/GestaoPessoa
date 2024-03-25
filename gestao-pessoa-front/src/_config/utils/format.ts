import dayjs from "dayjs";

export const formatLocalDate = (date: Date) =>
    date ? dayjs(date).format('DD-MM-YYYY') : '';

export const toDayjs = (date) => date && dayjs(date);

export const formatCpf = (value: any) => {
    return value
        ?.replace(/\D/g, '')
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d)/, '$1.$2')
        .replace(/(\d{3})(\d)/, '$1-$2')
        .replace(/(-\d{2})\d+?$/, '$1');
};

export const formatPhone = (value: any) => {
    return value
        ?.replace(/\D/g, '')
        .replace(/(\d{2})(\d)/, '($1) $2')
        .replace(/(\d{4})(\d)/, '$1-$2')
        .replace(/(-\d{4})\d+?$/, '$1');
};
