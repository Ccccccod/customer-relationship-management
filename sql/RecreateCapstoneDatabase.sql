USE master
GO
IF EXISTS(select * from sys.databases where name='Capstone')
BEGIN
	ALTER DATABASE Capstone SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
	DROP DATABASE Capstone
END

CREATE DATABASE Capstone