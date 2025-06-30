
#!/bin/bash

# Simple Railway Deployment Setup for KOC Tournament
# No payment verification required!

echo "🚂 Railway Deployment Setup for KOC Tournament"
echo "==============================================="
echo ""
echo "✅ No payment verification required"
echo "✅ GitHub integration"
echo "✅ Auto-deployments"
echo "✅ Great free tier"
echo ""

# Check if we're in the right directory
if [ ! -d "koc-tournament" ]; then
    echo "❌ Please run this script from the directory containing 'koc-tournament' folder"
    echo "📁 Current directory contents:"
    ls -la
    echo ""
    echo "💡 If you need to create the project first, run the setup script:"
    echo "   ./setup-tournament.sh"
    exit 1
fi

cd koc-tournament

echo "🔧 Setting up Railway configuration files..."

# Create railway.json for configuration
cat > railway.json << 'EOF'
{
  "build": {
    "builder": "heroku/buildpacks:20"
  },
  "deploy": {
    "startCommand": "java -Dserver.port=$PORT -jar target/tournament-management-1.0.0.jar",
    "healthcheckPath": "/",
    "healthcheckTimeout": 100
  }
}
EOF

# Create Procfile (Railway also supports this)
echo "web: java -Dserver.port=\$PORT -jar target/tournament-management-1.0.0.jar" > Procfile

# Update application.properties for Railway
echo "" >> src/main/resources/application.properties
echo "# Railway Production Configuration" >> src/main/resources/application.properties
echo "management.endpoints.web.exposure.include=health" >> src/main/resources/application.properties
echo "management.endpoint.health.show-details=always" >> src/main/resources/application.properties

# Create .gitignore if it doesn't exist
if [ ! -f ".gitignore" ]; then
    cat > .gitignore << 'EOF'
target/
!target/*.jar
.mvn/
mvnw
mvnw.cmd
.DS_Store
*.log
.idea/
*.iml
EOF
fi

echo "📦 Building application..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Build failed. Please check for errors above."
    exit 1
fi

echo ""
echo "✅ Railway setup complete!"
echo ""
echo "📁 Files created/updated:"
echo "   ✓ railway.json"
echo "   ✓ Procfile"
echo "   ✓ Updated application.properties"
echo "   ✓ .gitignore"
echo "   ✓ Built JAR file"
echo ""
echo "🚂 RAILWAY DEPLOYMENT STEPS:"
echo "============================"
echo ""
echo "1. 📤 Push your code to GitHub:"
echo "   git init"
echo "   git add ."
echo "   git commit -m 'KOC Tournament app ready for Railway'"
echo "   git branch -M main"
echo "   git remote add origin https://github.com/yourusername/your-repo.git"
echo "   git push -u origin main"
echo ""
echo "2. 🌐 Go to https://railway.app"
echo ""
echo "3. 🔐 Sign up/Login with GitHub (free account)"
echo ""
echo "4. 📦 Click 'New Project' → 'Deploy from GitHub repo'"
echo ""
echo "5. 🔗 Connect your GitHub account if prompted"
echo ""
echo "6. 📂 Select your koc-tournament repository"
echo ""
echo "7. ⚡ Railway will auto-detect Java and deploy!"
echo ""
echo "8. 🎉 Your app will be live at: https://your-app.railway.app"
echo ""
echo "💡 ALTERNATIVE - Use Railway CLI:"
echo "   npm install -g @railway/cli"
echo "   railway login"
echo "   railway deploy"
echo ""

# Create a quick GitHub setup script
cat > setup-github.sh << 'EOF'
#!/bin/bash

echo "📤 Setting up GitHub repository..."

# Check if git is initialized
if [ ! -d ".git" ]; then
    git init
    echo "✅ Git repository initialized"
fi

# Add all files
git add .

# Commit changes
git commit -m "KOC Tournament app ready for Railway deployment"

echo ""
echo "📋 Next steps:"
echo "1. Create a new repository on GitHub"
echo "2. Copy the repository URL"
echo "3. Run these commands:"
echo ""
echo "   git branch -M main"
echo "   git remote add origin YOUR_GITHUB_REPO_URL"
echo "   git push -u origin main"
echo ""
echo "4. Then deploy to Railway using the GitHub repo"

EOF

chmod +x setup-github.sh

echo ""
echo "📜 Created setup-github.sh for easy GitHub setup!"
echo ""
echo "🎯 QUICK START COMMANDS:"
echo "======================="
echo ""
echo "1. Setup GitHub (optional):"
echo "   ./setup-github.sh"
echo ""
echo "2. Or deploy directly with Railway CLI:"
echo "   npm install -g @railway/cli"
echo "   railway login"
echo "   railway deploy"
echo ""
echo "🎾 Your KOC Tournament app will be live in minutes! 🏆"