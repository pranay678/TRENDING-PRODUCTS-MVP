import React, { useState, useEffect } from 'react';
import { fetchTrendingProducts, analyzeProduct } from '../utils/api';
import ProductCard from '../components/ProductCard';
import FilterBar from '../components/FilterBar';
import Navbar from '../components/Navbar';

const Modal: React.FC<{ open: boolean; onClose: () => void; product: any }> = ({ open, onClose, product }) => {
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState<any>(null);
  if (!open) return null;
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setResult(null);
    try {
      const res = await analyzeProduct({
        name: product?.title,
        price: product?.price,
        platform: product?.platform,
      });
      setResult(res.data);
    } catch (err) {
      setResult({ recommendation: 'Error', confidence: 0, reasoning: 'Failed to get recommendation.' });
    }
    setLoading(false);
  };
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div className="bg-white rounded-xl shadow-lg p-8 w-full max-w-md relative">
        <button className="absolute top-3 right-3 text-gray-400 hover:text-gray-700" onClick={onClose}>&times;</button>
        <h2 className="text-xl font-bold mb-4 text-indigo-700">Analyze Investment</h2>
        <form className="flex flex-col gap-4" onSubmit={handleSubmit}>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Product</label>
            <input type="text" value={product?.title || ''} readOnly className="w-full border rounded-lg px-3 py-2 bg-gray-100" />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Platform</label>
            <input type="text" value={product?.platform || ''} readOnly className="w-full border rounded-lg px-3 py-2 bg-gray-100" />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Price</label>
            <input type="number" value={product?.price || ''} readOnly className="w-full border rounded-lg px-3 py-2 bg-gray-100" />
          </div>
          <button type="submit" className="w-full bg-gradient-to-r from-green-500 to-indigo-500 text-white font-semibold py-2 rounded-lg shadow hover:from-green-600 hover:to-indigo-600 transition-colors" disabled={loading}>{loading ? 'Analyzing...' : 'Get AI Recommendation'}</button>
        </form>
        {result && (
          <div className="mt-6 p-4 bg-gray-50 rounded-lg border">
            <div className="font-semibold text-lg mb-1">Recommendation: <span className={result.recommendation === 'Yes' ? 'text-green-600' : 'text-red-600'}>{result.recommendation}</span></div>
            <div className="mb-1">Confidence: <span className="font-bold">{result.confidence}%</span></div>
            <div className="text-gray-700 text-sm">{result.reasoning}</div>
          </div>
        )}
      </div>
    </div>
  );
};

const HomePage: React.FC = () => {
  const [priceRange, setPriceRange] = useState<[number, number]>([100, 500]);
  const [category, setCategory] = useState('All');
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState<any>(null);
  const [products, setProducts] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetchTrendingProducts()
      .then(res => setProducts(res.data))
      .catch(() => setProducts([]))
      .finally(() => setLoading(false));
  }, []);

  const handleInvestClick = (product: any) => {
    setSelectedProduct(product);
    setModalOpen(true);
  };

  // Filter products by price and category
  const filteredProducts = products.filter(product => {
    const inPrice = product.price >= priceRange[0] && product.price <= priceRange[1];
    const inCategory = category === 'All' || (product.category ? product.category === category : true);
    return inPrice && inCategory;
  });

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 pb-10">
      <Navbar />
      <div className="max-w-5xl mx-auto">
        <h1 className="text-4xl font-extrabold text-center text-indigo-700 mb-8 tracking-tight drop-shadow">Trending Products India <span className="text-base font-normal text-gray-500">(Under ₹500)</span></h1>
        <FilterBar
          priceRange={priceRange}
          category={category}
          onPriceChange={setPriceRange}
          onCategoryChange={setCategory}
        />
        {loading ? (
          <div className="text-center text-gray-500 py-10">Loading products...</div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8 mt-6">
            {filteredProducts.map((product, idx) => (
              <ProductCard
                key={product.id || idx}
                image={product.imageUrl || product.image}
                title={product.name || product.title}
                price={product.price}
                platform={product.platform}
                onInvestClick={() => handleInvestClick(product)}
              />
            ))}
          </div>
        )}
      </div>
      <Modal open={modalOpen} onClose={() => setModalOpen(false)} product={selectedProduct} />
    </div>
  );
};

export default HomePage;
