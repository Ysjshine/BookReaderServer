CREATE SCHEMA BookReaderServer;
USE BookReaderServer;
CREATE TABLE Users (
  UserID   INT PRIMARY KEY AUTO_INCREMENT,
  account  NVARCHAR(20) NOT NULL,
  password NVARCHAR(20) NOT NULL,
  nickname NVARCHAR(20) NOT NULL,
  gender   INT,
  UNIQUE (account)
);


CREATE TABLE Books (
  BookID       INT PRIMARY KEY AUTO_INCREMENT,
  title        NVARCHAR(50) NOT NULL,
  author       NVARCHAR(50) NOT NULL,
  price        FLOAT           DEFAULT 0,
  collectTimes INT             DEFAULT 0,
  readTimes    INT             DEFAULT 0,
--  fileSource   NVARCHAR(50)    DEFAULT NULL,
--  imgSource    NVARCHAR(50)    DEFAULT NULL,
  type         INT          NOT NULL
);

CREATE TABLE UserCategories (
  UCID         INT PRIMARY KEY AUTO_INCREMENT,
  UserID       INT          NOT NULL,
  CategoryID   INT          NOT NULL,
  CategoryName NVARCHAR(20) NOT NULL,
  UNIQUE (UserID, CategoryID),
  FOREIGN KEY (UserID) REFERENCES Users (UserID)
);

CREATE TABLE CategoryBooks (
  CBID       INT PRIMARY KEY AUTO_INCREMENT,
  UserID     INT NOT NULL,
  CategoryID INT NOT NULL,
  BookID     INT NOT NULL,
  UNIQUE (UserID, CategoryID, BookID),
  FOREIGN KEY (UserID, CategoryID) REFERENCES UserCategories (UserID, CategoryID)
);

CREATE TABLE Comments (
  CommentID INT PRIMARY KEY AUTO_INCREMENT,
  BookID    INT           NOT NULL,
  UserID    INT           NOT NULL,
  contents  NVARCHAR(100) NOT NULL,
  FOREIGN KEY (BookID) REFERENCES Books (BookID),
  FOREIGN KEY (UserID) REFERENCES Users (UserID)
);

CREATE TABLE ReadPositions (
  PosID   INT PRIMARY KEY AUTO_INCREMENT,
  UserID  INT NOT NULL,
  BookID  INT NOT NULL,
  chapter INT NOT NULL,
  page    INT NOT NULL,
  UNIQUE (UserID, BookID),
  CHECK (chapter >= 1 AND page >= 1),
  FOREIGN KEY (UserID) REFERENCES Users (UserID)
    ON DELETE CASCADE,
  FOREIGN KEY (BookID) REFERENCES Books (BookID)
    ON DELETE CASCADE
);


CREATE TRIGGER TriggerCollect AFTER INSERT ON CategoryBooks FOR EACH ROW BEGIN
  UPDATE Books SET collectTimes = collectTimes+1 WHERE BookID = new.BookID;
END;
CREATE TRIGGER OnDelCategoryBook
AFTER DELETE ON CategoryBooks
FOR EACH ROW
  BEGIN
    DECLARE temp INT;
    UPDATE Books SET collectTimes = collectTimes-1 WHERE BookID = old.BookID;
    SET temp = (SELECT count(*)
                FROM CategoryBooks
                WHERE UserID = old.UserID AND BookID = old.BookID);
    IF (temp = 0)
    THEN
      DELETE FROM ReadPositions
      WHERE UserID = old.UserID AND BookID = old.BookID;
    END IF;
  END;

CREATE TRIGGER OnUpdateReadPosition
AFTER UPDATE ON ReadPositions
FOR EACH ROW
  BEGIN
    UPDATE Books
    SET readTimes = readTimes + 1
    WHERE BookID = new.BookID;
  END;
