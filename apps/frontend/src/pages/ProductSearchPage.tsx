import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import FilterBar from '../components/FilterBar';
import ProductCard from '../components/ProductCard';

const ProductSearchPage = () => {
  const [keyword, setKeyword] = useState('');
  const [products, setProducts] = useState([]);
  const [trends, setTrends] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [filters, setFilters] = useState({
    country: 'India',
    state: '',
    city: '',
    maxPrice: 500,
    currency: 'INR',
    category: '',
    wholesaleOnly: false,
  });
  const [showBackToTop, setShowBackToTop] = useState(false);

  const fetchProducts = async (search = '', currentFilters = filters) => {
    setLoading(true);
    setError('');
    try {
      const params = new URLSearchParams();
      if (search) params.append('query', search);
      if (currentFilters.country)
        params.append('country', currentFilters.country);
      if (currentFilters.state) params.append('state', currentFilters.state);
      if (currentFilters.city) params.append('city', currentFilters.city);
      if (currentFilters.maxPrice)
        params.append('maxPrice', currentFilters.maxPrice.toString());
      if (currentFilters.currency)
        params.append('currency', currentFilters.currency);
      if (currentFilters.category)
        params.append('category', currentFilters.category);
      if (currentFilters.wholesaleOnly)
        params.append('wholesaleOnly', currentFilters.wholesaleOnly.toString());

      const response = await axios.get(
        `/api/trending-products/search?${params.toString()}`
      );
      setProducts(response.data.products || []);
      setTrends(response.data.trends || []);
    } catch (err) {
      console.error('Error fetching products:', err);
      setError('Failed to fetch products. Please try again.');
    }
    setLoading(false);
  };

  useEffect(() => {
    fetchProducts(); // Fetch all products on initial load
    const handleScroll = () => {
      setShowBackToTop(window.scrollY > 400);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const handleSearch = async (e) => {
    e.preventDefault();
    fetchProducts(keyword, filters);
  };

  const handleFiltersChange = (newFilters) => {
    setFilters(newFilters);
    fetchProducts(keyword, newFilters);
  };

  const handleQuickSearch = (searchTerm) => {
    setKeyword(searchTerm);
    fetchProducts(searchTerm, filters);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 pb-10">
      <Navbar />
      <div className="max-w-7xl mx-auto px-2 sm:px-4">
        <h1 className="text-4xl font-extrabold text-center text-indigo-700 mb-8 tracking-tight drop-shadow">
          Trending Products
        </h1>

        {/* Search Bar */}
        <form onSubmit={handleSearch} className="flex mb-6 max-w-2xl mx-auto">
          <input
            type="text"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
            placeholder="Search for trending products..."
            className="flex-1 px-4 py-3 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          />
          <button
            type="submit"
            className="px-6 py-3 bg-blue-600 text-white rounded-r-lg hover:bg-blue-700 transition-colors font-medium"
          >
            Search
          </button>
        </form>

        {/* Quick Search Suggestions */}
        <div className="mb-6">
          <h3 className="text-lg font-semibold text-gray-700 mb-3">
            Popular Searches:
          </h3>
          <div className="flex flex-wrap gap-2">
            {[
              'Smartphones',
              'Laptops',
              'Headphones',
              'Fitness Trackers',
              'Gaming Accessories',
            ].map((term) => (
              <button
                key={term}
                onClick={() => handleQuickSearch(term)}
                className="px-3 py-1 bg-white border border-gray-300 rounded-full text-sm hover:bg-gray-50 transition-colors"
              >
                {term}
              </button>
            ))}
          </div>
        </div>

        {/* Filters */}
        <FilterBar onFiltersChange={handleFiltersChange} />

        {/* Loading and Error States */}
        {loading && (
          <div className="text-center py-8">
            <div className="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600"></div>
            <p className="mt-2 text-gray-600">
              Searching for trending products...
            </p>
          </div>
        )}

        {error && (
          <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded mb-6">
            {error}
          </div>
        )}

        {/* Results Summary */}
        {products.length > 0 && (
          <div className="mb-6">
            <h2 className="text-xl font-semibold text-gray-800 mb-2">
              Found {products.length} trending products
            </h2>
            <p className="text-gray-600">
              Showing products under ₹{filters.maxPrice} in {filters.country}
              {filters.state && `, ${filters.state}`}
              {filters.city && `, ${filters.city}`}
            </p>
          </div>
        )}

        {/* Products Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
          {products.map((product, idx) => (
            <ProductCard key={product.id || idx} product={product} />
          ))}
        </div>

        {/* No Results */}
        {!loading && products.length === 0 && !error && (
          <div className="text-center py-12">
            <div className="text-gray-400 text-6xl mb-4">🔍</div>
            <h3 className="text-xl font-semibold text-gray-700 mb-2">
              No products found
            </h3>
            <p className="text-gray-500">
              Try adjusting your search terms or filters
            </p>
          </div>
        )}

        {/* Trending Keywords */}
        {trends.length > 0 && (
          <div className="mt-12 bg-white rounded-lg shadow-md p-6">
            <h2 className="text-2xl font-bold mb-4 text-indigo-700">
              Trending Keywords
            </h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
              {trends.map((trend, idx) => (
                <div
                  key={trend.name || idx}
                  className="flex items-center justify-between p-3 bg-gray-50 rounded-lg"
                >
                  <span className="font-semibold text-gray-800">
                    {trend.name || trend.keyword}
                  </span>
                  <span className="text-indigo-600 font-bold">
                    {trend.score}
                  </span>
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
      {/* Back to Top Button */}
      {showBackToTop && (
        <button
          onClick={() => window.scrollTo({ top: 0, behavior: 'smooth' })}
          className="fixed bottom-6 right-6 z-50 bg-indigo-600 text-white p-3 rounded-full shadow-lg hover:bg-indigo-700 transition-colors"
          aria-label="Back to Top"
        >
          ↑
        </button>
      )}
    </div>
  );
};

export default ProductSearchPage;
