import React from 'react';

const Navbar: React.FC = () => (
  <nav className="w-full bg-white shadow-md py-4 px-6 flex items-center justify-between mb-8">
    <div className="flex items-center gap-2">
      <span className="text-2xl font-bold text-indigo-700 tracking-tight">Market Trendz</span>
      <span className="ml-2 px-2 py-1 text-xs bg-indigo-100 text-indigo-700 rounded">MVP</span>
    </div>
    <div className="flex gap-4">
      <a href="#" className="text-gray-700 hover:text-indigo-600 font-medium">Home</a>
      <a href="#" className="text-gray-700 hover:text-indigo-600 font-medium">Wishlist</a>
      <a href="#" className="text-gray-700 hover:text-indigo-600 font-medium">Login</a>
    </div>
  </nav>
);

export default Navbar;
