# hyperlooppassengerbooking
passenger booking system for a hyperloop transport of a particular station.

# Hyperloop Passenger Booking System

## Introduction

The Hyperloop Passenger Booking System is a Java program that simulates the booking and transportation of passengers using a hyperloop transport system. This system is designed to efficiently manage the booking of passengers, start pods with the oldest passengers to their destinations via the fastest routes, and keep track of the passenger queue.

## System Logic

- The system initializes with the number of interconnecting routes and the starting station.
- Passengers can be added to the system with their name, age, and destination.
- Pods are started with passengers in order of their age, and the fastest route is chosen.
- The system can print the number of passengers remaining in the queue and their details.

## Command Line Interface

The system accepts the following commands:

1. **INIT** - Initializes the system with the number of routes and the starting station. The details of the routes are provided in the following lines.

2. **ADD_PASSENGER** - Adds passengers to the queue. Use the `ADD_PASSENGER X` command to add X passengers, followed by X lines containing passenger details (name, age, and destination).


3. **START_POD** - Starts pods with passengers. Use the `START_POD X` command to start X pods. The oldest passengers will be picked first and transported to their destinations via the fastest routes.


4. **PRINT_Q** - Prints the details of passengers remaining in the queue. Use `PRINT_Q X` to print details of X passengers.


## How to Run

1. Compile the Java code: `javac HyperloopBookingSystem.java`
2. Run the program: `java HyperloopBookingSystem`







