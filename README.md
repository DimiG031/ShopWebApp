# Shop Web Application

## Description
This project is a **Shop Web Application** developed using a combination of backend and frontend technologies. It serves as an example of a web store application.

## Technologies Used
- **Backend:**
  - Java
  - MySQL
- **Frontend:**
  - HTML
  - CSS
  - JavaScript

## Development Environment
- **Operating System:** Windows 10

## Features
- **User Authentication:** Allows users to register and log in.
- **Product Management:** Enables administrators to add, update, and delete products.
- **Shopping Cart:** Users can add products to their cart and proceed to checkout.
- **Order Processing:** Handles order placements and tracks order statuses.

## Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or later
- Apache Maven 3.6 or later
- MySQL Server

### Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/DimiG031/ShopWebApp.git
   cd ShopWebApp
   ```
2. **Set up the database:**
   - Create a MySQL database named `shop_db`.
   - Import the provided SQL dump file to set up the necessary tables:
     ```bash
     mysql -u root -p shop_db < Dump20240515.sql
     ```
3. **Configure database connection:**
   - Update the database connection settings in the application's configuration file to match your MySQL setup.
4. **Build the project:**
   ```bash
   mvn clean install
   ```
5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
6. **Access the application:**
   - Open a web browser and navigate to `http://localhost:8080`.

## Screenshots
*Include screenshots of the application here to showcase the user interface and functionality.*

## Future Enhancements
- **Responsive Design:** Improve the UI to be mobile-friendly.
- **Payment Integration:** Add payment gateway integration for processing transactions.
- **Search Functionality:** Implement product search and filtering options.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---
Feel free to contribute to this project by opening issues or submitting pull requests.

