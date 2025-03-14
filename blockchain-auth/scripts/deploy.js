const hre = require("hardhat");

async function main() {
    console.log("🚀 Deploying contract...");

    // Get the Contract Factory
    const Auth = await hre.ethers.getContractFactory("Auth");

    // Deploy the contract
    const auth = await Auth.deploy();

    // Wait for deployment to be mined
    await auth.waitForDeployment();

    // Get deployed contract address
    const contractAddress = auth.target; // ✅ Fix for ethers v6

    console.log(`✅ Contract deployed successfully to: ${contractAddress}`);
}

// Run the deployment script and handle errors
main().catch((error) => {
    console.error("❌ Deployment failed:", error);
    process.exitCode = 1;
});
