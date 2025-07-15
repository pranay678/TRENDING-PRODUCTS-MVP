import React from 'react';

interface ProductCardProps {
  product: {
    id: number;
    name: string;
    price: number;
    currency?: string;
    platform: string;
    imageUrl: string;
    country?: string;
    state?: string;
    city?: string;
    wholesalePrice?: number;
    wholesaleSupplier?: string;
    trendScore?: number;
    searchVolume?: number;
    category?: string;
    productUrl?: string;
  };
}

const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
  const formatPrice = (price: number, currency: string = 'INR') => {
    return new Intl.NumberFormat('en-IN', {
      style: 'currency',
      currency: currency,
      minimumFractionDigits: 0,
      maximumFractionDigits: 0,
    }).format(price);
  };

  const getProfitMargin = () => {
    if (product.wholesalePrice && product.price) {
      const margin = product.price - product.wholesalePrice;
      const marginPercentage = (margin / product.wholesalePrice) * 100;
      return { margin, marginPercentage };
    }
    return null;
  };

  const profitMargin = getProfitMargin();

  return (
    <div className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-xl hover:scale-[1.03] transition-transform duration-200 flex flex-col h-full">
      {/* Product Image */}
      <div className="relative h-48 bg-gray-200">
        <img
          src={product.imageUrl || '/placeholder-product.jpg'}
          alt={product.name}
          className="w-full h-full object-cover"
          onError={(e) => {
            e.currentTarget.src = '/placeholder-product.jpg';
          }}
        />
        {product.trendScore && (
          <div className="absolute top-2 right-2 bg-red-500 text-white px-2 py-1 rounded-full text-xs font-semibold">
            🔥 {product.trendScore}
          </div>
        )}
      </div>

      {/* Product Info */}
      <div className="p-4 flex flex-col flex-1">
        <h3 className="font-semibold text-lg mb-2 text-gray-800 line-clamp-2 truncate">
          {product.name}
        </h3>

        {/* Price Information */}
        <div className="mb-3">
          <div className="flex items-center justify-between">
            <span className="text-2xl font-bold text-green-600">
              {formatPrice(product.price, product.currency)}
            </span>
            <span className="text-sm text-gray-500">{product.platform}</span>
          </div>
        </div>

        {/* Wholesale Information */}
        {product.wholesalePrice && (
          <div className="mb-3 p-3 bg-blue-50 rounded-lg">
            <div className="text-sm text-gray-600 mb-1">Wholesale Price:</div>
            <div className="text-lg font-semibold text-blue-600">
              {formatPrice(product.wholesalePrice, product.currency)}
            </div>
            {profitMargin && (
              <div className="text-xs text-green-600 mt-1">
                Profit: {formatPrice(profitMargin.margin, product.currency)} (
                {profitMargin.marginPercentage.toFixed(1)}%)
              </div>
            )}
            {product.wholesaleSupplier && (
              <div className="text-xs text-gray-500 mt-1 truncate">
                Supplier: {product.wholesaleSupplier}
              </div>
            )}
          </div>
        )}

        {/* Geographic Information */}
        {(product.country || product.state || product.city) && (
          <div className="mb-3 text-sm text-gray-600">
            <div className="flex items-center gap-1">
              📍{' '}
              {[product.city, product.state, product.country]
                .filter(Boolean)
                .join(', ')}
            </div>
          </div>
        )}

        {/* Category and Metrics */}
        <div className="flex items-center justify-between text-sm text-gray-500 mb-3">
          {product.category && (
            <span className="bg-gray-100 px-2 py-1 rounded">
              {product.category}
            </span>
          )}
          {product.searchVolume && (
            <span className="flex items-center gap-1">
              📊 {product.searchVolume.toLocaleString()} searches
            </span>
          )}
        </div>

        {/* Action Buttons */}
        <div className="flex gap-2 mt-auto">
          {product.productUrl && (
            <a
              href={product.productUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="flex-1 bg-blue-600 text-white text-center py-2 px-4 rounded-md hover:bg-blue-700 transition-colors text-sm font-medium"
            >
              View Product
            </a>
          )}
          {product.wholesalePrice && (
            <button className="flex-1 bg-green-600 text-white py-2 px-4 rounded-md hover:bg-green-700 transition-colors text-sm font-medium">
              Contact Supplier
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
