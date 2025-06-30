#!/bin/bash

echo "🚀 Starting KOC Tournament locally with Docker"
echo "=============================================="

# Build if image doesn't exist
if ! docker images koc-tournament:latest --format "table {{.Repository}}:{{.Tag}}" | grep -q "koc-tournament:latest"; then
    echo "📦 Building Docker image first..."
    ./build-docker.sh
fi

# Stop existing container if running
if docker ps -q --filter "name=koc-tournament" | grep -q .; then
    echo "🛑 Stopping existing container..."
    docker stop koc-tournament
    docker rm koc-tournament
fi

# Run the container
echo "🚀 Starting container..."
docker run -d --name koc-tournament -p 8080:8080 koc-tournament:latest

if [ $? -eq 0 ]; then
    echo "✅ Container started successfully!"
    echo ""
    echo "🌐 Access your app at: http://localhost:8080"
    echo "📊 View logs: docker logs -f koc-tournament"
    echo "🛑 Stop container: docker stop koc-tournament"
else
    echo "❌ Failed to start container!"
    exit 1
fi
