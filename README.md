# Library System API REST

## Activity Objective
The objective of this project is to develop a RESTful API that allows users to manage a library system. This includes functionalities such as adding, updating, and removing books, as well as managing user accounts and tracking borrowed items.

## Database Structure
The database for the Library System consists of the following tables:

1. **Users**  
   - `id`: unique identifier for each user  
   - `name`: name of the user  
   - `email`: contact email (unique)  
   - `created_at`: timestamp of account creation  
   
2. **Books**  
   - `id`: unique identifier for each book  
   - `title`: title of the book  
   - `author`: author of the book  
   - `published_date`: date the book was published  
   - `availability`: status indicating if the book is available for borrowing  
   
3. **Transactions**  
   - `id`: unique identifier for each transaction  
   - `user_id`: reference to the user who borrowed the book  
   - `book_id`: reference to the borrowed book  
   - `borrowed_at`: timestamp when the book was borrowed  
   - `returned_at`: timestamp when the book was returned  

## Project Architecture
The project follows a standard layered architecture for a RESTful API:

- **Controller Layer**: Handles incoming requests and responses. It acts as an intermediary between the client and the service layer.
- **Service Layer**: Contains the business logic of the API. It processes requests from the controller and interacts with the data layer.
- **Data Access Layer**: Manages communication with the database. It contains methods for querying and manipulating data within the database.
- **Database**: Houses the actual data and supports various operations like CRUD (Create, Read, Update, Delete).  
Base ORM is used to simplify database interactions.

This structure promotes separation of concerns, making the application more maintainable and scalable.