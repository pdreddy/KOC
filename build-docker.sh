#!/bin/bash

echo "🐳 Building KOC Tournament Docker Image for Render"
echo "================================================"

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker not found. Please install Docker first:"
    echo "   macOS: brew install --cask docker"
    echo "   Linux: sudo apt install docker.io"
    echo "   Windows: Download Docker Desktop"
    exit 1
fi

# Build Docker image
echo "📦 Building Docker image..."
docker build -t koc-tournament:latest .

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Docker image built successfully!"
    echo ""
    echo "📊 Image details:"
    docker images koc-tournament:latest
    echo ""
    echo "🧪 Test locally:"
    echo "   docker run -p 8080:8080 koc-tournament:latest"
    echo "   # Or: docker-compose up"
    echo ""
    echo "🌐 Access at: http://localhost:8080"
else
    echo "❌ Docker build failed! Check errors above."
    exit 1
fi
