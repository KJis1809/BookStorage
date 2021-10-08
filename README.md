# BookStorage ![BookLogo](https://www.sheridancollege.ca/-/media/images/experience/trafalgar/student-life/trafalgar_library_shelves.jpg)
> **What to do?**
1. Clone project
```bash
  git clone https://github.com/KJis1809/BookStorage
```
2. Create database "book_storage" on your mysql server
```mysql
  CREATE DATABASE book_storage;
```
3. Change application.properties with your username and password
```bash
  spring.datasource.username=YOUR_USERNAME
  spring.datasource.password=YOUR_PASSWORD
```
4. Run application

> **Anything else?**

When your application is running you can insert test data with migration.
You need to:
* Go to resources -> db.migration
* Open V2__insertTestDataIntoDb and run this sql
