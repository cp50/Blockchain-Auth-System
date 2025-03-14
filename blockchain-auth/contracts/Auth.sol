// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract Auth {
    struct User {
        string email;
        string passwordHash;
    }

    mapping(address => User) private users;
    mapping(string => bool) private registeredEmails;

    event UserRegistered(address indexed userAddress, string email);
    event UserAuthenticated(address indexed userAddress, string email);

    function register(string memory _email, string memory _passwordHash) public {
        require(!registeredEmails[_email], "Email is already registered!");

        users[msg.sender] = User(_email, _passwordHash);
        registeredEmails[_email] = true;

        emit UserRegistered(msg.sender, _email);
    }

    function authenticate(string memory _email, string memory _passwordHash) public view returns (bool) {
        User memory user = users[msg.sender];

        if (
            keccak256(abi.encodePacked(user.email)) == keccak256(abi.encodePacked(_email)) &&
            keccak256(abi.encodePacked(user.passwordHash)) == keccak256(abi.encodePacked(_passwordHash))
        ) {
            return true;
        } else {
            return false;
        }
    }

    function getUserEmail() public view returns (string memory) {
        return users[msg.sender].email;
    }
}
