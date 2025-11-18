-- ===============================================
-- 1. SETUP INICIAL Y TABLAS DE LOOKUP (ENUMS)
-- ===============================================

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS archivet_db;
USE archivet_db;

-- ===============================================
-- 2. JERARQUÍA DE USUARIOS (USER, OWNER, VET_DOCTOR)
-- ===============================================

-- Tabla Base: USER (Contiene datos comunes y credenciales)
CREATE TABLE IF NOT EXISTS user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255),
    phone_number VARCHAR(20),
    sex VARCHAR(10),
    role_name VARCHAR(20) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

-- Subclase: OWNER (Hereda user_id como PK/FK)
CREATE TABLE IF NOT EXISTS owner (
    user_id INT PRIMARY KEY,
    billing_address VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- Subclase VET_DOCTOR (Hereda user_id como PK/FK)
CREATE TABLE IF NOT EXISTS vet_doctor (
    user_id INT PRIMARY KEY,
    license_number VARCHAR(50) NOT NULL UNIQUE,
    specialization VARCHAR(100),
    shift_schedule VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- ===============================================
-- 3. JERARQUÍA DE MASCOTAS (PET, DOG, CAT)
-- ===============================================

-- Tabla: PET (contiene datos comunes)
CREATE TABLE IF NOT EXISTS pet (
    pet_id INT PRIMARY KEY AUTO_INCREMENT,
    owner_id INT NOT NULL,
    name VARCHAR(100) NOT NULL, -- CORREGIDO: name_pet -> name
    birth_date DATE,
    sex VARCHAR(10),
    is_sterilized BOOLEAN,
    coat_color VARCHAR(50),
    species_type VARCHAR(20) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (owner_id) REFERENCES owner(user_id) ON DELETE RESTRICT
);

-- Subclase DOG (hereda pet_id como PK/FK)
CREATE TABLE IF NOT EXISTS dog (
    pet_id INT PRIMARY KEY,
    breed VARCHAR(100),
    training_level VARCHAR(50),
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id) ON DELETE CASCADE
);

-- Subclase CAT (hereda pet_id como PK/FK)
CREATE TABLE IF NOT EXISTS cat (
    pet_id INT PRIMARY KEY,
    is_indoor BOOLEAN,
    preferred_diet VARCHAR(100),
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id) ON DELETE CASCADE
);

-- ===============================================
-- 4. JERARQUÍA DE PRODUCTOS (PRODUCT, INVENTORY_ITEM, VACCINE_RECORD)
-- ===============================================

-- Tabla: Product (contiene datos comunes)
CREATE TABLE IF NOT EXISTS product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    unit_price DECIMAL(10, 2) NOT NULL,
    usage_type VARCHAR(20) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

-- Subclase: Inventory_Item (Venta y Stock)
CREATE TABLE IF NOT EXISTS inventory_item (
    product_id INT PRIMARY KEY,
    stock_quantity INT NOT NULL,
    expiration_date DATE,
    category VARCHAR(50),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

-- Subclase: Vaccine_Record (Registro de inmunización)
CREATE TABLE IF NOT EXISTS vaccine_record (
    product_id INT PRIMARY KEY,
    target_disease VARCHAR(100),
    lot_expiration_date DATE,
    lot_number VARCHAR(50),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

-- ===============================================
-- 5. ENTIDADES CLÍNICAS Y TRANSACCIONALES
-- ===============================================

-- Historial de la mascota
CREATE TABLE IF NOT EXISTS pet_history (
    history_id INT PRIMARY KEY AUTO_INCREMENT,
    pet_id INT NOT NULL,
    vet_doctor_id INT NOT NULL,
    date DATE NOT NULL,
    diagnosis TEXT,
    treatment TEXT,
    notes TEXT,
    requires_follow_up BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id) ON DELETE RESTRICT,
    FOREIGN KEY (vet_doctor_id) REFERENCES vet_doctor(user_id) ON DELETE RESTRICT
);

-- Citas
CREATE TABLE IF NOT EXISTS appointment (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    pet_id INT NOT NULL,
    vet_doctor_id INT NOT NULL,
    date_time DATETIME NOT NULL,
    reason VARCHAR(255),
    status VARCHAR(50),
    notes TEXT,
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id) ON DELETE RESTRICT,
    FOREIGN KEY (vet_doctor_id) REFERENCES vet_doctor(user_id) ON DELETE RESTRICT
);

-- Venta
CREATE TABLE IF NOT EXISTS sale (
    sale_id INT PRIMARY KEY AUTO_INCREMENT,
    vet_doctor_id INT NOT NULL,
    owner_id INT,
    pet_id INT,
    date_time DATETIME NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    amount_paid DECIMAL(10, 2),
    change_amount DECIMAL(10, 2),
    payment_method VARCHAR(50),
    status VARCHAR(50),
    FOREIGN KEY (vet_doctor_id) REFERENCES vet_doctor(user_id) ON DELETE RESTRICT,
    FOREIGN KEY (owner_id) REFERENCES owner(user_id) ON DELETE SET NULL,
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id) ON DELETE SET NULL
);

-- Detalle de la venta
CREATE TABLE IF NOT EXISTS sale_item (
    sale_item_id INT PRIMARY KEY AUTO_INCREMENT,
    sale_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price_at_sale DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sale(sale_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES inventory_item(product_id) ON DELETE RESTRICT
);