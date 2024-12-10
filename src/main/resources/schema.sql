-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS xolit;
USE xolit;

SET default_storage_engine = InnoDB;


-- Módulo Biblioteca
-- Usuarios de la biblioteca
CREATE TABLE library_users (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(100) NOT NULL,
                               email VARCHAR(100),
                               phone VARCHAR(20)
) ENGINE=InnoDB;

-- Libros
CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       author VARCHAR(100) NOT NULL,
                       publication_year INT,
                       genre VARCHAR(50),
                       available BOOLEAN NOT NULL
) ENGINE=InnoDB;

-- Préstamos
CREATE TABLE loans (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       book_id INT NOT NULL,
                       user_id INT NOT NULL,
                       loan_date DATETIME NOT NULL,
                       return_date DATETIME,
                       FOREIGN KEY (book_id) REFERENCES books(id),
                       FOREIGN KEY (user_id) REFERENCES library_users(id)
) ENGINE=InnoDB;


-- Módulo E-commerce
-- Productos
CREATE TABLE products (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          stock INT NOT NULL
) ENGINE=InnoDB;

-- Órdenes
CREATE TABLE orders (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        created_at DATETIME NOT NULL,
                        status VARCHAR(50)
) ENGINE=InnoDB;

-- Ítems de la orden
CREATE TABLE order_items (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             order_id INT NOT NULL,
                             product_id INT NOT NULL,
                             quantity INT NOT NULL,
                             price DECIMAL(10,2) NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (product_id) REFERENCES products(id)
) ENGINE=InnoDB;


-- Módulo Reservas
-- Clientes del restaurante
CREATE TABLE customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           email VARCHAR(100),
                           phone VARCHAR(20)
) ENGINE=InnoDB;

-- Mesas del restaurante
CREATE TABLE restaurant_tables (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   table_number INT NOT NULL,
                                   capacity INT NOT NULL
) ENGINE=InnoDB;

-- Reservas
CREATE TABLE reservations (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              customer_id INT NOT NULL,
                              table_id INT NOT NULL,
                              reservation_date DATE NOT NULL,
                              start_time TIME NOT NULL,
                              duration_hours INT NOT NULL,
                              persons_count INT NOT NULL,
                              FOREIGN KEY (customer_id) REFERENCES customers(id),
                              FOREIGN KEY (table_id) REFERENCES restaurant_tables(id)
) ENGINE=InnoDB;
