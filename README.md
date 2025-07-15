# Market Trendz - Trending Products MVP

A comprehensive platform for discovering trending products across different regions in India, with wholesale opportunities and AI-powered insights.

## 🚀 Features

### Current Features
- ✅ **Geographic Filtering**: Search by country, state, and city
- ✅ **Price Filtering**: Filter products under ₹500 (configurable)
- ✅ **Wholesale Integration**: Find wholesale suppliers and profit margins
- ✅ **Multi-Platform Data**: Amazon, JungleScout, Google Trends integration
- ✅ **MCP Server Ready**: Framework for Model Context Protocol integration
- ✅ **AI Analysis**: Product analysis and trend prediction
- ✅ **Modern UI**: Responsive design with Tailwind CSS

### Planned Features
- 🔄 **Real-time Data**: Live trending data updates
- 🔄 **E-commerce Integration**: Direct integration with Flipkart, Amazon Seller Central
- 🔄 **Advanced AI**: Product demand prediction and market analysis
- 🔄 **Wholesale Marketplace**: Direct supplier connections
- 🔄 **Analytics Dashboard**: Sales performance and trend analysis

## 🛠️ Tech Stack

### Backend
- **Spring Boot 3.x** - Main application framework
- **PostgreSQL** - Primary database
- **Spring Data JPA** - Data access layer
- **WebFlux** - Reactive programming for external APIs
- **Caffeine Cache** - In-memory caching

### Frontend
- **Next.js 13** - React framework
- **TypeScript** - Type safety
- **Tailwind CSS** - Styling
- **Axios** - HTTP client

### External Integrations
- **Amazon Product Advertising API**
- **JungleScout API**
- **Google Trends API**
- **OpenAI API** (for AI analysis)
- **MCP Server** (Model Context Protocol)

## 📋 Prerequisites

- Java 17+
- Node.js 18+
- PostgreSQL 13+
- Maven 3.6+

## 🚀 Quick Start

### 1. Database Setup
```bash
# Create PostgreSQL database
createdb market_trendz

# Or using psql
psql -U postgres
CREATE DATABASE market_trendz;
CREATE USER pranay WITH PASSWORD 'pranay';
GRANT ALL PRIVILEGES ON DATABASE market_trendz TO pranay;
```

### 2. Backend Setup
```bash
cd apps/backend

# Install dependencies
mvn clean install

# Set environment variables
export OPENAI_API_KEY="your-openai-key"
export AMAZON_API_KEY="your-amazon-key"
export JUNGLESCOUT_API_KEY="your-junglescout-key"
export MCP_API_URL="http://localhost:3001"

# Run the application
mvn spring-boot:run
```

### 3. Frontend Setup
```bash
cd apps/frontend

# Install dependencies
npm install

# Run development server
npm run dev
```

### 4. Access the Application
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080
- API Documentation: http://localhost:8080/swagger-ui.html

## 🔧 Configuration

### Environment Variables
```bash
# Database
DATABASE_URL=jdbc:postgresql://localhost:5432/market_trendz
DATABASE_USERNAME=pranay
DATABASE_PASSWORD=pranay

# External APIs
OPENAI_API_KEY=your-openai-key
AMAZON_API_KEY=your-amazon-key
JUNGLESCOUT_API_KEY=your-junglescout-key
GOOGLE_TRENDS_API_URL=https://trends.google.com/api

# MCP Server
MCP_API_URL=http://localhost:3001
MCP_API_KEY=your-mcp-key
```

## 📊 API Endpoints

### Product Search
```
GET /api/trending-products/search
Parameters:
- query (optional): Search term
- country (optional): Country filter (default: India)
- state (optional): State filter
- city (optional): City filter
- maxPrice (optional): Maximum price in INR (default: 500)
- currency (optional): Currency code (default: INR)
- category (optional): Product category
- wholesaleOnly (optional): Show only wholesale products
```

### Trending Products
```
GET /api/trending-products/trending
Parameters:
- country, state, city, maxPrice, category
```

### Wholesale Opportunities
```
GET /api/trending-products/wholesale-opportunities
Parameters:
- country, state, city, maxWholesalePrice, category
```

## 🎯 Next Steps & Roadmap

### Phase 1: Core Infrastructure (Current)
- [x] Basic product search and filtering
- [x] Geographic filtering (India focus)
- [x] Price filtering (₹500 limit)
- [x] Wholesale data structure
- [x] MCP server integration framework

### Phase 2: Data Integration (Next 2-4 weeks)
- [ ] **Real Amazon API Integration**
  - Amazon Product Advertising API setup
  - Product data extraction and normalization
  - Price tracking and history

- [ ] **JungleScout Integration**
  - Product research data
  - Competition analysis
  - Sales estimates

- [ ] **Google Trends Integration**
  - Real-time trend data
  - Geographic trend analysis
  - Seasonal trend patterns

### Phase 3: AI & Analytics (Next 4-6 weeks)
- [ ] **Advanced AI Analysis**
  - Product demand prediction
  - Market saturation analysis
  - Profit margin optimization
  - Seasonal trend forecasting

- [ ] **MCP Server Implementation**
  - Custom MCP server for product analysis
  - Integration with Claude/GPT for insights
  - Automated product recommendations

### Phase 4: E-commerce Integration (Next 6-8 weeks)
- [ ] **Wholesale Marketplace**
  - Direct supplier connections
  - Bulk order management
  - Payment processing

- [ ] **E-commerce Platform Integration**
  - Flipkart Seller Hub integration
  - Amazon Seller Central integration
  - Automated listing creation

### Phase 5: Advanced Features (Next 8-12 weeks)
- [ ] **Analytics Dashboard**
  - Sales performance tracking
  - ROI analysis
  - Market trend visualization

- [ ] **Mobile Application**
  - React Native app
  - Push notifications for trending products
  - Offline capability

## 🔍 Current Limitations

1. **Mock Data**: Currently using placeholder data for external APIs
2. **Limited Geographic Coverage**: Focused on India initially
3. **Basic AI**: Simple mock responses, needs real AI integration
4. **No Real-time Updates**: Data is cached, not live
5. **Limited E-commerce Integration**: No direct platform connections

## 🛠️ Development Guidelines

### Code Structure
```
apps/
├── backend/
│   ├── src/main/java/com/trendz/mvp/
│   │   ├── controller/     # REST API endpoints
│   │   ├── service/        # Business logic
│   │   ├── model/          # Data models
│   │   ├── repository/     # Data access layer
│   │   └── config/         # Configuration
│   └── src/main/resources/
│       └── application.properties
└── frontend/
    ├── src/
    │   ├── components/     # React components
    │   ├── pages/          # Next.js pages
    │   ├── utils/          # Utility functions
    │   └── styles/         # CSS styles
    └── package.json
```

### API Design Principles
- RESTful endpoints
- Consistent error handling
- Comprehensive logging
- Rate limiting
- Caching strategies

### Frontend Guidelines
- Component-based architecture
- Responsive design
- Accessibility compliance
- Performance optimization

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Contact: [Your Email]
- Documentation: [Link to docs when available]

---

**Note**: This is an MVP version. Production deployment requires additional security, monitoring, and scalability considerations.
