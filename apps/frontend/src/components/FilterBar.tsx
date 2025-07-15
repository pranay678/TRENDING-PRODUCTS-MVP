import React, { useState, useEffect } from 'react';

interface FilterBarProps {
  onFiltersChange: (filters: any) => void;
}

const FilterBar: React.FC<FilterBarProps> = ({ onFiltersChange }) => {
  const [filters, setFilters] = useState({
    country: 'India',
    state: '',
    city: '',
    maxPrice: 500,
    currency: 'INR',
    category: '',
    wholesaleOnly: false,
  });

  const [states, setStates] = useState<string[]>([]);
  const [cities, setCities] = useState<string[]>([]);
  const [categories, setCategories] = useState<string[]>([]);

  // Mock data for Indian states and cities
  const indianStates = [
    'Maharashtra',
    'Delhi',
    'Karnataka',
    'Tamil Nadu',
    'Telangana',
    'Gujarat',
    'West Bengal',
    'Uttar Pradesh',
    'Rajasthan',
    'Andhra Pradesh',
  ];

  const stateCities: { [key: string]: string[] } = {
    Maharashtra: ['Mumbai', 'Pune', 'Nagpur', 'Thane', 'Nashik'],
    Delhi: ['New Delhi', 'Delhi Cantonment', 'Dwarka'],
    Karnataka: ['Bangalore', 'Mysore', 'Hubli', 'Mangalore'],
    'Tamil Nadu': ['Chennai', 'Coimbatore', 'Madurai', 'Salem'],
    Telangana: ['Hyderabad', 'Warangal', 'Karimnagar', 'Nizamabad'],
    Gujarat: ['Ahmedabad', 'Surat', 'Vadodara', 'Rajkot'],
    'West Bengal': ['Kolkata', 'Howrah', 'Durgapur', 'Asansol'],
    'Uttar Pradesh': ['Lucknow', 'Kanpur', 'Varanasi', 'Agra'],
    Rajasthan: ['Jaipur', 'Jodhpur', 'Udaipur', 'Kota'],
    'Andhra Pradesh': ['Visakhapatnam', 'Vijayawada', 'Guntur', 'Nellore'],
  };

  const productCategories = [
    'Electronics',
    'Fashion',
    'Home & Kitchen',
    'Beauty & Personal Care',
    'Sports & Outdoors',
    'Books',
    'Toys & Games',
    'Automotive',
    'Health & Wellness',
    'Baby Products',
    'Pet Supplies',
    'Garden & Outdoor',
  ];

  useEffect(() => {
    setStates(indianStates);
    setCategories(productCategories);
  }, []);

  useEffect(() => {
    if (filters.state) {
      setCities(stateCities[filters.state] || []);
    } else {
      setCities([]);
    }
  }, [filters.state]);

  // Remove the problematic useEffect that was causing infinite loops
  // useEffect(() => {
  //   onFiltersChange(filters);
  // }, [filters, onFiltersChange]);

  const handleFilterChange = (key: string, value: any) => {
    setFilters((prev) => ({
      ...prev,
      [key]: value,
    }));
  };

  const handleApplyFilters = () => {
    onFiltersChange(filters);
  };

  const handleClearFilters = () => {
    const clearedFilters = {
      country: 'India',
      state: '',
      city: '',
      maxPrice: 500,
      currency: 'INR',
      category: '',
      wholesaleOnly: false,
    };
    setFilters(clearedFilters);
    onFiltersChange(clearedFilters);
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-md mb-6">
      <h3 className="text-lg font-semibold mb-4 text-gray-800">Filters</h3>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        {/* Country */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Country
          </label>
          <select
            value={filters.country}
            onChange={(e) => handleFilterChange('country', e.target.value)}
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="India">India</option>
          </select>
        </div>

        {/* State */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            State
          </label>
          <select
            value={filters.state}
            onChange={(e) => handleFilterChange('state', e.target.value)}
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">All States</option>
            {states.map((state) => (
              <option key={state} value={state}>
                {state}
              </option>
            ))}
          </select>
        </div>

        {/* City */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            City
          </label>
          <select
            value={filters.city}
            onChange={(e) => handleFilterChange('city', e.target.value)}
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            disabled={!filters.state}
          >
            <option value="">All Cities</option>
            {cities.map((city) => (
              <option key={city} value={city}>
                {city}
              </option>
            ))}
          </select>
        </div>

        {/* Max Price */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Max Price (₹)
          </label>
          <input
            type="number"
            value={filters.maxPrice}
            onChange={(e) =>
              handleFilterChange('maxPrice', parseInt(e.target.value) || 500)
            }
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            min="0"
            max="10000"
          />
        </div>

        {/* Category */}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Category
          </label>
          <select
            value={filters.category}
            onChange={(e) => handleFilterChange('category', e.target.value)}
            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">All Categories</option>
            {categories.map((category) => (
              <option key={category} value={category}>
                {category}
              </option>
            ))}
          </select>
        </div>

        {/* Wholesale Only */}
        <div className="flex items-center">
          <input
            type="checkbox"
            id="wholesaleOnly"
            checked={filters.wholesaleOnly}
            onChange={(e) =>
              handleFilterChange('wholesaleOnly', e.target.checked)
            }
            className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
          />
          <label
            htmlFor="wholesaleOnly"
            className="ml-2 block text-sm text-gray-700"
          >
            Wholesale Only
          </label>
        </div>
      </div>

      {/* Filter Actions */}
      <div className="mt-4 flex justify-between">
        <button
          onClick={handleClearFilters}
          className="px-4 py-2 text-sm text-gray-600 border border-gray-300 rounded-md hover:bg-gray-50"
        >
          Clear Filters
        </button>
        <button
          onClick={handleApplyFilters}
          className="px-4 py-2 text-sm bg-blue-600 text-white rounded-md hover:bg-blue-700"
        >
          Apply Filters
        </button>
      </div>
    </div>
  );
};

export default FilterBar;
