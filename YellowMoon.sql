USE [master]
GO
/****** Object:  Database [YellowMoon]    Script Date: 10/18/2020 8:25:04 PM ******/
CREATE DATABASE [YellowMoon]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'YellowMoon', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\YellowMoon.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'YellowMoon_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\YellowMoon_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [YellowMoon] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [YellowMoon].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [YellowMoon] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [YellowMoon] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [YellowMoon] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [YellowMoon] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [YellowMoon] SET ARITHABORT OFF 
GO
ALTER DATABASE [YellowMoon] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [YellowMoon] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [YellowMoon] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [YellowMoon] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [YellowMoon] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [YellowMoon] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [YellowMoon] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [YellowMoon] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [YellowMoon] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [YellowMoon] SET  ENABLE_BROKER 
GO
ALTER DATABASE [YellowMoon] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [YellowMoon] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [YellowMoon] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [YellowMoon] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [YellowMoon] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [YellowMoon] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [YellowMoon] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [YellowMoon] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [YellowMoon] SET  MULTI_USER 
GO
ALTER DATABASE [YellowMoon] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [YellowMoon] SET DB_CHAINING OFF 
GO
ALTER DATABASE [YellowMoon] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [YellowMoon] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [YellowMoon] SET DELAYED_DURABILITY = DISABLED 
GO
USE [YellowMoon]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [varchar](10) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblLog]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblLog](
	[logID] [int] IDENTITY(1,1) NOT NULL,
	[productID] [int] NULL,
	[userID] [varchar](100) NULL,
	[updateDate] [datetime] NULL DEFAULT (getdate()),
PRIMARY KEY CLUSTERED 
(
	[logID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [varchar](100) NULL,
	[productID] [int] NULL,
	[quantity] [int] NOT NULL,
	[totalPrice] [real] NOT NULL,
 CONSTRAINT [PK__tblOrder__83077839146C0027] PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [varchar](100) NOT NULL CONSTRAINT [DF__tblOrders__order__1DB06A4F]  DEFAULT (newid()),
	[userID] [varchar](100) NULL,
	[name] [nvarchar](50) NULL,
	[address] [nvarchar](100) NULL,
	[phoneNumber] [nvarchar](10) NULL,
	[total] [real] NOT NULL,
	[orderDate] [datetime] NULL CONSTRAINT [DF__tblOrders__order__1EA48E88]  DEFAULT (getdate()),
	[paymentID] [varchar](10) NULL,
	[paymentStatus] [varchar](10) NULL CONSTRAINT [DF__tblOrders__payme__208CD6FA]  DEFAULT ('N'),
 CONSTRAINT [PK__tblOrder__0809337DE7F91E7B] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblPaymentMethod]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblPaymentMethod](
	[paymentID] [varchar](10) NOT NULL,
	[methodName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[paymentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblPaymentStatus]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblPaymentStatus](
	[ID] [varchar](10) NOT NULL,
	[status] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblProducts]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblProducts](
	[productID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[image] [varchar](100) NOT NULL,
	[price] [real] NOT NULL,
	[description] [nvarchar](200) NOT NULL,
	[createDate] [date] NOT NULL,
	[expirationDate] [date] NOT NULL,
	[quantity] [int] NOT NULL,
	[categoryID] [varchar](10) NOT NULL,
	[statusID] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleID] [varchar](10) NOT NULL,
	[role] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusID] [varchar](10) NOT NULL,
	[status] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 10/18/2020 8:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](100) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[password] [varchar](50) NULL,
	[address] [nvarchar](100) NULL,
	[phoneNumber] [nvarchar](10) NULL,
	[statusID] [varchar](10) NULL,
	[roleID] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'CB', N'Combo')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'DB', N'Đặc biệt')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'DEO', N'Bánh dẻo')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'TT', N'Bánh truyền thống')
SET IDENTITY_INSERT [dbo].[tblLog] ON 

INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (1, 6, N'admin', CAST(N'2020-10-10 21:07:22.397' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (2, 4, N'admin', CAST(N'2020-10-10 21:10:00.247' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (3, 4, N'admin', CAST(N'2020-10-10 21:10:06.680' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (4, 1, N'admin', CAST(N'2020-10-10 21:10:18.280' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (5, 2, N'admin', CAST(N'2020-10-10 21:10:28.990' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (6, 1, N'admin', CAST(N'2020-10-10 21:11:08.980' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (7, 6, N'admin', CAST(N'2020-10-11 18:41:28.613' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (8, 7, N'admin', CAST(N'2020-10-11 22:27:46.553' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (9, 9, N'admin', CAST(N'2020-10-13 13:56:07.773' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (10, 1, N'admin', CAST(N'2020-10-18 20:05:15.970' AS DateTime))
INSERT [dbo].[tblLog] ([logID], [productID], [userID], [updateDate]) VALUES (11, 10, N'admin', CAST(N'2020-10-18 20:05:24.327' AS DateTime))
SET IDENTITY_INSERT [dbo].[tblLog] OFF
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (1, N'4EC689A5-9B0C-EB11-8C6B-848F69B731E3', 7, 10, 85)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (2, N'4EC689A5-9B0C-EB11-8C6B-848F69B731E3', 8, 20, 180)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (3, N'4EC689A5-9B0C-EB11-8C6B-848F69B731E3', 2, 50, 250)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (4, N'01AE0E53-A20C-EB11-8C6B-848F69B731E3', 7, 20, 170)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (5, N'01AE0E53-A20C-EB11-8C6B-848F69B731E3', 4, 10, 50)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (6, N'6C0BDCA9-A20C-EB11-8C6B-848F69B731E3', 7, 10, 85)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (7, N'6C0BDCA9-A20C-EB11-8C6B-848F69B731E3', 4, 10, 50)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (8, N'6C0BDCA9-A20C-EB11-8C6B-848F69B731E3', 1, 20, 80)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (9, N'3A3DB375-4111-EB11-8C76-848F69B731E3', 7, 10, 85)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (10, N'3A3DB375-4111-EB11-8C76-848F69B731E3', 2, 20, 100)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (11, N'23A524B3-4211-EB11-8C76-848F69B731E3', 7, 1, 8.5)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [productID], [quantity], [totalPrice]) VALUES (12, N'BDF2D39C-695F-4A07-9BB5-F513969F72F0', 11, 1, 234)
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
INSERT [dbo].[tblOrders] ([orderID], [userID], [name], [address], [phoneNumber], [total], [orderDate], [paymentID], [paymentStatus]) VALUES (N'01AE0E53-A20C-EB11-8C6B-848F69B731E3', N'longlb', N'Long Le', N'HCM', N'0903', 220, CAST(N'2020-10-12 22:48:09.260' AS DateTime), N'COD', N'N')
INSERT [dbo].[tblOrders] ([orderID], [userID], [name], [address], [phoneNumber], [total], [orderDate], [paymentID], [paymentStatus]) VALUES (N'23A524B3-4211-EB11-8C76-848F69B731E3', N'', N'Test Buy', N'123 HCM', N'09090', 8.5, CAST(N'2020-10-18 20:06:14.593' AS DateTime), N'COD', N'N')
INSERT [dbo].[tblOrders] ([orderID], [userID], [name], [address], [phoneNumber], [total], [orderDate], [paymentID], [paymentStatus]) VALUES (N'3A3DB375-4111-EB11-8C76-848F69B731E3', N'longlebao2000@gmail.com', N'Long Gmail', N'SG', N'123', 185, CAST(N'2020-10-18 19:57:22.013' AS DateTime), N'COD', N'N')
INSERT [dbo].[tblOrders] ([orderID], [userID], [name], [address], [phoneNumber], [total], [orderDate], [paymentID], [paymentStatus]) VALUES (N'4EC689A5-9B0C-EB11-8C6B-848F69B731E3', N'', N'Long', N'HCM', N'090', 515, CAST(N'2020-10-12 22:00:21.163' AS DateTime), N'COD', N'N')
INSERT [dbo].[tblOrders] ([orderID], [userID], [name], [address], [phoneNumber], [total], [orderDate], [paymentID], [paymentStatus]) VALUES (N'6C0BDCA9-A20C-EB11-8C6B-848F69B731E3', N'', N'Test', N'HN', N'1234643535', 215, CAST(N'2020-10-12 22:50:34.890' AS DateTime), N'COD', N'N')
INSERT [dbo].[tblOrders] ([orderID], [userID], [name], [address], [phoneNumber], [total], [orderDate], [paymentID], [paymentStatus]) VALUES (N'BDF2D39C-695F-4A07-9BB5-F513969F72F0', N'longlebao2000@gmail.com', N'Long Gmail', N'SG', N'123', 234, CAST(N'2020-10-18 20:21:17.630' AS DateTime), N'COD', N'N')
INSERT [dbo].[tblPaymentMethod] ([paymentID], [methodName]) VALUES (N'COD', N'Cash On Delivery')
INSERT [dbo].[tblPaymentMethod] ([paymentID], [methodName]) VALUES (N'PP', N'Paypal')
INSERT [dbo].[tblPaymentStatus] ([ID], [status]) VALUES (N'N', N'Not yet')
INSERT [dbo].[tblPaymentStatus] ([ID], [status]) VALUES (N'P', N'Paid')
SET IDENTITY_INSERT [dbo].[tblProducts] ON 

INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (1, N'Bánh Đậu Xanh', N'D:/LabWeb/images/YellowMoon/tt-dau-xanh.jpg', 4, N'Bánh đậu xanh 2 trứng', CAST(N'2020-10-07' AS Date), CAST(N'2020-10-10' AS Date), 80, N'TT', N'A')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (2, N'Bánh Dẻo Đậu Xanh', N'D:/LabWeb/images/YellowMoon/deo-dau-xanh.jpg', 5, N'Bánh dẻo nhân đậu xanh', CAST(N'2020-10-07' AS Date), CAST(N'2020-10-22' AS Date), 580, N'DEO', N'A')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (4, N'Bánh Thập Cẩm', N'D:/LabWeb/images/YellowMoon/tt-thap-cam.jpg', 5, N'Bánh thập cẩm 2 trứng', CAST(N'2020-10-08' AS Date), CAST(N'2020-12-30' AS Date), 280, N'TT', N'A')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (6, N'test update', N'D:/LabWeb/images/YellowMoon/simple15.jpg', 20, N'test 123', CAST(N'2021-11-09' AS Date), CAST(N'2021-12-10' AS Date), 100, N'DB', N'D')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (7, N'Bánh Đậu Đỏ', N'D:/LabWeb/images/YellowMoon/03-dau-do-kieu-nhat1.png', 8.5, N'Bánh trung thu nhân đậu đỏ 1 trứng', CAST(N'2020-10-11' AS Date), CAST(N'2020-12-11' AS Date), 959, N'TT', N'A')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (8, N'Bánh Lava', N'D:/LabWeb/images/YellowMoon/banh-trung-thu-lava.jpg', 9, N'Bánh trung thu lava custard', CAST(N'2020-10-11' AS Date), CAST(N'2020-11-30' AS Date), 500, N'DB', N'D')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (9, N'test', N'D:/LabWeb/images/YellowMoon/alex-iby-Nb-KiMNgJTc-unsplash.jpg', 123, N'test', CAST(N'2020-10-10' AS Date), CAST(N'2020-10-20' AS Date), 0, N'DB', N'D')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (10, N'a', N'D:/LabWeb/images/YellowMoon/simple31.jpg', 123, N'a', CAST(N'2020-12-12' AS Date), CAST(N'2020-12-12' AS Date), 123, N'DB', N'D')
INSERT [dbo].[tblProducts] ([productID], [name], [image], [price], [description], [createDate], [expirationDate], [quantity], [categoryID], [statusID]) VALUES (11, N'Last test', N'D:/LabWeb/images/YellowMoon/nai.jpg', 234, N'Last test', CAST(N'2020-12-12' AS Date), CAST(N'2020-12-31' AS Date), 149, N'DB', N'A')
SET IDENTITY_INSERT [dbo].[tblProducts] OFF
INSERT [dbo].[tblRole] ([roleID], [role]) VALUES (N'ADMIN', N'Admin')
INSERT [dbo].[tblRole] ([roleID], [role]) VALUES (N'GMAIL', N'Gmail User')
INSERT [dbo].[tblRole] ([roleID], [role]) VALUES (N'GUEST', N'Guest')
INSERT [dbo].[tblRole] ([roleID], [role]) VALUES (N'MEM', N'Member')
INSERT [dbo].[tblStatus] ([statusID], [status]) VALUES (N'A', N'Active')
INSERT [dbo].[tblStatus] ([statusID], [status]) VALUES (N'D', N'Delete')
INSERT [dbo].[tblUsers] ([userID], [name], [password], [address], [phoneNumber], [statusID], [roleID]) VALUES (N'admin', N'Admin', N'admin', N'HCM', N'0909', N'A', N'ADMIN')
INSERT [dbo].[tblUsers] ([userID], [name], [password], [address], [phoneNumber], [statusID], [roleID]) VALUES (N'longlb', N'Long Le', N'lebaolong', N'HCM', N'0903', N'A', N'MEM')
INSERT [dbo].[tblUsers] ([userID], [name], [password], [address], [phoneNumber], [statusID], [roleID]) VALUES (N'longlebao2000@gmail.com', N'Long Gmail', NULL, N'SG', N'123', N'A', N'GMAIL')
ALTER TABLE [dbo].[tblLog]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[tblProducts] ([productID])
GO
ALTER TABLE [dbo].[tblLog]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__tblOrderD__order__245D67DE] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK__tblOrderD__order__245D67DE]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__tblOrderD__produ__25518C17] FOREIGN KEY([productID])
REFERENCES [dbo].[tblProducts] ([productID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK__tblOrderD__produ__25518C17]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK__tblOrders__payme__1F98B2C1] FOREIGN KEY([paymentID])
REFERENCES [dbo].[tblPaymentMethod] ([paymentID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK__tblOrders__payme__1F98B2C1]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK__tblOrders__payme__2180FB33] FOREIGN KEY([paymentStatus])
REFERENCES [dbo].[tblPaymentStatus] ([ID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK__tblOrders__payme__2180FB33]
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRole] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
USE [master]
GO
ALTER DATABASE [YellowMoon] SET  READ_WRITE 
GO
