📘 Full-Stack Exam Portal
A secure and scalable web-based assessment system built using Spring Boot, Angular, and MySQL. This project streamlines the creation, management, and delivery of online quizzes for both administrators and students.
🚀 Features
- 🔐 Authentication & Authorization
Role-based login system using Spring Security and JWT tokens for Admin and Student access.
- 🧮 Quiz Management
Admins can create, update, and delete quizzes, questions, and categories.
- 🧑‍🎓 Student Interface
    - Students can browse quizzes, take timed assessments, and receive instant feedback.
- 📊 Result Processing
Automatic scoring and performance tracking with real-time result display.
- 🌐 RESTful APIs
Robust endpoints for quiz operations, user management, and result tracking.
- 💻 Responsive Frontend
Angular-based UI with secure routing, form validation, and dynamic data binding.
🛠️ Tech Stack
- Backend: Spring Boot, Hibernate, Spring Security, JWT
- Frontend: Angular
- Database: MySQL
- Tools: Postman, Git, Tomcat, VS Code, Eclipse
📂 Project Structure
exam-portal/
├── backend/
│   ├── src/main/java/com/examportal
│   └── REST APIs, Security Config, Services
├── frontend/
│   ├── src/app/
│   └── Components, Services, Routing
└── database/
    └── MySQL schema and seed data


🎯 Purpose
This project demonstrates full-stack development skills, secure authentication flows, and production-grade architecture. It’s ideal for educational platforms and aligns with enterprise standards like those required by Wipro’s WILP program.
📎 How to Run
- Clone the repo
- Set up MySQL and import schema
- Run backend with Spring Boot
- Serve frontend with Angular CLI
- Access via http://localhost:4200
