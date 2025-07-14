import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';

const ProductSearchPage = () => {
  const [keyword, setKeyword] = useState('');
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      const response = await axios.get(`/api/products/search?query=${keyword}`);
      setProducts(response.data);
    } catch (err) {
      setError('Failed to fetch products.');
    }
    setLoading(false);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 pb-10">
      <Navbar />
      <div className="max-w-5xl mx-auto">
        <h1 className="text-4xl font-extrabold text-center text-indigo-700 mb-8 tracking-tight drop-shadow">
          Trending Products
        </h1>
        <form onSubmit={handleSearch} className="flex mb-6">
          <input
            type="text"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
            placeholder="Search products..."
            className="flex-1 px-4 py-2 border rounded-l focus:outline-none"
          />
          <button type="submit" className="px-6 py-2 bg-blue-600 text-white rounded-r">Search</button>
        </form>
        {loading && <div className="text-center"><span className="loader"></span>Loading...</div>}
        {error && <div className="text-red-500 mb-4">{error}</div>}
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8 mt-6">
          {products.map((product) => (
            <div key={product.id} className="border rounded shadow p-4 flex flex-col items-center">
              <img src={product.imageUrl} alt={product.name} className="w-32 h-32 object-cover mb-2" />
              <h3 className="font-bold text-lg mb-1">{product.name}</h3>
              <p className="text-gray-700 mb-1">${product.price}</p>
              <span className="text-xs text-gray-500">Source: {product.platform}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ProductSearchPage;
