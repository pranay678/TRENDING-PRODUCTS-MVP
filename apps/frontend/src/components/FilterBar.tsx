import React from 'react';

interface FilterBarProps {
  priceRange: [number, number];
  category: string;
  onPriceChange: (range: [number, number]) => void;
  onCategoryChange: (category: string) => void;
}

const categories = ['All', 'Electronics', 'Fashion', 'Home', 'Beauty'];

const FilterBar: React.FC<FilterBarProps> = ({ priceRange, category, onPriceChange, onCategoryChange }) => (
  <div className="flex flex-wrap gap-6 mb-6 items-center justify-between bg-white p-4 rounded-xl shadow">
    <div className="flex items-center gap-3">
      <label className="text-sm font-medium text-gray-700">Price:</label>
      <input
        type="range"
        min={100}
        max={500}
        value={priceRange[1]}
        onChange={e => onPriceChange([100, Number(e.target.value)])}
        className="mx-2 accent-indigo-500 w-32"
      />
      <span className="text-sm text-gray-600">₹{priceRange[0]}–₹{priceRange[1]}</span>
    </div>
    <div className="flex items-center gap-3">
      <label className="text-sm font-medium text-gray-700">Category:</label>
      <select
        value={category}
        onChange={e => onCategoryChange(e.target.value)}
        className="border border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-500"
      >
        {categories.map(cat => (
          <option key={cat} value={cat}>{cat}</option>
        ))}
      </select>
    </div>
  </div>
);

export default FilterBar;
