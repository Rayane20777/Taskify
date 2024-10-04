INSERT INTO `teams` (`id`, `name`, `created_at`, `updated_at`, `deleted_at`)
VALUES
(1, 'Development Team A', NOW(), NOW(), NOW()),
(2, 'Design Team B', NOW(), NOW(), NOW()),
(3, 'Marketing Team C', NOW(), NOW(), NOW()),
(4, 'Development Team D', NOW(), NOW(), NOW()),
(5, 'Operations Team E', NOW(), NOW(), NOW()),
(6, 'Support Team F', NOW(), NOW(), NOW()),
(7, 'Sales Team G', NOW(), NOW(), NOW()),
(8, 'Product Team H', NOW(), NOW(), NOW()),
(9, 'QA Team I', NOW(), NOW(), NOW()),
(10, 'Admin Team J', NOW(), NOW(), NOW());


INSERT INTO `projects` (`id`, `name`, `description`, `start_date`, `end_date`, `status`, `team_id`, `created_at`, `updated_at`, `deleted_at`)
VALUES
(1, 'Website Redesign', 'Redesign of the company website', '2024-09-01', '2024-12-01', 'TODO', 1, NOW(), NOW(), NOW()),
(2, 'Mobile App Development', 'Development of a new mobile application', '2024-08-01', '2024-11-01', 'DOING', 2, NOW(), NOW(), NOW()),
(3, 'Database Migration', 'Migration of the database to a new server', '2024-07-01', '2024-10-01', 'PAUSED', 3, NOW(), NOW(), NOW()),
(4, 'Marketing Campaign', 'Planning and execution of a marketing campaign', '2024-09-15', '2024-11-30', 'DONE', 1, NOW(), NOW(), NOW()),
(5, 'E-commerce Platform', 'Development of a new e-commerce platform', '2024-05-01', '2024-09-30', 'CANCELED', 2, NOW(), NOW(), NOW()),
(6, 'SEO Optimization', 'SEO optimization for the company website', '2024-08-10', '2024-10-10', 'TODO', 3, NOW(), NOW(), NOW()),
(7, 'CRM System Upgrade', 'Upgrade to the company CRM system', '2024-09-01', '2024-12-31', 'DOING', 4, NOW(), NOW(), NOW()),
(8, 'Cloud Integration', 'Integration of cloud services into the company infrastructure', '2024-07-01', '2024-12-01', 'TODO', 5, NOW(), NOW(), NOW()),
(9, 'Social Media Strategy', 'Development of a new social media strategy', '2024-06-01', '2024-09-30', 'DONE', 4, NOW(), NOW(), NOW()),
(10, 'Cybersecurity Audit', 'Full audit of the company’s cybersecurity', '2024-10-01', '2025-01-01', 'TODO', 5, NOW(), NOW(), NOW());


INSERT INTO `members` (`id`, `fname`, `lname`, `email`, `role`, `team_id`, `created_at`, `updated_at`, `deleted_at`)
VALUES
(1, 'John', 'Doe', 'john.doe@example.com', 'PROJECT_MANAGER', 1, NOW(), NOW(), NOW()),
(2, 'Jane', 'Smith', 'jane.smith@example.com', 'DEVELOPER', 1, NOW(), NOW(), NOW()),
(3, 'Mike', 'Johnson', 'mike.johnson@example.com', 'DESIGNER', 2, NOW(), NOW(), NOW()),
(4, 'Anna', 'Brown', 'anna.brown@example.com', 'DEVELOPER', 2, NOW(), NOW(), NOW()),
(5, 'David', 'White', 'david.white@example.com', 'PROJECT_MANAGER', 3, NOW(), NOW(), NOW()),
(6, 'Laura', 'Garcia', 'laura.garcia@example.com', 'DESIGNER', 3, NOW(), NOW(), NOW()),
(7, 'Tom', 'Harris', 'tom.harris@example.com', 'DEVELOPER', 4, NOW(), NOW(), NOW()),
(8, 'Sara', 'Clark', 'sara.clark@example.com', 'DEVELOPER', 4, NOW(), NOW(), NOW()),
(9, 'Kevin', 'Lewis', 'kevin.lewis@example.com', 'DESIGNER', 5, NOW(), NOW(), NOW()),
(10, 'Emma', 'Walker', 'emma.walker@example.com', 'PROJECT_MANAGER', 5, NOW(), NOW(), NOW());


INSERT INTO `tasks` (`id`, `title`, `description`, `priority`, `status`, `start_date`, `end_date`, `member_id`, `project_id`, `created_at`, `updated_at`, `deleted_at`)
VALUES
(1, 'Design Homepage', 'Create design mockups for the homepage', 'HIGH', 'TODO', '2024-09-01', '2024-09-15', 3, 1, NOW(), NOW(), NOW()),
(2, 'Develop API', 'Develop the API for the mobile app', 'HIGH', 'DOING', '2024-09-05', '2024-09-30', 2, 2, NOW(), NOW(), NOW()),
(3, 'Database Backup', 'Create a backup of the database', 'MEDIUM', 'DONE', '2024-09-01', '2024-09-01', 6, 3, NOW(), NOW(), NOW()),
(4, 'SEO Analysis', 'Analyze the SEO performance', 'LOW', 'TODO', '2024-09-10', '2024-09-20', 9, 6, NOW(), NOW(), NOW()),
(5, 'Create Social Media Ads', 'Create ads for social media platforms', 'MEDIUM', 'DONE', '2024-09-01', '2024-09-15', 8, 9, NOW(), NOW(), NOW()),
(6, 'Setup Cloud Server', 'Set up cloud server infrastructure', 'HIGH', 'TODO', '2024-10-01', '2024-10-10', 7, 8, NOW(), NOW(), NOW()),
(7, 'Fix UI Bug', 'Fix the UI bug in the mobile app', 'MEDIUM', 'DOING', '2024-09-20', '2024-09-25', 4, 2, NOW(), NOW(), NOW()),
(8, 'Update CRM Features', 'Add new features to the CRM system', 'HIGH', 'TODO', '2024-09-05', '2024-09-30', 1, 7, NOW(), NOW(), NOW()),
(9, 'Review Design', 'Review design of the e-commerce platform', 'LOW', 'CANCELED', '2024-07-01', '2024-07-15', 5, 5, NOW(), NOW(), NOW()),
(10, 'Implement Security Patch', 'Implement the latest security patch', 'HIGH', 'TODO', '2024-10-01', '2024-10-05', 10, 10, NOW(), NOW(), NOW());

