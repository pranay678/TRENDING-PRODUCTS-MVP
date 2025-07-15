# 🚀 Running Market Trendz MVP Locally

This guide will help you run both the frontend and backend applications locally.

## 📋 Prerequisites

Make sure you have the following installed:

- **Java 17+** - [Download here](https://adoptium.net/)
- **Node.js 18+** - [Download here](https://nodejs.org/)
- **Maven 3.6+** - [Download here](https://maven.apache.org/download.cgi)

## 🎯 Quick Start (Recommended)

### Option 1: Using the startup script

**On macOS/Linux:**
```bash
./start-local.sh
```

**On Windows:**
```cmd
start-local.bat
```

### Option 2: Manual startup

#### 1. Start the Backend
```bash
cd apps/backend
mvn clean install -DskipTests
mvn spring-boot:run
```

#### 2. Start the Frontend (in a new terminal)
```bash
cd apps/frontend
npm install
npm run dev
```

## 🌐 Access the Application

Once both services are running, you can access:

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console
- **API Documentation**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/api/health

## 🗄️ Database

The application uses **H2 in-memory database** for local development, which means:
- No setup required
- Data is reset when you restart the application
- Sample data is automatically loaded on startup

## 📊 Sample Data

The application comes with 8 sample products including:
- Wireless Bluetooth Headphones (₹450)
- Smart Fitness Band (₹399)
- Portable Power Bank (₹499)
- And more...

Each product includes:
- Geographic information (state, city)
- Wholesale pricing
- Trend scores
- Search volumes

## 🔧 Troubleshooting

### Backend won't start
- Check if Java 17+ is installed: `java -version`
- Check if Maven is installed: `mvn -version`
- Check if port 8080 is available

### Frontend won't start
- Check if Node.js 18+ is installed: `node -version`
- Check if port 3000 is available
- Try deleting `node_modules` and running `npm install` again

### API calls failing
- Make sure backend is running on port 8080
- Check browser console for CORS errors
- Verify the API endpoints in the browser network tab

### Database issues
- Access H2 console at http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:market_trendz`
- Username: `sa`
- Password: (leave empty)

## 🎨 Features to Test

1. **Product Search**: Search for products by keyword
2. **Geographic Filtering**: Filter by Indian states and cities
3. **Price Filtering**: Products under ₹500
4. **Wholesale Information**: View wholesale prices and suppliers
5. **Trend Analysis**: See trend scores and search volumes

## 🔄 Development Workflow

1. **Backend Changes**: Restart the Spring Boot application
2. **Frontend Changes**: Next.js will hot-reload automatically
3. **Database Changes**: Restart the backend to recreate the schema

## 📝 Environment Variables

You can set these environment variables for additional features:

```bash
export OPENAI_API_KEY="your-openai-key"
export AMAZON_API_KEY="your-amazon-key"
export JUNGLESCOUT_API_KEY="your-junglescout-key"
export MCP_API_URL="http://localhost:3001"
```

## 🆘 Need Help?

- Check the main README.md for detailed documentation
- Look at the API documentation at http://localhost:8080/swagger-ui.html
- Check the browser console and backend logs for errors

---

**Happy coding! 🎉** 