

# Java Chat App 💬  ✅ README.md
A simple realtime chat application built in Java with socket programming and multithreading.

## 🔍 Project Overview  
This project implements a client-server model for chat communication. Multiple clients can connect to a server and exchange messages in realtime. The focus is on demonstrating network communication, threading, and basic UI/console logic in Java.

## 🎯 Features  
- Server accepts multiple client connections concurrently using threads  
- Clients send and receive messages through the server  
- Clean separation of server and client modules  
- Lightweight and easy to run for local network/testing purposes  

## ⚙️ Technologies & Tools  
- Java (JDK 8+ or later)  
- Socket programming (`ServerSocket`, `Socket`)  
- Multithreading (`Thread` or `ExecutorService`)  
- IDE: IntelliJ IDEA Community Edition (optional)  
- (Optional) GUI or console based interface  

## 🚀 Getting Started  
### Prerequisites  
- Java JDK installed  
- (Optional) IntelliJ IDEA or any Java IDE  

### How to Run  
1. Clone the repository:  
   ```bash
   git clone https://github.com/SourishPati04/Java-Chat-App.git


Navigate into project directory:

cd Java-Chat-App


Compile the code (if using console):

javac -d out src/**/*.java


Run the server:

java -cp out com.yourpackage.Server


(Replace com.yourpackage.Server with actual server class path.)

Run one or multiple clients:

java -cp out com.yourpackage.Client


Start chatting! Clients will connect to the server and can send/receive messages.


