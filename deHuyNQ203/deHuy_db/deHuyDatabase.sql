create database DeHuyNQ203;
GO

use DeHuyNQ203;
GO


create table department (
	id INT IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(255) NOT NULL
)
GO

create table employee (
	id INT IDENTITY(1,1) PRIMARY KEY,
	id_department INT,
	name NVARCHAR(255) NOT NULL,
	email NVARCHAR(255) NOT NULL, 
	salary FLOAT,
	FOREIGN KEY (id_department) REFERENCES department(id)
)
GO


insert into
    department
values
    ('Phong ban 1'),
    ('Phong ban 2'),
    ('Phong ban 3'),
    ('Phong ban 4');
GO

insert into
    employee(name, email, salary, id_department)
values
    ('Nhan vien 1', 'nhanvien1@gmail.com', 1000.00, 1),
    ('Nhan vien 2', 'nhanvien2@gmail.com', 2000.00, 2),
    ('Nhan vien 3', 'nhanvien3@gmail.com', 3000.00, 3),
    ('Nhan vien 4', 'nhanvien4@gmail.com', 4000.00, 4);
GO

SELECT * FROM department
SELECT * FROM employee