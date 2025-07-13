import axios from 'axios';

// For Next.js, use process.env.NEXT_PUBLIC_API_BASE_URL or fallback to localhost
const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || 'http://localhost:8080/api';

export const fetchTrendingProducts = async () => {
  return axios.get(`${API_BASE_URL}/trending-products`);
};

export const analyzeProduct = async (product: any) => {
  return axios.post(`${API_BASE_URL}/analyze-product`, product);
};
