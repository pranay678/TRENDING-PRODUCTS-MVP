import axios from 'axios';

// For Next.js, use process.env.NEXT_PUBLIC_API_BASE_URL or fallback to localhost
const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || 'http://localhost:8080/api';

// Create axios instance with default config
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add request interceptor for logging
apiClient.interceptors.request.use(
  (config) => {
    console.log(`Making request to: ${config.method?.toUpperCase()} ${config.url}`);
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Add response interceptor for error handling
apiClient.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.error('API Error:', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

export const fetchTrendingProducts = async (filters?: any) => {
  const params = new URLSearchParams();
  if (filters) {
    Object.entries(filters).forEach(([key, value]) => {
      if (value !== null && value !== undefined && value !== '') {
        params.append(key, String(value));
      }
    });
  }
  
  return apiClient.get(`/trending-products/search?${params.toString()}`);
};

export const fetchTrendingProductsOnly = async (filters?: any) => {
  const params = new URLSearchParams();
  if (filters) {
    Object.entries(filters).forEach(([key, value]) => {
      if (value !== null && value !== undefined && value !== '') {
        params.append(key, String(value));
      }
    });
  }
  
  return apiClient.get(`/trending-products/trending?${params.toString()}`);
};

export const fetchWholesaleOpportunities = async (filters?: any) => {
  const params = new URLSearchParams();
  if (filters) {
    Object.entries(filters).forEach(([key, value]) => {
      if (value !== null && value !== undefined && value !== '') {
        params.append(key, String(value));
      }
    });
  }
  
  return apiClient.get(`/trending-products/wholesale-opportunities?${params.toString()}`);
};

export const analyzeProduct = async (product: any) => {
  return apiClient.post(`/analyze-product`, product);
};

export const healthCheck = async () => {
  return apiClient.get('/health');
};

export default apiClient;
