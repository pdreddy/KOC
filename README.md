# 🎾 KOC Tournament Management System

A Spring Boot application for managing the Kings of the Court Tournament with H2 database integration.

## 🚀 Quick Start

1. **Run the setup script:**
   ```bash
   ./start-tournament.sh
   ```

2. **Access the application:**
   - **Main App**: http://localhost:8080
   - **H2 Console**: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:tournament`
     - Username: `sa`
     - Password: (leave empty)

## 🏆 Features

✅ **Team Standings** for Group A & B
✅ **Add Match Results** via web interface
✅ **Automatic Rankings** based on points → sets → singles
✅ **Match History** with timestamps
✅ **H2 Database** for data persistence
✅ **REST APIs** for external integration
✅ **Responsive Design** with modern UI

## 📊 Pre-loaded Teams

### Group A
- Rally Squad (RS)
- Spin Kings (SK)
- Topspin Titans (TT)
- Karnas Crusaders (KC)
- Net Ninjas (NN)

### Group B
- Court Conquerors (CC)
- Mega Lions (ML)
- Agni Aces (AA)
- Baseline Game Masters (BGM)
- Rally Royals (RR)

## 🔧 Manual Build & Run

If you prefer manual control:

```bash
# Build the project
mvn clean compile

# Run the application
mvn spring-boot:run

# Or package and run
mvn clean package
java -jar target/tournament-management-1.0.0.jar
```

## 🌐 API Endpoints

- `GET /` - Main standings page
- `POST /api/matches` - Add match result
- `GET /api/standings/{group}` - Get standings for group (A or B)
- `GET /api/matches/{group}` - Get match history for group

## 📝 Requirements

- Java 17+
- Maven 3.6+
- Web browser for UI access

## 🎯 Usage

1. Navigate to http://localhost:8080
2. Select Group A or Group B tab
3. Use the "Add Match Result" form to input scores
4. View updated standings automatically
5. Check match history below standings

Ready to track your KOC Season 1 matches! 🏆
