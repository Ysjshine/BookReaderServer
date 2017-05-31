DELIMITER //

CREATE PROCEDURE Register(_username NVARCHAR(50), _password NVARCHAR(50), _gender INT)
  BEGIN
    INSERT INTO BookReaderServer.Users (account, password, nickname, gender)
    VALUES (_username, _password, _username, _gender);
  END //

CREATE PROCEDURE UpdatePassword(_userID INT, _password NVARCHAR(50))
  BEGIN
    UPDATE BookReaderServer.Users
    SET password = _password
    WHERE UserID = _userID;
  END //

CREATE PROCEDURE UpdateNickname(_userID INT, _nickname NVARCHAR(50))
  BEGIN
    UPDATE BookReaderServer.Users
    SET nickname = _nickname
    WHERE UserID = _userID;
  END //

CREATE PROCEDURE GetUserInfoByUsername(_username NVARCHAR(50))
  BEGIN
    SELECT *
    FROM BookReaderServer.Users
    WHERE account = _username;
  END //

CREATE PROCEDURE GetUserInfoByID(_userID INT)
  BEGIN
    SELECT *
    FROM BookReaderServer.Users
    WHERE UserID = _userID;
  END //

CREATE PROCEDURE AddBook(_title NVARCHAR(50), _author NVARCHAR(50), _fileSrc NVARCHAR(50), _imgSrc NVARCHAR(50),
                         _type  INT)
  BEGIN
    INSERT INTO BookReaderServer.Books (title, author, fileSource, imgSource, type)
    VALUES (_title, _author, _fileSrc, _imgSrc, _type);
  END //

CREATE PROCEDURE DeleteBook(_bookID INT)
  BEGIN
    DELETE FROM BookReaderServer.Books
    WHERE BookID = _bookID;
  END //

CREATE PROCEDURE GetBookByTitle(_title NVARCHAR(50))
  BEGIN
    SELECT *
    FROM BookReaderServer.Books
    WHERE title = _title;
  END //

CREATE PROCEDURE GetBookByID(_bookID INT)
  BEGIN
    SELECT *
    FROM BookReaderServer.Books
    WHERE BookID = _bookID;
  END //

CREATE PROCEDURE GetBookByType(_type INT)
  BEGIN
    SELECT *
    FROM BookReaderServer.Books
    WHERE type = _type;
  END //

CREATE PROCEDURE AddCategory(_userID INT, _categoryName NVARCHAR(50))
  BEGIN
    DECLARE _maxCategoryID INT;
    SELECT _maxCategoryID = max(CategoryID) + 1
    FROM BookReaderServer.UserCategories
    WHERE UserID = _userID;
    INSERT INTO BookReaderServer.UserCategories (UserID, CategoryID, CategoryName)
    VALUES (_userID, _maxCategoryID, _categoryName);
  END //

CREATE PROCEDURE DeleteCategory(_userID INT, _categoryID INT)
  BEGIN
    DELETE FROM BookReaderServer.UserCategories
    WHERE UserID = _userID AND CategoryID = _categoryID;
  END //

CREATE PROCEDURE GetCategoryByUserID(_userID INT)
  BEGIN
    SELECT *
    FROM BookReaderServer.UserCategories
    WHERE UserID = _userID;
  END //

CREATE PROCEDURE CollectBook(_userID INT, _categoryID INT, _bookID INT)
  BEGIN
    INSERT INTO BookReaderServer.CategoryBooks (UserID, CategoryID, BookID) VALUES (_userID, _categoryID, _bookID);
  END //

CREATE PROCEDURE UncollectBook(_userID INT, _bookID INT)
  BEGIN
    DELETE FROM BookReaderServer.CategoryBooks
    WHERE UserID = _userID AND BookID = _bookID;
  END //

CREATE PROCEDURE GetCollectBook(_userID INT, _category INT)
  BEGIN
    SELECT *
    FROM BookReaderServer.CategoryBooks
    WHERE UserID = _userID AND CategoryID = _category;
  END //

CREATE PROCEDURE AddComment(_bookID INT, _userID INT, _contents NVARCHAR(100))
  BEGIN
    INSERT INTO BookReaderServer.Comments (BookID, UserID, contents) VALUES (_bookID, _userID, _contents);
  END //

CREATE PROCEDURE DeleteComment(_bookID INT, _userID INT)
  BEGIN
    DELETE FROM BookReaderServer.Comments
    WHERE BookID = _bookID AND UserID = _userID;
  END //

CREATE PROCEDURE GetAllCommentsByBookID(_bookID INT)
  BEGIN
    SELECT *
    FROM Comments
    WHERE BookID = _bookID;
  END //


DELIMITER ;
