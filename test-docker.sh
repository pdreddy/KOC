#!/bin/bash

echo "🧪 Testing Docker Container Locally"
echo "==================================="

# Stop any existing container
if docker ps -q --filter "name=koc-test" | grep -q .; then
    echo "🛑 Stopping existing container..."
    docker stop koc-test
    docker rm koc-test
fi

# Run container
echo "🚀 Starting container..."
docker run -d --name koc-test -p 8080:8080 koc-tournament:latest

if [ $? -eq 0 ]; then
    echo "✅ Container started successfully!"
    echo ""
    echo "🌐 Access your app at: http://localhost:8080"
    echo "📊 View logs: docker logs -f koc-test"
    echo "🛑 Stop test: docker stop koc-test && docker rm koc-test"
    echo ""
    echo "⏳ Waiting for app to start (30 seconds)..."
    sleep 30
    echo "🔍 Testing health endpoint..."
    if curl -f http://localhost:8080/actuator/health > /dev/null 2>&1; then
        echo "✅ Health check passed! App is ready."
    else
        echo "⚠️  Health check failed. Check logs: docker logs koc-test"
    fi
else
    echo "❌ Failed to start container!"
    exit 1
fi
