// This file configures and exports an Axios instance for making API requests.
// It sets a base URL and default headers for all requests made using this instance.
import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
});

export default apiClient;
