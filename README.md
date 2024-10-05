# 🛠️ Welcome to Taskify! 🛠️

Hello, Project Management Enthusiasts! 👋

Welcome to **Taskify**, your advanced Java-based web application for collaborative task management. Designed for businesses seeking efficient project and task tracking, **Taskify** is built using modern web technologies like **Java Servlets, JSP, and JSTL** to handle projects, tasks, and teams with ease.

## 🚀 About Taskify

**Taskify** helps teams manage projects, assign tasks, and track their completion with an intuitive web interface. It allows you to create and manage projects, assign tasks to team members, and track the overall progress through status updates. The platform is built to handle the collaborative needs of modern teams with robust features for CRUD operations, real-time statistics, and user-friendly interfaces.

## 📁 Project Structure

Here's an overview of the project structure for **Taskify**:

- **config**: Configuration settings such as `DatabaseConnection` and session management.
- **controllers**: Contains controllers for handling HTTP requests and managing projects, tasks, and teams.
- **dao**: Data Access Objects responsible for interacting with the database.
- **dto**: Data Transfer Objects to facilitate passing data between layers.
- **models**: Contains classes representing the core entities: `Project`, `Task`, `Team`, `Member`, and enums for status and priority.
- **repositories**: Contains classes for querying and managing entities in the database.
- **services**: Business logic layer that manages operations related to projects, tasks, and team members.
- **utils**: Utility classes that handle additional helper functions and input validation.
- **webapp**: Contains JSP pages and static resources (CSS, JavaScript) for rendering the UI.
- **resources**: Stores configuration files such as `app.properties` and database setup scripts.

## 🧩 Key Features

- **Project Management**: Create, view, and manage projects with essential details such as name, description, status, and statistics.
- **Team Management**: Add, update, and manage teams and their members.
- **Pagination**: View projects, and team members with paginated results.
- **Search**: Search projects and tasks using name and description.

## 🌐 Web Application Pages

### Project Management Page
- **Project Listing with Pagination**: Browse projects and view their statuses.
- **Create, Update, Delete Projects**: Manage project details and life cycles.
- **Search**: Search for specific projects.
- **Statistics**: View basic statistics for each project, including the number of tasks and members.

### Team Management Page
- **Team Listing with Pagination**: Manage teams and members with an overview of their tasks.
- **Add, Update, Delete Teams and Members**: Handle team compositions and assign roles.

## 🎨 Class Structure Overview

- **Project**: `name`, `description`, `dateStart`, `dateEnd`, `status` (Enum: *TODO*, *DOING*, *PAUSED*, *DONE*, *CANCELED*).
- **Task**: `title`, `description`, `priority` (Enum: *Low*, *Medium*, *High*), `status` (Enum: *To Do*, *In Progress*, *Completed*), `dateCreated`, `deadline`.
- **Member**: `firstName`, `lastName`, `email`, `role` (Enum: *Project Manager*, *Developer*, *Designer*).
- **Team**: `name`, list of `Members`.

## 🎯 Project Objectives

- Develop a web-based application using **Java Servlets, JSP, and JSTL**.
- Implement CRUD operations for managing projects, tasks, members, and teams.
- Adapt the existing database schema for web-based usage.
- Design an intuitive, responsive user interface.
- Apply agile project management principles throughout the development.
- Use advanced Java concepts and best practices to ensure performance and scalability.

## 🛠️ How to Use Taskify

### Prerequisites

Before running **Taskify**, ensure you have the following installed:

- **Java 8** or later
- **MySQL** database with the necessary tables and schema (setup instructions below)
- **JDBC Driver** for MySQL
- **Apache Tomcat** for deployment

### Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/Rayane20777/Taskify.git
   cd Taskify


2. Create a MySQL database and run the SQL script to set up the necessary tables:
    ```bash
    psql -U yourusername -d yourdatabase -f resources/Taskify.sql
    ```
   Replace `yourusername` and `yourdatabase` with your MySQL credentials and database name.

3. Update the `app.properties` file in the `resources` directory with your database connection details.
4. Deploy the application on Apache Tomcat or run it locally via your browser at `http://localhost:8080/Taskify`.


## 🎉 Get Started with Taskify Today!
Transform how your team manages projects and tasks with **Taskify**!  

For any questions, feedback, or suggestions, feel free to reach out to us. 📧