import { defineStore } from 'pinia';
import api from '@/services/api';
import { jwtDecode } from 'jwt-decode';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('token') || null,
    user: JSON.parse(localStorage.getItem('user')) || null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.token,
    isAdmin: (state) => state.user && state.user.roles.includes('ROLE_FINANCE_DIRECTOR'),
    hasRole: (state) => (role) => state.user && state.user.roles.includes(role),
  },
  actions: {
    async login(credentials) {
      try {
        const response = await api.login(credentials);
        const token = response.data.token;
        const decodedToken = jwtDecode(token);

        const user = {
            sub: decodedToken.sub,
            roles: decodedToken.roles ? [decodedToken.roles] : [] // Ensure roles is an array
        };


        this.token = token;
        this.user = user;
        localStorage.setItem('token', token);
        localStorage.setItem('user', JSON.stringify(user));
      } catch (error) {
        console.error('Login failed:', error);
        throw error;
      }
    },
    logout() {
      this.token = null;
      this.user = null;
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      window.location.reload();
    },
    async register(user) {
      try {
        await api.register(user);
      } catch (error) {
        console.error('Registration failed:', error);
        throw error;
      }
    },
  },
});
