import React from 'react';

interface ProductCardProps {
  image: string;
  title: string;
  price: number;
  platform: string;
  onInvestClick: () => void;
}

const ProductCard: React.FC<ProductCardProps> = ({
  image,
  title,
  price,
  platform,
  onInvestClick,
}) => (
  <div className="bg-white rounded-xl shadow-lg p-5 flex flex-col items-center transition-transform hover:scale-105 hover:shadow-2xl border border-gray-100">
    <div className="w-32 h-32 mb-3 flex items-center justify-center bg-gray-100 rounded-lg overflow-hidden">
      <img src={image} alt={title} className="object-contain w-full h-full" />
    </div>
    <h3 className="font-semibold text-lg text-gray-800 mb-1 text-center line-clamp-2">
      {title}
    </h3>
    <p className="text-base font-bold text-indigo-600 mb-1">₹{price}</p>
    <span className="text-xs bg-gray-200 text-gray-600 px-2 py-0.5 rounded mb-2">
      {platform}
    </span>
    <button
      className="mt-2 w-full bg-gradient-to-r from-blue-500 to-indigo-500 text-white font-medium py-2 rounded-lg shadow hover:from-blue-600 hover:to-indigo-600 transition-colors"
      onClick={onInvestClick}
    >
      Should I Invest?
    </button>
  </div>
);

export default ProductCard;
