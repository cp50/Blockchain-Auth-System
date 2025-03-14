🚀 Blockchain-Based Secure Authentication System

🔹 Overview

This project is an innovative authentication system that leverages blockchain technology to ensure secure, tamper-proof user authentication. Unlike traditional systems that store credentials in a centralized database, this system registers and authenticates users on the Ethereum blockchain, providing enhanced security and trust.

📌 Why Blockchain?

Traditional authentication systems are prone to data breaches, identity theft, and unauthorized access. Blockchain eliminates these risks by decentralizing and securing authentication using smart contracts.

🔥 Key Features

✅ Decentralized Authentication: User credentials are stored on Ethereum blockchain, eliminating the risk of centralized breaches.
✅ JWT-Based Authentication: Generates JWT tokens for session management after successful authentication.
✅ Spring Boot Backend: Uses Spring Boot for API endpoints and Web3j for blockchain interaction.
✅ Ethereum Smart Contracts: Developed using Solidity, deployed on Hardhat (local blockchain).
✅ Role-Based Access (Future Expansion): Users & Admins with specific permissions.

🛠️ Tech Stack

📌 Backend
Java (Spring Boot) – REST API, JWT Authentication
Web3j – Blockchain interaction
MongoDB – (For hybrid authentication if needed)
📌 Blockchain
Ethereum (Hardhat Local Testnet)
Solidity – Smart contract development
Ethers.js – For contract interaction
Ganache / Sepolia (Future Expansion) – Public testnet deployment

📂 Project Structure

📦 blockchain-auth-system
 ┣ 📂 blockchain
 ┃ ┣ 📜 Auth.sol   # Smart contract (Solidity)
 ┃ ┣ 📜 deploy.js  # Deployment script (Ethers.js)
 ┃ ┣ 📜 hardhat.config.js  # Hardhat configuration
 ┃ ┗ 📜 package.json  # Node dependencies
 ┣ 📂 backend
 ┃ ┣ 📂 src/main/java/com/projectjava/demo
 ┃ ┃ ┣ 📂 controller  # API Controllers
 ┃ ┃ ┣ 📂 service     # Business Logic
 ┃ ┃ ┣ 📂 security    # JWT & Authentication
 ┃ ┃ ┗ 📜 Application.java  # Main Spring Boot class
 ┃ ┣ 📜 pom.xml       # Maven dependencies
 ┗ 📜 README.md       # Project documentation

 ⚙️ Installation & Setup
 
📌 Prerequisites

JDK 17+
Node.js & Hardhat
MongoDB (Optional)
Metamask (For blockchain transactions)

Step 1️⃣: Clone Repository

git clone https://github.com/your-username/blockchain-auth-system.git

cd blockchain-auth-system

Step 2️⃣: Start Blockchain

cd blockchain

npx hardhat node

Step 3️⃣: Deploy Smart Contract

npx hardhat run scripts/deploy.js --network localhost

Copy the contract address from the terminal.

Step 4️⃣: Configure Backend

Update application.properties with:

blockchain.private-key=0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d

blockchain.contract-address=YOUR_DEPLOYED_CONTRACT_ADDRESS

Step 5️⃣: Run Spring Boot Backend

cd backend

mvn spring-boot:run

🌍 API Endpoints

Method	Endpoint	Description

POST	/api/blockchain/register	     Register user on blockchain
POST	/api/blockchain/authenticate	 Authenticate user via blockchain
GET	/api/blockchain/clientVersion	   Get blockchain client info

🚀 Future Expansions

📌 Deploy on a Public Ethereum Testnet (Goerli, Sepolia)
📌 Integrate with a Frontend (React, Angular)
📌 Enhance Role-Based Authentication (User, Admin)

🤝 Contributing

Pull requests are welcome! Fork the repository, make changes, and submit a PR.

🎯 Why This Project is Rare & Innovative?

Very few systems implement user authentication entirely on the blockchain.
It eliminates centralized credential storage, reducing hacking risks.
A combination of JWT & Blockchain for secure, tamper-proof login systems.
Future potential for Decentralized Identity (DID) and Web3 applications.

📜 License

This project is open-source under the MIT License.

