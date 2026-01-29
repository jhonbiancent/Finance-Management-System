import axios from 'axios';
import { useAuthStore } from '@/stores/auth';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.request.use(
  (config) => {
    const authStore = useAuthStore();
    const token = authStore.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default {
  login(credentials) {
    return apiClient.post('/v1/auth/authenticate', credentials);
  },
  register(user) {
    return apiClient.post('/v1/auth/register', user);
  },
  getTransactions() {
    return apiClient.get('/v1/transactions');
  },
  createTransaction(transaction) {
    return apiClient.post('/v1/transactions', transaction);
  },
  updateTransaction(id, transaction) {
    return apiClient.put(`/v1/transactions/${id}`, transaction);
  },
  deleteTransaction(id) {
    return apiClient.delete(`/v1/transactions/${id}`);
  },
};
