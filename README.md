# COM212_Project

## Introduction
This project is a part of the COM212 course with the goal of creating a system to manage students employed in a think tank. The system will track student information, their ideas, and provide various functionalities to manage and retrieve this data efficiently.

## Project Overview
We aim to develop a robust database system to manage student employees and their ideas. The system needs to be fast, user-friendly, and fully functional.

## Features
- **Student Records**: Each student record includes last name, email login name, four-digit SSN, four-digit student number, the student's last 10 ideas, and the average rating of these ideas.
- **Efficient Access**: Retrieve any student record by SSN in less than O(n) average time. Display includes last name, email, SSN, average score of the last 10 ideas, and access to the last 10 ideas.
- **Idea Management**: Ideas include an idea number, submitter's SSN, description, and rating. Only the last 10 ideas per student are kept, with the ability to add new ideas and delete the oldest in constant time.
- **Best Idea Access**: The best idea is accessible in constant time, and new ideas can be added or the best idea deleted in O(log n) time.
- **Email Lookup**: Lookup a student's email using the student number in less than O(n) average time.
- **Student List**: Print a list of students (student number, name, SSN, average score) ordered by student number in O(n) time.
- **Data Persistence**: The program can save data, shut down, and restart with all data intact using serialization or a text file.
- **Extra Credit**: Maintain a list of all ideas ever added, searchable by idea number, with deletion functionality.

## Installation
To set up this project locally, follow these steps:
1. Clone the repository:
   ```sh
   git clone https://github.com/CSJ7701/COM212_Project.git
   ```
2. Navigate to the project directory:
   ```sh
   cd COM212_Project
   ```
3. Compile the Java files:
   ```sh
   javac -d bin src/**/*.java
   ```
4. Run the project:
   ```sh
   java -cp bin main
   ```

## Usage
- **Add Idea**: Enter the student's SSN, idea description, and rating to create a new idea and add it to the system.
- **Add Student**: Enter student details including last name, email, SSN, and student number.
- **Search Student Records**: Search by SSN or Student ID to view/edit student information and their last 10 ideas.
- **Search Idea Records**: Search for an idea directly by its ID to view/edit idea information.
- **List All Student Records**: Show a list of all student records, sorted by their Student ID. Allows the user to select a student and see more information.
- **Retrieve/Sell Best Idea**: Show information about the best idea from the pool of available ideas. Also provides the option to 'sell' the idea.

## Contribution Guidelines
We welcome contributions! To contribute to this project, follow these steps:
1. Fork the repository.
2. Create a new branch:
   ```sh
   git checkout -b feature-branch
   ```
3. Make your changes and commit them:
   ```sh
   git commit -m "Description of changes"
   ```
4. Push to your forked repository:
   ```sh
   git push origin feature-branch
   ```
5. Create a pull request.

## Recent Development Activity
For recent commits, visit the [commit history](https://github.com/CSJ7701/COM212_Project/commits).

## Issues and Discussions
To view or participate in discussions, check out the [discussions page](https://github.com/CSJ7701/COM212_Project/discussions).
For any issues, visit the [issues page](https://github.com/CSJ7701/COM212_Project/issues).
