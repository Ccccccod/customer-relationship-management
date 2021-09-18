USE [master]
GO
/****** Object:  Database [Capstone]    Script Date: 9/18/2021 10:49:15 AM ******/
CREATE DATABASE [Capstone]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Capstone', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Capstone.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Capstone_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Capstone_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Capstone] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Capstone].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Capstone] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Capstone] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Capstone] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Capstone] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Capstone] SET ARITHABORT OFF 
GO
ALTER DATABASE [Capstone] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Capstone] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Capstone] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Capstone] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Capstone] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Capstone] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Capstone] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Capstone] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Capstone] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Capstone] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Capstone] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Capstone] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Capstone] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Capstone] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Capstone] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Capstone] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Capstone] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Capstone] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Capstone] SET  MULTI_USER 
GO
ALTER DATABASE [Capstone] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Capstone] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Capstone] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Capstone] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Capstone] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Capstone] SET QUERY_STORE = OFF
GO
USE [Capstone]
GO
/****** Object:  Table [dbo].[business_type]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[business_type](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[career]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[career](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[field_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[classification]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[classification](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[contact]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[contact](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[code] [varchar](255) NOT NULL,
	[address] [nvarchar](255) NULL,
	[bank] [nvarchar](255) NULL,
	[bank_account] [varchar](255) NULL,
	[date_of_birth] [date] NULL,
	[email] [varchar](255) NULL,
	[facebook] [varchar](255) NULL,
	[last_name] [nvarchar](255) NULL,
	[not_call_phone] [bit] NULL,
	[not_send_email] [bit] NULL,
	[office_email] [varchar](255) NULL,
	[office_phone] [varchar](255) NULL,
	[other_phone] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[country_id] [bigint] NULL,
	[customer_id] [bigint] NULL,
	[department_id] [bigint] NULL,
	[district_id] [bigint] NULL,
	[gender] [bigint] NULL,
	[marital_status] [bigint] NULL,
	[position_id] [bigint] NULL,
	[province_id] [bigint] NULL,
	[source_id] [bigint] NULL,
	[vocative_id] [bigint] NULL,
	[ward_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[contact_classification]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[contact_classification](
	[contact_id] [bigint] NOT NULL,
	[classification_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[contact_id] ASC,
	[classification_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[address] [nvarchar](255) NULL,
	[bank] [nvarchar](255) NULL,
	[bank_account] [varchar](255) NULL,
	[code] [varchar](255) NOT NULL,
	[customer_since] [date] NULL,
	[email] [varchar](255) NULL,
	[founded_date] [date] NULL,
	[name] [nvarchar](255) NULL,
	[phone] [varchar](255) NULL,
	[short_name] [nvarchar](255) NULL,
	[tax_code] [varchar](255) NULL,
	[website] [nvarchar](255) NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[country_id] [bigint] NULL,
	[district_id] [bigint] NULL,
	[income_id] [bigint] NULL,
	[province_id] [bigint] NULL,
	[source_id] [bigint] NULL,
	[type_id] [bigint] NULL,
	[ward_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer_career]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer_career](
	[customer_id] [bigint] NOT NULL,
	[career_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC,
	[career_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer_classification]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer_classification](
	[customer_id] [bigint] NOT NULL,
	[classification_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC,
	[classification_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer_field]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer_field](
	[customer_id] [bigint] NOT NULL,
	[field_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC,
	[field_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[department]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[department](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[field]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[field](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[gender]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gender](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[income]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[income](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[invoice]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[invoice](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[address] [nvarchar](255) NULL,
	[bank] [nvarchar](255) NULL,
	[bank_account] [varchar](255) NULL,
	[code] [varchar](255) NOT NULL,
	[receiver_email] [nvarchar](255) NULL,
	[receiver_name] [nvarchar](255) NULL,
	[phone] [varchar](255) NULL,
	[tax_code] [varchar](255) NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[buyer_id] [bigint] NULL,
	[country_id] [bigint] NULL,
	[customer_id] [bigint] NULL,
	[district_id] [bigint] NULL,
	[order_id] [bigint] NULL,
	[province_id] [bigint] NULL,
	[ward_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[marital_status]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[marital_status](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[opportunity]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[opportunity](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[address] [nvarchar](255) NULL,
	[code] [varchar](255) NOT NULL,
	[expected_end_date] [date] NOT NULL,
	[name] [nvarchar](255) NULL,
	[success_rate] [int] NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[contact_id] [bigint] NULL,
	[country_id] [bigint] NULL,
	[customer_id] [bigint] NULL,
	[district_id] [bigint] NULL,
	[opportunity_phase_id] [bigint] NULL,
	[province_id] [bigint] NULL,
	[source_id] [bigint] NULL,
	[ward_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[opportunity_phase]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[opportunity_phase](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[success_rate] [int] NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[address] [nvarchar](255) NULL,
	[code] [varchar](255) NOT NULL,
	[delivery_date] [date] NOT NULL,
	[explanation] [nvarchar](255) NULL,
	[liquidation_date] [date] NOT NULL,
	[order_date] [date] NOT NULL,
	[received_money] [bigint] NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[contact_id] [bigint] NULL,
	[country_id] [bigint] NULL,
	[customer_id] [bigint] NULL,
	[district_id] [bigint] NULL,
	[opportunity_id] [bigint] NULL,
	[province_id] [bigint] NULL,
	[ward_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[permission_action]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permission_action](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[permission_function]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permission_function](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[permission_function_action]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permission_function_action](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[permission_action_id] [bigint] NOT NULL,
	[permission_function_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [PERMISSION_FUNCTION_ACTION_UK] UNIQUE NONCLUSTERED 
(
	[permission_function_id] ASC,
	[permission_action_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phone_area_code]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phone_area_code](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[position]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[position](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[potential]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[potential](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[address] [nvarchar](255) NULL,
	[bank] [nvarchar](255) NULL,
	[bank_account] [varchar](255) NULL,
	[code] [varchar](255) NOT NULL,
	[customer_id] [nvarchar](255) NULL,
	[customer_tax_code] [varchar](255) NULL,
	[date_of_birth] [date] NULL,
	[email] [varchar](255) NULL,
	[facebook] [varchar](255) NULL,
	[founded_date] [date] NULL,
	[last_name] [nvarchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[not_call_phone] [bit] NULL,
	[not_send_email] [bit] NULL,
	[office_email] [varchar](255) NULL,
	[office_phone] [varchar](255) NULL,
	[other_phone] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[tax_code] [varchar](255) NULL,
	[website] [varchar](255) NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[business_type_id] [bigint] NULL,
	[country_id] [bigint] NULL,
	[department_id] [bigint] NULL,
	[district_id] [bigint] NULL,
	[gender_id] [bigint] NULL,
	[income_id] [bigint] NULL,
	[phone_area_code_id] [bigint] NULL,
	[position_id] [bigint] NULL,
	[province_id] [bigint] NULL,
	[source_id] [bigint] NULL,
	[vocative_id] [bigint] NULL,
	[ward_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[potential_career]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[potential_career](
	[potential_id] [bigint] NOT NULL,
	[career_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[potential_id] ASC,
	[career_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[potential_classification]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[potential_classification](
	[potential_id] [bigint] NOT NULL,
	[classification_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[potential_id] ASC,
	[classification_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[potential_field]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[potential_field](
	[potential_id] [bigint] NOT NULL,
	[field_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[potential_id] ASC,
	[field_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[code] [varchar](255) NOT NULL,
	[buy_price] [bigint] NULL,
	[cost_unit_price] [bigint] NULL,
	[enter_unit_priority_after_tax] [bit] NULL,
	[explanation] [nvarchar](255) NULL,
	[implicit_record] [bit] NULL,
	[permanent_price] [bigint] NULL,
	[sell_price] [bigint] NULL,
	[sell_price1] [bigint] NULL,
	[sell_price2] [bigint] NULL,
	[vat] [int] NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[product_type_id] [bigint] NULL,
	[unit_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_info]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_info](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[amount] [int] NOT NULL,
	[discount] [int] NOT NULL,
	[explanation] [nvarchar](255) NULL,
	[price] [bigint] NOT NULL,
	[product_code] [varchar](255) NOT NULL,
	[vat] [int] NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[invoice_id] [bigint] NULL,
	[opportunity_id] [bigint] NULL,
	[order_id] [bigint] NULL,
	[product_id] [bigint] NULL,
	[unit_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_type]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_type](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[code] [varchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[product_type_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[description] [nvarchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [ROLE_UK] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role_permission_function_action]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role_permission_function_action](
	[role_id] [bigint] NOT NULL,
	[permission_function_action_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC,
	[permission_function_action_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[source]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[source](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_user_country]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user_country](
	[user_countryid] [bigint] IDENTITY(1,1) NOT NULL,
	[user_country_code] [varchar](255) NOT NULL,
	[user_country_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[user_countryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_user_district]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user_district](
	[user_districtid] [bigint] IDENTITY(1,1) NOT NULL,
	[user_district_name] [nvarchar](255) NULL,
	[type] [nvarchar](255) NULL,
	[user_provinceid] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[user_districtid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_user_province]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user_province](
	[user_provinceid] [bigint] IDENTITY(1,1) NOT NULL,
	[user_province_name] [nvarchar](255) NULL,
	[type] [nvarchar](255) NULL,
	[user_countryid] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[user_provinceid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_user_ward]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user_ward](
	[user_wardid] [bigint] IDENTITY(1,1) NOT NULL,
	[user_ward_name] [nvarchar](255) NULL,
	[type] [nvarchar](255) NULL,
	[districtid] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[user_wardid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[type]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[type](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[unit]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[unit](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[address] [nvarchar](255) NULL,
	[date_of_birth] [date] NULL,
	[email] [varchar](320) NOT NULL,
	[last_name] [nvarchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[password] [varchar](128) NOT NULL,
	[phone] [varchar](255) NULL,
	[username] [varchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
	[gender] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [USER_UK] UNIQUE NONCLUSTERED 
(
	[username] ASC,
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_role]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_role](
	[user_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[vocative]    Script Date: 9/18/2021 10:49:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vocative](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NULL,
	[deleted] [bit] NULL,
	[shared] [bit] NULL,
	[updated_at] [datetime2](7) NULL,
	[name] [nvarchar](255) NOT NULL,
	[created_by] [bigint] NULL,
	[owner] [bigint] NULL,
	[updated_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[business_type]  WITH CHECK ADD  CONSTRAINT [FKai33qul1bnhtvgpsx3hrxt4u6] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[business_type] CHECK CONSTRAINT [FKai33qul1bnhtvgpsx3hrxt4u6]
GO
ALTER TABLE [dbo].[business_type]  WITH CHECK ADD  CONSTRAINT [FKh9qxncqgteyt3et2ibojv0ped] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[business_type] CHECK CONSTRAINT [FKh9qxncqgteyt3et2ibojv0ped]
GO
ALTER TABLE [dbo].[business_type]  WITH CHECK ADD  CONSTRAINT [FKs4xsts6ccpwkvp39pfy0yace0] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[business_type] CHECK CONSTRAINT [FKs4xsts6ccpwkvp39pfy0yace0]
GO
ALTER TABLE [dbo].[career]  WITH CHECK ADD  CONSTRAINT [FKdu8oym29jk0u4u49em4lhvmau] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[career] CHECK CONSTRAINT [FKdu8oym29jk0u4u49em4lhvmau]
GO
ALTER TABLE [dbo].[career]  WITH CHECK ADD  CONSTRAINT [FKimbu4w5si97cvtdpjdnk42u01] FOREIGN KEY([field_id])
REFERENCES [dbo].[field] ([id])
GO
ALTER TABLE [dbo].[career] CHECK CONSTRAINT [FKimbu4w5si97cvtdpjdnk42u01]
GO
ALTER TABLE [dbo].[career]  WITH CHECK ADD  CONSTRAINT [FKkywnm08cwjjsx0r951wgha9ax] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[career] CHECK CONSTRAINT [FKkywnm08cwjjsx0r951wgha9ax]
GO
ALTER TABLE [dbo].[career]  WITH CHECK ADD  CONSTRAINT [FKs7lpj3b5av8nsse2pb7bfb9ia] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[career] CHECK CONSTRAINT [FKs7lpj3b5av8nsse2pb7bfb9ia]
GO
ALTER TABLE [dbo].[classification]  WITH CHECK ADD  CONSTRAINT [FK9pw5kb4j2cldubdo24x37ry2q] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[classification] CHECK CONSTRAINT [FK9pw5kb4j2cldubdo24x37ry2q]
GO
ALTER TABLE [dbo].[classification]  WITH CHECK ADD  CONSTRAINT [FKas2an64gk21de8r42bae2ulma] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[classification] CHECK CONSTRAINT [FKas2an64gk21de8r42bae2ulma]
GO
ALTER TABLE [dbo].[classification]  WITH CHECK ADD  CONSTRAINT [FKdp6gj7at4642qedogsl3jmed6] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[classification] CHECK CONSTRAINT [FKdp6gj7at4642qedogsl3jmed6]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FK45virqanqc00k84nr1e9ndq0d] FOREIGN KEY([ward_id])
REFERENCES [dbo].[tbl_user_ward] ([user_wardid])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FK45virqanqc00k84nr1e9ndq0d]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FK4jijf38vu0fqfkgop0bi9rgc7] FOREIGN KEY([department_id])
REFERENCES [dbo].[department] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FK4jijf38vu0fqfkgop0bi9rgc7]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FK6i5ead01yhytr38htxcdagu60] FOREIGN KEY([position_id])
REFERENCES [dbo].[position] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FK6i5ead01yhytr38htxcdagu60]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FK8lqhpxoim7bgf41j4liycj6w9] FOREIGN KEY([marital_status])
REFERENCES [dbo].[marital_status] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FK8lqhpxoim7bgf41j4liycj6w9]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FK8o8f7jybltkgwn86ybinvq9d0] FOREIGN KEY([vocative_id])
REFERENCES [dbo].[vocative] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FK8o8f7jybltkgwn86ybinvq9d0]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FK9kqktkra6s8g3nhmfu8x5n19r] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FK9kqktkra6s8g3nhmfu8x5n19r]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKa02s6b6wk0lgooijmdyw1qca3] FOREIGN KEY([district_id])
REFERENCES [dbo].[tbl_user_district] ([user_districtid])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKa02s6b6wk0lgooijmdyw1qca3]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKckoarj5a5jmet3b3smgdhaopw] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKckoarj5a5jmet3b3smgdhaopw]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKdoetcflog2qbwm0m10fj5ncxa] FOREIGN KEY([gender])
REFERENCES [dbo].[gender] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKdoetcflog2qbwm0m10fj5ncxa]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKh4ljp8thjj9l2lj5mhcvnbrp5] FOREIGN KEY([country_id])
REFERENCES [dbo].[tbl_user_country] ([user_countryid])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKh4ljp8thjj9l2lj5mhcvnbrp5]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKiygi14xoq8q2g6f1om8dj5ch4] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKiygi14xoq8q2g6f1om8dj5ch4]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKjilgqaqw6ttl7kd4js7jgwis1] FOREIGN KEY([source_id])
REFERENCES [dbo].[source] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKjilgqaqw6ttl7kd4js7jgwis1]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKtii99opplwgdanpjpn1v1hlmu] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKtii99opplwgdanpjpn1v1hlmu]
GO
ALTER TABLE [dbo].[contact]  WITH CHECK ADD  CONSTRAINT [FKx654575qt9dpqjqate28kjcq] FOREIGN KEY([province_id])
REFERENCES [dbo].[tbl_user_province] ([user_provinceid])
GO
ALTER TABLE [dbo].[contact] CHECK CONSTRAINT [FKx654575qt9dpqjqate28kjcq]
GO
ALTER TABLE [dbo].[contact_classification]  WITH CHECK ADD  CONSTRAINT [FKac26gnixxxj3swkcwwv2d0qtw] FOREIGN KEY([contact_id])
REFERENCES [dbo].[contact] ([id])
GO
ALTER TABLE [dbo].[contact_classification] CHECK CONSTRAINT [FKac26gnixxxj3swkcwwv2d0qtw]
GO
ALTER TABLE [dbo].[contact_classification]  WITH CHECK ADD  CONSTRAINT [FKrvv4gfeqye921g1g7y8ye84yp] FOREIGN KEY([classification_id])
REFERENCES [dbo].[classification] ([id])
GO
ALTER TABLE [dbo].[contact_classification] CHECK CONSTRAINT [FKrvv4gfeqye921g1g7y8ye84yp]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FK10egq4i27ny290bnntg8ichjv] FOREIGN KEY([type_id])
REFERENCES [dbo].[type] ([id])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FK10egq4i27ny290bnntg8ichjv]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FK1snkpnnxmcj26e1lb1vea8f79] FOREIGN KEY([ward_id])
REFERENCES [dbo].[tbl_user_ward] ([user_wardid])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FK1snkpnnxmcj26e1lb1vea8f79]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FK2u8yln0t4tdoqghxt6ocrmnnp] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FK2u8yln0t4tdoqghxt6ocrmnnp]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FK812jol25877detfd2wqx2o8bn] FOREIGN KEY([district_id])
REFERENCES [dbo].[tbl_user_district] ([user_districtid])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FK812jol25877detfd2wqx2o8bn]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FKepdpxypby8fuhaj1g6ut0xnt3] FOREIGN KEY([income_id])
REFERENCES [dbo].[income] ([id])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FKepdpxypby8fuhaj1g6ut0xnt3]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FKf07lybk72u9p9diinasx7tihd] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FKf07lybk72u9p9diinasx7tihd]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FKf0j7i801gg32kls23j24fkus7] FOREIGN KEY([country_id])
REFERENCES [dbo].[tbl_user_country] ([user_countryid])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FKf0j7i801gg32kls23j24fkus7]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FKfc20as04lwa5cgxl25cjufote] FOREIGN KEY([source_id])
REFERENCES [dbo].[source] ([id])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FKfc20as04lwa5cgxl25cjufote]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FKkbks3ivux6kbsucbpljo6anmj] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FKkbks3ivux6kbsucbpljo6anmj]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD  CONSTRAINT [FKphnnyo43lohppgu0i0l3h4x64] FOREIGN KEY([province_id])
REFERENCES [dbo].[tbl_user_province] ([user_provinceid])
GO
ALTER TABLE [dbo].[customer] CHECK CONSTRAINT [FKphnnyo43lohppgu0i0l3h4x64]
GO
ALTER TABLE [dbo].[customer_career]  WITH CHECK ADD  CONSTRAINT [FK3xsx5rcq5f5vvc6b5j9fsnh8m] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[customer_career] CHECK CONSTRAINT [FK3xsx5rcq5f5vvc6b5j9fsnh8m]
GO
ALTER TABLE [dbo].[customer_career]  WITH CHECK ADD  CONSTRAINT [FKfiqe2b6plroiciime6cvht6d1] FOREIGN KEY([career_id])
REFERENCES [dbo].[career] ([id])
GO
ALTER TABLE [dbo].[customer_career] CHECK CONSTRAINT [FKfiqe2b6plroiciime6cvht6d1]
GO
ALTER TABLE [dbo].[customer_classification]  WITH CHECK ADD  CONSTRAINT [FK314p5vyq6kbt80j2712i5qva8] FOREIGN KEY([classification_id])
REFERENCES [dbo].[classification] ([id])
GO
ALTER TABLE [dbo].[customer_classification] CHECK CONSTRAINT [FK314p5vyq6kbt80j2712i5qva8]
GO
ALTER TABLE [dbo].[customer_classification]  WITH CHECK ADD  CONSTRAINT [FKpc93si9fmekm5tw69q3h4lnly] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[customer_classification] CHECK CONSTRAINT [FKpc93si9fmekm5tw69q3h4lnly]
GO
ALTER TABLE [dbo].[customer_field]  WITH CHECK ADD  CONSTRAINT [FKddxg5iluhflyu3mny99a2c6wh] FOREIGN KEY([field_id])
REFERENCES [dbo].[field] ([id])
GO
ALTER TABLE [dbo].[customer_field] CHECK CONSTRAINT [FKddxg5iluhflyu3mny99a2c6wh]
GO
ALTER TABLE [dbo].[customer_field]  WITH CHECK ADD  CONSTRAINT [FKnwbn2ov48dpjobewqvu1sxqc2] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[customer_field] CHECK CONSTRAINT [FKnwbn2ov48dpjobewqvu1sxqc2]
GO
ALTER TABLE [dbo].[department]  WITH CHECK ADD  CONSTRAINT [FK1ei4mpm2vsw7sk1t2wj4cxjfv] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[department] CHECK CONSTRAINT [FK1ei4mpm2vsw7sk1t2wj4cxjfv]
GO
ALTER TABLE [dbo].[department]  WITH CHECK ADD  CONSTRAINT [FKcsfr3wy1130iqhbosnh03y7pq] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[department] CHECK CONSTRAINT [FKcsfr3wy1130iqhbosnh03y7pq]
GO
ALTER TABLE [dbo].[department]  WITH CHECK ADD  CONSTRAINT [FKpn617h8ym4n7ds4q5ykj06ux8] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[department] CHECK CONSTRAINT [FKpn617h8ym4n7ds4q5ykj06ux8]
GO
ALTER TABLE [dbo].[field]  WITH CHECK ADD  CONSTRAINT [FK8dnk90f6nc2jlfjvlueatdqnh] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[field] CHECK CONSTRAINT [FK8dnk90f6nc2jlfjvlueatdqnh]
GO
ALTER TABLE [dbo].[field]  WITH CHECK ADD  CONSTRAINT [FKhlrdsjwb078k9kclb20f2u986] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[field] CHECK CONSTRAINT [FKhlrdsjwb078k9kclb20f2u986]
GO
ALTER TABLE [dbo].[field]  WITH CHECK ADD  CONSTRAINT [FKssx46yvlikgn95y3dri2sbmkr] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[field] CHECK CONSTRAINT [FKssx46yvlikgn95y3dri2sbmkr]
GO
ALTER TABLE [dbo].[gender]  WITH CHECK ADD  CONSTRAINT [FK3eo7t8yev8lyntal4jtj038t4] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[gender] CHECK CONSTRAINT [FK3eo7t8yev8lyntal4jtj038t4]
GO
ALTER TABLE [dbo].[gender]  WITH CHECK ADD  CONSTRAINT [FK6ryh659pp6u6bvc34pf82hbin] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[gender] CHECK CONSTRAINT [FK6ryh659pp6u6bvc34pf82hbin]
GO
ALTER TABLE [dbo].[gender]  WITH CHECK ADD  CONSTRAINT [FKbogi7p6etievs3359m2w3iakr] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[gender] CHECK CONSTRAINT [FKbogi7p6etievs3359m2w3iakr]
GO
ALTER TABLE [dbo].[income]  WITH CHECK ADD  CONSTRAINT [FKa83fetjl1i7ih43rxdefa4hw8] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[income] CHECK CONSTRAINT [FKa83fetjl1i7ih43rxdefa4hw8]
GO
ALTER TABLE [dbo].[income]  WITH CHECK ADD  CONSTRAINT [FKasx0cllfn6ev618b779f2s2h1] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[income] CHECK CONSTRAINT [FKasx0cllfn6ev618b779f2s2h1]
GO
ALTER TABLE [dbo].[income]  WITH CHECK ADD  CONSTRAINT [FKpw2knoxkrtrgll0cpn1nsovpx] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[income] CHECK CONSTRAINT [FKpw2knoxkrtrgll0cpn1nsovpx]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FK1a8a9xlalw1t5p02cqly78705] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FK1a8a9xlalw1t5p02cqly78705]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FK5e32ukwo9uknwhylogvta4po6] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FK5e32ukwo9uknwhylogvta4po6]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKahae8jegfgt70kinmiu5v6mmh] FOREIGN KEY([district_id])
REFERENCES [dbo].[tbl_user_district] ([user_districtid])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKahae8jegfgt70kinmiu5v6mmh]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKg40b81tq2rwg8aokwocfm3gy0] FOREIGN KEY([buyer_id])
REFERENCES [dbo].[contact] ([id])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKg40b81tq2rwg8aokwocfm3gy0]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKjraveyyq531r4ee7o2pnenuq0] FOREIGN KEY([country_id])
REFERENCES [dbo].[tbl_user_country] ([user_countryid])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKjraveyyq531r4ee7o2pnenuq0]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKoeukx7owop25xgim7e6fkgrr3] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKoeukx7owop25xgim7e6fkgrr3]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKojdct2hhscv0upky1sfowqg1a] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKojdct2hhscv0upky1sfowqg1a]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKq8cnwu398t4fv5q97vyxfk3gg] FOREIGN KEY([province_id])
REFERENCES [dbo].[tbl_user_province] ([user_provinceid])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKq8cnwu398t4fv5q97vyxfk3gg]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKr27vrfyll0shs80upv1rmctie] FOREIGN KEY([order_id])
REFERENCES [dbo].[order] ([id])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKr27vrfyll0shs80upv1rmctie]
GO
ALTER TABLE [dbo].[invoice]  WITH CHECK ADD  CONSTRAINT [FKsjirnclyosyd0evcap2rradw5] FOREIGN KEY([ward_id])
REFERENCES [dbo].[tbl_user_ward] ([user_wardid])
GO
ALTER TABLE [dbo].[invoice] CHECK CONSTRAINT [FKsjirnclyosyd0evcap2rradw5]
GO
ALTER TABLE [dbo].[marital_status]  WITH CHECK ADD  CONSTRAINT [FKanx7ss2uwttevrrivqfs8ftam] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[marital_status] CHECK CONSTRAINT [FKanx7ss2uwttevrrivqfs8ftam]
GO
ALTER TABLE [dbo].[marital_status]  WITH CHECK ADD  CONSTRAINT [FKj2alm1r424vq8bolw52qms72r] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[marital_status] CHECK CONSTRAINT [FKj2alm1r424vq8bolw52qms72r]
GO
ALTER TABLE [dbo].[marital_status]  WITH CHECK ADD  CONSTRAINT [FKjrlv84gdb31ufybkd514heeci] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[marital_status] CHECK CONSTRAINT [FKjrlv84gdb31ufybkd514heeci]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FK5i8wgyt0jmk0wdok7ipa1jdtv] FOREIGN KEY([opportunity_phase_id])
REFERENCES [dbo].[opportunity_phase] ([id])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FK5i8wgyt0jmk0wdok7ipa1jdtv]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FK5w60qtapnp6t1qrjgqrpeqetu] FOREIGN KEY([district_id])
REFERENCES [dbo].[tbl_user_district] ([user_districtid])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FK5w60qtapnp6t1qrjgqrpeqetu]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FK9kgkwli0oydb5uridq9s73h36] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FK9kgkwli0oydb5uridq9s73h36]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKb3dq6sojb3gvm04a0w3awc1m] FOREIGN KEY([source_id])
REFERENCES [dbo].[source] ([id])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKb3dq6sojb3gvm04a0w3awc1m]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKd7u94o520mhwrpxftupmsy109] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKd7u94o520mhwrpxftupmsy109]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKfla4w2uaukiscbn00jy2kxul8] FOREIGN KEY([ward_id])
REFERENCES [dbo].[tbl_user_ward] ([user_wardid])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKfla4w2uaukiscbn00jy2kxul8]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKhprqxsse9nt42mtq6b0o7jxcs] FOREIGN KEY([country_id])
REFERENCES [dbo].[tbl_user_country] ([user_countryid])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKhprqxsse9nt42mtq6b0o7jxcs]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKjlk869qujnh87hgtjpa5ni4ly] FOREIGN KEY([province_id])
REFERENCES [dbo].[tbl_user_province] ([user_provinceid])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKjlk869qujnh87hgtjpa5ni4ly]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKmsp9tcwkqfu3x8xmcu6epmb9n] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKmsp9tcwkqfu3x8xmcu6epmb9n]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKo5iiy8cn6h1f2kae3s49yteot] FOREIGN KEY([contact_id])
REFERENCES [dbo].[contact] ([id])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKo5iiy8cn6h1f2kae3s49yteot]
GO
ALTER TABLE [dbo].[opportunity]  WITH CHECK ADD  CONSTRAINT [FKov0mdcjiekto2qxferfbk6nt0] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[opportunity] CHECK CONSTRAINT [FKov0mdcjiekto2qxferfbk6nt0]
GO
ALTER TABLE [dbo].[opportunity_phase]  WITH CHECK ADD  CONSTRAINT [FK9cd6c6dta3kxb8rr91t2l3121] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[opportunity_phase] CHECK CONSTRAINT [FK9cd6c6dta3kxb8rr91t2l3121]
GO
ALTER TABLE [dbo].[opportunity_phase]  WITH CHECK ADD  CONSTRAINT [FK9dl1yx6v3d57adwt9n1kybagh] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[opportunity_phase] CHECK CONSTRAINT [FK9dl1yx6v3d57adwt9n1kybagh]
GO
ALTER TABLE [dbo].[opportunity_phase]  WITH CHECK ADD  CONSTRAINT [FKp7v7haslxm0lv44ebw1s219lo] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[opportunity_phase] CHECK CONSTRAINT [FKp7v7haslxm0lv44ebw1s219lo]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK1oduxyuuo3n2g98l3j7754vym] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK1oduxyuuo3n2g98l3j7754vym]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK27m8e2in2i2e8d54o5kktagay] FOREIGN KEY([contact_id])
REFERENCES [dbo].[contact] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK27m8e2in2i2e8d54o5kktagay]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK7uheke8mll49lrlti3dtj2ge8] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK7uheke8mll49lrlti3dtj2ge8]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK9kn0ltxulu4x65n9d9pb2yroq] FOREIGN KEY([district_id])
REFERENCES [dbo].[tbl_user_district] ([user_districtid])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK9kn0ltxulu4x65n9d9pb2yroq]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK9sihfacufobxpu9o5wbct4mgt] FOREIGN KEY([country_id])
REFERENCES [dbo].[tbl_user_country] ([user_countryid])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK9sihfacufobxpu9o5wbct4mgt]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FKfqnscsvyyia946hde7t5uu5g8] FOREIGN KEY([province_id])
REFERENCES [dbo].[tbl_user_province] ([user_provinceid])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FKfqnscsvyyia946hde7t5uu5g8]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FKi75qg3x5q9bg1gvp2eg4gycth] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FKi75qg3x5q9bg1gvp2eg4gycth]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FKoym3iuej78j8pnhg1dc0cgso1] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FKoym3iuej78j8pnhg1dc0cgso1]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FKqf0hgg3516q572m6hmfvxt7ic] FOREIGN KEY([opportunity_id])
REFERENCES [dbo].[opportunity] ([id])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FKqf0hgg3516q572m6hmfvxt7ic]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FKsisgh5k7ahkoyadyhckv3ycxl] FOREIGN KEY([ward_id])
REFERENCES [dbo].[tbl_user_ward] ([user_wardid])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FKsisgh5k7ahkoyadyhckv3ycxl]
GO
ALTER TABLE [dbo].[permission_action]  WITH CHECK ADD  CONSTRAINT [FK6v6y5ovihine7arxl7gewmatk] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_action] CHECK CONSTRAINT [FK6v6y5ovihine7arxl7gewmatk]
GO
ALTER TABLE [dbo].[permission_action]  WITH CHECK ADD  CONSTRAINT [FKgubasbs76qgo1wbm6pb7xj8w8] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_action] CHECK CONSTRAINT [FKgubasbs76qgo1wbm6pb7xj8w8]
GO
ALTER TABLE [dbo].[permission_action]  WITH CHECK ADD  CONSTRAINT [FKt50xv1ndxeu1x5ctb37qhppon] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_action] CHECK CONSTRAINT [FKt50xv1ndxeu1x5ctb37qhppon]
GO
ALTER TABLE [dbo].[permission_function]  WITH CHECK ADD  CONSTRAINT [FK49jot2l1eagg73ob65kyn4sfe] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_function] CHECK CONSTRAINT [FK49jot2l1eagg73ob65kyn4sfe]
GO
ALTER TABLE [dbo].[permission_function]  WITH CHECK ADD  CONSTRAINT [FKgdw5vtr2llm8h00jxmpugjf3o] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_function] CHECK CONSTRAINT [FKgdw5vtr2llm8h00jxmpugjf3o]
GO
ALTER TABLE [dbo].[permission_function]  WITH CHECK ADD  CONSTRAINT [FKm1bxjtvi2q7gydm9or62f5jtp] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_function] CHECK CONSTRAINT [FKm1bxjtvi2q7gydm9or62f5jtp]
GO
ALTER TABLE [dbo].[permission_function_action]  WITH CHECK ADD  CONSTRAINT [FK1g5jdn1ytb88am1hw1njtl36j] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_function_action] CHECK CONSTRAINT [FK1g5jdn1ytb88am1hw1njtl36j]
GO
ALTER TABLE [dbo].[permission_function_action]  WITH CHECK ADD  CONSTRAINT [FK9rs3hdvy03x8a0ajp82gf3una] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_function_action] CHECK CONSTRAINT [FK9rs3hdvy03x8a0ajp82gf3una]
GO
ALTER TABLE [dbo].[permission_function_action]  WITH CHECK ADD  CONSTRAINT [FKgo6i73owbi995qwmjiuas75kw] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[permission_function_action] CHECK CONSTRAINT [FKgo6i73owbi995qwmjiuas75kw]
GO
ALTER TABLE [dbo].[permission_function_action]  WITH CHECK ADD  CONSTRAINT [FKh85bvei8b4p7k68uvj5egi3gv] FOREIGN KEY([permission_function_id])
REFERENCES [dbo].[permission_function] ([id])
GO
ALTER TABLE [dbo].[permission_function_action] CHECK CONSTRAINT [FKh85bvei8b4p7k68uvj5egi3gv]
GO
ALTER TABLE [dbo].[permission_function_action]  WITH CHECK ADD  CONSTRAINT [FKmgpur64o94x0lata9f47wihge] FOREIGN KEY([permission_action_id])
REFERENCES [dbo].[permission_action] ([id])
GO
ALTER TABLE [dbo].[permission_function_action] CHECK CONSTRAINT [FKmgpur64o94x0lata9f47wihge]
GO
ALTER TABLE [dbo].[phone_area_code]  WITH CHECK ADD  CONSTRAINT [FK42icpa0hcpk1npn4qd3yo38tt] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[phone_area_code] CHECK CONSTRAINT [FK42icpa0hcpk1npn4qd3yo38tt]
GO
ALTER TABLE [dbo].[phone_area_code]  WITH CHECK ADD  CONSTRAINT [FKn53xug1r7c8fr8621tevwaanp] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[phone_area_code] CHECK CONSTRAINT [FKn53xug1r7c8fr8621tevwaanp]
GO
ALTER TABLE [dbo].[phone_area_code]  WITH CHECK ADD  CONSTRAINT [FKtm5kxrdjkjlowpimfrfktg5yb] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[phone_area_code] CHECK CONSTRAINT [FKtm5kxrdjkjlowpimfrfktg5yb]
GO
ALTER TABLE [dbo].[position]  WITH CHECK ADD  CONSTRAINT [FKihu5v8nl9h19m95r8r5n7b4s6] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[position] CHECK CONSTRAINT [FKihu5v8nl9h19m95r8r5n7b4s6]
GO
ALTER TABLE [dbo].[position]  WITH CHECK ADD  CONSTRAINT [FKo1qv5d6hue6wmy5sehexcbfdb] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[position] CHECK CONSTRAINT [FKo1qv5d6hue6wmy5sehexcbfdb]
GO
ALTER TABLE [dbo].[position]  WITH CHECK ADD  CONSTRAINT [FKo8bmfllwyv814ceec1s5wl086] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[position] CHECK CONSTRAINT [FKo8bmfllwyv814ceec1s5wl086]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK3hnuxll1jgo5qqseugnvg14va] FOREIGN KEY([ward_id])
REFERENCES [dbo].[tbl_user_ward] ([user_wardid])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK3hnuxll1jgo5qqseugnvg14va]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK4fhi8bc8piosflnsahlv1yce4] FOREIGN KEY([source_id])
REFERENCES [dbo].[source] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK4fhi8bc8piosflnsahlv1yce4]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK7ejwmwyjxcvkbqeggyqdrsxtk] FOREIGN KEY([department_id])
REFERENCES [dbo].[department] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK7ejwmwyjxcvkbqeggyqdrsxtk]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK7hqskhqmtf9dvvxtwkh39pl6l] FOREIGN KEY([district_id])
REFERENCES [dbo].[tbl_user_district] ([user_districtid])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK7hqskhqmtf9dvvxtwkh39pl6l]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK7k7y443cy1pnggqxa0kffv9nm] FOREIGN KEY([income_id])
REFERENCES [dbo].[income] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK7k7y443cy1pnggqxa0kffv9nm]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK826q09okr4ocgob3r7e0974qr] FOREIGN KEY([province_id])
REFERENCES [dbo].[tbl_user_province] ([user_provinceid])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK826q09okr4ocgob3r7e0974qr]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK8dh2tpiawamiiiq8uxtcd5tt8] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK8dh2tpiawamiiiq8uxtcd5tt8]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FK8mwuucluqig9sr6clewm07o08] FOREIGN KEY([vocative_id])
REFERENCES [dbo].[vocative] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FK8mwuucluqig9sr6clewm07o08]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FKb7rjjsaixslndqfyoj4x7027v] FOREIGN KEY([gender_id])
REFERENCES [dbo].[gender] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FKb7rjjsaixslndqfyoj4x7027v]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FKbc8b7dya9qbabmgqswosmlgrt] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FKbc8b7dya9qbabmgqswosmlgrt]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FKbjygrh5u9x2mhpbpaoaelsq9r] FOREIGN KEY([position_id])
REFERENCES [dbo].[position] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FKbjygrh5u9x2mhpbpaoaelsq9r]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FKjk187qw4ajcgfk5u1sfst9eyk] FOREIGN KEY([phone_area_code_id])
REFERENCES [dbo].[phone_area_code] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FKjk187qw4ajcgfk5u1sfst9eyk]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FKmpjgxxeqcqjm9qolgwrj7vy1t] FOREIGN KEY([business_type_id])
REFERENCES [dbo].[business_type] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FKmpjgxxeqcqjm9qolgwrj7vy1t]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FKpecphwrrm4jbvbvoerk4r3p5x] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FKpecphwrrm4jbvbvoerk4r3p5x]
GO
ALTER TABLE [dbo].[potential]  WITH CHECK ADD  CONSTRAINT [FKptmtxda1swrs3jjcxx7arij2s] FOREIGN KEY([country_id])
REFERENCES [dbo].[tbl_user_country] ([user_countryid])
GO
ALTER TABLE [dbo].[potential] CHECK CONSTRAINT [FKptmtxda1swrs3jjcxx7arij2s]
GO
ALTER TABLE [dbo].[potential_career]  WITH CHECK ADD  CONSTRAINT [FKigrc60072kpb10vm5dvfj28tf] FOREIGN KEY([career_id])
REFERENCES [dbo].[career] ([id])
GO
ALTER TABLE [dbo].[potential_career] CHECK CONSTRAINT [FKigrc60072kpb10vm5dvfj28tf]
GO
ALTER TABLE [dbo].[potential_career]  WITH CHECK ADD  CONSTRAINT [FKnvtpu6adrq6mp3w3myt4au7ke] FOREIGN KEY([potential_id])
REFERENCES [dbo].[potential] ([id])
GO
ALTER TABLE [dbo].[potential_career] CHECK CONSTRAINT [FKnvtpu6adrq6mp3w3myt4au7ke]
GO
ALTER TABLE [dbo].[potential_classification]  WITH CHECK ADD  CONSTRAINT [FK6jp167ee24s9h7l7716sf57x1] FOREIGN KEY([classification_id])
REFERENCES [dbo].[classification] ([id])
GO
ALTER TABLE [dbo].[potential_classification] CHECK CONSTRAINT [FK6jp167ee24s9h7l7716sf57x1]
GO
ALTER TABLE [dbo].[potential_classification]  WITH CHECK ADD  CONSTRAINT [FKppbsk3x6615uekfpk465m995u] FOREIGN KEY([potential_id])
REFERENCES [dbo].[potential] ([id])
GO
ALTER TABLE [dbo].[potential_classification] CHECK CONSTRAINT [FKppbsk3x6615uekfpk465m995u]
GO
ALTER TABLE [dbo].[potential_field]  WITH CHECK ADD  CONSTRAINT [FKghvgyot6the33g9abn61orsgs] FOREIGN KEY([potential_id])
REFERENCES [dbo].[potential] ([id])
GO
ALTER TABLE [dbo].[potential_field] CHECK CONSTRAINT [FKghvgyot6the33g9abn61orsgs]
GO
ALTER TABLE [dbo].[potential_field]  WITH CHECK ADD  CONSTRAINT [FKr7oiqchs1q4346yxy65akk1ga] FOREIGN KEY([field_id])
REFERENCES [dbo].[field] ([id])
GO
ALTER TABLE [dbo].[potential_field] CHECK CONSTRAINT [FKr7oiqchs1q4346yxy65akk1ga]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK6m9cn3m6r5j83x12jsxqsutsa] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK6m9cn3m6r5j83x12jsxqsutsa]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK9snagkrgntmlvj8omplsngaj4] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK9snagkrgntmlvj8omplsngaj4]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FKafa3gtwa7hqx22jwqaq1xu5i6] FOREIGN KEY([id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FKafa3gtwa7hqx22jwqaq1xu5i6]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FKlabq3c2e90ybbxk58rc48byqo] FOREIGN KEY([product_type_id])
REFERENCES [dbo].[product_type] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FKlabq3c2e90ybbxk58rc48byqo]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FKndrubbm6whifirg6o2bpdcf6b] FOREIGN KEY([unit_id])
REFERENCES [dbo].[unit] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FKndrubbm6whifirg6o2bpdcf6b]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FKrllvr9c7b39tv3vb9gxydt3fn] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FKrllvr9c7b39tv3vb9gxydt3fn]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FK51m6a4nrmlfdj3o2o69r6wn1q] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FK51m6a4nrmlfdj3o2o69r6wn1q]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FK7qlqca2lm1m6h6h4cinclfp9d] FOREIGN KEY([unit_id])
REFERENCES [dbo].[unit] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FK7qlqca2lm1m6h6h4cinclfp9d]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FK9uxbwdtl5wtgwxur1l04htpse] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FK9uxbwdtl5wtgwxur1l04htpse]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FKfys7y6yyws0x37u8oicm7ake9] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FKfys7y6yyws0x37u8oicm7ake9]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FKhdsehll1rwpwcyeawsqymtofm] FOREIGN KEY([order_id])
REFERENCES [dbo].[order] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FKhdsehll1rwpwcyeawsqymtofm]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FKiowtjkojipdwd97l109av371s] FOREIGN KEY([opportunity_id])
REFERENCES [dbo].[opportunity] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FKiowtjkojipdwd97l109av371s]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FKodumaxasnykauuldhtmlkavus] FOREIGN KEY([invoice_id])
REFERENCES [dbo].[invoice] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FKodumaxasnykauuldhtmlkavus]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD  CONSTRAINT [FKrrt19b3bx5jisv8qey0f3pabw] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product_info] CHECK CONSTRAINT [FKrrt19b3bx5jisv8qey0f3pabw]
GO
ALTER TABLE [dbo].[product_type]  WITH CHECK ADD  CONSTRAINT [FKjgxfsqbacs3yxvq8cgb7sk5ch] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product_type] CHECK CONSTRAINT [FKjgxfsqbacs3yxvq8cgb7sk5ch]
GO
ALTER TABLE [dbo].[product_type]  WITH CHECK ADD  CONSTRAINT [FKmmmkpdlg0simjumb9gluru0gn] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product_type] CHECK CONSTRAINT [FKmmmkpdlg0simjumb9gluru0gn]
GO
ALTER TABLE [dbo].[product_type]  WITH CHECK ADD  CONSTRAINT [FKqxwarqyvufu8wxju3p87c3ydv] FOREIGN KEY([product_type_id])
REFERENCES [dbo].[product_type] ([id])
GO
ALTER TABLE [dbo].[product_type] CHECK CONSTRAINT [FKqxwarqyvufu8wxju3p87c3ydv]
GO
ALTER TABLE [dbo].[product_type]  WITH CHECK ADD  CONSTRAINT [FKrgiu8m5vjnpculj0pqgnb7paf] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[product_type] CHECK CONSTRAINT [FKrgiu8m5vjnpculj0pqgnb7paf]
GO
ALTER TABLE [dbo].[role]  WITH CHECK ADD  CONSTRAINT [FK9swfn6f8s0i3gjsu2a5hxcypv] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[role] CHECK CONSTRAINT [FK9swfn6f8s0i3gjsu2a5hxcypv]
GO
ALTER TABLE [dbo].[role]  WITH CHECK ADD  CONSTRAINT [FKfrmh6ua1euw6xnrhtfakrup37] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[role] CHECK CONSTRAINT [FKfrmh6ua1euw6xnrhtfakrup37]
GO
ALTER TABLE [dbo].[role]  WITH CHECK ADD  CONSTRAINT [FKp81oqp59j02r5tflq196y5goa] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[role] CHECK CONSTRAINT [FKp81oqp59j02r5tflq196y5goa]
GO
ALTER TABLE [dbo].[role_permission_function_action]  WITH CHECK ADD  CONSTRAINT [FKg5qf30s6moorum9rjagih6cb0] FOREIGN KEY([permission_function_action_id])
REFERENCES [dbo].[permission_function_action] ([id])
GO
ALTER TABLE [dbo].[role_permission_function_action] CHECK CONSTRAINT [FKg5qf30s6moorum9rjagih6cb0]
GO
ALTER TABLE [dbo].[role_permission_function_action]  WITH CHECK ADD  CONSTRAINT [FKofbyjmpxk2y3escbvsct1thti] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[role_permission_function_action] CHECK CONSTRAINT [FKofbyjmpxk2y3escbvsct1thti]
GO
ALTER TABLE [dbo].[source]  WITH CHECK ADD  CONSTRAINT [FK4k33lr2ibuwhwo4kfok4pxei2] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[source] CHECK CONSTRAINT [FK4k33lr2ibuwhwo4kfok4pxei2]
GO
ALTER TABLE [dbo].[source]  WITH CHECK ADD  CONSTRAINT [FK4wsuo18g3rf4eetk0b29btrvo] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[source] CHECK CONSTRAINT [FK4wsuo18g3rf4eetk0b29btrvo]
GO
ALTER TABLE [dbo].[source]  WITH CHECK ADD  CONSTRAINT [FKtd5tv8mt4lyylrew0xg6fog26] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[source] CHECK CONSTRAINT [FKtd5tv8mt4lyylrew0xg6fog26]
GO
ALTER TABLE [dbo].[tbl_user_district]  WITH CHECK ADD  CONSTRAINT [FKrik231wb7wtkfim3k93iljjqx] FOREIGN KEY([user_provinceid])
REFERENCES [dbo].[tbl_user_province] ([user_provinceid])
GO
ALTER TABLE [dbo].[tbl_user_district] CHECK CONSTRAINT [FKrik231wb7wtkfim3k93iljjqx]
GO
ALTER TABLE [dbo].[tbl_user_province]  WITH CHECK ADD  CONSTRAINT [FK6wlbwmkw2t33jqgkxiylc8qhv] FOREIGN KEY([user_countryid])
REFERENCES [dbo].[tbl_user_country] ([user_countryid])
GO
ALTER TABLE [dbo].[tbl_user_province] CHECK CONSTRAINT [FK6wlbwmkw2t33jqgkxiylc8qhv]
GO
ALTER TABLE [dbo].[tbl_user_ward]  WITH CHECK ADD  CONSTRAINT [FKj7vwaqwf33n6kem1q4kswfbun] FOREIGN KEY([districtid])
REFERENCES [dbo].[tbl_user_district] ([user_districtid])
GO
ALTER TABLE [dbo].[tbl_user_ward] CHECK CONSTRAINT [FKj7vwaqwf33n6kem1q4kswfbun]
GO
ALTER TABLE [dbo].[type]  WITH CHECK ADD  CONSTRAINT [FK360qg6xn84ueh3rdv8b22smfq] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[type] CHECK CONSTRAINT [FK360qg6xn84ueh3rdv8b22smfq]
GO
ALTER TABLE [dbo].[type]  WITH CHECK ADD  CONSTRAINT [FK5mx19w9jwnst8auck2qi4p0jn] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[type] CHECK CONSTRAINT [FK5mx19w9jwnst8auck2qi4p0jn]
GO
ALTER TABLE [dbo].[type]  WITH CHECK ADD  CONSTRAINT [FKbeaiolh1igprd8b19yjl1wmpn] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[type] CHECK CONSTRAINT [FKbeaiolh1igprd8b19yjl1wmpn]
GO
ALTER TABLE [dbo].[unit]  WITH CHECK ADD  CONSTRAINT [FKnlb2mrwvgyfgwuye8ugor21wx] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[unit] CHECK CONSTRAINT [FKnlb2mrwvgyfgwuye8ugor21wx]
GO
ALTER TABLE [dbo].[unit]  WITH CHECK ADD  CONSTRAINT [FKolu6un0xvmkrbj1s4ci235xeq] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[unit] CHECK CONSTRAINT [FKolu6un0xvmkrbj1s4ci235xeq]
GO
ALTER TABLE [dbo].[unit]  WITH CHECK ADD  CONSTRAINT [FKr99r9p67p7sx5mvvbyckhm8or] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[unit] CHECK CONSTRAINT [FKr99r9p67p7sx5mvvbyckhm8or]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FKaa7rqqslkee8beudpnxcfc9l8] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FKaa7rqqslkee8beudpnxcfc9l8]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FKbg1hex1p24e9vsoo2cr91n1jp] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FKbg1hex1p24e9vsoo2cr91n1jp]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FKguwcm2aklhbaohmoglw3m81l8] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FKguwcm2aklhbaohmoglw3m81l8]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FKra5y69t2b0w23wjys2sob32bt] FOREIGN KEY([gender])
REFERENCES [dbo].[gender] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FKra5y69t2b0w23wjys2sob32bt]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKa68196081fvovjhkek5m97n3y] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKa68196081fvovjhkek5m97n3y]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKfgsgxvihks805qcq8sq26ab7c] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKfgsgxvihks805qcq8sq26ab7c]
GO
ALTER TABLE [dbo].[vocative]  WITH CHECK ADD  CONSTRAINT [FK7f1lgypt54pchxj4gw8cgphfl] FOREIGN KEY([updated_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[vocative] CHECK CONSTRAINT [FK7f1lgypt54pchxj4gw8cgphfl]
GO
ALTER TABLE [dbo].[vocative]  WITH CHECK ADD  CONSTRAINT [FKjvyn7u7b2cgvdob8wr427eykg] FOREIGN KEY([created_by])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[vocative] CHECK CONSTRAINT [FKjvyn7u7b2cgvdob8wr427eykg]
GO
ALTER TABLE [dbo].[vocative]  WITH CHECK ADD  CONSTRAINT [FKprearnosah7380vw72y5rkicg] FOREIGN KEY([owner])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[vocative] CHECK CONSTRAINT [FKprearnosah7380vw72y5rkicg]
GO
USE [master]
GO
ALTER DATABASE [Capstone] SET  READ_WRITE 
GO
