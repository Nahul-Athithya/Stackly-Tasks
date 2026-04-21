import axios from 'axios';

const BASE_URL = 'http://localhost:8080/employee/api';

const api = axios.create({
  baseURL: BASE_URL,
  headers: { 'Content-Type': 'application/json' },
});

export const getAllEmployees = () => api.get('/');

export const getEmployeeById = (id) => api.get(`/${id}`);

export const createEmployee = (data) => api.post('/', data);

export const updateEmployee = (id, data) => api.put(`/${id}`, data);

export const deleteEmployee = (id) => api.delete(`/${id}`);

export const searchByDepartment = (department) =>
  api.get('/search/department', { params: { department } });

export const searchBySalaryRange = (min, max) =>
  api.get('/search/salary', { params: { min, max } });

export const getEmployeesPaginated = (page = 0, size = 10) =>
  api.get('/page', { params: { page, size } });
